<%@ include file="authheader.jsp"%>

<main>


<div class="container-fluid">
	<h1 class="mt-4">View Centre</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value='/dashboard' />">Dashboard</a></li>
		<li class="breadcrumb-item active">View Centre</li>
	</ol>

	<div class="col-md-12">
		<div class="card shadow">
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