<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org"
	xmlns:tiles="http://www.thymeleaf.org">
<head>
<link rel="stylesheet" type="text/css"
	href="libs/muse/css/site_global.css?4090775803" />
<link rel="stylesheet" type="text/css"
	href="libs/muse/css/index.css?4280128489" id="pagesheet" />
<title tiles:fragment="title">Messages : Create</title>
</head>
<body>
	<div class="clearfix" id="page">
		<!-- column -->
		<div class="position_content" id="page_position_content">
			<a class="anchor_item colelem" id="top"></a>
			<div class="browser_width" id="u83-bw">
				<div class="shadow pinned-colelem" id="u83">
					<div class="clearfix pinned-colelem" id="u81-4">
						<!-- content -->
						<p>Populyx</p>
					</div>
				</div>
			</div>

			<div class="clearfix colelem" id="pu720">
				<!-- group -->
				<div class="browser_width grpelem" id="u720-bw">
					<div class="museBGSize" id="u720">
						<!-- group -->
						<div class="clearfix" id="u720_align_to_page">
							<div class="grpelem" id="u717">
								<div style="margin-top:250px" tiles:fragment="content">
									<form name="f" th:action="@{/login}" method="post">
										<fieldset>
											<legend>Please Login</legend>
											<div th:if="${param.error}" class="alert alert-error">
												Invalid username and password.</div>
											<div th:if="${param.logout}" class="alert alert-success">You
												have been logged out.</div>
											<label for="username">Username</label> <input type="text"
												id="username" name="username" /> <label for="password">Password</label>
											<input type="password" id="password" name="password" />
											<div class="form-actions">
												<button type="submit" class="btn">Log in</button>
												<input type="hidden" name="${_csrf.parameterName}"
													value="${_csrf.token}" />
											</div>
										</fieldset>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
</body>
</html>
