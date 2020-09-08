<%@ include file="authheader.jsp"%>


<div class="content mt-3" ng-app="myModule">
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
					<strong class="card-title">Visitors Report</strong>
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
									<c:url var="getVisitorReport" value="/getVisitorReport" />
									<mvc:form method="POST" action="${getVisitorReport}"
										enctype="multipart/form-data" id="demo-form2"
										modelAttribute="Filter"
										class="form-horizontal form-label-left">

										<div class="row">
										
										<div class="col-md-6">
												<label for="disabledTextInput">TYPE*</label> <select
													id="type" name="type" path="type" required ng-model="typeAction"
													class="form-control">
													<option value="">Select option</option>
													<option value="CENTRE">CENTRE</option>
													<option value="AREA">AREA</option>
													<option value="ZONE">ZONE</option>
													<option value="DISTRICT">DISTRICT</option>
												</select>
											</div>

											<div class="col-md-6" ng-show="typeAction=='CENTRE'">
												<label for="disabledTextInput">Centre*</label> <select
													id="centre" name="centre" path="centre" ng-required="typeAction=='CENTRE'"
													class="form-control">
													<option value="">Select option</option>
													<option value="LOVE">LOVE</option>
													<option value="PEACE">PEACE</option>
													<option value="JOY">JOY</option>
													<option value="FAITH">FAITH</option>
												</select>
											</div>
											
											<div class="col-md-6" ng-show="typeAction=='AREA'">
												<label for="disabledTextInput">Area*</label> <select
													id="area" name="area" path="area" ng-required="typeAction=='AREA'"
													class="form-control">
													<option value="">Select option</option>
													<option value="AREA1">AREA1</option>
													<option value="AREA2">AREA2</option>
													<option value="AREA3">AREA3</option>
												</select>
											</div>
											
											<div class="col-md-6" ng-show="typeAction=='ZONE'">
												<label for="disabledTextInput">Zone*</label> <select
													id="zone" name="zone" path="zone" ng-required="typeAction=='ZONE'"
													class="form-control">
													<option value="">Select option</option>
													<option value="ZONE1">ZONE1</option>
													<option value="ZONE2">ZONE2</option>
													<option value="ZONE3">ZONE3</option>
												</select>
											</div>
											
											<div class="col-md-6" ng-show="typeAction=='DISTRICT'">
												<label for="disabledTextInput">District*</label> <select
													id="district" name="district" path="district" ng-required="typeAction=='DISTRICT'"
													class="form-control">
													<option value="">Select option</option>
													<option value="DISTRICT1">DISTRICT1</option>
													<option value="DISTRICT2">DISTRICT2</option>
													<option value="DISTRICT3">DISTRICT3</option>
												</select>
											</div>

											<div class=" col-md-6">
												<label for="disabledTextInput">Date From*</label> <input
													id="dateFromStr" name="dateFromStr" type="date"
													path="dateFromStr" class="form-control" required />
											</div>
											<div class=" col-md-6">
												<label for="disabledTextInput">Date To*</label> <input
													id="dateToStr" name="dateToStr" type="date"
													path="dateToStr" class="form-control" required />
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
					<table id="example" class="table table-striped table-bordered">
						<thead>
							<tr style="font-weight: bold;">
								<th>Visitor Name</th>
								<th>Visitor Email</th>
								<th>Visitor Phone Number</th>
								<th>Visitor Gender</th>
								<th>Centre</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${visitorReport}" var="response">
								<tr>
									<td>${response.visitorName}</td>
									<td>${response.visitorEmail}</td>
									<td>${response.visitorPhoneNo}</td>
									<td>${response.visitorGender}</td>
									<td>${response.centre}</td>
									<td>${response.dateCreated}</td>
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

<script src="<c:url value='/js/app.js' />"></script>