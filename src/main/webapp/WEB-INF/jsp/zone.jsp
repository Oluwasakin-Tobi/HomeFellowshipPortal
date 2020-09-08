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
				<%-- 
				<a href="<c:url value = '/viewWelfarePerUser'/>">
			<button type = "button" class="btn btn-primary">View Request</button></a> --%>
			
					<div class="card-header">
						<strong class="card-title">CREATE NEW ZONE</strong>
					</div>
					<div class="card-body">
						<c:url var="createZone" value="/createZone" />
						<mvc:form method="POST" action="${createZone}"
							enctype="multipart/form-data" id="demo-form2"
							modelAttribute="Centre"
							class="form-horizontal form-label-left">

							<div class="row">

								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

								<div class="col-md-6">
									<label class="bmd-label-floating">Name*</label> <input
										class="form-control" type="hidden" name="userName" id="userName"
										value="${username}" path="userName" required readonly
										autocomplete="off">
								</div>
								
								<div class="col-md-6">
									<label class="bmd-label-floating">New Zone Name*</label> <input
										class="form-control" type="text" name="centre" id="centre"
										value="${request.centre}" path="centre" required
										autocomplete="off">
								</div>

							</div>
							<br>
							<div class="row">

								<div class=" col-md-6">
									<label for="disabledTextInput">Parent Zone*</label> <select
										id="parentCentre" name="parentCentre" path="parentCentre"
										class="form-control">
										<option value="">Select option</option>
										<c:forEach items="${response}" varStatus="current"
											var="zoneDetails">
											<option value="${zoneDetails.centre}">${zoneDetails.centre}</option>
										</c:forEach>
									</select>
								</div>
								
								<div class=" col-md-6">
									<label for="disabledTextInput">Zonal Leader*</label> <select
										id="leaderInCharge" name="leaderInCharge" path="leaderInCharge"
										class="form-control" required>
										<option value="">Select option</option>
										<c:forEach items="${zoneLeaders}" varStatus="current"
											var="leader">
											<option value="${leader.userFullName}">${leader.userFullName}</option>
										</c:forEach>
									</select>
								</div>
								
								<!-- <div class="col-md-6">
									<label for="disabledTextInput">District*</label>
										<select
											id="district" name="district" path="district" required
											class="form-control">
											<option value="">Select option</option>
											<option value="DISTRICT1">DISTRICT1</option>
											<option value="DISTRICT2">DISTRICT2</option>
											<option value="DISTRICT3">DISTRICT3</option>
										</select>
								</div> -->
								
								<div class=" col-md-6">
									<label for="disabledTextInput">District*</label> <select
										id="district" name="district" path="district"
										class="form-control" required>
										<option value="">Select option</option>
										<c:forEach items="${districtDetails}" varStatus="current"
											var="districtDetails">
											<option value="${districtDetails.centre}">${districtDetails.centre}</option>
										</c:forEach>
									</select>
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