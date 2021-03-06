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
						<strong class="card-title">CREATE REPORT</strong>
					</div>
					<div class="card-body">
						<c:url var="weeklyReport" value="/weeklyReport" />
						<mvc:form method="POST" action="${weeklyReport}"
							enctype="multipart/form-data" id="demo-form2"
							modelAttribute="WeeklyReport"
							class="form-horizontal form-label-left">

						 	<div class="row">

								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
									
								<div class="col-md-6">
									<label for="disabledTextInput">Centre*</label>
										<select
											id="centre" name="centre" path="centre"
											class="form-control">
											<option value="">Select option</option>
											<option value="LOVE">LOVE</option>
											<option value="PEACE">PEACE</option>
											<option value="JOY">JOY</option>
											<option value="FAITH">FAITH</option>
										</select>
									<div class="has-error" style="color: red">
										<mvc:errors path="centre" class="help-inline" />
									</div>
								</div>

								<div class="col-md-6">
									<label class="bmd-label-floating">Centre Address*</label> <input
										class="form-control" type="text" name="centreAddress" id="centreAddress"
										value="${report.centreAddress}" path="centreAddress" required
										autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="centreAddress" class="help-inline" />
									</div>
								</div>

								<div class="col-md-6">
									<label for="disabledTextInput">Area*</label>
										<select
											id="area" name="area" path="area"
											class="form-control">
											<option value="">Select option</option>
											<option value="AREA1">AREA1</option>
											<option value="AREA2">AREA2</option>
											<option value="AREA3">AREA3</option>
										</select>
									<div class="has-error" style="color: red">
										<mvc:errors path="area" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Zone*</label>
										<select
											id="zone" name="zone" path="zone"
											class="form-control">
											<option value="">Select option</option>
											<option value="ZONE1">ZONE1</option>
											<option value="ZONE2">ZONE2</option>
											<option value="ZONE3">ZONE3</option>
										</select>
									<div class="has-error" style="color: red">
										<mvc:errors path="zone" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">District*</label>
										<select
											id="district" name="district" path="district"
											class="form-control">
											<option value="">Select option</option>
											<option value="DISTRICT1">DISTRICT1</option>
											<option value="DISTRICT2">DISTRICT2</option>
											<option value="DISTRICT3">DISTRICT3</option>
										</select>
									<div class="has-error" style="color: red">
										<mvc:errors path="district" class="help-inline" />
									</div>
								</div>
							</div>
							<br>
							<div class="row">

								<div class="col-md-6">
									<label for="disabledTextInput">Name of Leader*</label> <input
										class="form-control" type="text" name="leaderName"
										id="leaderName" value="${report.leaderName}"
										path="leaderName" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="leaderName" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Asst. Leader*</label> <input
										class="form-control" type="text" name="asstLeader"
										id="asstLeader" value="${report.asstLeader}"
										path="asstLeader" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="asstLeader" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Intern*</label> <input
										class="form-control" type="text" name="intern"
										id="intern" value="${report.intern}"
										path="intern" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="intern" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Worship Leader*</label> <input
										class="form-control" type="text" name="worshipLeader"
										id="worshipLeader" value="${report.worshipLeader}"
										path="worshipLeader" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="worshipLeader" class="help-inline" />
									</div>
								</div>
							</div>
							
							<div class="row">

								<div class="col-md-6">
									<label for="disabledTextInput">Topic of Outline</label> <input
										class="form-control" type="text" name="outlineTopic"
										id="outlineTopic" value="${report.outlineTopic}"
										path="outlineTopic" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="outlineTopic" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">No. of Male Present*</label> <input
										class="form-control" type="number" name="noOfMale"
										id="noOfMale" value="${report.noOfMale}"
										path="noOfMale" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfMale" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">No. of Female Present*</label> <input
										class="form-control" type="number" name="noOfFemale"
										id="noOfFemale" value="${report.noOfFemale}"
										path="noOfFemale" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="noOfFemale" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">No. of Children Present(0-13 years)*</label> <input
										class="form-control" type="text" name="childPresent"
										id="childPresent" value="${report.childPresent}"
										path="childPresent" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="childPresent" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">No. of Visitors*</label> <input
										class="form-control" type="number" name="visitors"
										id="visitors" value="${report.visitors}" ng-model="noOfVisitors"
										path="visitors" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="visitors" class="help-inline" />
									</div>
								</div>
							</div>

							<div class="row">

								<div class="col-md-6">
									<label for="disabledTextInput">Host Name</label> <input
										class="form-control" type="text" name="hostName"
										id="hostName" value="${report.hostName}"
										path="hostName" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="hostName" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Host Phone Number*</label> <input
										class="form-control" type="number" name="hostPhoneNo"
										id="hostPhoneNo" value="${report.hostPhoneNo}"
										path="hostPhoneNo" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="hostPhoneNo" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Host EMail</label> <input
										class="form-control" type="text" name="hostEmail"
										id="hostEmail" value="${report.hostEmail}"
										path="hostEmail" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="hostEmail" class="help-inline" />
									</div>
								</div>
								
								<div class="col-md-6">
									<label for="disabledTextInput">Total Offering</label> <input
										class="form-control" type="number" name="offering"
										id="offering" value="${report.offering}"
										path="offering" required autocomplete="off">
									<div class="has-error" style="color: red">
										<mvc:errors path="offering" class="help-inline" />
									</div>
								</div>
							</div> 
							
							
							
							<div>
                              <hr>
                            </div> 	
							
							<div class="col-md-12 col-sm-12 col-xs-12 fields1">
 							<div class="form-group">
                                 <label class="control-label col-md-4 col-sm-3 col-xs-12">&nbsp;
                                     </label>
                                     <div class="col-md-8 col-sm-6 col-xs-12">
                                       <button class="add_field1 btn btn-xs btn-warning"><i class="fa fa-plus"></i> Add Testimony</button>
                                            </div>
                                    
                            </div>
                            
                             <c:forEach items="${report.testifierNames}" varStatus="current" var="detail1">								
						      <c:if test="${(current.count-1) > 0}">
						      <div class="form-section">

							<div class="form-group col-md-6">
									<label class="bmd-label-floating">Testifier's Name*</label> <input
										class="form-control" type="text" name="testifierNames" id="testifierNames"
										value="${detail1}" path="testifierNames" required
										autocomplete="off">
								</div>

								<div class="form-group col-md-6">
									<label class="bmd-label-floating">Testimony Topic*</label> <input
										class="form-control" type="text" name="testimonyTopics" id="testimonyTopics"
										value="${report.testimonyTopics[current.count-1]}" path="testimonyTopics" required
										autocomplete="off">
								</div>

								<div class="form-group col-md-6">
									<label class="bmd-label-floating">Testimony*</label> <input
										class="form-control" type="text" name="testimonys"
										id="testimonys" value="${report.testimonys[current.count-1]}"
										path="testimonys" required autocomplete="off">
								</div>
								
								<div>
                              <hr>
                            </div>
							  <div class="col-md-4">&nbsp;</div><a style="margin-left:10px;" href="#" class="remove_field1 btn btn-danger btn-xs"> <i class="fa fa-minus"></i> Remove Testimony</a>
							 </div> 
						</c:if> 
			</c:forEach> 
			</div>
							
							<div>
                              <hr>
                            </div> 
							
							<div ng-show="noOfVisitors != ''">
							<div class="col-md-12 col-sm-12 col-xs-12 fields">
 							<div class="form-group" ng-show="noOfVisitors != '0'">
                                 <label class="control-label col-md-4 col-sm-3 col-xs-12">&nbsp;
                                     </label>
                                     <div class="col-md-8 col-sm-6 col-xs-12">
                                       <button class="add_field btn btn-xs btn-warning"><i class="fa fa-plus"></i> Add Visitor's Detail</button>
                                            </div>
                                    
                            </div>
                            
                             <c:forEach items="${report.visitorNames}" varStatus="current" var="detail">								
						      <c:if test="${(current.count-1) > 0}">
						      <div class="form-section">

							<div class="form-group col-md-6">
									<label class="bmd-label-floating">Visitor's Name*</label> <input
										class="form-control" type="text" name="visitorNames" id="visitorNames"
										value="${detail}" path="visitorNames" required
										autocomplete="off">
								</div>

								<div class="form-group col-md-6">
									<label class="bmd-label-floating">Visitor's Phone No*</label> <input
										class="form-control" type="number" name="visitorPhoneNos" id="visitorPhoneNos"
										value="${report.visitorPhoneNos[current.count-1]}" path="visitorPhoneNos" required
										autocomplete="off">
								</div>

								<div class="form-group col-md-6">
									<label class="bmd-label-floating">Visitor's Email*</label> <input
										class="form-control" type="text" name="visitorEmails"
										id="visitorEmails" value="${report.visitorEmails[current.count-1]}"
										path="visitorEmails" required autocomplete="off">
								</div>
								
								 <div class="form-group col-md-6">
										<label for="exampleFormControlSelect1">Visitor's Gender*</label> <select
											id="visitorGenders" name="visitorGenders" path="visitorGenders"
											class="form-control">
											<option value="${report.visitorGenders[current.count-1]}">Select option</option>
											<option value="MALE">MALE</option>
											<option value="FEMALE">FEMALE</option>
										</select>
									</div> 
								
								<div>
                              <hr>
                            </div>
							  <div class="col-md-4">&nbsp;</div><a style="margin-left:10px;" href="#" class="remove_field btn btn-danger btn-xs"> <i class="fa fa-minus"></i> Remove Visitor's detail</a>
							 </div> 
						</c:if> 
			</c:forEach> 
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

