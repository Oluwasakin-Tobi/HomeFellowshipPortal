<%@ include file="authheader.jsp"%>


<div class="content mt-3">
	<!-- <div class="animated fadeIn"> -->
	<div class="row">

<div class="col-md-12">
			<div class="card">
				<div class="card-header">
					<h2><strong class="card-title">View Announcement</strong></h2>
				</div>
			</div>
		</div>
		<%-- <div class="col-md-12">
			<div class="card">
				<div class="card-header">
					<strong class="card-title">View Announcement</strong>
				</div>
				<div class="card-body">
					<table id="bootstrap-data-table-export"
						class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Name</th>
								<th>Announcement</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${announce}" varStatus="current"
								var="response">
								<tr>
									<td>${response.name}</td>
									<td>${response.announce}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div> --%>
		<c:forEach items="${announce}" varStatus="current"
								var="response">
		<div class="col-md-12">
			<div class="card">
				<div class="card-header">
					<strong class="card-title">${response.category}</strong>
				</div>
				<div class="card-body">
					${response.announce} to ${response.name}
					<c:if test="${not empty response.meetingLink}">
						<a target="_blank" href="${response.meetingLink}">
                                Join a meeting <i class="fa fa-meeting"></i>
                            </a>
					</c:if>
				</div>
			</div>
		</div>
</c:forEach>

	</div>
</div>
