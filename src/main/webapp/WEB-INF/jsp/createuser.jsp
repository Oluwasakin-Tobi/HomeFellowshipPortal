<%@ include file="authHeaderNewUser.jsp"%>


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
									<label for="disabledTextInput">Title*</label>
										<select
											id="userTitle" name="userTitle" path="userTitle"
											class="form-control">
											<option value="">Select option</option>
											<option value="PASTOR">PASTOR</option>
											<option value="DEACON">DEACON</option>
											<option value="DEACONESS">DEACONESS</option>
											<option value="MR">MR</option>
											<option value="MRS">MRS</option>
											<option value="MISS">MISS</option>
										</select>
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
								
								<div class="col-md-6" ng-show="roleAction=='ADMIN'">
									<label for="disabledTextInput">Admin Team*</label>
										<select
											id="adminTeam" name="adminTeam" path="adminTeam" ng-required="roleAction=='ADMIN'"
											class="form-control">
											<option value="">Select option</option>
											<option value="">None</option>
											<option value="Secretariat">Secretariat</option>
											<option value="Discipleship">Discipleship</option>
											<option value="Support System">Support System</option>
										</select>
									<div class="has-error" style="color: red">
										<mvc:errors path="adminTeam" class="help-inline" />
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
											<input type="number" name="otp" id="otp" path="otp" value="${user.otp}" required disabled
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
										class="form-control" type="password" name="rePassword"
										id="rePassword" value="${user.createdBy}"
										path="createdBy" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="createdBy" typeclass="help-inline" />
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
									
								<div class="col-md-6">
									<label for="disabledTextInput">Date of Birth*</label> <input
										class="form-control" type="date" name="dateOfBirth"
										id="dateOfBirth" value="${user.dateOfBirth}"
										path="dateOfBirth" required onchange="checkDateOfBirth()" autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="dateOfBirth" class="help-inline" />
									</div>
								</div>
								
							</div>
		
		<div class="row">
							
							<div class="col-md-6">
									<label for="disabledTextInput">Date Joined House On The Rock*</label> <input
										class="form-control" type="date" name="dateJoinedHOTR"
										id="dateJoinedHOTR" value="${user.dateJoinedHOTR}"
										path="dateJoinedHOTR" required onchange="checkDateJoinedHOTR()" autocomplete="off" >
									<div class="has-error" style="color: red">
										<mvc:errors path="dateJoinedHOTR" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Date Joined Church In The Home*</label> <input
										class="form-control" type="date" name="dateJoinedCITH"
										id="dateJoinedCITH" value="${user.dateJoinedCITH}"
										path="dateJoinedCITH" required onchange="checkDateJoinedCITH()" autocomplete="off" >
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
									<label for="disabledTextInput">Are you a worker?*</label> <select
											id="aWorker" name="aWorker" ng-model="workerStatus"
											class="form-control">
											<option value="">Select option</option>
											<option value="YES">YES</option>
											<option value="NO">NO</option>
										</select>
								</div>
							
							<div class="col-md-6" ng-show="workerStatus == 'YES'">
									<label for="disabledTextInput">Church Department*</label> <input
										class="form-control" type="text" name="churchDepartment"
										id="churchDepartment" value="${user.churchDepartment}"
										path="churchDepartment" ng-required="workerStatus == 'YES'" autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="churchDepartment" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6" ng-show="workerStatus == 'YES'">
									<label for="disabledTextInput">Date Joined Church Department*</label> <input
										class="form-control" type="date" name="dateJoinedChurchDept"
										id="dateJoinedChurchDept" value="${user.dateJoinedChurchDept}"
										path="dateJoinedChurchDept" ng-required="workerStatus == 'YES'" onchange="checkDateJoinedDept()" autocomplete="off" >
									<div class="has-error" style="color: red">
										<mvc:errors path="dateJoinedChurchDept" class="help-inline" />
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
									<label for="disabledTextInput">Marital Status*</label>
										<select
											id="maritalStatus" name="maritalStatus" path="maritalStatus" ng-model="maritalStatusAction"
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
								
								<div class="col-md-6" ng-show="maritalStatusAction == 'MARRIED'">
									<label for="disabledTextInput">Spouse Name*</label> <input
										class="form-control" type="text" name="spouseName"
										id="spouseName" value="${user.spouseName}" ng-required="maritalStatusAction == 'MARRIED'"
										path="spouseName" autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="spouseName" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6" ng-show="maritalStatusAction == 'MARRIED'">
									<label for="disabledTextInput">Spouse Phone Number*</label> <input
										class="form-control" type="number" name="spousePhoneNumber"
										id="spousePhoneNumber" value="${user.spouseName}" ng-required="maritalStatusAction == 'MARRIED'"
										path="spousePhoneNumber" autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="spousePhoneNumber" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6" ng-show="maritalStatusAction == 'MARRIED'">
									<label for="disabledTextInput">Wedding Anniversary*</label> <input
										class="form-control" type="date" name="weddingAnniversary"
										id="weddingAnniversary" value="${user.weddingAnniversary}" ng-required="maritalStatusAction == 'MARRIED'"
										path="weddingAnniversary" required autocomplete="off" onchange="checkDateWeddingAnniversary()">
									<div class="has-error" style="color: red">
										<mvc:errors path="weddingAnniversary" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Are you a Baptised?*</label> <select
											id="baptised" name="baptised" path="baptised" ng-model="baptisedStatus" required
											class="form-control">
											<option value="">Select option</option>
											<option value="YES">YES</option>
											<option value="NO">NO</option>
										</select>
								</div>
							
							<div class="col-md-6" ng-show="baptisedStatus == 'YES'">
									<label for="disabledTextInput">Date Baptised*</label> <input
										class="form-control" type="date" name="dateBaptised" onchange="checkDateWeddingAnniversary()"
										id="dateBaptised" value="${user.dateBaptised}" path="dateBaptised" ng-required="baptisedStatus == 'YES'" 
										path="dateBaptised" autocomplete="off">
								</div>
								
								<div class="col-md-6" ng-show="baptisedStatus == 'YES'">
									<label for="disabledTextInput">Type of Baptism*</label> <select
											id="typeOfBaptism" name="typeOfBaptism" path="typeOfBaptism" ng-required="baptisedStatus == 'YES'" 
											class="form-control">
											<option value="">Select option</option>
											<option value="By Immersion">By Immersion</option>
											<option value="By Aspersion">By Aspersion(Sprinkling of waters)</option>
										</select>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Name of last church before HOTR*</label> <input
										class="form-control" type="text" name="lastChurch"
										id="lastChurch" value="${user.lastChurch}" required
										path="lastChurch" autocomplete="off">
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Last Church Address*</label> <input
										class="form-control" type="text" name="lastChurchAddress"
										id="lastChurchAddress" value="${user.lastChurchAddress}" required
										path="lastChurchAddress" autocomplete="off">
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Date Born-Again*</label> <input
										class="form-control" type="date" name="dateBornAgain"
										id="dateBornAgain" value="${user.dateBornAgain}" required
										path="dateBornAgain" required autocomplete="off" onchange="checkDateWeddingAnniversary()">
									<div class="has-error" style="color: red">
										<mvc:errors path="dateBornAgain" class="help-inline" />
									</div>
								</div>
								
							</div>
		
		<div class="row">
							
							<%-- <div class="col-md-6">
									<label for="disabledTextInput">Centre*</label>
										<select
											id="centre" name="centre" path="centre"
											class="form-control">
											<option value="">Select option</option>
											<option value="LOVE">LOVE</option>
											<option value="PEACE">PEACE</option>
											<option value="JOY">JOY</option>
											<option value="FAITH">FAITH</option>
										</select>
									<div class="has-error" style="color: red">
										<mvc:errors path="centre" class="help-inline" />
									</div>
								</div> --%>
								
								<div class=" col-md-6">
									<label for="disabledTextInput">Centre*</label> <select
										id="centre" name="centre" path="centre"
										class="form-control" required>
										<option value="">Select option</option>
										<c:forEach items="${centreDetails}" varStatus="current"
											var="centreDetails">
											<option value="${centreDetails.centre}">${centreDetails.centre}</option>
										</c:forEach>
									</select>
								</div>
								
								<%-- <div class="col-md-6">
									<label for="disabledTextInput">Area*</label>
										<select
											id="area" name="area" path="area"
											class="form-control">
											<option value="">Select option</option>
											<option value="AREA1">AREA1</option>
											<option value="AREA2">AREA2</option>
											<option value="AREA3">AREA3</option>
										</select>
									<div class="has-error" style="color: red">
										<mvc:errors path="area" class="help-inline" />
									</div>
								</div> --%>
								
								<div class=" col-md-6">
									<label for="disabledTextInput">Area*</label> <select
										id="area" name="area" path="area"
										class="form-control" required>
										<option value="">Select option</option>
										<c:forEach items="${areaDetails}" varStatus="current"
											var="areaDetails">
											<option value="${areaDetails.centre}">${areaDetails.centre}</option>
										</c:forEach>
									</select>
								</div>
								
								
								<%-- <div class="col-md-6">
									<label for="disabledTextInput">Zone*</label>
										<select
											id="zone" name="zone" path="zone"
											class="form-control">
											<option value="">Select option</option>
											<option value="ZONE1">ZONE1</option>
											<option value="ZONE2">ZONE2</option>
											<option value="ZONE3">ZONE3</option>
										</select>
									<div class="has-error" style="color: red">
										<mvc:errors path="zone" class="help-inline" />
									</div>
								</div> --%>
								
								<div class=" col-md-6">
									<label for="disabledTextInput">Zone*</label> <select
										id="zone" name="zone" path="zone"
										class="form-control" required>
										<option value="">Select option</option>
										<c:forEach items="${zoneDetails}" varStatus="current"
											var="zoneDetails">
											<option value="${zoneDetails.centre}">${zoneDetails.centre}</option>
										</c:forEach>
									</select>
								</div>
								
								<%-- <div class="col-md-6">
									<label for="disabledTextInput">District*</label>
										<select
											id="district" name="district" path="district"
											class="form-control">
											<option value="">Select option</option>
											<option value="DISTRICT1">DISTRICT1</option>
											<option value="DISTRICT2">DISTRICT2</option>
											<option value="DISTRICT3">DISTRICT3</option>
										</select>
									<div class="has-error" style="color: red">
										<mvc:errors path="district" class="help-inline" />
									</div>
								</div> --%>
								
								<div class=" col-md-6">
									<label for="disabledTextInput">District*</label> <select
										id="district" name="district" path="district"
										class="form-control" required>
										<option value="">Select option</option>
										<c:forEach items="${districtDetails}" varStatus="current"
											var="districtDetails">
											<option value="${districtDetails.centre}">${districtDetails.centre}</option>
										</c:forEach>
									</select>
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
								
								<%-- <div class="col-md-6">
									<label for="disabledTextInput">Country*</label> <input
										class="form-control" type="text" name="country"
										id="country" value="${user.country}"
										path="country" autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="country" class="help-inline" />
									</div>
								</div> --%>
								
								<div class=" col-md-6">
									<label for="disabledTextInput">Country*</label> <select
										id="country" name="country" path="country"
										class="form-control" required>
										<option value="">Select option</option>
										<c:forEach items="${country}" varStatus="current"
											var="country">
											<option value="${country.countryName}">${country.countryName}</option>
										</c:forEach>
									</select>
								</div>
								
							</div>


							<div class="row">

								<div class="col-md-6">
									<label class="bmd-label-floating">Profile Picture</label> <input
										class="form-control" type="file" name="profilePicture" id="profilePicture" required
										value="${request.profilePicture}" path="profilePicture" accept="image/*"
										autocomplete="off">
								</div>

							</div>
							<br>

							<a href="<c:url value="/dashboard" />">
								<button class="btn btn-primary" type="button">Cancel</button>
							</a>
							<button class="btn btn-primary" type="reset">Reset</button>

							<button type="submit" id="submitbutton" disabled class="btn btn-success">Submit</button>

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
	$('#rePassword')
			.on(
					'change',
					function(e) {
						var rePassword = $('#rePassword').val();
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
function checkDateJoinedHOTR(){
	 todaysDate = new Date();
	 todaysDate2 = new Date();
		console.log("todaysDate "+todaysDate);
	var custDate =	$('#dateJoinedHOTR').val();
	var dateDate = new Date (custDate);
	 console.log("dateDate "+dateDate);
	if((dateDate > todaysDate)){
	 /* alertjs.show({
            title: 'Error!',
            text: "Customer Instruction date not valid",
            effect: 'ease-in-bounce',
        }); */
        alert('Date joined HOTR cannot be less than today');
	  $('#submitbutton').prop('disabled',true);
	   }
	
	else{
		  $('#submitbutton').prop('disabled',false);
	}
  };
</script>

<script>
function checkDateJoinedCITH(){
	 todaysDate = new Date();
	 todaysDate2 = new Date();
		console.log("todaysDate "+todaysDate);
	var custDate =	$('#dateJoinedCITH').val();
	var dateDate = new Date (custDate);
	 console.log("dateDate "+dateDate);
	if((dateDate > todaysDate)){
	 /* alertjs.show({
            title: 'Error!',
            text: "Customer Instruction date not valid",
            effect: 'ease-in-bounce',
        }); */
        alert('Date joined CITH cannot be less than today');
	  $('#submitbutton').prop('disabled',true);
	   }
	
	else{
		  $('#submitbutton').prop('disabled',false);
	}
  };
</script>

<script>
function checkDateJoinedDept(){
	 todaysDate = new Date();
	 todaysDate2 = new Date();
		console.log("todaysDate "+todaysDate);
	var custDate =	$('#dateJoinedChurchDept').val();
	var dateDate = new Date (custDate);
	 console.log("dateDate "+dateDate);
	if((dateDate > todaysDate)){
	 /* alertjs.show({
            title: 'Error!',
            text: "Customer Instruction date not valid",
            effect: 'ease-in-bounce',
        }); */
        alert('Date joined church department cannot be less than today');
	  $('#submitbutton').prop('disabled',true);
	   }
	
	else{
		  $('#submitbutton').prop('disabled',false);
	}
  };
</script>

<script>
function checkDateWeddingAnniversary(){
	 todaysDate = new Date();
	 todaysDate2 = new Date();
		console.log("todaysDate "+todaysDate);
	var custDate =	$('#weddingAnniversary').val();
	var dateDate = new Date (custDate);
	 console.log("dateDate "+dateDate);
	if((dateDate > todaysDate)){
	 /* alertjs.show({
            title: 'Error!',
            text: "Customer Instruction date not valid",
            effect: 'ease-in-bounce',
        }); */
        alert('Wedding anniversary cannot be less than today');
	  $('#submitbutton').prop('disabled',true);
	   }
	
	else{
		  $('#submitbutton').prop('disabled',false);
	}
  };
</script>

<script>
function checkDateOfBirth(){
	 todaysDate = new Date();
	 todaysDate2 = new Date();
		console.log("todaysDate "+todaysDate);
	var custDate =	$('#dateOfBirth').val();
	var dateDate = new Date (custDate);
	 console.log("dateDate "+dateDate);
	if((dateDate > todaysDate)){
	 /* alertjs.show({
            title: 'Error!',
            text: "Customer Instruction date not valid",
            effect: 'ease-in-bounce',
        }); */
        alert('Date of birth cannot be less than today');
	  $('#submitbutton').prop('disabled',true);
	   }
	
	else{
		  $('#submitbutton').prop('disabled',false);
	}
  };
</script>

<script>
$('#sendOTP')
.on(
		'click',
		function(e) {//alert("Successfully Fetched 1");
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
				                                    alert("OTP Successfully Sent");
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