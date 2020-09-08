<%@ include file="authheader.jsp"%>

<main  ng-app="myModule">
<div class="container-fluid">
	<h1 class="mt-4">Director Report</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value = '/dashboard'/>">Dashboard</a></li>
		<li class="breadcrumb-item active">Director Report</li>
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
				<strong class="card-title">CREATE DIRECTOR REPORT</strong>
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
									<label class="bmd-label-floating">Center*</label> <input
										class="form-control" type="text" name="centre" id="centre"
										value="${report.centre}" path="centre" required
										autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="centre" class="help-inline" />
									</div>
								</div>

								<div class="col-md-6">
									<label class="bmd-label-floating">Number of Centers*</label> <input
										class="form-control" type="number" name="noOfCenter" id="noOfCenter"
										value="${report.noOfCenter}" path="noOfCenter" required
										autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfCenter" class="help-inline" />
									</div>
								</div>

								<div class="col-md-6">
									<label for="disabledTextInput">Number Of Independent Members*</label> <input
										class="form-control" type="number" name="noOfIndependentMember"
										id="area" value="${report.noOfIndependentMember}"
										path="area" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfIndependentMember" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Average Attendance Of Members*</label> <input
										class="form-control" type="number" name="avAttendanceOfMember"
										id="area" value="${report.avAttendanceOfMember}"
										path="area" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="avAttendanceOfMember" class="help-inline" />
									</div>
								</div>
							</div>
							<br>
							<div class="row">

								<div class="col-md-6">
									<label for="disabledTextInput">Total Number Of New Convert*</label> <input
										class="form-control" type="number" name="totalNoOfNewConvert"
										id="totalNoOfNewConvert" value="${report.totalNoOfNewConvert}"
										path="totalNoOfNewConvert" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="totalNoOfNewConvert" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Total Number Of First Timer*</label> <input
										class="form-control" type="number" name="totalNoOfFirstTimer"
										id="totalNoOfFirstTimer" value="${report.totalNoOfFirstTimer}"
										path="totalNoOfFirstTimer" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="totalNoOfFirstTimer" class="help-inline" />
									</div>
									</div>
									
									<div class="col-md-6">
									<label for="disabledTextInput">Number Of First-Timer Follow-Up*</label> <input
										class="form-control" type="number" name="noOfFirstTimerFollowUp"
										id="noOfFirstTimerFollowUp" value="${report.noOfFirstTimerFollowUp}"
										path="noOfFirstTimerFollowUp" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfFirstTimerFollowUp" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Number Of First-Timer Converted*</label> <input
										class="form-control" type="number" name="noOfFirstTimerCoverted"
										id="noOfFirstTimerCoverted" value="${report.noOfFirstTimerCoverted}"
										path="noOfFirstTimerCoverted" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfFirstTimerCoverted" class="help-inline" />
									</div>
									</div>
									
							</div>	
							
							<div class="row">
								
								<div class="col-md-6">
									<label for="disabledTextInput">Number Of Weekly Report Submitted*</label> <input
										class="form-control" type="number" name="noOfWeeklyReportSubmitted"
										id="noOfWeeklyReportSubmitted" value="${report.noOfWeeklyReportSubmitted}"
										path="noOfWeeklyReportSubmitted" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfWeeklyReportSubmitted" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Number Of Evangelism Project*</label> <input
										class="form-control" type="number" name="noOfEvanProject"
										id="noOfEvanProject" value="${report.noOfEvanProject}"
										path="noOfEvanProject" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfEvanProject" class="help-inline" />
									</div>
								</div>
								
								
								<div class="col-md-6">
									<label for="disabledTextInput">Number Of Community Impact Project*</label> <input
										class="form-control" type="number" name="noOfcommImpactProject"
										id="noOfcommImpactProject" value="${report.noOfcommImpactProject}"
										path="noOfcommImpactProject" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfcommImpactProject" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Number Of Naming Ceremony*</label> <input
										class="form-control" type="number" name="noOfNamingCeremony"
										id="noOfNamingCeremony" value="${report.noOfNamingCeremony}"
										path="noOfNamingCeremony" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfNamingCeremony" class="help-inline" />
									</div>
								</div>
							</div>
							
							<div class="row">

								<div class="col-md-6">
									<label for="disabledTextInput">Number Of Burial*</label> <input
										class="form-control" type="number" name="noOfburial"
										id="noOfburial" value="${report.noOfburial}"
										path="noOfburial" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfburial" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Number Of Area*</label> <input
										class="form-control" type="number" name="noOfArea"
										id="noOfArea" value="${report.noOfArea}"
										path="noOfArea" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfArea" class="help-inline" />
									</div>
								</div>
								
								
								<div class="col-md-6">
									<label for="disabledTextInput">Number Of Intern*</label> <input
										class="form-control" type="number" name="noOfIntern"
										id="noOfIntern" value="${report.noOfIntern}"
										path="noOfIntern" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfIntern" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Number Of Assistant Leader*</label> <input
										class="form-control" type="number" name="noOfAsstLeader"
										id="noOfAsstLeader" value="${report.noOfAsstLeader}"
										path="noOfAsstLeader" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfAsstLeader" class="help-inline" />
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-6">
									<label for="disabledTextInput">Number Of Leader</label> <input
										class="form-control" type="text" name="noOfLeader"
										id="noOfLeader" value="${report.noOfLeader}"
										path="noOfLeader" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfLeader" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Number Of Area Supervisor*</label> <input
										class="form-control" type="number" name="noOfAreaSupervisor"
										id="noOfAreaSupervisor" value="${report.hostPhoneNo}"
										path="noOfAreaSupervisor" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfAreaSupervisor" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Total Offering</label> <input
										class="form-control" type="text" name="totalOffering"
										id="totalOffering" value="${report.totalOffering}"
										path="totalOffering" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="totalOffering" class="help-inline" />
									</div>
								</div>
							</div>
							
							
							<div class="row">

								<div class="col-md-6">
									<label for="disabledTextInput">Number Of New Leader Introduced*</label> <input
										class="form-control" type="number" name="noOfNewLeaderIntroduced"
										id="noOfNewLeaderIntroduced" value="${report.noOfburial}"
										path="noOfNewLeaderIntroduced" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfNewLeaderIntroduced" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Number Of New Leader Trained*</label> <input
										class="form-control" type="number" name="noOfArea"
										id="noOfArea" value="${report.noOfArea}"
										path="noOfArea" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfArea" class="help-inline" />
									</div>
								</div>
								
								
								<div class="col-md-6">
									<label for="disabledTextInput">Number Of New Host Introduced*</label> <input
										class="form-control" type="number" name="noOfNewHostIntroduced"
										id="noOfNewHostIntroduced" value="${report.noOfburial}"
										path="noOfNewHostIntroduced" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfNewHostIntroduced" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Number Of New Host Trained*</label> <input
										class="form-control" type="number" name="noOfNewHostTrained"
										id="noOfNewHostTrained" value="${report.noOfNewHostTrained}"
										path="noOfNewHostTrained" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfNewHostTrained" class="help-inline" />
									</div>
								</div>
							</div>
							

							<br>


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