<script src="<c:url value='/js/kdynamicfields.js' />"></script>
<!-- jQuery -->
	<script src="<c:url value='/vendors/jquery/dist/jquery.min.js' />"
		type="text/javascript"></script>
	<!-- Bootstrap -->
	<%-- <script
		src="<c:url value='/vendors/bootstrap/dist/js/bootstrap.min.js' />"
		type="text/javascript"></script> --%>
		
    <script>
        $(document).ready(function() {
            var max_fields = 50; //maximum input field allowed
            var fields = $(".fields"); //Fields
            var add_button = $(".add_field"); //Add button
			var remove_button = $(".remove_field");
            var x = 1; //initlal text box count
            $(add_button).on('click',(function(e) { //on add input button click
                e.preventDefault(); //undo event
                if (x < max_fields) { //max input box allowed
                    x++; //text box increment
                    $(fields).append('<div> <hr> </div> <div class="form-section">'+
				'<div class="form-group col-md-6"> <hr />'+
							'<label class="bmd-label-floating">'+
								'Visitor\'s Name*</label>'+
								'<input class="form-control" type="text" name="visitorNames" id="visitorNames"'+
									' path="visitorNames" '+
									'required autocomplete="off">'+
							'</div>'+
						'<div>&nbsp;</div>'+
						'<div class="form-group col-md-6">'+
						'<label class="bmd-label-floating">'+
							'Visitor\'s Phone No*</label>'+
							'<input class="form-control" type="number" name="visitorPhoneNos" id="visitorPhoneNos"'+
								' path="visitorPhoneNos" '+
								'required autocomplete="off">'+
						'</div>'+
						'<div class="form-group col-md-6">'+
						'<label class="bmd-label-floating">'+
							'Visitor\'s Email*</label>'+
							'<input class="form-control" type="text" name="visitorEmails" id="visitorEmails"'+
								' path="visitorEmails" '+
								'required autocomplete="off">'+
						'</div>'+
						'<div class="form-group col-md-6">'+
						'<label for="exampleFormControlSelect1">'+
							'Visitor\'s Gender*</label>'+
							'<select id="visitorGenders" name="visitorGenders" path="visitorGenders" class="form-control">'+
								'<option value="">Select option</option>'+
								'<option value="MALE">MALE</option>'+
								'<option value="FEMALE">FEMALE</option>'+
								'</select>'+
						'</div>'+
						'<br> <div class="col-md-4">&nbsp;</div><a style="margin-left:10px;" href="#" class="remove_field btn btn-danger btn-xs"> <i class="fa fa-minus"></i> Remove Visitor\'s detail</a>'+
							'</div>'); //add input box
                }
            }));

            $(fields).on("click", ".remove_field", function(e) { //user click on remove text
                e.preventDefault();
               $(this).parent('div').remove();
                x--;
            })
        });
            
