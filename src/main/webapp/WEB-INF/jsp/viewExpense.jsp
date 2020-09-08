<%@ include file="authheader.jsp"%>

<main>


<div class="container-fluid">
	<h1 class="mt-4">View Expenses</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value='/dashboard' />">Dashboard</a></li>
		<li class="breadcrumb-item active">View Expenses</li>
	</ol>

	<div class="col-md-12">
		<div class="card shadow">
			<div class="card-header">
				<strong class="card-title">View Expenses</strong>
			</div>
			<div class="card-body">
				<table id="bootstrap-data-table-export"
						class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Name</th>
								<th>Expense</th>
								<th>Amount</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${expense}" varStatus="current"
								var="response">
								<tr>
									<td>${response.name}</td>
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
</main>

<%@ include file="footer.jsp"%>