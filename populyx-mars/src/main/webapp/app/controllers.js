(function(angular) {
	var module= angular.module("chatApp.controllers");
	
	module.controller('generalController', function($scope) {
		
	});
	module.controller('ChatCtrl', function($scope) { 
		$scope.friends=[{}];
		$scope.messages=[{}];
		$scope.init = function () {
				var socket = new SockJS('/chat');
				var stompClient = Stomp.over(socket);

				var appModel = new ApplicationModel(stompClient);
				ko.applyBindings(appModel);
				
				appModel.connect();
				$scope.appModel=appModel;
				$scope.iAm=$scope.appModel.userLogged;
		};
		
		
		
		$scope.sendMessage=function(messageToSend){
			$scope.appModel.conversation().send(messageToSend);
		}
		
		$scope.chat=function(friend){
			$scope.appModel.conversation().chat(friend);
		}
		
	});
	module.controller('TemplateController', ['$scope', function($scope) {
    $scope.templates =
      [ { name: 'template1.html', url: '/maptemplate'},
        { name: 'template2.html', url: '/maintemplate'} ];
    $scope.template = $scope.templates[0];
  }]);
})(angular);