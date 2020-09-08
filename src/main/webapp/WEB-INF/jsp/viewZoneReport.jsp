<%@ include file="authheader.jsp"%>

<main>


<div class="container-fluid">
	<h1 class="mt-4">Zonal Report</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value='/dashboard' />">Dashboard</a></li>
		<li class="breadcrumb-item active">Zonal Report</li>
	</ol>

	<div class="col-md-12">
		<div class="card shadow">
			<div class="card-header">
				<strong class="card-title">Zonal Report</strong>
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
									<c:url var="getZoneReport" value="/getZoneReport" />
									<mvc:form method="POST" action="${getZoneReport}"
										enctype="multipart/form-data" id="demo-form2"
										modelAttribute="Filter"
										class="form-horizontal form-label-left">

										<div class="row">

											<div class="col-md-6">
												<label for="disabledTextInput">Zone*</label> <select
													id="zone" name="zone" path="zone" class="form-control">
													<option value="">Select option</option>
													<option value="ZONE1">ZONE1</option>
													<option value="ZONE2">ZONE2</option>
													<option value="ZONE3">ZONE3</option>
												</select>
												<div class="has-error" style="color: red">
													<mvc:errors path="zone" class="help-inline" />
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
								<th>Zone</th>
								<th>Zonal Supervisor</th>
								<th>Offering</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${zoneReport}" var="response">
								<tr>
									<td>${response.zone}</td>
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
</main>

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

<%@ include file="footer.jsp"%>