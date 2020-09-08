<%@ include file="authheader.jsp"%>

<main  ng-app="myModule">
<div class="container-fluid">
	<h1 class="mt-4">Training Schedule</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value = '/dashboard'/>">Dashboard</a></li>
		<li class="breadcrumb-item"><a
			href="<c:url value = '/calendar'/>">Calendar Event</a></li>
		<li class="breadcrumb-item active">Training Schedule</li>
	</ol>
	<div class="col-md-12">
		<div class="card shadow">

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

			<div class="card-header">
				<strong class="card-title">CREATE TRAINING SCHEDULE</strong>
			</div>
			<div class="card-body">
					<c:url var="loadCalendar" value="/loadCalendar" />
						<mvc:form method="POST" action="${loadCalendar}"
							enctype="multipart/form-data" id="demo-form2"
							modelAttribute="CalendarDetail" class="form-horizontal form-label-left">

							<div class="row">

								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

								<div class="col-md-6">
									<label class="bmd-label-floating">Name*</label> <input
										class="form-control" type="text" name="createdBy" id="createdBy"
										value="${username}" path="createdBy" required readonly
										autocomplete="off">
								</div>

							</div>
							<br>
							<div class="row">

								<div class="col-md-6">
										<label for="exampleFormControlSelect1">Training*</label> <select
											id="event" name="event" path="event"
											class="form-control" required>
											<option value="">Select option</option>
											<c:forEach items="${getTraining}" varStatus="current"
											var="training">
											<option value="${training.trainingName}">${training.trainingName}</option>
										</c:forEach>
										</select>
									</div>
								
								<div class="col-md-6">
									<label class="bmd-label-floating">Event Date*</label> <input
										class="form-control" type="date" name="eventDate" id="eventDate"
										value="${request.eventDate}" path="eventDate" required onchange="checkdate()"
										autocomplete="off">
								</div>
								
								<!-- <div class=" col-md-6">
									<label for="disabledTextInput">Send To*</label> <select
										id="sendTo" name="sendTo" path="sendTo"
										class="form-control" required>
										<option value="">Select option</option>
										<option value="ALL">ALL</option>
										<option value="MEMBERS">MEMBERS</option>
										<option value="AREA SUPERVISOR">AREA SUPERVISORS</option>
										<option value="DISTRICT PASTOR">DISTRICT PASTORS</option>
									</select>
								</div> -->
								
								<div class="col-md-6">
									<label class="bmd-label-floating">Training Link</label> <textarea
										class="form-control" type="text" name="meetingLink" id="meetingLink"
										value="${request.meetingLink}" path="meetingLink"
										autocomplete="off"></textarea>
								</div>
								
								<div class="col-md-6">
									<label class="bmd-label-floating">Send To*</label> <input
										class="form-control" type="text" name="sendTo" id="sendTo"
										value="${request.sendTo}" path="sendTo" required 
										autocomplete="off">
								</div>
								
								

							</div>



					<div class="row">
						<div class="col-md-6 mt-3">
							<a>
								<button class="btn btn-primary" type="button">Cancel</button>
							</a>
							<button class="btn btn-warning" type="reset">Reset</button>

							<button type="submit" id="submitbutton" class="btn btn-success">Submit</button>
						</div>
					</div>
				</mvc:form>



			</div>
		</div>
	</div>

</div>
</main>


<script>
function checkdate(){
	 todaysDate = new Date();
	 todaysDate2 = new Date();
		console.log("todaysDate "+todaysDate);
	var custDate =	$('#eventDate').val();
	var dateDate = new Date (custDate);
	 console.log("dateDate "+dateDate);
	if((dateDate < todaysDate)){
	 /* alertjs.show({
            title: 'Error!',
            text: "Customer Instruction date not valid",
            effect: 'ease-in-bounce',
        }); */
        alert('Event Date date cannot be less than today');
	  $('#submitbutton').prop('disabled',true);
	   }
	
	else{
		  $('#submitbutton').prop('disabled',false);
	}
  };

<%@ include file="footer.jsp"%>
<script src="<c:url value='/js/app.js' />"></script>