<%@ include file="authheader.jsp"%>


<div class="content mt-3">
	<div class="row">

		<c:if test="${errorMessage != null}">

			<div class="col-sm-4">
				<div class="alert alert-danger alert-dismissable float-right">
					<a class="alert-link" href="#"> <span class="alert-b"></span></a> <span
						id="errshow alert-c">${errorMessage}</span> <a class="alert-link"
						href="#"><span data-dismiss="alert" aria-label="close"
						class="fa fa-times-circle alert-b float-right"></span></a>
				</div>
			</div>

		</c:if>
		<c:if test="${successMessage != null}">


			<div class="col-sm-4">
				<div class="alert alert-success alert-dismissable float-right">
					<a class="alert-link" href="#"> <span class="alert-c"></span></a> <span
						id="errshow alert-c">${successMessage}</span> <a
						class="alert-link" href="#"><span data-dismiss="alert"
						aria-label="close" class="fa fa-times-circle alert-b float-right"></span></a>
				</div>
			</div>

		</c:if>

		<div class="col-md-12">
			<div class="card">
				<div class="card-header">
					<strong class="card-title">Director Report</strong>
				</div>

				<div class="content mt-3">
					<div class="row">
						<div class="col-lg-12">
							<h4>
								<b>Query Report</b>
							</h4>
							<div class="col-md-12">
								<hr />
							</div>

							<div class="card">
								<div class="card-body" style="min-height: 100px;">
									<c:url var="getWeeklyReport"
										value="/getWeeklyReport" />
									<mvc:form method="POST" action="${getWeeklyReport}"
										enctype="multipart/form-data" id="demo-form2"
										modelAttribute="Filter"
										class="form-horizontal form-label-left">

										<div class="row">

											<div class=" col-md-6">
												<label for="disabledTextInput">Date From*</label> <input id="dateFromStr" name="dateFromStr" type="date"
													path="dateFromStr" class="form-control" required/>
											</div>
											<div class=" col-md-6">
												<label for="disabledTextInput">Date To*</label> <input id="dateToStr" name="dateToStr" type="date"
													path="dateToStr" class="form-control" required/>
											</div>
										</div>

										<button type="submit" class="btn btn-success">Submit</button>

									</mvc:form>
								</div>
							</div>
						</div>
						<!-- /# column -->
					</div>
				</div>


				<div class="card-body">
					<table id="example"
						class="table table-striped table-bordered">
						<thead>
							<tr  style="font-weight: bold;">
							
						
							
								<th>Centre</th>
								<th>Number of Centre</th>
								<th>Number Of Independent Member</th>
								<th>Avg. Attendance Of Member</th>
								<th>Total No. Of New Convert</th>
								
								<th>Total No. Of First Timer</th>
								<th>No. Of FirstTimer FollowUp</th>
								<th>No. Of FirstTimer Converted</th>
								<th>No. Of Weekly Report Submitted</th>
								<th>No. Of Evangelism Project</th>
								
								<th>No. Of commImpact Project</th>
								<th>No Of Naming Ceremony</th>
								<th>No. Of Burial</th>
								<th>No. Of Area</th>
								<th>No. Of Intern</th>
								
								<th>No. Of AsstLeader</th>
								<th>No. Of Leader</th>
								<th>No. Of AreaSupervisor</th>
								<th>Total Offering</th>
								<th>No. Of NewLeader Introduced</th>
								
								<th>No. Of NewLeader Trained</th>
								<th>No. Of NewHost Introduced</th>
								<th>No. Of NewHost Trained</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${directorReport}" var="response">
								<tr>
									<td>${response.center}</td>
									<td>${response.noOfCenter}</td>
									<td>${response.noOfIndependentMember}</td>
									<td>${response.avAttendanceOfMember}</td>
									<td>${response.totalNoOfNewConvert}</td>
									<td>${response.totalNoOfFirstTimer}</td>
									<td>${response.noOfFirstTimerFollowUp}</td>
									<td>${response.noOfFirstTimerCoverted}</td>
									<td>${response.noOfWeeklyReportSubmitted}</td>
									<td>${response.noOfEvanProject}</td>
									<td>${response.noOfcommImpactProject}</td>
									<td>${response.noOfNamingCeremony}</td>
									<td>${response.noOfburial}</td>
									<td>${response.noOfArea}</td>
									<td>${response.noOfIntern}</td>
									
									<td>${response.noOfAsstLeader}</td>
									<td>${response.noOfLeader}</td>
									<td>${response.noOfAreaSupervisor}</td>
									<td>${response.totalOffering}</td>
									<td>${response.noOfNewLeaderIntroduced}</td>
									
									<td>${response.noOfNewLeaderTrained}</td>
									<td>${response.noOfNewHostIntroduced}</td>
									<td>${response.noOfNewHostTrained}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
</div>


<script>
	$(document).ready(function() {
		$('#example').DataTable({
			dom : 'Blfrtip',
			buttons : [ 'copy', 'csv', 'excel', 'pdf', 'print' ],
			lengthMenu : [ [ 10, 25, 50, -1 ], [ 10, 25, 50, "All" ] ],
			responsive : true
		});
	});

</script>
