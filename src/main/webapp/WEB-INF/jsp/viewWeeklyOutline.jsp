<%@ include file="authheader.jsp"%>


<div class="content mt-3">
	<!-- <div class="animated fadeIn"> -->
	<div class="row">

<div class="col-md-12">
			<div class="card">
				<div class="card-header">
					<h2><strong class="card-title">WEEKLY OUTLINES</strong></h2>
				</div>
			</div>
		</div>
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
					<a href="<c:url value='/downloadOutline-${response.outlineID}'/>">
					<button type="button" class="btn btn-success">Download Outline</button>
					</a>
					</c:if>
				</div>
			</div>
		</div>
</c:forEach>

	</div>
</div>
