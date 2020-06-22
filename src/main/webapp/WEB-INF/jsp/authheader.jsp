
<!doctype html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html class="no-js" lang="en">
<!--<![endif]-->

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>HOME FELLOWSHIP -</title>
<meta name="description" content="Sufee Admin - HTML5 Admin Template">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon" href="apple-icon.png">
<link rel="shortcut icon" href="favicon.ico">

<link rel="stylesheet" type="text/css"
	href="<c:url value='/vendors/bootstrap/dist/css/bootstrap.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/vendors/font-awesome/css/font-awesome.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/vendors/themify-icons/css/themify-icons.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/vendors/flag-icon-css/css/flag-icon.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/vendors/selectFX/css/cs-skin-elastic.css'/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value='/vendors/datatables.net-bs4/css/dataTables.bootstrap4.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/vendors/datatables.net-buttons-bs4/css/buttons.bootstrap4.min.css'/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/css/style.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/css/robot_tff.css'/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/css/font_awesome4.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/css/material-dashboard.css?v=2.1.1" rel="stylesheet'/>">
	
<link rel="stylesheet" href="<c:url value = '/css/main.css'/>" />

<!-- <link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800'
	rel='stylesheet' type='text/css'> -->

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

<body>


	<!-- Left Panel -->

	<!-- Left Panel -->


	<aside id="left-panel" class="left-panel">
		<nav class="navbar navbar-expand-sm navbar-default">

			<div class="navbar-header">
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#main-menu" aria-controls="main-menu"
					aria-expanded="false" aria-label="Toggle navigation">
					<i class="fa fa-bars"></i>
				</button>
				<!-- <a class="navbar-brand" href="./"><img
					src="images/ecoAriba.jpeg" width="150" height="94"
					style="box-shadow: 2px 2px 6px #c1c1c1; margin-bottom: 10px;"
					alt="Logo"></a> -->

			</div>

			<div id="main-menu" class="main-menu collapse navbar-collapse"
				style="height: 140vh !important; margin-top: -344px !important;">
				<ul class="nav navbar-nav">
					<li class="active"><a href="<c:url value='/dashboard'/>">
							<i class="menu-icon fa fa-dashboard"></i>Dashboard
					</a></li>

					<h3 class="menu-title"></h3>
					<!-- /.menu-title -->
					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-book"></i>Report
					</a>
						<ul class="sub-menu children dropdown-menu">
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/weeklyReport" />">Weekly Report</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/getWeeklyReport" />">View Weekly Report</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/monthlyReport" />">Monthly Report</a></li>
							
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/directorReport" />">Director Report</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/getDirectorReport" />">View Director Report</a></li>
						</ul></li>
						
					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-book"></i>MESSAGES
					</a>
						<ul class="sub-menu children dropdown-menu">
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/chat" />">Chat</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/sendSMS" />">Send SMS</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/sendMail" />">Send Mail</a></li>
						</ul></li>
						
						<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-book"></i>WELFARE
					</a>
						<ul class="sub-menu children dropdown-menu">
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/welfareRequest" />">Welfare Request</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/viewWelfareRequest" />">View Welfare Request</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/incident" />">Incident Report</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/viewIncident" />">View Incident Report</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/whistleBlowing" />">Whistle Blowing Report</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/viewWhistleBlowing" />">View Whistle Blowing Report</a></li>
						</ul></li>
						
						<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-book"></i>PRAYER
					</a>
						<ul class="sub-menu children dropdown-menu">
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/prayerRequest" />">Prayer Request</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/viewPrayerRequest" />">View Prayer Request</a></li>
						</ul></li>
						
						<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-book"></i>ANNOUNCEMENT
					</a>
						<ul class="sub-menu children dropdown-menu">
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/announcement" />">Create Announcement</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/viewAnnouncement" />">View Announcement</a></li>
									
							
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/viewWeeklyOutline" />">View Weekly Outline</a></li>
									
							
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/getCalendarEvent" />">View Event Calendar</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/socialEvents" />">Social Event</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/viewSocialEvent" />">View Social Events</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/liveStreaming" />">Streaming</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/centreDirectory" />">Centre Directory</a></li>
						</ul></li>
						
						<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-book"></i>FINANCIALS
					</a>
						<ul class="sub-menu children dropdown-menu">
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/expenseRequest" />">Expense Request</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/viewExpenseRequest" />">View Expense</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/communityProject" />">Community Impact Project</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/viewCommunityProject" />">View Community Impact Project</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/donation" />">Donation</a></li>
						</ul></li>
						
						
						<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-book"></i>DOCUMENT MANAGEMENT
					</a>
						<ul class="sub-menu children dropdown-menu">
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/weeklyOutline" />">Create Weekly Outline</a></li>
									
							<li><i class="fa fa-book"></i><a
									href="<c:url value="/calendar" />">Create Event Calendar</a></li>
						</ul></li>

					<sec:authorize access="hasRole('ADMIN') or hasRole('CUSTOM')">
						<h3 class="menu-title">USER MANAGEMENT</h3>
						<!-- /.menu-title -->
						<li class="menu-item-has-children dropdown"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <i
								class="menu-icon fa fa-users"></i>User Settings
						</a>
							<ul class="sub-menu children dropdown-menu">
								<li><i class="fa fa-puzzle-piece"></i><a
									href="<c:url value='/createuser'/>"><spring:message
											code="create.user.setting" /></a></li>
											
								<li><i class="fa fa-puzzle-piece"></i><a
									href="<c:url value='/authoriseuser'/>"><spring:message
											code="authorise.user.setting" /></a></li>
								
								<li><i class="fa fa-id-badge"></i><a
									href="<c:url value='/edituser'/>"><spring:message
											code="edit.usertorole.setting" /></a></li>
											
								<li><i class="fa fa-id-badge"></i><a
									href="<c:url value='/changePassword'/>">Change Password</a></li>

							</ul></li>
					</sec:authorize>
					
					<sec:authorize access="hasRole('MEMBER')">
						<h3 class="menu-title">USER MANAGEMENT</h3>
						<!-- /.menu-title -->
						<li class="menu-item-has-children dropdown"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <i
								class="menu-icon fa fa-users"></i>User Settings
						</a>
							<ul class="sub-menu children dropdown-menu">
								
								<li><i class="fa fa-id-badge"></i><a
									href="<c:url value='/edituserdetails'/>"><spring:message
											code="edit.usertorole.setting" /></a></li>
											
								<li><i class="fa fa-id-badge"></i><a
									href="<c:url value='/changePassword'/>">Change Password</a></li>

							</ul></li>
					</sec:authorize>

					<h3 class="menu-title"></h3>
					<!-- /.menu-title -->
					<li class=""><a class="nav-link"
						href="<c:url value ='/logout'/>"><i
							class="menu-icon fa fa-power-off"></i> Logout</a></li>

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
	</aside>
	<!-- /#left-panel -->
	<!-- Left Panel -->

	<!-- Right Panel -->

	<div id="right-panel" class="right-panel">

		<!-- Header-->
		<header id="header" class="header">

			<div class="header-menu">

				<div class="col-sm-7">
					<a id="menuToggle" class="menutoggle pull-left"><i
						class="fa fa fa-tasks"></i></a>

				</div>



				<div class="col-sm-5">

					<div class="user-area dropdown float-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <!-- <img class="user-avatar rounded-circle" src="images/4.png" alt="User Avatar"> -->
							<button class="btn"
								style="background-image: linear-gradient(-45deg, #00578a, #9496a1) !important;">
								<b>${loggedinuser.fullName} | ${loggedinuser.userRolesStr}</b>
							</button>
						</a>


					</div>

					<div class="language-select dropdown" id="language-select">
						<a class="dropdown-toggle" href="#" data-toggle="dropdown"
							id="language" aria-haspopup="true" aria-expanded="true"> </a>

					</div>

				</div>
			</div>

		</header>
		<!-- /header -->
		<!-- Header-->


		<!--</div> /#right-panel -->

		<!-- Right Panel -->


		<script src="<c:url value='/vendors/jquery/dist/jquery.min.js'/>"></script>
		<script
			src="<c:url value='/vendors/popper.js/dist/umd/popper.min.js'/>"></script>
		<script
			src="<c:url value='/vendors/bootstrap/dist/js/bootstrap.min.js'/>"></script>
		<script src="<c:url value='/assets/js/main.js'/>"></script>

		<script src="<c:url value='/js/jquery.js' />"></script>
		<script src="<c:url value='/js/angular.min.js' />"></script>


		<script
			src="<c:url value='/vendors/datatables.net/js/jquery.dataTables.min.js'/>"></script>
		<script
			src="<c:url value='/vendors/datatables.net-bs4/js/dataTables.bootstrap4.min.js'/>"></script>
		<script
			src="<c:url value ='/vendors/datatables.net-buttons/js/dataTables.buttons.min.js'/>"></script>
		<script
			src="<c:url value='/vendors/datatables.net-buttons-bs4/js/buttons.bootstrap4.min.js'/>"></script>
		<script src="<c:url value='/vendors/jszip/dist/jszip.min.js'/>"></script>
		<script src="<c:url value='/vendors/pdfmake/build/pdfmake.min.js'/>"></script>
		<script src="<c:url value='/vendors/pdfmake/build/vfs_fonts.js'/>"></script>
		<script
			src="<c:url value='/vendors/datatables.net-buttons/js/buttons.html5.min.js'/>"></script>
		<script
			src="<c:url value='/vendors/datatables.net-buttons/js/buttons.print.min.js'/>"></script>
		<script
			src="<c:url value='/vendors/datatables.net-buttons/js/buttons.colVis.min.js'/>"></script>
		<script
			src="<c:url value='/assets/js/init-scripts/data-table/datatables-init.js'/>"></script>
		<script>
			(function($) {
				"use strict";

				jQuery('#vmap').vectorMap({
					map : 'world_en',
					backgroundColor : null,
					color : '#ffffff',
					hoverOpacity : 0.7,
					selectedColor : '#1de9b6',
					enableZoom : true,
					showTooltip : true,
					values : sample_data,
					scaleColors : [ '#1de9b6', '#03a9f5' ],
					normalizeFunction : 'polynomial'
				});
			})(jQuery);
		</script>
</body>

</html>
