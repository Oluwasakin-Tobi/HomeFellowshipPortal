<%@ include file="authheader.jsp"%>


<div class="content mt-3">
	<!-- <div class="animated fadeIn"> -->
	<div class="row">


		<div class="col-md-12">
			<div class="card">
				<div class="card-header">
					<strong class="card-title">View Welfare Request</strong>
				</div>
				<div class="card-body">
					<table id="bootstrap-data-table-export"
						class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Name</th>
								<th>Welfare Request</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${welfare}" varStatus="current"
								var="response">
								<tr>
									<td>${response.name}</td>
									<td>${response.welfare}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>


	</div>
</div>
