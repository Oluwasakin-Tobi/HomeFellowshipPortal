<%@ include file="authheader.jsp" %>

<div class="content mt-3">
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
                        <strong class="card-title">EDIT USER DETAILS</strong>
                    </div>
                       <div class="card-body" >
                       
                       <c:url var="edituserdetails" value="/edituserdetails" />
							<mvc:form method="POST" action="${edituserdetails}"
								enctype="multipart/form-data" id="demo-form2"
								modelAttribute="editUserDetails"
								class="form-horizontal form-label-left">

                        <div class="row">

<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />


                            <div class="col-md-6">
                                <label class="bmd-label-floating">UserName</label>
                                <input class="form-control" type="text" name="userName" id="userName"
										value="${user.userName}" path="userName" 
										readonly required>
                            </div>
                            
                            <input type="hidden" name="userID" id="userID"  value="${user.userID}" path="userID"/>

                            <div class="col-md-6">
                                <label for="disabledTextInput">Full Name</label>
                                <input class="form-control" type="text" name="userFullName"
										value="${user.userFullName}" path="userFullName" required autocomplete="off">
                            </div>
                        </div>
                        <br>
                        <div class="row">

                            <div class="col-md-6">
                                <label for="disabledTextInput">Email Address</label>
                                <input class="form-control" type="email" name="userEmailAdd"
										value="${user.userEmailAdd}" path="userEmailAdd" required autocomplete="off">
                            </div>
                           <c:choose>
                           <c:when test="${userRole=='ADMIN'}">
                           
                            <div class="form-group col-md-6">
                            <label for="userBranch">Role</label>
                                <select id="userRoles" name="userRoles" path="userRoles"
										class="form-control" required >
										 <option value="${user.userRoles}">${user.userRoles}</option>
										<c:forEach items="${userroles}" varStatus="current"
											var="userrole">
											<option value="${userrole.roleName}">${userrole.roleName}</option>
										</c:forEach> ${selectedUserrole}
										</select>
                        </div> 
                            </c:when>
                        <c:otherwise>
                        
                        <div class="col-md-6">
                                <label for="disabledTextInput">Role</label>
                                <input class="form-control" type="text" name="userRoles"
										value="${user.userRoles}" path="userRoles" required readonly autocomplete="off">
                            </div>
                         
                        </c:otherwise>
                        </c:choose> 
                    </div> 
                    
                    
                    <br>
                    
                    <a href="<c:url value='/dashboard'/>"><button type="button" class="btn btn-primary">Cancel</button></a>
                    <button type="reset" class="btn btn-primary">Reset</button>
                    <button type="submit" class="btn btn-success">Submit</button>

                </mvc:form>



            </div>



            </div>
        </div>
    </div>


</div>
</div><!-- .animated -->