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
					<strong class="card-title">Area Report</strong>
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
									<c:url var="getAreaReport" value="/getAreaReport" />
									<mvc:form method="POST" action="${getAreaReport}"
										enctype="multipart/form-data" id="demo-form2"
										modelAttribute="Filter"
										class="form-horizontal form-label-left">

										<div class="row">

											<div class="col-md-6">
												<label for="disabledTextInput">Area*</label> <select
													id="area" name="area" path="area" class="form-control">
													<option value="">Select option</option>
													<option value="AREA1">AREA1</option>
													<option value="AREA2">AREA2</option>
													<option value="AREA3">AREA3</option>
												</select>
												<div class="has-error" style="color: red">
													<mvc:errors path="area" class="help-inline" />
												</div>
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
								<th>Area</th>
								<th>Area Supervisor</th>
								<th>Offering</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${areaReport}" var="response">
								<tr>
									<td>${response.area}</td>
									<td>${response.leaderName}</td>
									<td>${response.offering}</td>
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
