<%@ include file="authheader.jsp"%>

<main>
 <c:if test="${errorMessage != null}">

	<div class="alert alert-danger alert-dismissable float-right">
		<a class="alert-link" href="#"> <span class="alert-b"></span></a> <span
			id="errshow alert-c">${errorMessage}</span> <a class="alert-link"
			href="#"><span data-dismiss="alert" aria-label="close"
			class="fa fa-times-circle alert-b float-right"></span></a>
	</div>

</c:if> <c:if test="${successMessage != null}">

	<div class="alert alert-success alert-dismissable float-right">
		<a class="alert-link" href="#"> <span class="alert-c"></span></a> <span
			id="errshow alert-c">${successMessage}</span> <a class="alert-link"
			href="#"><span data-dismiss="alert" aria-label="close"
			class="fa fa-times-circle alert-b float-right"></span></a>
	</div>

</c:if>


<div class="container-fluid">
	<h1 class="mt-4">View Welfare Request</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a href="<c:url value='/dashboard' />">Dashboard</a></li>
		<li class="breadcrumb-item active">View Welfare Request</li>
	</ol>
	<div class="col-md-12">
		<div class="card shadow">
			<div class="card-header">
				<strong class="card-title">View Welfare Request</strong>
			</div>
			<div class="card-body">
				<table id="bootstrap-data-table-export"
					class="table table-striped table-bordered">
					<thead>
						<tr>
							<th>Name</th>
							<th>Welfare Request</th>
							<th>Action</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${welfare}" varStatus="current" var="response">
							<tr>
								<td>${response.name}</td>
								<td>${response.welfare}</td>
								<td><button class="btn btn-danger" type="button"
										data-toggle="modal" data-target="#reject${current.count-1}">Reject</button>
										<button class="btn btn-primary" type="button"
										data-toggle="modal" data-target="#approve${current.count-1}">Approve</button></td>


							</tr>


							<div class="modal fade" id="reject${current.count-1}"
								tabindex="-1" role="dialog"
								aria-labelledby="authModalCenterTitle" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered modal-lg"
									role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h4 class="modal-title" id="authModalLongTitle">
												<b>REJECT COMMENT</b>
											</h4>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>

										</div>

										<c:url var="updateWelfare" value="/updateWelfare" />
										<mvc:form method="POST" action="${updateWelfare}"
											enctype="multipart/form-data" id="demo-form2"
											modelAttribute="welfare"
											class="form-horizontal form-label-left">
											<div class="modal-body">

												<input type="hidden" name="welfareId"
													value="${response.welfareId}" path="welfareId"> <input
													type="hidden" name="status" value="REJECT" path="status">

												<div class="col-md-6">
													<label for="disabledTextInput">Why Reject?</label>
													<textarea type="text" name="comment" id="comment" required
														class="form-control" autocomplete="off">${response.comment}</textarea>
												</div>
												<p>&nbsp;</p>
												<div class="modal-footer">
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">Close</button>

													<button type="submit" class="btn btn-success">Submit</button>
												</div>
											</div>
										</mvc:form>
									</div>
								</div>
							</div>

							<div class="modal fade" id="approve${current.count-1}"
								tabindex="-1" role="dialog"
								aria-labelledby="authModalCenterTitle" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered modal-lg"
									role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h4 class="modal-title" id="authModalLongTitle">
												<b>APPROVE COMMENT</b>
											</h4>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>

										</div>

										<c:url var="updateWelfare" value="/updateWelfare" />
										<mvc:form method="POST" action="${updateWelfare}"
											enctype="multipart/form-data" id="demo-form2"
											modelAttribute="welfare"
											class="form-horizontal form-label-left">
											<div class="modal-body">

												<input type="hidden" name="welfareId"
													value="${response.welfareId}" path="welfareId"> <input
													type="hidden" name="status" value="APPROVE" path="status">

												<div class="col-md-6">
													<label for="disabledTextInput">Comment</label>
													<textarea type="text" name="comment" id="comment" required
														class="form-control" autocomplete="off">${response.comment}</textarea>
												</div>
												<p>&nbsp;</p>
												<div class="modal-footer">
													<button type="button" class="btn btn-primary"
														data-dismiss="modal">Close</button>

													<button type="submit" class="btn btn-success">Submit</button>
												</div>
											</div>
										</mvc:form>
									</div>
								</div>
							</div>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

</div>
</main>

<%@ include file="footer.jsp"%>