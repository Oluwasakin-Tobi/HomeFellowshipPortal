<%@ include file="authheader.jsp"%>


<div class="content mt-3">
	<!-- <div class="animated fadeIn"> -->
	<div class="row">
	
	<c:if test="${errorMessage != null}">

				<div class="alert alert-danger alert-dismissable float-right">
					<a class="alert-link" href="#"> <span class="alert-b"></span></a> <span
						id="errshow alert-c">${errorMessage}</span> <a class="alert-link"
						href="#"><span data-dismiss="alert" aria-label="close"
						class="fa fa-times-circle alert-b float-right"></span></a>
				</div>

			</c:if>
			<c:if test="${successMessage != null}">

				<div class="alert alert-success alert-dismissable float-right">
					<a class="alert-link" href="#"> <span class="alert-c"></span></a> <span
						id="errshow alert-c">${successMessage}</span> <a
						class="alert-link" href="#"><span data-dismiss="alert"
						aria-label="close" class="fa fa-times-circle alert-b float-right"></span></a>
				</div>

			</c:if>

<div class="col-md-12">
			<div class="card">
				<div class="card-header">
					<h2><strong class="card-title">SOCIAL EVENTS</strong></h2>
				</div>
			</div>
		</div>
		<c:forEach items="${socialEvent}" varStatus="current"
								var="response">
		<div class="col-md-12">
			<div class="card">
				<div class="card-header">
					<strong class="card-title">${response.topic}</strong>
				</div>
				<div class="card-body">
					${response.event}
					<br>
					<c:if test="${response.uploadFlag == 'Y'}">
					<a href="<c:url value='/viewSocialEventDoc-${response.eventID}'/>">
					<button type="button" class="btn btn-success">View Events</button>
					</a>
					</c:if>
					<br>
					<c:if test="${UserRole == 'ADMIN'}">
					<a href="<c:url value='/deleteSocialEvent-${response.eventID}'/>">
					<button type="button" class="btn btn-primary">Delete Events</button>
					</a>
					</c:if>
				</div>
			</div>
		</div>
</c:forEach>

	</div>
</div>

