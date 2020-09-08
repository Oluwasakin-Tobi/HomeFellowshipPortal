<%@ include file="authheader.jsp"%>

<main> 


<div class="container-fluid">
	<h1 class="mt-4">View Prayer Request</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value='/dashboard' />">Dashboard</a></li>
		<li class="breadcrumb-item active">View Prayer Request</li>
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
				<strong class="card-title">View Prayer Request</strong>
			</div>
			<div class="card-body">
				<table id="dataTable"
					class="table table-striped table-bordered">
					<thead>
							<tr>
								<th>Name</th>
								<th>Prayer Request</th>
								<th></th>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${prayer}" varStatus="current"
								var="response">
								<tr>
									<td>${response.name}</td>
									<td>${response.prayer}</td>
									<c:choose>
									<c:when test="${response.status =='SHARE'}">
									<td><button class="btn btn-primary" type="button" data-toggle="modal"
							data-target="#unshare${current.count-1}">UnShare</button></td>
									</c:when>
									<c:otherwise>
									<td><button class="btn btn-success" type="button" data-toggle="modal"
							data-target="#share${current.count-1}">Share</button></td>
									</c:otherwise>
									</c:choose>
									
								</tr>
								
								
								<div class="modal fade" id="share${current.count-1}" tabindex="-1" role="dialog"
						aria-labelledby="authModalCenterTitle" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered modal-lg"
							role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title" id="authModalLongTitle">
										<b>SHARE COMMENT</b>
									</h4>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>

								</div>

								<c:url var="sharePrayer" value="/sharePrayer" />
								<mvc:form method="POST" action="${sharePrayer}"
									enctype="multipart/form-data" id="demo-form2"
									modelAttribute="prayerRequest"
									class="form-horizontal form-label-left">
									<div class="modal-body">

										<input type="hidden" name="prayerId"
											value="${response.prayerId}" path="prayerId">
											
										<input type="hidden" name="status"
											value="SHARED" path="status">

										<div class="col-md-6">
											<label for="disabledTextInput">Why Sharing?</label>
											<textarea type="text" name="comment" id="comment" 
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
					
					
					
					
					<div class="modal fade" id="unshare${current.count-1}" tabindex="-1" role="dialog"
						aria-labelledby="authModalCenterTitle" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered modal-lg"
							role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>

								</div>

								<c:url var="sharePrayer" value="/sharePrayer" />
								<mvc:form method="POST" action="${sharePrayer}"
									enctype="multipart/form-data" id="demo-form2"
									modelAttribute="prayerRequest"
									class="form-horizontal form-label-left">
									<div class="modal-body">

										<input type="hidden" name="prayerId"
											value="${response.prayerId}" path="prayerId">
											
										<input type="hidden" name="status"
											value="UNSHARED" path="status">

										<p>Are you sure you want to unshare?</p>
										<p>&nbsp;</p>
										<div class="modal-footer">
											<button type="button" class="btn btn-primary"
												data-dismiss="modal">Close</button>

											<button type="submit" class="btn btn-success">Yes</button>
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