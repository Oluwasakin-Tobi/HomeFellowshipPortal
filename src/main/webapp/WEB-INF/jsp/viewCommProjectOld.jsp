<%@ include file="authheader.jsp"%>


<div class="content mt-3">
	<!-- <div class="animated fadeIn"> -->
	<div class="row">


		<div class="col-md-12">
			<div class="card">
				<div class="card-header">
					<strong class="card-title">View Community Impact Project</strong>
				</div>
				<div class="card-body">
					<table id="bootstrap-data-table-export"
						class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Name</th>
								<th>Community</th>
								<th>Impact Project</th>
								<th>Amount</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${project}" varStatus="current"
								var="response">
								<tr>
									<td>${response.name}</td>
									<td>${response.category}</td>
									<td>${response.expense}</td>
									<td><fmt:formatNumber type="number"
													minFractionDigits="2" maxFractionDigits="2"
													value="${response.amount}"></fmt:formatNumber></td>
							
								</tr>
								
						
					
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>


	</div>
</div>
