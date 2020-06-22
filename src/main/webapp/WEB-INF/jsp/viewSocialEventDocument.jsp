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
					<h2><strong class="card-title">SOCIAL EVENTS - ${topic}</strong></h2>
				</div>
			</div>
			
			<c:forEach items="${socialEventDoc}" varStatus="current"
                           var="response">
                    <div class="span12">
                        <BR>
                    </div>
                    <BR>
                    <div class="span12">
                    
                    <c:choose>
                    <c:when test="${response.filetype == 'video/mp4'}">
                    	<video width="400" controls>
  							<source type="video/mp4" src="data:video/mp4;base64,${response.inputStreamStr}">
						</video>
                    </c:when>
                    <c:when test="${response.filetype == 'audio/mpeg'}">
                    	<audio controls src="data:audio/ogg;base64,${response.inputStreamStr}">
                    	</audio>
                    </c:when>
                    <c:otherwise>
                    	<img width ="400" src="data:image/png;base64,${response.inputStreamStr}"/>
                    </c:otherwise>
                    </c:choose>
                        
                        
                    </div>
                    <div class="span12">
                        <BR>
                        =============================================================
                    </div>
                </c:forEach>

		</div>

	</div>
</div>
