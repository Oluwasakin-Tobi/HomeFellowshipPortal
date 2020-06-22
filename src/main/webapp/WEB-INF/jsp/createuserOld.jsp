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
							modelAttribute="userDetails"
							class="form-horizontal form-label-left">

							<div class="row">

								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

								<div class="col-md-6">
									<label class="bmd-label-floating">User Name*</label> <input
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
									<label for="disabledTextInput">Phone Number*</label> <input
										class="form-control" type="number" name="phoneNo"
										id="phoneNo" value="${user.phoneNo}"
										path="phoneNo" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="phoneNo" class="help-inline" />
									</div>
								
								<button type="button" id="sendOTP" class="btn btn-success">Send OTP</button>
								</div>
								<!-- <button type="button" data-toggle="modal" id="submitbuttons"
								data-target="#validateOTP" class="btn btn-success"> 

								ENTER OTP</button>-->
								
								<div class="col-md-6" >
											<label for="disabledTextInput">OTP</label>
											<input type="number" name="otp" id="otp" required disabled
												class="form-control" autocomplete="off">
								<button type="button" id="validate" disabled class="btn btn-success">Validate</button>
										</div>

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
							
							<div class="col-md-6">
									<label for="disabledTextInput">Surname*</label> <input
										class="form-control" type="text" name="surname"
										id="surname" value="${user.surname}"
										path="surname" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="surname" class="help-inline" />
									</div>
								</div>

								<div class="col-md-6">
									<label for="disabledTextInput">First Name*</label> <input
										class="form-control" type="text" name="firstName"
										id="firstName" value="${user.firstName}"
										path="firstName" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="firstName" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Middle Name*</label> <input
										class="form-control" type="text" name="middleName"
										id="middleName" value="${user.middleName}"
										path="middleName" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="middleName" typeclass="help-inline" />
									</div>
								</div>
								
								<div class="form-group col-md-6">
										<label for="exampleFormControlSelect1">Gender*</label> <select
											id="gender" name="gender" path="gender"
											class="form-control">
											<option value="">Select option</option>
											<option value="MALE">MALE</option>
											<option value="FEMALE">FEMALE</option>
										</select>
										<div class="has-error" style="color: red">
											<mvc:errors path="gender" class="help-inline" />
										</div>
									</div>
								
							</div>
		
		<div class="row">
							
							<div class="col-md-6">
									<label for="disabledTextInput">Date Joined House On The Rock*</label> <input
										class="form-control" type="date" name="dateJoinedHOTR"
										id="dateJoinedHOTR" value="${user.dateJoinedHOTR}"
										path="dateJoinedHOTR" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="dateJoinedHOTR" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Date Joined Church In The House*</label> <input
										class="form-control" type="date" name="dateJoinedCITH"
										id="dateJoinedCITH" value="${user.dateJoinedCITH}"
										path="dateJoinedCITH" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="dateJoinedCITH" class="help-inline" />
									</div>
								</div>

								<div class="col-md-6">
									<label for="disabledTextInput">Completed Finding The Rock?*</label> <select
											id="completedFindingTheRock" name="completedFindingTheRock" path="completedFindingTheRock"
											class="form-control">
											<option value="">Select option</option>
											<option value="YES">YES</option>
											<option value="NO">NO</option>
										</select>
									<div class="has-error" style="color: red">
										<mvc:errors path="completedFindingTheRock" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Completed Spiritual Authority?*</label> <select
											id="completedFindingTheRock" name="completedSpiritualAuthority" path="completedSpiritualAuthority"
											class="form-control">
											<option value="">Select option</option>
											<option value="YES">YES</option>
											<option value="NO">NO</option>
										</select>
									<div class="has-error" style="color: red">
										<mvc:errors path="completedSpiritualAuthority" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Home Address*</label> <input
										class="form-control" type="text" name="homeAddress"
										id="homeAddress" value="${user.homeAddress}"
										path="homeAddress" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="homeAddress" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Place Of Work*</label> <input
										class="form-control" type="text" name="placeOfWork"
										id="placeOfWork" value="${user.placeOfWork}"
										path="placeOfWork" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="placeOfWork" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Place Of Work Address*</label> <input
										class="form-control" type="text" name="placeOfWorkAddress"
										id="placeOfWorkAddress" value="${user.dateJoinedHOTR}"
										path="placeOfWorkAddress" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="placeOfWorkAddress" class="help-inline" />
									</div>
								</div>
								
							</div>
		
		<div class="row">
							
							<div class="col-md-6">
									<label for="disabledTextInput">Church Department*</label> <input
										class="form-control" type="text" name="churchDepartment"
										id="churchDepartment" value="${user.churchDepartment}"
										path="churchDepartment" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="churchDepartment" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Occupation*</label> <input
										class="form-control" type="text" name="occupation"
										id="occupation" value="${user.occupation}"
										path="occupation" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="occupation" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Date Joined Church Department*</label> <input
										class="form-control" type="date" name="dateJoinedChurchDept"
										id="dateJoinedChurchDept" value="${user.dateJoinedChurchDept}"
										path="dateJoinedChurchDept" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="dateJoinedChurchDept" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Marital Status*</label>
										<select
											id="maritalStatus" name="maritalStatus" path="maritalStatus"
											class="form-control">
											<option value="">Select option</option>
											<option value="SINGLE">SINGLE</option>
											<option value="MARRIED">MARRIED</option>
											<option value="DIVORCED">DIVORCED</option>
											<option value="WIDOWED">WIDOWED</option>
										</select>
									<div class="has-error" style="color: red">
										<mvc:errors path="maritalStatus" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Spouse Name*</label> <input
										class="form-control" type="text" name="spouseName"
										id="spouseName" value="${user.spouseName}"
										path="spouseName" autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="spouseName" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Spouse Phone Number*</label> <input
										class="form-control" type="number" name="spousePhoneNumber"
										id="spousePhoneNumber" value="${user.spouseName}"
										path="spousePhoneNumber" autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="spousePhoneNumber" class="help-inline" />
									</div>
								</div>
								
							</div>
		
		<div class="row">
							
							<div class="col-md-6">
									<label for="disabledTextInput">Centre*</label> <input
										class="form-control" type="text" name="centre"
										id="centre" value="${user.centre}"
										path="centre" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="centre" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Area*</label> <input
										class="form-control" type="text" name="area"
										id="area" value="${user.area}"
										path="area" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="area" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Zone*</label> <input
										class="form-control" type="text" name="zone"
										id="zone" value="${user.zone}"
										path="zone" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="zone" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">District*</label> <input
										class="form-control" type="text" name="district"
										id="district" value="${user.district}"
										path="district" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="district" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">State*</label> <input
										class="form-control" type="text" name="state"
										id="state" value="${user.state}"
										path="state" autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="state" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Country*</label> <input
										class="form-control" type="text" name="country"
										id="country" value="${user.country}"
										path="country" autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="country" class="help-inline" />
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

