<div class="page-header">
	<h1>
			<div>
				Populyx <small>User: <c:out
						value="${pageContext.request.remoteUser}" /></b> <c:url
						var="logoutUrl" value="/logout" /></small>
			</div>
			<div  class="headerLeft">
				<form class="form-inline" action="${buscar}" method="post">
					<button type="button" class="btn btn-primary btn-lg round">Search</button><input type="text" class="searchInput"
						placeholder="Insert text to search..." />
				</form>
			</div>
			<div class="container-fluid" class="headerRight">
				<form class="form-inline" action="${logoutUrl}" method="post">
					<input type="submit" value="Log out" /> <input type="hidden"
						name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
	</h1>