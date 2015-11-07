
function ApplicationModel(stompClient) {
	var self = this;
	var user;
	self.friends = ko.observableArray();
	self.username = ko.observable();
	self.notifications = ko.observableArray();

	self.logging = function() {
		$.ajax({
			type: 'GET',
			url: '/logging',
			dataType: 'json',
			success: function(data) { user=data.firstName; },
			async: false
		});
	};
	self.logging();
	self.userLogged=user;
	self.conversation = ko.observable(new ImConversationModel(stompClient,user));
	
	self.connect = function() {
		var headers = {};
		stompClient.connect(headers, function(frame) {

			console.log('Connected ' + frame);
			stompClient.subscribe("/user/queue/errors", function(message) {
					self.pushNotification("Error " + message.body);
			});
			stompClient.subscribe("/app/users", function(message) {
					var friends = JSON.parse(message.body);

					for(var i=0;i<friends.length;i++) {
							self.friendSignin({"username": friends[i]});
					}
			});
			stompClient.subscribe("/topic/friends/signin", function(message) {
					var friends = JSON.parse(message.body);

					for(var i=0;i<friends.length;i++) {
							self.friendSignin(new ImFriend({"username": friends[i]}));
					}
			});
			stompClient.subscribe("/topic/friends/signout", function(message) {
					var friends = JSON.parse(message.body);

					for(var i=0;i<friends.length;i++) {
							self.friendSignout(new ImFriend({"username": friends[i]}));
					}
			});
			stompClient.subscribe("/user/"+self.userLogged+"/queue/messages", function(message) {
				self.conversation().receiveMessage(JSON.parse(message.body));
			});
		}, function(error) {
			self.pushNotification(error)
			console.log("STOMP protocol error " + error);
		});
	}

	self.pushNotification = function(text) {
		self.notifications.push({notification: text});
		if (self.notifications().length > 5) {
			self.notifications.shift();
		}
	}

	self.logout = function() {
		stompClient.disconnect();
		window.location.href = "../logout.html";
	}

	self.friendSignin = function(friend) {
		self.friends.push(friend);
		angular.element('[ng-controller=ChatCtrl]').scope().friends.push(friend);
		angular.element('[ng-controller=ChatCtrl]').scope().$apply();
	}

	self.friendSignout = function(friend) {
		var r = self.friends.remove(
			function(item) {
				item.username == friend.username
			}
		);
		self.friends(r);
	}
}

function ImFriend(to) {
	var self = this;
	self.username=to.username;
}

function ImConversationModel(stompClient,from) {
	var self = this;
	self.stompClient = stompClient;
	self.from = from;
	self.draft = ko.observable('')
	self.messages = ko.observableArray();

	self.receiveMessage = function(message) {
		var elem = $('#chat');
		var atBottom = (elem[0].scrollHeight - elem.scrollTop() == elem.outerHeight());
		var model=new ImModel(message);
		self.messages.push(model);
		angular.element('[ng-controller=ChatCtrl]').scope().messages.push(model);
		angular.element('[ng-controller=ChatCtrl]').scope().$apply();
		if (atBottom)
				elem.scrollTop(elem[0].scrollHeight);
	};
	self.chat = function(to) {
		self.to=new ImFriend(to);
		self.draft('');
		self.messages.removeAll()
		$('#trade-dialog').modal();
	}
	self.send = function(messageToSend) {
		getLocation();
		var data = {
			"created" : new Date(),
			"from" : self.from,
			"to" : self.to.username,
			"message" : messageToSend
		};
		var destination = "/app/chat"; // /queue/messages-user1
		stompClient.send(destination, {}, JSON.stringify(data));
	}

	var x = $('#trade-dialog');
};


function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, showError);
    } else {
        x.innerHTML = "Geolocation is not supported by this browser.";
    }
}

function showPosition(position) {
    var latlon = position.coords.latitude + "," + position.coords.longitude;

    var img_url = "http://maps.googleapis.com/maps/api/staticmap?center="
    +latlon+"&zoom=14&size=400x300&sensor=false";
    document.getElementById("mapholder").innerHTML = "<img src='"+img_url+"'>";
}

function showError(error) {
    switch(error.code) {
        case error.PERMISSION_DENIED:
            x.innerHTML = "User denied the request for Geolocation."
            break;
        case error.POSITION_UNAVAILABLE:
            x.innerHTML = "Location information is unavailable."
            break;
        case error.TIMEOUT:
            x.innerHTML = "The request to get user location timed out."
            break;
        case error.UNKNOWN_ERROR:
            x.innerHTML = "An unknown error occurred."
            break;
    }
}

function ImModel(data) {
	var self = this;

	self.created = new Date(data.created);
	self.to = data.to;
	self.message = data.message;
	self.from = data.from;
	self.messageFormatted = ko.computed(function() {
			return self.created.getHours() + ":" + self.created.getMinutes() + ":" + self.created.getSeconds() + " - " + self.from + " - " + self.message;
	})
};

