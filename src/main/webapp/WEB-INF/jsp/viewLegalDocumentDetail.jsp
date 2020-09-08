<%@ include file="authheader.jsp"%>

<main>


<div class="container-fluid">
	<h1 class="mt-4">View Legal Documents</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value='/dashboard' />">Dashboard</a></li>
		<li class="breadcrumb-item active">View Legal Documents</li>
	</ol>

	<div class="col-md-12">
		<div class="card shadow">
			<div class="card-header">
				<strong class="card-title">View Legal Documents</strong>
			</div>
			<div class="card-body">
				<c:forEach items="${legalDocument}" varStatus="current" var="response">
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<strong class="card-title">${response.topic}</strong>
							</div>
							<div class="card-body">
								${response.event} <br>
								<c:if test="${response.uploadFlag == 'Y'}">
									<a
										href="<c:url value='/viewLegalDoc-${response.eventID}'/>">
										<button type="button" class="btn btn-success">
											Download Document</button>
									</a>
								</c:if>
								<br>
								<c:if test="${UserRole == 'ADMIN'}">
									<a
										href="<c:url value='/deleteLegalDoc-${response.eventID}'/>">
										<button type="button" class="btn btn-primary">Delete
											Document</button>
									</a>
								</c:if>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

</div>
</main>

<%@ include file="footer.jsp"%>