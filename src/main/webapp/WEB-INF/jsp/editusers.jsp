<%@ include file="authheader.jsp"%>


<div class="content mt-3">
	<!-- <div class="animated fadeIn"> -->
	<div class="row">

		<c:if test="${errorMessage != null}">

			<div class="col-sm-4">
				<div class="alert alert-danger alert-dismissable float-right">
					<a class="alert-link" href="#"> <span class="alert-b"></span></a> <span
						id="errshow alert-c">${errorMessage}</span> <a class="alert-link"
						href="#"><span data-dismiss="alert" aria-label="close"
						class="fa fa-times-circle alert-b float-right"></span></a>
				</div>
			</div>

		</c:if>
		<c:if test="${successMessage != null}">


			<div class="col-sm-4">
				<div class="alert alert-success alert-dismissable float-right">
					<a class="alert-link" href="#"> <span class="alert-c"></span></a> <span
						id="errshow alert-c">${successMessage}</span> <a
						class="alert-link" href="#"><span data-dismiss="alert"
						aria-label="close" class="fa fa-times-circle alert-b float-right"></span></a>
				</div>
			</div>

		</c:if>

		<div class="col-md-12">
			<div class="card">
				<div class="card-header">
					<strong class="card-title">Edit User Attribute</strong>
				</div>
				<div class="card-body">
					<table id="bootstrap-data-table-export"
						class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>User ID</th>
								<th>User Name</th>
								<th>Full Name</th>
								<th>Role</th>
								<th>Email</th>
								<th>Affiliate</th>
								<th></th>
								<!-- <th></th> -->

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${usersroledetails}" varStatus="current"
								var="user">
								<tr>
									<td>${user.userID}</td>
									<td>${user.userName}</td>
									<td>${user.userFullName}</td>
									<td>${user.userRoles}</td>
									<td>${user.userEmailAdd}</td>
									<td>${user.affiliateCode}</td>


									<div class="modal fade" id="disable${current.count-1}"
										tabindex="-1" role="dialog"
										aria-labelledby="authModalCenterTitle" aria-hidden="true">
										<div class="modal-dialog modal-dialog-centered modal-lg"
											role="document">
											<div class="modal-content">
												<div class="modal-body">

													<h4 class="modal-title" id="authModalLongTitle">Are
														you sure you want to disable user " ${user.userName} "?</h4>
												</div>

												<div class="modal-footer">
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">No</button>

													<a href="<c:url value="/diasableuser-${user.userID}" />">
														<button type="button" class="btn btn-success"
															type="button">Yes</button>
													</a>
												</div>
											</div>
										</div>
									</div>

									<div class="modal fade" id="enable${current.count-1}"
										tabindex="-1" role="dialog"
										aria-labelledby="authModalCenterTitle" aria-hidden="true">
										<div class="modal-dialog modal-dialog-centered modal-lg"
											role="document">
											<div class="modal-content">
												<div class="modal-body">

													<h4 class="modal-title" id="authModalLongTitle">Are
														you sure you want to enable user " ${user.userName} "?</h4>
												</div>

												<div class="modal-footer">
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">No</button>

													<a href="<c:url value="/enableuser-${user.userID}" />">
														<button type="button" class="btn btn-success"
															type="button">Yes</button>
													</a>
												</div>
											</div>
										</div>
									</div>


									<td><c:choose>
											<c:when test="${user.deleteFlag!='1'}">
												<button type="button" class="btn btn-primary" type="button"
													data-toggle="modal"
													data-target="#disable${current.count-1}">Disable
													User</button>
											</c:when>
											<c:otherwise>
												<button type="button" class="btn btn-primary" type="button"
													data-toggle="modal" data-target="#enable${current.count-1}">Enable
													User</button>
											</c:otherwise>
										</c:choose> <c:if test="${user.deleteFlag=='0'}">
											<a href="<c:url value='/edituserdetails-${user.userID}' />"
												class="btn btn-success custom-width">Edit User Details</a>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>


	</div>
</div>

<%-- <%@ include file="footer.jsp"%> --%>