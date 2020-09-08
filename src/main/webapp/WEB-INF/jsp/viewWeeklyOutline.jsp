<%@ include file="authheader.jsp"%>

<main> 


<div class="container-fluid">
	<h1 class="mt-4">View Weekly Outline</h1>
	<ol class="breadcrumb mb-4">
		<li class="breadcrumb-item"><a
			href="<c:url value='/dashboard' />">Dashboard</a></li>
		<li class="breadcrumb-item active">View Weekly Outline</li>
	</ol>

	<div class="col-md-12">
		<div class="card shadow">
			<div class="card-header">
				<strong class="card-title">View Weekly Outline</strong>
			</div>
			<div class="card-body">
							<c:forEach items="${weeklyOutline}" varStatus="current"
								var="response">
		<div class="col-md-12">
			<div class="card">
				<div class="card-header">
					<strong class="card-title"><b>${response.topic}</b> - On ${response.dateCreated}</strong>
				</div>
				<div class="card-body">
					${response.outline}
					<c:if test="${response.uploadFlag == 'Y'}">
					<%-- <a href="<c:url value='/downloadOutline-${response.outlineID}'/>" >
					<button type="button"  class="btn btn-success">View Outline</button>
					</a> --%>
					
					<c:url var="previewDocURL" value="/preview" />
										<mvc:form method="POST" target="_blank"
											action="${previewDocURL}" modelAttribute="filter" class="download">
											<input name="value" path="value"
												type="hidden" value="${response.outlineID}">
											<button type="submit" class="btn btn-primary btn-xs"
												style="width: 90px;">
												<i class="fa fa-search"></i> Preview....
											</button>
										</mvc:form>
					</c:if>
				</div>
			</div>
		</div>
</c:forEach>
			</div>
		</div>
	</div>

</div>
</main>

<%@ include file="footer.jsp"%>