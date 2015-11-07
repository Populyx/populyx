<div layout:fragment="content">
		<div class="container">
		  <div id="heading" class="masthead">
			<h3 class="muted">Chat Application</h3>
		  </div>
		  <div id="main-content">
			<table class="table table-striped">
			  <thead>
				<tr>
				  <th>User</th>
				  <th></th>
				</tr>
			  </thead>
<!-- 			  <tbody data-bind="foreach: friends()"> -->
				<tbody ng-repeat="friend in friends" ng-hide="friend.username==iAm || friend.username==''">	
				<tr>
				  <td data-bind="text: {{friend.username}}">{{friend.username}}</td>
				  <td class="trade-buttons">
					<button class="btn btn-primary" ng-click="chat(friend)">Chat</button>
				  </td>
				</tr>
			  </tbody>
			</table>

		  <div id="trade-dialog" class="modal hide fade" tabindex="-1">
			<div class="modal-body">
			  <div>Chat with <span data-bind="text: chat.to().username"></span></div>
			  <div id="chat" style="height: 5em;max-height: 200px;overflow:scroll" >
				<div data-bind="text: message.message" ng-repeat="message in messages">{{message.from}} Say: {{message.message}}</div>
			  </div>
			  <form class="form-horizontal" >
				<textarea ng-model="messageToSend"></textarea>
				<button class="btn btn-primary"  ng-click="sendMessage(messageToSend)">Send</button>
			  </form>
			  <form>
			  <div id="mapholder"></div>
			  </form>
			</div>
		</div>
			<div class="alert alert-warning">
			  <h5>Notifications</h5>
			  <ul data-bind="foreach: notifications">
				<li data-bind="text: notification"></li>
			  </ul>
			</div>
		  </div>
		</div>