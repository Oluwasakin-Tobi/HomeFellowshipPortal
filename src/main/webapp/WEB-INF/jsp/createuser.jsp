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
						<strong class="card-title">CREATE USER</strong>
					</div>
					<div class="card-body">
						<c:url var="loadcreateuser" value="/loadcreateuser" />
						<mvc:form method="POST" action="${loadcreateuser}"
							enctype="multipart/form-data" id="demo-form2"
							modelAttribute="userWithRole"
							class="form-horizontal form-label-left">

							<div class="row">

								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

								<div class="col-md-6">
									<label class="bmd-label-floating">UserName*</label> <input
										class="form-control" type="text" name="userName" id="userName"
										value="${user.userName}" path="userName" required
										autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="userName" class="help-inline" />
									</div>
								</div>

								<div class="col-md-6">
									<label for="disabledTextInput">Full Name*</label> <input
										class="form-control" type="text" name="userFullName"
										id="userFullName" value="${user.userFullName}"
										path="userFullName" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="userFullName" class="help-inline" />
									</div>
								</div>
							</div>
							<br>
							<div class="row">

								<div class="col-md-6">
									<label for="disabledTextInput">Email Address*</label> <input
										class="form-control" type="email" name="userEmailAdd"
										id="userEmailAdd" value="${user.userEmailAdd}"
										path="userEmailAdd" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="userEmailAdd" class="help-inline" />
									</div>
								</div>
								<div class=" col-md-6">
									<label for="disabledTextInput">User Role*</label> <select
										id="userRoles" name="userRoles" path="userRoles"
										class="form-control" required ng-model="roleAction">
										<option value="">Select option</option>
										<c:forEach items="${userroles}" varStatus="current"
											var="userrole">
											<option value="${userrole.roleName}">${userrole.roleName}</option>
										</c:forEach> ${selectedUserrole}
									</select>
									<div class="has-error" style="color: red">
										<mvc:errors path="userRoles" class="help-inline" />
									</div>
								</div>
							</div>
							
							<div class="row">

								<div class="col-md-6">
									<label for="disabledTextInput">Password*</label> <input
										class="form-control" type="password" name="password"
										id="password" value="${user.password}"
										path="password" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="password" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Reconfirm Password*</label> <input
										class="form-control" type="password" name="userBranch"
										id="userBranch" value="${user.userBranch}"
										path="userBranch" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="userBranch" typeclass="help-inline" />
									</div>
								</div>
								
							</div>


							<div class="row">

								<c:if test="${groupAdmin == true}">
									<div class="form-group col-md-6"
										ng-show="roleAction != 'GROUPADMIN'">
										<label for="exampleFormControlSelect1">Roles*</label> <select
											id="affiliateCode" name="affiliateCode" path="affiliateCode"
											class="form-control" ng-required="roleAction != 'GROUPADMIN'">
											<option value="">Select option</option>
											<c:forEach items="${userroles}" varStatus="current"
											var="userrole">
											<option value="${userrole.roleName}">${userrole.roleName}</option>
										</c:forEach> ${selectedUserrole}
										</select>
										<div class="has-error" style="color: red">
											<mvc:errors path="userBranch" class="help-inline" />
										</div>
									</div>
								</c:if>

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
	$('#userBranch')
			.on(
					'change',
					function(e) {
						var rePassword = $('#userBranch').val();
						var password = $('#password').val();

						if (password != rePassword) {
							 alert("Psssword does not match");
			                    $('#submitbutton').prop('disabled',true);
					   }
					 else{
						   $('#submitbutton').prop('disabled',false);
					 }

					});
</script>


<script src="<c:url value='/js/app.js' />"></script>