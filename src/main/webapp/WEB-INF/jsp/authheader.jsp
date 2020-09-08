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
        <!-- Navbar User Details-->
        
        <a href="<c:url value = '/viewWeeklyOutline'/>">
			<button type = "button" class="btn btn-primary">View Weekly Outline</button></a>
			
		<a href="<c:url value = '/viewAnnouncement'/>">
			<button type = "button" class="btn btn-success">View Announcement</button></a>
			
		<a href="<c:url value = '/viewSocialEvent'/>">
			<button type = "button" class="btn btn-danger">View Social Events</button></a>
			
		<a href="<c:url value = '/centreDirectory'/>">
			<button type = "button" class="btn btn-warning">View Centre Directory</button></a>
			
		<a href="<c:url value = '/chat'/>">
			<button type = "button" class="btn btn-danger">Group Chat</button></a>
			
		<a href="<c:url value = '/liveStreaming'/>">
			<button type = "button" class="btn btn-danger">Online Services</button></a>
        
        
        <div class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
            <button class="btn btn-white" style="background-image: linear-gradient(45deg, #f1f1f1, #fff) !important;">
                <b>${loggedinuser.fullName} | ${loggedinuser.userRolesStr}</b>
            </button>
            <img width="40"
									src="data:image/png;base64,${loggedinuser.inputStreamStr}" />
        </div>
        <!-- Navbar-->
        <ul class="navbar-nav ml-auto ml-md-0">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                    <a class="dropdown-item" href="#">Settings</a>
                    <a class="dropdown-item" href="#">Activity Log</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="<c:url value = '/logout'/>">Logout</a>
                </div>
            </li>
        </ul>
    </nav>
    <div id="layoutSidenav">
        <div id="layoutSidenav_nav">
            <nav class="sb-sidenav accordion" id="sidenavAccordion" style="background-color: #45ca62 !important;">
                <div class="sb-sidenav-menu">
                    <div class="nav">
                        <li class="nav-item">
                            <a class="nav-link side-nav m-2" href="<c:url value = '/dashboard'/>">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Dashboard
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link side-nav collapsed m-2" href="#" data-toggle="collapse" data-target="#collapseMessages" aria-expanded="false" aria-controls="collapseMessages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                MESSAGING
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                        </li>
                        <div class="collapse" id="collapseMessages" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link side-nav m-1" href="<c:url value="/chat" />">Chat</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/sendSMS" />">Send SMS</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/sendMail" />">Send Mail</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/message" />">Send Messages</a>
                            </nav>
                        </div>
                        <li class="nav-item">
                            <a class="nav-link side-nav collapsed m-2" href="#" data-toggle="collapse" data-target="#collapseWelfare" aria-expanded="false" aria-controls="collapseWelfare">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                WELFARE
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                        </li>
                        <div class="collapse" id="collapseWelfare" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link side-nav m-1" href="<c:url value = '/welfareRequest'/>">Welfare Request</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/viewWelfareRequest" />">View Welfare Request</a>
                                <a class="nav-link side-nav m-1" href="<c:url value = '/incident'/>">Incident Request</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/viewIncident" />">View Incident Report</a>
                                <a class="nav-link side-nav m-1" href="<c:url value = '/whistleBlowing'/>">Whistle Blowing Request</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/viewWhistleBlowing" />">View Whistle Blowing Report</a>
                                <a class="nav-link side-nav m-1" href="<c:url value = '/district'/>">Create District</a>
                                <a class="nav-link side-nav m-1" href="<c:url value = '/zone'/>">Create Zone</a>
                                <a class="nav-link side-nav m-1" href="<c:url value = '/area'/>">Create Area</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/centre" />">Create Centre</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/viewCentre" />">View Centre</a>
                            </nav>
                        </div>
                        
                        <a class="nav-link collapsed side-nav m-2" href="#" data-toggle="collapse" data-target="#collapsePrayer" aria-expanded="false" aria-controls="collapsePrayer">
                            <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                            PRAYER
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapsePrayer" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link side-nav m-1" href="<c:url value="/prayerRequest" />">Prayer Request</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/viewPrayerRequest" />">View Prayer Request</a>
                            </nav>
                        </div>
                        
                        <a class="nav-link collapsed side-nav m-2" href="#" data-toggle="collapse" data-target="#collapseAnnouncement" aria-expanded="false" aria-controls="collapseAnnouncement">
                            <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                            ANNOUNCEMENT
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseAnnouncement" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link side-nav m-1" href="<c:url value="/announcement" />">Create Announcement</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/viewAnnouncement" />">View Announcement</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/weeklyOutline" />">Create Weekly Outline</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/viewWeeklyOutline" />">View Weekly Outline</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/calendar" />">Create Event Calendar</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/getCalendarEvent" />">View Event Calendar</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/socialEvents" />">Social Event</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/viewSocialEvent" />">View Social Events</a>
                                
                                <a class="nav-link side-nav m-1" href="<c:url value="/liveStreaming" />">Streaming</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/centreDirectory" />">Centre Directory</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/training" />">Create Training</a>
                                
                                
                                <a class="nav-link side-nav m-1" href="<c:url value="/centreMembers" />">Centre Members</a>
                            </nav>
                        </div>
                        
                        <a class="nav-link collapsed side-nav m-2" href="#" data-toggle="collapse" data-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                            <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                            REPORTS
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link side-nav m-1" href="<c:url value="/weeklyReport" />">Weekly Report</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/getWeeklyReport" />">View Weekly Report</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/getCentreReport" />">View Centre Report</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/getAreaReport" />">View Area Report</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/getZoneReport" />">View Zonal Report</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/getDistrictReport" />">View District Report</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/directorReport" />">Director Report</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/getDirectorReport" />">View Director Report</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/getVisitorReport" />">View Visitors</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/documents" />">Create Legal Documents</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/viewLegalDocuments" />">View Legal Documents</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/testimony" />">View Testimony</a>
                            </nav>
                        </div>
                        
                        <a class="nav-link collapsed side-nav m-2" href="#" data-toggle="collapse" data-target="#collapseFinancials" aria-expanded="false" aria-controls="collapseFinancials">
                            <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                            FINANCIALS
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseFinancials" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <a class="nav-link side-nav m-1" href="<c:url value="/expenseRequest" />">Expense Request</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/viewExpenseRequest" />">View Expense</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/communityProject" />">Community Impact Project</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/viewCommunityProject" />">View Community Impact Project</a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/donation" />">Donation</a>
                            </nav>
                        </div>
                        
                        <a class="nav-link collapsed side-nav m-2" href="#" data-toggle="collapse" data-target="#collapseUsers" aria-expanded="false" aria-controls="collapseUsers">
                            <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                            USER MANAGEMENT
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapseUsers" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav">
                                <%-- <a class="nav-link side-nav m-1" href="<c:url value='/createuser'/>"><spring:message
											code="create.user.setting" /></a> --%>
                                <a class="nav-link side-nav m-1" href="<c:url value="/authoriseuser"/>"><spring:message
											code="authorise.user.setting" /></a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/edituser"/>"><spring:message
											code="edit.usertorole.setting" /></a>
                                <a class="nav-link side-nav m-1" href="<c:url value="/changePassword"/>">Change Password</a>
                            </nav>
                        </div>
                        
                      <!--   <a class="nav-link collapsed side-nav m-2" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                            <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                            Pages
                            <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                        </a>
                        <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-parent="#sidenavAccordion">
                            <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                        Authentication
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-parent="#sidenavAccordionPages">
                                    <nav class="sb-sidenav-menu-nested nav">
                                        <a class="nav-link" href="login.html">Login</a>
                                        <a class="nav-link" href="register.html">Register</a>
                                        <a class="nav-link" href="password.html">Forgot Password</a>
                                    </nav>
                                </div>
                                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#pagesCollapseError" aria-expanded="false" aria-controls="pagesCollapseError">
                                        Error
                                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                                    </a>
                                <div class="collapse" id="pagesCollapseError" aria-labelledby="headingOne" data-parent="#sidenavAccordionPages">
                                    <nav class="sb-sidenav-menu-nested nav">
                                        <a class="nav-link" href="401.html">401 Page</a>
                                        <a class="nav-link" href="404.html">404 Page</a>
                                        <a class="nav-link" href="500.html">500 Page</a>
                                    </nav>
                                </div>
                            </nav>
                        </div>
                        <div class="sb-sidenav-menu-heading" style="color: #fff;">USER MANAGEMENT</div>
                        <a class="nav-link side-nav m-2" href="charts.html">
                            <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                            Charts
                        </a>
                        <a class="nav-link side-nav m-2" href="tables.html">
                            <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                            Tables
                        </a>
                        <a class="nav-link side-nav m-2" href="change-password.html">
                            <div class="sb-nav-link-icon"><i class="fas fa-anchor"></i></div>
                            Change Password
                        </a> -->
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
</body>

</html>