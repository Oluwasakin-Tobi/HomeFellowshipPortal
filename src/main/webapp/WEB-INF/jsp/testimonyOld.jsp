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
					<strong class="card-title">Centre Report</strong>
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
									<c:url var="getCentreReport" value="/getCentreReport" />
									<mvc:form method="POST" action="${getCentreReport}"
										enctype="multipart/form-data" id="demo-form2"
										modelAttribute="Filter"
										class="form-horizontal form-label-left">

										<div class="row">

											<div class="col-md-6">
												<label for="disabledTextInput">Centre*</label> <select
													id="centre" name="centre" path="centre" required
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
								<th>Centre</th>
								<th>Testifier</th>
								<th>Topic</th>
								<th>Testimony</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${testimony}" var="response">
								<tr>
									<td>${response.centre}</td>
									<td>${response.testifier}</td>
									<td>${response.topic}</td>
									<td>${response.testimony}</td>
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
