<%@ include file="authheader.jsp"%>


<div class="content mt-3">
	<!-- <div class="animated fadeIn"> -->
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
					<strong class="card-title">View Welfare Request</strong>
				</div>
				<div class="card-body">
					<table id="bootstrap-data-table-export"
						class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Name</th>
								<th>Welfare Request</th>
								<th>Status</th>
								<th>Comment</th> 

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${welfare}" varStatus="current"
								var="response">
								<tr>
									<td>${response.name}</td>
									<td>${response.welfare}</td>
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
