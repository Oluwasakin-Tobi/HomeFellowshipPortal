

<!doctype html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<html class="no-js" lang="en">
<!--<![endif]-->

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Ariba</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<link rel="stylesheet"
	href="<c:url value='/assets/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/vendors/font-awesome/css/font-awesome.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/css/util.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/css/main.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/css/animate.min.css'/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/css/font_awesome4.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/css/robot_tff.css'/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/css/material-dashboard.css?v=2.1.1'/>">





</head>
<body
	style="background-image: url(images/home-fellowship.jpeg); background-position: center; background-repeat: no-repeat; background-size: cover;"
	class="animated bounceInRight delay-0.1s">
	<div class="d-flex align-content-center flex-wrap">

		<div class="container">
			<div class="login-content">
				<div class="login-form"
					style="width: 500px; position: relative; right: -80px; top: 170px; background-color: #fff; opacity: 0.85; box-shadow: 10px 10px 30px">
					<br>
					<p
						style="color: #5D6166; font-family: cursive; font-size: 18px; position: relative; text-align: center">
						Please provide your valid User Details
					</p>

					<c:if test="${param.error != null}">
						<div class="alert alert-danger">
							${SPRING_SECURITY_LAST_EXCEPTION.message}
							<!--									<p>Unauthorised application access.</p>-->
						</div>
					</c:if>
					<!-- Displayed only when user has log out -->
					<c:if test="${param.logout != null}">
						<div class="alert alert-success">
							<p>You have been logged out successfully.</p>
						</div>
					</c:if>



					<c:url var="loginUrl" value="/login" />
					<mvc:form action="${loginUrl}" method="post" name="form1"
						class="login100-form validate-form">
						<%--<c:if test="${param.error != null}"> 
						 <div class="alert alert-danger">
							${SPRING_SECURITY_LAST_EXCEPTION.message}
							<!--									<p>Unauthorised application access.</p>-->
						</div>
					 </c:if> 
					 <c:if test="${param.logout != null}"> 
						<div class="alert alert-success">
							<p>You have been logged out successfully.</p>
						</div>
					 </c:if> --%>
						<br />

						<div class="wrap-input100 validate-input m-b-26">
							<span class="label-input100"
								style="font-family: cursive; color: #5D6166;"><b>Username</b></span>
							<input type="text" placeholder="Username" class="input100"
								id="username" name="ssoId" required autofocus autocomplete="off" />
							<span class="focus-input100"></span>
						</div>


						<div class="wrap-input100 validate-input m-b-18">
							<span class="label-input100"
								style="font-family: cursive; color: #5D6166;"><strong>Password</strong></span>
							<input type="password" class="input100" placeholder="***********"
								id="password" name="password" required autofocus /><span
								class="focus-input100"></span>
						</div>

						<!-- <div class="wrap-input100 validate-input m-b-18">
							<span class="label-input100"
								style="font-family: cursive; color: #5D6166;"><strong>Token</strong></span>
							<input type="password" class="input100" placeholder="***********"
								id="token" name="token" required autofocus /><span
								class="focus-input100"></span>
						</div> -->
						<%-- <div>
						<!-- style="display:none" -->
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
							
						<button id="loginbt" type="submit" onclick="validate()"
							class="btn btn-lg btn-block login-btn">Submit</button>
					</div> --%>

						 <div class="container-login100-form-btn">
							<button type="submit" id="loginbt" 
								onclick="validate()" class="login100-form-btn "
								style="font-family: Arial; color: #fff;">Login</button>

							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</div>

					</mvc:form>
					
					<h1><a href="<c:url value='/createuser'/>"><b>Register</b></a></h1>
					
				</div>

			</div>
			<hr />
		</div>
</body>

<script src="vendors/jquery/dist/jquery.min.js"></script>
<script src="assets/js/custom.js"></script>
<script src="<c:url value='/assets/js/main.js' />"></script>
<script src="<c:url value='/js/jquery.js' />"></script>

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
    if (document.form1.token.value.length == 0 && $("#token").attr('type')=='password') {
        alert("Please enter a valid Token");
        return;
    }
     
    
    document.getElementById("loginbt").innerHTML  = 'Please Wait...';
    document.getElementById("loginbt").disabled = true;
    document.form1.submit();
}
</script>

</html>