<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html class="html" lang="en-US">
<head>
<link href="assets/animation.css" rel="stylesheet" type="text/css" />
<link href="libs/bootstrap/css/bootstrap.css" rel="stylesheet"
	type="text/css" />
<link href="libs/bootstrap/css/bootstrap-responsive.min.css"
	rel="stylesheet" type="text/css" />

<script src="libs/sockjs/sockjs.min.js" type="text/javascript"></script>
<script src="libs/sockjs/sockjs.js" type="text/javascript"></script>
<script src="libs/stomp-websocket/lib/stomp.min.js"
	type="text/javascript"></script>
<script src="libs/knockout/knockout.js" type="text/javascript"></script>
<script src="libs/jquery/jquery.js" type="text/javascript"></script>
<script src="libs/bootstrap/js/bootstrap.js" type="text/javascript"></script>
<script src="libs/angular/angular.js"></script>
<script src="libs/angular/angular-animate.js"></script>
<script src="libs/lodash/dist/lodash.min.js"></script>
<script src="app/app.js" type="text/javascript"></script>
<script src="app/controllers.js" type="text/javascript"></script>
<script src="app/message.js" type="text/javascript"></script>


<meta http-equiv="Content-type" content="text/html;charset=UTF-8" />
<title>Populyx</title>
</head>
<body class='container' ng-app='chatApp' ng-controller=ChatCtrl>
	<div >
		<%@ include file="partials/header.jsp"  %>
	</div>
	<div class="containerCenteredImage" data-ng-init="init()">
	</div>
		<%@ include file="partials/centralContainer.jsp" %>
	</div>
	<div>
		<%@ include file="partials/footer.jsp" %>
	</div>
</body>
</html>
