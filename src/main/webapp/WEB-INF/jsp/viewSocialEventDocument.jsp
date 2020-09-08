<%@ include file="authheader.jsp"%>

<main>


<div class="container-fluid">
	<h1 class="mt-4">View Social Event</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value='/dashboard' />">Dashboard</a></li>
		<li class="breadcrumb-item active">View Social Event</li>
	</ol>

	<div class="col-md-12">
		<div class="card shadow">
			<div class="card-header">
				<strong class="card-title">SOCIAL EVENTS - ${topic}</strong>
			</div>
			<div class="card-body">
				<c:forEach items="${socialEventDoc}" varStatus="current"
					var="response">
					<div class="span12">
						<BR>
					</div>
					<BR>
					<div class="span12">

						<c:choose>
							<c:when test="${response.filetype == 'video/mp4'}">
								<video width="400" controls>
									<source type="video/mp4"
										src="data:video/mp4;base64,${response.inputStreamStr}">
								</video>
							</c:when>
							<c:when test="${response.filetype == 'audio/mpeg'}">
								<audio controls
									src="data:audio/ogg;base64,${response.inputStreamStr}">
								</audio>
							</c:when>
							<c:otherwise>
								<img width="400"
									src="data:image/png;base64,${response.inputStreamStr}" />
							</c:otherwise>
						</c:choose>


					</div>
					<div class="span12">
						<BR>
						=============================================================
					</div>
				</c:forEach>
			</div>
		</div>
	</div>

</div>
</main>

<%@ include file="footer.jsp"%>