</script>


<script>
        $(document).ready(function() {
            var max_fields = 50; //maximum input field allowed
            var fields = $(".fields1"); //Fields
            var add_button = $(".add_field1"); //Add button
			var remove_button = $(".remove_field1");
            var x = 1; //initlal text box count
            $(add_button).on('click',(function(e) { //on add input button click
                e.preventDefault(); //undo event
                if (x < max_fields) { //max input box allowed
                    x++; //text box increment
                    $(fields).append('<div> <hr> </div> <div class="form-section">'+
				'<div class="form-group col-md-6"> <hr />'+
							'<label class="bmd-label-floating">'+
								'Testifier\'s Name*</label>'+
								'<input class="form-control" type="text" name="testifierNames" id="testifierNames"'+
									' path="testifierNames" '+
									'required autocomplete="off">'+
							'</div>'+
						'<div>&nbsp;</div>'+
						'<div class="form-group col-md-6">'+
						'<label class="bmd-label-floating">'+
							'Testimony Topic*</label>'+
							'<input class="form-control" type="text" name="testimonyTopics" id="testimonyTopics"'+
								' path="testimonyTopics" '+
								'required autocomplete="off">'+
						'</div>'+
						'<div class="form-group col-md-6">'+
						'<label class="bmd-label-floating">'+
							'Testimony*</label>'+
							'<input class="form-control" type="text" name="testimonys" id="testimonys"'+
								' path="testimonys" '+
								'required autocomplete="off">'+
						'</div>'+
						'<br> <div class="col-md-4">&nbsp;</div><a style="margin-left:10px;" href="#" class="remove_field1 btn btn-danger btn-xs"> <i class="fa fa-minus"></i> Remove Testimony</a>'+
							'</div>'); //add input box
                }
            }));

            $(fields).on("click", ".remove_field1", function(e) { //user click on remove text
                e.preventDefault();
               $(this).parent('div').remove();
                x--;
            })
        });
            
</script>




<script src="<c:url value='/js/app.js' />"></script>