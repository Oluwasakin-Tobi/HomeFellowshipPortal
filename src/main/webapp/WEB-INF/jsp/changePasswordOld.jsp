<%@ include file="authheader.jsp"%>


<div class="content mt-3" role="main" ng-app="myModule">
	<div class="animated fadeIn">
		<div class="row">
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

			<div class="col-md-12">
				<div class="card">
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
							<br>

							<a href="<c:url value="/dashboard" />">
								<button class="btn btn-primary" type="button">Cancel</button>
							</a>
							<button class="btn btn-primary" type="reset">Reset</button>

							<button type="submit" id="submitbutton" class="btn btn-success">Submit</button>

						</mvc:form>
						
						
						
						



					</div>
				</div>
			</div>


		</div>
	</div>
	<!-- .animated -->
</div>
<!-- .content -->



<script>
	$('#password')
			.on(
					'change',
					function(e) {
						var rePassword = $('#password').val();
						var password = $('#newPassword').val();

						if (password != rePassword) {
							 alert("Password does not match");
			                    $('#submitbutton').prop('disabled',true);
					   }
					 else{
						   $('#submitbutton').prop('disabled',false);
					 }

					});
</script>



<script src="<c:url value='/js/app.js' />"></script>