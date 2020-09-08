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
						<button type="button" class="btn btn-primary">Delete</button>
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
</div>
