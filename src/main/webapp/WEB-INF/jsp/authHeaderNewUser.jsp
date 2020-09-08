<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Dashboard - SB Admin</title>
    <link href="css/styles.css" rel="stylesheet" />
    <link href="css/global.css" rel="stylesheet" />
    <link href="css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
    <script src="<c:url value='js/all.min.js'/>" crossorigin="anonymous"></script>
    
    
<script type="text/javascript">
	<script type="text/javascript">
	function reolveLocale(locale) {
		$.ajax({
			type : "GET",
			url : "${pageContext.request.contextPath}/?lang=" + locale,
			success : function(resp) {
				window.location.reload();
			},
			error : function(xhr, status, text) {
				return;
			}
		});
	}

	function selectAll() {
		$(".checker-exec").prop("checked", true); //input-checkboxx
		$(".input-checkboxx").prop("value", "true");
	}

	var times = 0;

	var one = function() {
		$(".checker-exec").prop("checked", true); //input-checkboxx
		$(".input-checkboxx").prop("value", "true");

		document.getElementById("selectAll").innerHTML = "UnSelect All";
	}

	var two = function() {
		$(".checker-exec").prop("checked", false); //input-checkboxx
		$(".input-checkboxx").prop("value", "false");

		document.getElementById("selectAll").innerHTML = "Select All";
	}

	var decideFunction = function() {
		if (times == 0) {
			one();
			times++;
		} else {
			two();
			times--;
		}
	}

	function validate(e) {

		var f = $(e);
		var form = f.closest("form");

		console.log(form);
		console.log($(form));

		for (var i = 0; i < form[0].length; i++) {
			if (form[0][i].type == 'hidden' || form[0][i].required == false
					|| form[0][i].localName == 'button') {
				continue;
			}
			if (form[0][i].value == null || form[0][i].value == undefined
					|| form[0][i].value.length < 1) {
				alert("please fill the required fields");
				return;
			}
		}

		f.prop('disabled', true);
		f.html("Please wait...");
		form.submit();

	}

	/* function noBack(){window.history.forward()} 
	 noBack(); 
	 window.onload=noBack; 
	 window.onpageshow=function(evt){if(evt.persisted)noBack()} 
	 window.onunload=function(){void(0)} */
	//window.oncontextmenu = function() {return false;} 
	function handleCheckboxChange(checkbox, id) {
		if (checkbox.checked == true) {
			document.getElementById(id).value = "true";
		} else {
			document.getElementById(id).value = "false";
		}
	}

	$(document).ready(function() {
		var table = $('#datatablenobutton').DataTable;
		//table.buttons( '.export' ).remove();

	});
</script>
</head>

<body class="sb-nav-fixed">
    <nav class="sb-topnav navbar navbar-expand shadow" style="background-color: #fff;">
        <img src="assets/img/HORock.png" class="navbar-brand" href="index.html" />
        <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#" style="background-color: #fff; color: #45ca62;"><i class="fas fa-bars"></i></button>
        
    </nav>
    
     <div id="layoutSidenav">
        <div id="layoutSidenav_nav">
            <nav class="sb-sidenav accordion" id="sidenavAccordion" style="background-color: #45ca62 !important;">
                <div class="sb-sidenav-menu">
                    <div class="nav">
                        <li class="nav-item">
                            <a class="nav-link side-nav m-2" href="<c:url value = '/login'/>">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Login
                            </a>
                        </li>
                    </div>
                </div>
            </nav>
        </div>
      
    </div>
    
    <div id="layoutSidenav">
    <div id="layoutSidenav_content">
    
            
    <script src="js/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
    <script src="js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
    <script src="js/Chart.min.js" crossorigin="anonymous"></script>
    <script src="assets/demo/chart-area-demo.js"></script>
    <script src="assets/demo/chart-bar-demo.js"></script>
    <script src="js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
    <script src="js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
    <script src="assets/demo/datatables-demo.js"></script>
    
    <script src="<c:url value='/js/angular.min.js' />"></script>
    <%-- <script src="<c:url value='/js/main.js'/>"></script> --%>
</body>

</html>