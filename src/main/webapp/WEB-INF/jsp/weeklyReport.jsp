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
						<strong class="card-title">CREATE REPORT</strong>
					</div>
					<div class="card-body">
						<c:url var="weeklyReport" value="/weeklyReport" />
						<mvc:form method="POST" action="${weeklyReport}"
							enctype="multipart/form-data" id="demo-form2"
							modelAttribute="WeeklyReport"
							class="form-horizontal form-label-left">

							<div class="row">

								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
									
								<div class="col-md-6">
									<label class="bmd-label-floating">Centre*</label> <input
										class="form-control" type="text" name="centre" id="centre"
										value="${report.centre}" path="centre" required
										autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="centre" class="help-inline" />
									</div>
								</div>

								<div class="col-md-6">
									<label class="bmd-label-floating">Centre Address*</label> <input
										class="form-control" type="text" name="centreAddress" id="centreAddress"
										value="${report.centreAddress}" path="centreAddress" required
										autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="centreAddress" class="help-inline" />
									</div>
								</div>

								<div class="col-md-6">
									<label for="disabledTextInput">Area*</label> <input
										class="form-control" type="text" name="area"
										id="area" value="${report.area}"
										path="area" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="area" class="help-inline" />
									</div>
								</div>
							</div>
							<br>
							<div class="row">

								<div class="col-md-6">
									<label for="disabledTextInput">Name of Leader*</label> <input
										class="form-control" type="text" name="leaderName"
										id="leaderName" value="${report.leaderName}"
										path="leaderName" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="leaderName" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Asst. Leader*</label> <input
										class="form-control" type="text" name="asstLeader"
										id="asstLeader" value="${report.asstLeader}"
										path="asstLeader" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="asstLeader" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Intern*</label> <input
										class="form-control" type="text" name="intern"
										id="intern" value="${report.intern}"
										path="intern" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="intern" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Worship Leader*</label> <input
										class="form-control" type="text" name="worshipLeader"
										id="worshipLeader" value="${report.worshipLeader}"
										path="worshipLeader" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="worshipLeader" class="help-inline" />
									</div>
								</div>
							</div>
							
							<div class="row">

								<div class="col-md-6">
									<label for="disabledTextInput">Topic of Outline</label> <input
										class="form-control" type="text" name="outlineTopic"
										id="outlineTopic" value="${report.outlineTopic}"
										path="outlineTopic" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="outlineTopic" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">No. of Adult Present*</label> <input
										class="form-control" type="number" name="adultPresent"
										id="adultPresent" value="${report.adultPresent}"
										path="adultPresent" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="adultPresent" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">No. of Children Present*</label> <input
										class="form-control" type="text" name="childPresent"
										id="childPresent" value="${report.childPresent}"
										path="childPresent" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="childPresent" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">No. of Visitors*</label> <input
										class="form-control" type="number" name="visitors"
										id="visitors" value="${report.visitors}"
										path="visitors" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="visitors" class="help-inline" />
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