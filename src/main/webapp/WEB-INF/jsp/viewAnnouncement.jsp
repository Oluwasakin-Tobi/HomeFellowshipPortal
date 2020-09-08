<%@ include file="authheader.jsp"%>

<main>


<div class="container-fluid">
	<h1 class="mt-4">View Announcement</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value='/dashboard' />">Dashboard</a></li>
		<li class="breadcrumb-item active">View Announcement</li>
	</ol>

	<div class="col-md-12">
		<div class="card shadow">
			<div class="card-header">
				<strong class="card-title">View Announcement</strong>
			</div>
			<div class="card-body">
				<c:forEach items="${announce}" varStatus="current" var="response">
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<strong class="card-title">${response.category}</strong>
							</div>
							<div class="card-body">
								${response.announce} to ${response.name}
								<c:if test="${not empty response.meetingLink}">
									<a target="_blank" href="${response.meetingLink}"> Join a
										meeting <i class="fa fa-meeting"></i>
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