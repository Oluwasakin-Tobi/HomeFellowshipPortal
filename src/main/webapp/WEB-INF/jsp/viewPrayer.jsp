<%@ include file="authheader.jsp"%>


<div class="content mt-3">
	<!-- <div class="animated fadeIn"> -->
	<div class="row">


		<div class="col-md-12">
			<div class="card">
				<div class="card-header">
					<strong class="card-title">View Prayer Request</strong>
				</div>
				<div class="card-body">
					<table id="bootstrap-data-table-export"
						class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Name</th>
								<th>Prayer Request</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${prayer}" varStatus="current"
								var="response">
								<tr>
									<td>${response.name}</td>
									<td>${response.prayer}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>


	</div>
</div>
