<%@ include file="authheader.jsp"%>

<main  ng-app="myModule">
<div class="container-fluid">
	<h1 class="mt-4">Donations</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value = '/dashboard'/>">Dashboard</a></li>
		<li class="breadcrumb-item active">Donations</li>
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
				<strong class="card-title">DONATIONS</strong>
			</div>
			<div class="card-body">
				<c:url var="welfareRequest" value="/welfareRequest" />
						<mvc:form method="POST" action="${welfareRequest}"
							enctype="multipart/form-data" id="demo-form2"
							modelAttribute="Welfare"
							class="form-horizontal form-label-left">

							<div class="row">

								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

								<div class="col-md-6">
									<label class="bmd-label-floating">E-mail*</label> <input
										class="form-control" type="text" name="email" id="email"
										value="${loggedinuser.email}" required
										autocomplete="off">
								</div>
								
								<div class="col-md-6">
									<label class="bmd-label-floating">Amount*</label> <input
										class="form-control" type="number" name="amount" id="amount"
										value="${request.welfare}" required
										autocomplete="off">
								</div>

							</div>
							<br>
							<div class="row">

								<div class="col-md-6">
									<label class="bmd-label-floating">Phone Number*</label> <input
										class="form-control" type="text" name="phoneNo" id="phoneNo"
										value="${request.welfare}" required
										autocomplete="off">
								</div>
								
								<div class="col-md-6">
									<label class="bmd-label-floating">Donation for*</label> <textarea
										class="form-control" type="text" name="donation" id="donation"
										value="${request.welfare}" required
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



			</div>
		</div>
	</div>

</div>
</main>

<%@ include file="footer.jsp"%>
<script src="<c:url value='/js/app.js' />"></script>

  <script src="https://js.paystack.co/v1/inline.js"></script>
 
<script>
  function payWithPaystack(){
	  alert($('#amount').val());
    var handler = PaystackPop.setup({
      key: 'pk_test_a18006072cbe9cecb2fa9ed3358553a696849cda',
      email: $('#email').val(),
      amount: $('#amount').val()*100,
      currency: "NGN",
      /* ref: ''+Math.floor((Math.random() * 1000000000) + 1),  */// generates a pseudo-unique reference. Please replace with a reference you generated. Or remove the line entirely so our API will generate one for you
      metadata: {
         custom_fields: [
            {
                display_name: "Mobile Number",
                variable_name: "mobile_number",
                value: $('#phoneNo').val()
            }
         ]
      },
      callback: function(response){
          alert('success. transaction ref is ' + response.reference);
      },
      onClose: function(){
          alert('window closed');
      }
    });
    handler.openIframe();
  }
</script>