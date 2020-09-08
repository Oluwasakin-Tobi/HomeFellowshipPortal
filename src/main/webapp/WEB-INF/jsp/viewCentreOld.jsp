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
					<strong class="card-title">VIEW CENTRE</strong>
				</div>

				<div class="content mt-3">
					<div class="row">
						<div class="col-lg-12">
							<h4>
								<b>Query Centre</b>
							</h4>
							<div class="col-md-12">
								<hr />
							</div>

							<div class="card">
								<div class="card-body" style="min-height: 100px;">
									<c:url var="viewCentre"
										value="/viewCentre" />
									<mvc:form method="POST" action="${viewCentre}"
										enctype="multipart/form-data" id="demo-form2"
										modelAttribute="Centre"
										class="form-horizontal form-label-left">

										<div class="row">

											<div class="col-md-6">
									<label for="disabledTextInput">Type*</label>
										<select
											id="type" name="type" path="type" required
											class="form-control">
											<option value="">Select option</option>
											<option value="CENTRE">CENTRE</option>
											<option value="AREA">AREA</option>
											<option value="ZONE">ZONE</option>
											<option value="DISTRICT">DISTRICT</option>
										</select>
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
								<th>NAME</th>
								<th>PARENT CENTRE</th>
								<th>LEADER</th>
								<th>DATE CREATED</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${response}" var="response">
								<tr>
									<td>${response.centre}</td>
									<td>${response.parentCentre}</td>
									<td>${response.leaderInCharge}</td>
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
