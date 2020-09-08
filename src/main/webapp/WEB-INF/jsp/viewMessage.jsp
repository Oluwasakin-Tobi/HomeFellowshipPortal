<%@ include file="authheader.jsp"%>


<div class="content mt-3">
	<!-- <div class="animated fadeIn"> -->
	<div class="row">


		<div class="col-md-12">
			<div class="card">
			<a href="<c:url value = '/viewSentMessage'/>">
			<button type = "button" class="btn btn-primary">Sent Messages</button></a>
				<div class="card-header">
					<strong class="card-title">View Inbox Message(s)</strong>
				</div>
				<div class="card-body">
					<table id="bootstrap-data-table-export"
						class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Sender</th>
								<th>Message</th>
								<c:if test="${sent == 'No'}">
								<th></th>
								</c:if>

							</tr>
						</thead>
						<tbody>
							<c:forEach items="${message}" varStatus="current" var="response">
								<tr>
									<td>${response.sender} ON ${response.dateCreated}</td>
									<td>${response.message}</td>
									<c:if test="${sent == 'No'}">
									<td><a href="<c:url value = '/replyMessage-${response.sender}'/>">
											<button type="button" class="btn btn-primary">Reply</button>
									</a></td>
									</c:if>
									<%-- <td><button class="btn btn-primary custom-width" type="button"
															data-toggle="modal" data-target="#sendMessage${current.count}">Reply</button></td> --%>

								</tr>




								<div class="modal fade" id="#sendMessage${current.count}"
									tabindex="-1" role="dialog"
									aria-labelledby="authModalCenterTitle" aria-hidden="true">
									<div class="modal-dialog modal-dialog-centered modal-lg"
										role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h4 class="modal-title" id="authModalLongTitle">
													<b>REPLY MESSAGE</b>
												</h4>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>

											</div>

											<c:url var="createMessage" value="/createMessage" />
											<mvc:form method="POST" action="${createMessage}"
												enctype="multipart/form-data" id="demo-form2"
												modelAttribute="Message"
												class="form-horizontal form-label-left">
												<div class="modal-body">

													<input type="hidden" name="sendTo" path="sendTo"
														value="${response.sender}" />

													<div class="col-md-6">
														<label for="disabledTextInput">New Message</label>
														<textarea type="text" name="message" id="message"
															value="${request.message}" path="message" required
															class="form-control" autocomplete="off"></textarea>
													</div>
													<p>&nbsp;</p>
													<div class="modal-footer">
														<button type="button" class="btn btn-primary"
															data-dismiss="modal">Cancel</button>

														<button type="submit" class="btn btn-success">Reply</button>
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
</div>
