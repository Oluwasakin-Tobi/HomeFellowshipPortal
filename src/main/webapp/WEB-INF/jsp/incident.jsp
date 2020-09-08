<%@ include file="authheader.jsp"%>

<main>
<div class="container-fluid">
	<h1 class="mt-4">Incident Report</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value = '/dashboard'/>">Dashboard</a></li>
		<li class="breadcrumb-item active">Incident Report</li>
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
				<strong class="card-title">Incident Report</strong>
				<div class="float-right">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#viewIncidentPerUser">View Request</button>
				</div>


			</div>
			<div class="card-body">
				<c:url var="loadIncident" value="/loadIncident" />
				<mvc:form method="POST" action="${loadIncident}"
					enctype="multipart/form-data" id="demo-form2"
					modelAttribute="Incident" class="form-horizontal form-label-left">
					<div class="row">

						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<div class="col-md-6">
							<label class="bmd-label-floating">Name*</label> <input
								class="form-control" type="text" name="name" id="name"
								value="${username}" path="name" required readonly
								autocomplete="off">
						</div>

					</div>
					<br>
					<div class="row">

						<div class="col-md-6">
							<label class="bmd-label-floating">Incident Request*</label>
							<textarea class="form-control" type="text" name="welfare"
								id="welfare" value="${request.welfare}" path="welfare" required
								autocomplete="off"></textarea>
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

				<!-- Modal -->
				<div class="modal fade" id="viewIncidentPerUser" tabindex="-1"
					role="dialog" aria-labelledby="viewIncidentPerUserLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="viewIncidentPerUserLabel">View
									Incident Request</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div class="col-md-12">
									<div class="card">
										<div class="card-body">
											<table id="bootstrap-data-table-export"
												class="table table-striped table-bordered">
												<thead>
													<tr>
														<th>Name</th>
														<th>Incident Request</th>
														<th>Status</th>
														<th>Comment</th>

													</tr>
												</thead>
												<tbody>
													<c:forEach items="${incident}" varStatus="current"
														var="response">
														<tr>
															<td>${response.name}</td>
															<td>${response.incident}</td>
															<td>${response.status}</td>
															<td>${response.comment}</td>

														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>

</div>
</main>

<%@ include file="footer.jsp"%>