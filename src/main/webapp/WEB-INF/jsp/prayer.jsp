<%@ include file="authheader.jsp"%>

<main>
<div class="container-fluid">
	<h1 class="mt-4">Prayer Request</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value = '/dashboard'/>">Dashboard</a></li>
		<li class="breadcrumb-item active">Prayer Request</li>
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
				<strong class="card-title">PRAYER REQUEST</strong>
				<div class="float-right">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#viewPrayerPerUser">View Request</button>
				</div>


			</div>
			<div class="card-body">
				<c:url var="prayerRequest" value="/prayerRequest" />
				<mvc:form method="POST" action="${prayerRequest}"
					enctype="multipart/form-data" id="demo-form2"
					modelAttribute="prayerRequest"
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

					</div>
					<br>
					<div class="row">

						<div class="col-md-6">
							<label class="bmd-label-floating">Prayer Request*</label>
							<textarea class="form-control" type="text" name="prayer"
								id="prayer" value="${request.prayer}" path="prayer" required
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
				<div class="modal fade" id="viewPrayerPerUser" tabindex="-1"
					role="dialog" aria-labelledby="viewPrayerPerUserLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="viewPrayerPerUserLabel">View
									Prayer Request</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<div class="col-md-12">
									<div class="card">
										<div class="card-body">
											<table id="dataTable"
												class="table table-striped table-bordered">
												<thead>
													<tr>
														<th>Name</th>
														<th>Prayer Request</th>
														<th>Status</th>
														<!-- <th>Comment</th> -->

													</tr>
												</thead>
												<tbody>
													<c:forEach items="${prayer}" varStatus="current"
														var="response">
														<tr>
															<td>${response.name}</td>
															<td>${response.prayer}</td>
															<td>${response.status}</td>
															<%-- <td>${response.comment}</td> --%>

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