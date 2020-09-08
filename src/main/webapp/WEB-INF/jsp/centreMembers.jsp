<%@ include file="authheader.jsp"%>

<main>


<div class="container-fluid">
	<h1 class="mt-4">Centre Member</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value='/dashboard' />">Dashboard</a></li>
		<li class="breadcrumb-item active">Centre Member</li>
	</ol>

	<div class="col-md-12">
		<div class="card shadow">
			<div class="card-header">
				<strong class="card-title">Centre Members for ${fellowshipCentre} centre</strong>
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
									<c:url var="centreMember"
										value="/centreMembers" />
									<mvc:form method="POST" action="${centreMember}"
										enctype="multipart/form-data" id="demo-form2"
										modelAttribute="Filter"
										class="form-horizontal form-label-left">

										<div class="row">

											<div class="col-md-6">
									<label for="disabledTextInput">Centre*</label>
										<select
											id="centre" name="centre" path="centre" required
											class="form-control">
											<option value="">Select option</option>
											<option value="LOVE">LOVE</option>
											<option value="PEACE">PEACE</option>
											<option value="JOY">JOY</option>
											<option value="FAITH">FAITH</option>
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
								<th>Name</th>
								<th>Role</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${member}" var="response">
								<tr>
									<td>${response.userFullName}</td>
									<td>${response.userRoles}</td>
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