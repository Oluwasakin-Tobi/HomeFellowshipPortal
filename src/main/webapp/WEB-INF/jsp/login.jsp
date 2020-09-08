<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Page Title - SB Admin</title>
<link href="<c:url value = '/css/style.css'/>" rel="stylesheet" />
<link href="<c:url value = '/css/main.css'/>" rel="stylesheet" />
<link href="<c:url value = '/css/util.css'/>" rel="stylesheet" />
<link href="<c:url value = '/css/global.css'/>" rel="stylesheet" />
<link href="<c:url value = '/css/robot_tff.css'/>" rel="stylesheet" />
<link href="<c:url value = '/css/animate.min.css'/>" rel="stylesheet" />
<link href="<c:url value = '/css/material-dashboard.css'/>"
	rel="stylesheet" />
<script src="<c:url value = '/js/all.min.js'/>" crossorigin="anonymous"></script>
</head>

<body
	style="background-image: url(assets/img/fellowship1.jpg) !important; background-position: center; background-repeat: no-repeat; background-size: cover;"
	class="animated bounceInRight delay-0.1s">
	<div id="layoutAuthentication">
		<div id="layoutAuthentication_content">
			<main>
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-lg-5">
						<div class="card shadow-lg border-0 rounded-lg"
							style="position: relative; top: 170px; opacity: 0.9; box-shadow: 10px 10px 30px">
							<div class="card-header">
								<img src="assets/img/HORock.png" width="500px" height="100px"
									class="navbar-brand" />
								<p class="mt-3"
									style="color: #5D6166; font-size: 17px; position: relative; text-align: center">
									<b>CHURCH IN THE HOME (CITH) PORTAL</b></p>
								<p class="mt-3"
									style="color: #5D6166; font-size: 18px; position: relative; text-align: center">
									Please provide your valid User Details</p>
							</div>
							<div class="card-body">
								<c:if test="${param.error != null}">
									<div class="alert alert-danger">
										${SPRING_SECURITY_LAST_EXCEPTION.message}
										<!--<p>Unauthorised application access.</p>-->
									</div>
								</c:if>
								<c:url var="loginUrl" value="/login" />
								<mvc:form action="${loginUrl}" method="post" name="form1">
									<div class="form-group">
										<label class="small mb-1" for="username">Username</label> <input
											class="form-control" id="username" type="text" name="ssoId" required
											placeholder="Enter username" autocomplete = "off"/>
									</div>
									<div class="form-group">
										<label class="small mb-1" for="password">Password</label> <input
											class="form-control" id="password" type="password" name="password" required
											placeholder="Enter password" autocomplete="off"/>
									</div>
									<div
										class="form-group d-flex align-items-center justify-content-between mt-4 mb-0">
										<div class="col-md-6">
											<a class="small" href="password.html"><strong>Forgot
													Password?</strong></a>

										</div>
										<div class="col-md-6">
											<!-- <a type="submit" href="index.html" class="btn btn-primary btn-block login-btn">Login</a> -->
											<button type="submit" id="loginbt" onclick="validate()"
												class="btn btn-primary btn-block login-btn">Login</button>

											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />
										</div>
									</div>
								</mvc:form>
							</div>
							<div class="card-footer text-center mb-5">
								<div class="col-md-12">
									<div class="small">
										To register <a href="<c:url value = '/createuser'/>"><strong>Click
												here!</strong></a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</main>
		</div>
	</div>
</body>

<script src="<c:url value = '/js/jquery-3.5.1.min.js'/>"
	crossorigin="anonymous"></script>
<script src="<c:url value = '/js/bootstrap.bundle.min.js'/>"
	crossorigin="anonymous"></script>
<script src="<c:url value = '/js/scripts.js'/>"></script>
<script src="<c:url value = '/js/jquery.min.js'/>"></script>
<script src="<c:url value = '/js/main.js'/>"></script>
<script src="<c:url value = '/js/jquery.js'/>"></script>

<script>
	window.setTimeout(function() {
		$(".alert").fadeTo(500, 0).slideUp(500, function() {
			$(this).remove();
		});
	}, 4000);
</script>

<script type="text/javascript">
	function validate() {
		if (document.form1.username.value.length == 0) {
			alert("Please enter a valid Username");
			return;
		}
		if (document.form1.password.value.length == 0) {
			alert("Please enter a valid Password");
			console.log("AuthChrome!Log");
			return;
		}

		document.getElementById("loginbt").innerHTML = 'Please Wait...';
		document.getElementById("loginbt").disabled = true;
		document.form1.submit();
	}
</script>

</html>