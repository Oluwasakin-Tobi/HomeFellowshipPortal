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
						<strong class="card-title">CREATE MONTHLY REPORT</strong>
					</div>
					<div class="card-body">
						<c:url var="monthlyReport" value="/monthlyReport" />
						<mvc:form method="POST" action="${monthlyReport}"
							enctype="multipart/form-data" id="demo-form2"
							modelAttribute="MonthlyReport"
							class="form-horizontal form-label-left">

							<div class="row">

								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
									
								<div class="col-md-6">
									<label class="bmd-label-floating">Name*</label> <input
										class="form-control" type="text" name="name" id="name"
										value="${userName}" path="centre" required readonly
										autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="name" class="help-inline" />
									</div>
								</div>

								<div class="col-md-6">
									<label class="bmd-label-floating">Date Visited*</label> <input
										class="form-control" type="date" name="dateVisited" id="dateVisited"
										value="${report.dateVisited}" path="dateVisited" required
										autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="dateVisited" class="help-inline" />
									</div>
								</div>
							</div>
							<br>
							<div class="row">

								<div class="col-md-6">
									<label for="disabledTextInput">Visited By*</label> <input
										class="form-control" type="text" name="visitedBy"
										id="visitedBy" value="${report.visitedBy}"
										path="visitedBy" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="visitedBy" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Visitation Report*</label> <input
										class="form-control" type="text" name="visitationReport"
										id="visitationReport" value="${report.visitationReport}"
										path="visitationReport" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="visitationReport" class="help-inline" />
									</div>
								</div>
							</div>
							
							<div class="row">

								<div class="col-md-6">
									<label for="disabledTextInput">Leader's Remark</label> <input
										class="form-control" type="text" name="leaderRemark"
										id="leaderRemark" value="${report.leaderRemark}"
										path="leaderRemark" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="leaderRemark" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Week*</label>
										<select
											id="week" name="week" path="week"
											class="form-control">
											<option value="">Select option</option>
											<option value="WEEK 1">WEEK 1</option>
											<option value="WEEK 2">WEEK 2</option>
											<option value="WEEK 3">WEEK 3</option>
											<option value="WEEK 4">WEEK 4</option>
											<option value="WEEK 5">WEEK 5</option>
										</select>
									<div class="has-error" style="color: red">
										<mvc:errors path="week" class="help-inline" />
									</div>
								</div>
							</div>

							

							<br>

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