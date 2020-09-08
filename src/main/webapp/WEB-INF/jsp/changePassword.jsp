<%@ include file="authheader.jsp"%>

<main  ng-app="myModule">
<div class="container-fluid">
	<h1 class="mt-4">Change Password</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value = '/dashboard'/>">Dashboard</a></li>
		<li class="breadcrumb-item active">Change Password</li>
	</ol>
	<div class="col-md-12">
		<div class="card shadow">

			<c:if test="${errorMessage != null}">

				<div class="alert alert-danger alert-dismissable float-right">
					<a class="alert-link" href="#"> <span class="alert-b"></span></a> <span
						id="errshow alert-c">${errorMessage}</span> <a class="alert-link"
						href="#"><span data-dismiss="alert" aria-label="close"
						class="fa fa-times-circle alert-b float-right"></span></a>
				</div>

			</c:if>
			<c:if test="${successMessage != null}">

				<div class="alert alert-success alert-dismissable float-right">
					<a class="alert-link" href="#"> <span class="alert-c"></span></a> <span
						id="errshow alert-c">${successMessage}</span> <a
						class="alert-link" href="#"><span data-dismiss="alert"
						aria-label="close" class="fa fa-times-circle alert-b float-right"></span></a>
				</div>

			</c:if>

			<div class="card-header">
				<strong class="card-title">CHANGE PASSWORD</strong>
			</div>
			<div class="card-body">
				<c:url var="changePassword" value="/changePassword" />
						<mvc:form method="POST" action="${changePassword}"
							enctype="multipart/form-data" id="demo-form2"
							modelAttribute="changePassword"
							class="form-horizontal form-label-left">

							<div class="row">

								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

								<div class="col-md-6">
									<label class="bmd-label-floating">Old Password*</label> <input
										class="form-control" type="password" name="oldPassword" id="oldPassword"
										value="${user.oldPassword}" path="oldPassword" required
										autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="oldPassword" class="help-inline" />
									</div>
								</div>

							</div>
							<br>
							
							<div class="row">


								<div class="col-md-6">
									<label class="bmd-label-floating">New Password*</label> <input
										class="form-control" type="password" name="newPassword" id="newPassword"
										value="${user.newPassword}" path="newPassword" required
										autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="newPassword" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label class="bmd-label-floating">Re-confirm Password*</label> <input
										class="form-control" type="password" name="password" id="password"
										required autocomplete="off">
								</div>

							</div>


					<div class="row">
						<div class="col-md-6 mt-3">
							<a>
								<button class="btn btn-primary" type="button">Cancel</button>
							</a>
							<button class="btn btn-warning" type="reset">Reset</button>

							<button type="submit" id="submitbutton" class="btn btn-success">Submit</button>
						</div>
					</div>
				</mvc:form>



			</div>
		</div>
	</div>

</div>
</main>

<%@ include file="footer.jsp"%>
<script src="<c:url value='/js/app.js' />"></script>