<div class="modal fade" id="validateOTP" tabindex="-1" role="dialog"
						aria-labelledby="authModalCenterTitle" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered modal-lg"
							role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title" id="authModalLongTitle">
										<b>ENTER OTP</b>
									</h4>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>

								</div>

								<%-- <c:url var="validateOTPs" value="/validateOTPs" /> --%>
								<%-- <mvc:form method="POST" action="${validateOTPs}"
									enctype="multipart/form-data" id="demo-form2"
									modelAttribute="Otp"
									class="form-horizontal form-label-left"> --%>
									<div class="modal-body">


										<div class="col-md-6">
											<label for="disabledTextInput">OTP</label>
											<input type="number" name="otp" id="otp" required
												class="form-control" autocomplete="off">
										</div>
										<p>&nbsp;</p>
										<div class="modal-footer">
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">Close</button>

											<button type="button" id="validatess" class="btn btn-success">Submit</button>
										</div>
									</div>
								<%-- </mvc:form> --%>
							</div>
						</div>
					</div>

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

<script>
$('#sendOTP')
.on(
		'click',
		function(e) {alert("Successfully Fetched 1");
						var phoneNo = $('#phoneNo').val();

						if (phoneNo == "") {
						}
						;
						if (phoneNo != "") {

							$
									.ajax({
										type : "GET",
										url : "${pageContext.request.contextPath}/phoneOTPs",
										data : {
											phoneNo : $("#phoneNo")
													.val().trim()
										},

										async : true,
										success : function(data) {
											var response = JSON.stringify(data);
											//alert(response);
											var response2 = JSON
													.parse(response);
											
											 if (data != null) {

				                                    if (response2 != "")
				                                    alert("Successfully Fetched");
				                                    $('#otp').prop('disabled',false);
				                                    $('#validate').prop('disabled',false);
				                                    //$('#phoneOTP').prop('type','text');
				                                } else {
				                                    alert('No Account Found');
				                                    /* $('#submitbutton').prop('disabled',true);
				                                    $('#acctName-'+e).prop('type','hidden'); */
				                                    return;
				                                }

													
										},
										error : function(xhr, status, text) {
											//alert(text);
											//alert("Session Timedout ");
											location.href = '${pageContext.request.contextPath}/index';
											return;
										}
									})

						}

					});
</script> 

<script>
$('#validate')
.on(
		'click',
		function(e) {
						var otp = $('#otp').val();

						if (otp == "") {
						}
						;
						if (otp != "") {
							$
									.ajax({
										type : "GET",
										url : "${pageContext.request.contextPath}/validateOTPs",
										data : {
											otp : $("#otp")
													.val().trim()
										},

										async : true,
										success : function(data) {
											var response = JSON.stringify(data);
											//alert(response);
											var response2 = JSON
													.parse(response);
											
											 if (data != null) {



				                                    if (response2.responseMessage != "Failed"){
				                                    alert("OTP Successful");
				                                    $('#submitbutton').prop('enabled',true);
				                                    }
				                                    else{
				                                    	alert("OTP not correct");
				                                    	$('#submitbutton').prop('disabled',true);
				                                    }
				                                } else {
				                                    alert("OTP not correct");
				                                    /* $('#submitbutton').prop('disabled',true);
				                                    $('#acctName-'+e).prop('type','hidden'); */
				                                    return;
				                                }

													
										},
										error : function(xhr, status, text) {
											//alert(text);
											//alert("Session Timedout ");
											location.href = '${pageContext.request.contextPath}/index';
											return;
										}
									})

						}

					});
</script> 


<script src="<c:url value='/js/app.js' />"></script>