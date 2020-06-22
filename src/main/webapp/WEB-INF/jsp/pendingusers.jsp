<%@ include file="authheader.jsp"%>



<div class="content mt-3">
	<div class="animated fadeIn">
		<div class="row">

			<c:if test="${errorMessage != null}">

				<div class="col-sm-4">
					<div class="alert alert-danger alert-dismissable float-right">
						<a class="alert-link" href="#"> <span class="alert-b"></span></a>
						<span id="errshow alert-c">${errorMessage}</span> <a
							class="alert-link" href="#"><span data-dismiss="alert"
							aria-label="close" class="fa fa-times-circle alert-b float-right"></span></a>
					</div>
				</div>

			</c:if>
			<c:if test="${successMessage != null}">


				<div class="col-sm-4">
					<div class="alert alert-success alert-dismissable float-right">
						<a class="alert-link" href="#"> <span class="alert-c"></span></a>
						<span id="errshow alert-c">${successMessage}</span> <a
							class="alert-link" href="#"><span data-dismiss="alert"
							aria-label="close" class="fa fa-times-circle alert-b float-right"></span></a>
					</div>
				</div>

			</c:if>

			<div class="col-md-12">
				<div class="card">
					<div class="card-header">
						<strong class="card-title"> <c:if
								test="${appuserdetails=='true'}"> Edited</c:if> <c:if
								test="${approveeditrole=='true'}"> Removed User(s) Role</c:if>
							User(s) Pending Authorization</strong>
					</div>
					<div class="card-body">
						<table id="bootstrap-data-table-export"
							class="table table-striped table-bordered">
							<thead>
								<tr>

									<th>User Name</th>
									<th>Full Name</th>
									<th>Affiliate</th>
									<th>Email</th>
									<c:if test="${approveeditrole=='true'}">
										<th>Date Disabled</th>
									</c:if>
									<c:if test="${approveeditrole == null}">
										<th>Date Created</th>
									</c:if>
									<c:if test="${approveeditrole != null}">
										<th>Role To Remove</th>
									</c:if>
									<c:if test="${approveeditrole == null}">
										<th>Role</th>
										<!-- <th>Step</th> -->
									</c:if>
									<th>Action</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${pendingUsers}" varStatus="current"
									var="user">
									<tr>
										<td>${user.userName}</td>
										<td>${user.userFullName}</td>
										<td>${user.affiliateCode==null?"N/A":user.affiliateCode}</td>
										<td>${user.userEmailAdd}</td>
										<td>${user.dateCreated}</td>
										<td>${user.userRoles}</td>
										<c:if test="${approveeditrole == null}">
											<%-- <td>${user.stepName==null?"N/A":user.stepName}</td> --%>
										</c:if>
										<td>

														<c:url var="authpenduser" value="/authpenduser" />
														<mvc:form method="POST" action="${authpenduser}"
															enctype="multipart/form-data" id="demo-form2"
															modelAttribute="userClient"
															class="form-horizontal form-label-left">

															<input type="hidden" name="userID" path="userID"
																value="${user.userID}" />
															<input type="hidden" name="role" path="role"
																value="${user.userRoles}" />
															<input type="hidden" name="affiliate" path="affiliate"
																value="${user.affiliateCode}" />

															<button type="submit" class="btn btn-success">Approve</button>


														</mvc:form>


														<c:url var="rejpenduser" value="/rejpenduser" />
														<mvc:form method="POST" action="${rejpenduser}"
															enctype="multipart/form-data" id="demo-form2"
															modelAttribute="UserClient"
															class="form-horizontal form-label-left">

															<input type="hidden" name="userID" path="userID"
																value="${user.userID}" />
															<input type="hidden" name="role" path="role"
																value="${user.userRoles}" />
															<input type="hidden" name="affiliate" path="affiliate"
																value="${user.affiliateCode}" />

															<button type="submit" class="btn btn-danger">Reject</button>


														</mvc:form>
											</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>


		</div>
	</div>
	<!-- .animated -->
</div>
<%-- <%@ include file="footer.jsp"%> --%>