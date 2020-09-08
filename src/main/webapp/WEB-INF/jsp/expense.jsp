<%@ include file="authheader.jsp"%>

<main ng-app="myModule">
<div class="container-fluid">
	<h1 class="mt-4">Expense Request</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value = '/dashboard'/>">Dashboard</a></li>
		<li class="breadcrumb-item active">Expense Request</li>
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
				<strong class="card-title">EXPENSE REQUEST</strong>
			</div>
			<div class="card-body">
				<c:url var="expenseRequest" value="/expenseRequest" />
				<mvc:form method="POST" action="${expenseRequest}"
					enctype="multipart/form-data" id="demo-form2"
					modelAttribute="Expenses" class="form-horizontal form-label-left">

					<div class="row">

						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<div class="col-md-6">
							<label class="bmd-label-floating">Name*</label> <input
								class="form-control" type="text" name="name" id="name"
								value="${loggedinuser.adUsername}" path="name" required readonly
								autocomplete="off">
						</div>

					</div>
					<br>
					<div class="row">

						<div class="col-md-6">
							<label class="bmd-label-floating">Expense Request*</label>
							<textarea class="form-control" type="text" name="expense"
								id="expense" value="${request.expense}" path="expense" required
								autocomplete="off"></textarea>
						</div>

						<div class="col-md-6">
							<label class="bmd-label-floating">Amount*</label> <input
								class="form-control" type="text" name="amountStr" id="amountStr"
								value="${request.amountStr}" path="amountStr" required
								autocomplete="off">
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



			</div>
		</div>
	</div>

</div>
</main>

<%@ include file="footer.jsp"%>
<script src="<c:url value='/js/app.js' />"></script>