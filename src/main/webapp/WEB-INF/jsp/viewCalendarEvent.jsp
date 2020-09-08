<%@ include file="authheader.jsp"%>

<main> 


<div class="container-fluid">
	<h1 class="mt-4">View Calendar Event</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value='/dashboard' />">Dashboard</a></li>
		<li class="breadcrumb-item active">View Calendar Event</li>
	</ol>

	<div class="col-md-12">
		<div class="card shadow">
			<div class="card-header">
				<strong class="card-title">View Calendar Event</strong>
			</div>
			<div class="card-body">
				<table id="bootstrap-data-table-export"
					class="table table-striped table-bordered">
					<thead>
							<tr>
								<th>Event</th>
								<th>Event Date</th>
								<th></th>
								<th></th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${calendarEvent}" varStatus="current"
								var="response">
								<tr>
									<td>${response.event}</td>
									<td>${response.eventDateD}</td>
									<td><a href="<c:url value = '/deleteCalendarEvent-${response.calendarID}'/>">
						<button type="button" class="btn btn-danger">Delete</button>
					</a></td>
							
							<td><a href="<c:url value = '/getCalendarEventToUpdate-${response.calendarID}'/>">
						<button type="button" class="btn btn-primary">Update</button>
					</a></td>
							
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