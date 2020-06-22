<%@ include file="authheader.jsp"%>


<div class="content mt-3" role="main" ng-app="myModule">
	<div class="animated fadeIn">
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
						<strong class="card-title">CREATE SPECIAL ANNOUNCEMENT</strong>
					</div>
					<div class="card-body">
						<c:url var="loadAnnouncement" value="/loadAnnouncement" />
						<mvc:form method="POST" action="${loadAnnouncement}"
							enctype="multipart/form-data" id="demo-form2"
							modelAttribute="Announcement"
							class="form-horizontal form-label-left">

							<div class="row">

								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

								<div class="col-md-6">
									<label class="bmd-label-floating">Name*</label> <input
										class="form-control" type="text" name="name" id="name"
										value="${username}" path="name" required readonly
										autocomplete="off">
								</div>
								
							 	<div class="col-md-6">
									<label class="bmd-label-floating">Category*</label> <select
										class="form-control" type="text" name="category" id="category"
										path="category" required ng-model="choice">
										<option value="">Select option</option>
										<option value="BIRTHDAY">BIRTHDAY</option>
										<option value="WEDDING ANNIVERSARY">WEDDING ANNIVERSARY</option>
										<option value="NAMING CEREMONY">NAMING CEREMONY</option>
										<option value="CHILD DEDICATION">CHILD DEDICATION</option>
										<option value="WEDDING">WEDDING</option>
										<option value="MEETING">MEETING</option>
										<option value="OTHERS">OTHERS</option>
										 </select>
										 
										 <input ng-show="choice=='OTHERS'"
										class="form-control" type="text" name="category" id="category"
										value="${request.category}" path="category" ng-required="choice=='OTHERS'"
										autocomplete="off">
								</div> 
								
								
								<div class="col-md-6" ng-show="choice=='MEETING'">
									<label class="bmd-label-floating">Meeting Type*</label> <select
										class="form-control" type="text" name="meetingType" id="meetingType"
										path="meetingType" ng-required="choice=='MEETING'" ng-model="meetingType">
										<option value="">Select option</option>
										<option value="GENERAL MEETING">GENERAL MEETING</option>
										<option value="ANNUAL MEETING">ANNUAL MEETING</option>
										<option value="DISTRICT MEETING">DISTRICT MEETING</option>
										<option value="PRAYER MEETING">PRAYER MEETING</option>
										<option value="REVIEW MEETING">REVIEW MEETING</option>
										<option value="ANNUAL RETREAT">ANNUAL RETREAT</option>
										<option value="OTHER MEETINGS">OTHERS</option>
										 </select>
										 
										 <input ng-show="meetingType=='OTHER MEETINGS'"
										class="form-control" type="text" name="meetingType" id="meetingType"
										value="${request.meetingType}" path="meetingType" ng-required="meetingType=='OTHER MEETINGS'"
										autocomplete="off">
								</div>
								
								<div class="col-md-6" ng-show="choice=='MEETING'">
									<label class="bmd-label-floating">Meeting Link</label> <textarea
										class="form-control" type="text" name="meetingLink" id="meetingLink"
										value="${request.meetingLink}" path="meetingLink"
										autocomplete="off"></textarea>
								</div> 

							</div>
							<br>
							 <div class="row">

								 <div class="col-md-6">
									<label class="bmd-label-floating">Announcement*</label> <textarea
										class="form-control" type="text" name="announce" id="announce"
										value="${request.announce}" path="announce" required
										autocomplete="off"></textarea>
								</div> 
								
								 <div class="col-md-6">
									<label class="bmd-label-floating">Event Date*</label> <input
										class="form-control" type="date" name="eventDate" id="eventDate"
										value="${request.eventDate}" path="eventDate" required
										autocomplete="off">
								</div>
								
								<div class="col-md-6" ng-show="choice=='MEETING'">
									<label class="bmd-label-floating">Send To*</label> <select
										class="form-control" type="text" name="sendTo" id="sendTo"
										path="sendTo" ng-required="choice=='MEETING'" ng-model="sender">
										<option value="">Select option</option>
										<option value="ALL">ALL</option>
										<option value="MEMBERS">MEMBERS</option>
										<option value="OTHER USER">USER</option>
										 </select>
										 
										 <input ng-show="sender=='OTHER USER'"
										class="form-control" type="text" name="sendToAUser" id="sendToAUser"
										value="${request.sendToAUser}" path="sendToAUser" ng-required="sender=='OTHER USER'"
										autocomplete="off">
								</div>  
								
							</div> 
							

							<a href="<c:url value="/dashboard" />">
								<button class="btn btn-primary" type="button">Cancel</button>
							</a>
							<button class="btn btn-primary" type="reset">Reset</button>

							<button type="submit" id="submitbutton" class="btn btn-success">Submit</button>

						</mvc:form>



					</div>
				</div>
			</div>


		</div>
	</div>
	<!-- .animated -->
</div>
<!-- .content -->



<script src="<c:url value='/js/app.js' />"></script>