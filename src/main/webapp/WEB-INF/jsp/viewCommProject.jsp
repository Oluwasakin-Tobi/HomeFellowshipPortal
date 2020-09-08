<%@ include file="authheader.jsp"%>

<main>


<div class="container-fluid">
	<h1 class="mt-4">View Community Impact Project</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value='/dashboard' />">Dashboard</a></li>
		<li class="breadcrumb-item active">View Community Impact Project</li>
	</ol>

	<div class="col-md-12">
		<div class="card shadow">
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
</main>

<%@ include file="footer.jsp"%>