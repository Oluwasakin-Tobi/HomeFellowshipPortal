package com.portal.homeFellowship;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.portal.homeFellowship.model.*;
import com.portal.homeFellowship.service.*;
import com.portal.homeFellowship.utility.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
// @RequestMapping("/admin")
public class WelcomeController {

	static final Logger LOGGER = LoggerFactory.getLogger(WelcomeController.class);
	final static DateFormat MARSHARLLERDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
	final static ObjectMapper MAPPER = new ObjectMapper();

	private static final Random RANDOMGENERATOR = new Random();
	@Autowired
	@Lazy(true)
	AuthenticationTrustResolver authenticationTrustResolver;
	@Autowired
	MessageSource messageSource;
	@Autowired
	Environment environment;
	@Autowired
	AdminService adminService;
	@Autowired
	InquiryService inquiryService;
	@Autowired
	AuditService auditService;
	@Autowired
	private UserAdmin user;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	HttpServletRequest request;
	@Autowired
	RestTemplate restTemplate;

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private UserAdmin getPrincipal() {
		return user;
	}

	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) throws Exception {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests. If users is already logged-in and
	 * tries to goto login page again, will be redirected to dashboard page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		LOGGER.info("login here");
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		} else {
			return "redirect:/dashboard";
		}
		// return "redirect:/dashboard";
	}

	/**
	 * This method handles logout requests. Toggle the handlers if you are
	 * RememberMe functionality is useless in your app.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			// persistentTokenBasedRememberMeServices.logout(request, response,
			// auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	/**
	 * This method returns true if users is not already authenticated [logged-in],
	 * else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	/**
	 * This method returns true if users is global admin else false.
	 */
	private boolean isPrincipalGlobalAdmin() {
		for (UserProfileAdmin userProfile : getPrincipal().getUserProfiles())
			if ("ADMIN".equalsIgnoreCase(userProfile.getType()) || "GROUPADMIN".equalsIgnoreCase(userProfile.getType()))
				return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/createuser" }, method = RequestMethod.GET)
	public String createUser(ModelMap model) throws Exception {

		//model.addAttribute("loggedinuser", getPrincipal());

		List<Role> userroles = inquiryService.getroles();
		model.addAttribute("userroles", userroles);

		return "createuser";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/loadcreateuser" }, method = RequestMethod.POST)
	public String loadCreateUser(@Valid UserDetails userDetails, RedirectAttributes redirectAttributes,
			ModelMap model) throws Exception {
		LOGGER.info("** loadcreateuser ==> " + userDetails + " **");
		
		
		HttpSession session = request.getSession();
		  String sessionId = session.getId();
		  LOGGER.info("otp 2" + userDetails.getOtp());
		  Otp req = new Otp();
		  req.setSessionID(sessionId);
		  LOGGER.info("otp 3" + userDetails.getOtp());
		List<Otp> response = inquiryService.getOTP(req);

		LOGGER.info("response " + response);
		
		if(!(userDetails.getOtp().equals(response.get(0).getOtp())&&sessionId.equals(response.get(0).getSessionID()))) {
			model.addAttribute("errorMessage",
					(messageSource.getMessage("createrole.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ " - OTP not correct");

			UserDetails user = new UserDetails();
			user.setUserRoles(userDetails.getUserRoles());
			user.setUserEmailAdd(userDetails.getUserEmailAdd());
			user.setUserName(userDetails.getUserName());
			user.setPhoneNo(userDetails.getPhoneNo());
			user.setCountry(userDetails.getCountry());
			user.setSurname(userDetails.getSurname());
			user.setFirstName(userDetails.getFirstName());
			user.setMiddleName(userDetails.getMiddleName());
			user.setGender(userDetails.getGender());
			user.setDateOfBirth(userDetails.getDateOfBirth());
			user.setWeddingAnniversary(userDetails.getWeddingAnniversary());
			user.setDateJoinedHOTR(userDetails.getDateJoinedHOTR());
			user.setDateJoinedCITH(userDetails.getDateJoinedCITH());
			user.setCompletedFindingTheRock(userDetails.getCompletedFindingTheRock());
			user.setCompletedSpiritualAuthority(userDetails.getCompletedSpiritualAuthority());
			user.setHomeAddress(userDetails.getHomeAddress());
			user.setPlaceOfWork(userDetails.getPlaceOfWork());
			user.setPlaceOfWorkAddress(userDetails.getPlaceOfWorkAddress());
			user.setChurchDepartment(userDetails.getChurchDepartment());
			user.setOccupation(userDetails.getOccupation());
			user.setDateJoinedChurchDept(userDetails.getDateJoinedChurchDept());
			user.setMaritalStatus(userDetails.getMaritalStatus());
			user.setSpouseName(userDetails.getSpouseName());
			user.setSpousePhoneNumber(userDetails.getSpousePhoneNumber());
			user.setCentre(userDetails.getCentre());
			user.setArea(userDetails.getArea());
			user.setZone(userDetails.getZone());
			user.setDistrict(userDetails.getDistrict());
			user.setState(userDetails.getState());
			user.setCountry(userDetails.getCountry());

			List<Role> userroles = inquiryService.getroles();
			model.addAttribute("userroles", userroles);
			model.addAttribute("user", user);

			return "createuser";
		}
		
		
		

		Response creatUserroleresp = adminService.createUser(userDetails);
		LOGGER.info("** creatUserroleresp ==> " + creatUserroleresp + " **");

		if (creatUserroleresp == null || !("00".equals(creatUserroleresp.getResponseCode()))) {
			model.addAttribute("errorMessage",
					(messageSource.getMessage("createrole.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ " - "
							+ (creatUserroleresp != null && creatUserroleresp.getResponseMessage() != null
									? creatUserroleresp.getResponseMessage()
									: ""));

			//model.addAttribute("loggedinuser", getPrincipal());

			List<Role> userroles = inquiryService.getroles();
			model.addAttribute("userroles", userroles);

			return "createuser";

		}

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.createuser", new Object[] { "" }, Locale.getDefault())));

		LocalDate date1 = LocalDate.now();
		java.util.Date date2 = java.sql.Date.valueOf(date1);
		String date = MARSHARLLERDATEFORMAT.format(date2);

//		AuditMasterDetail auditMasterDetail = new AuditMasterDetail();
//		auditMasterDetail.setUserName(getPrincipal().getAdUsername());
//		auditMasterDetail.setEventDesc(getPrincipal().getAdUsername() + " of affiliate " + getPrincipal().getAffiliate()
//				+ " created a user with the following attributes; Full name is - " + " ' "
//				+ userDetails.getUserFullName() + " ' " + ", UserName - " + " ' " + userDetails.getUserName()
//				+ " ' " + ", Email Address - " + " ' " + userDetails.getUserEmailAdd() + " ' " + ", User Branch - "
//				+ " ' " + userDetails.getUserBranch() == null
//						? ""
//						: userDetails.getUserBranch() + " ' " + ", Transaction Limit - " + " ' "
//								+ user.getUserTransactionLimit() + " ' " + " and configured on Role - " + " ' "
//								+ userDetails.getUserRoles() + " ' " + " with User ID "
//								+ creatUserroleresp.getResponseMessage() + " and role " +
//
//								// (("OPERATIONS").equalsIgnoreCase(userRoleAccount.getUserRoles())
//								// ? (" with step ID " + userRoleAccount.getStepName())
//								// : (userRoleAccount.getUserRoles()))
//								(userDetails.getUserRoles())
//
//								+ " on " + date);
//		auditMasterDetail.setEventSrc("CREATE USER");
//		auditMasterDetail.setServerIp(getPrincipal().getCurrentLoginIPAddress());
//		auditMasterDetail.setExtRef(creatUserroleresp.getResponseMessage() + "CU");
//		Response insertIntoAuditmaster = auditService.insertIntoAdminAudit(auditMasterDetail, getPrincipal());

		return "redirect:/createuser";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/authoriseuser" }, method = RequestMethod.GET)
	public String authoriseUser(ModelMap model) throws Exception {


		List<UserDetails> pendusers = inquiryService.getpenduser(getPrincipal().getAffiliate());
		LOGGER.info("** pendusers ==> " + pendusers + " **");


		model.addAttribute("pendingUsers", pendusers);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("loggedinusername", getPrincipal().getAdUsername().toLowerCase());

		return "pendingusers";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/authpenduser", method = RequestMethod.POST)
	public String authPendUser(@Valid UserClient user, RedirectAttributes redirectAttributes, ModelMap model)
			throws Exception {

		if (!getPrincipal().getUserRolesStr().contains("GROUPADMIN") && "GROUPADMIN".equalsIgnoreCase(user.getRole())) {

			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("authuser.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ " - AFFILIATE ADMIN CANNOT AUTHORISE GROUP ADMIN ");
			return "redirect:/authoriseuser";

		}

		if (!getPrincipal().getUserRolesStr().contains("GROUPADMIN")
				&& !user.getAffiliate().equalsIgnoreCase(getPrincipal().getAffiliate())) {

			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("authuser.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ " - AFFILIATE ADMIN CANNOT AUTHORISE USER IN ANOTHER AFFILIATE ");
			return "redirect:/authoriseuser";

		}

		Response authuserresp = adminService.authpenduser(user.getUserID(), "APPROVE", getPrincipal());

		if (authuserresp == null || !("00".equals(authuserresp.getResponseCode()))) {
			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("authuser.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ " - "
							+ (authuserresp != null && authuserresp.getResponseMessage() != null
									? authuserresp.getResponseMessage()
									: ""));

			//
			return "redirect:/authoriseuser";
		}
		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.authuser", new Object[] { "" }, Locale.getDefault())));

		LocalDate date1 = LocalDate.now();
		java.util.Date date2 = java.sql.Date.valueOf(date1);
		String date = MARSHARLLERDATEFORMAT.format(date2);

		AuditMasterDetail auditMasterDetail = new AuditMasterDetail();
		auditMasterDetail.setUserName(getPrincipal().getAdUsername());
		auditMasterDetail.setEventDesc(getPrincipal().getAdUsername() + " of affiliate " + getPrincipal().getAffiliate()
				+ " authorized the user with user ID " + " ' " + user.getUserID() + " ' " + ", and user to role ID - "
				+ " ' " + user.getUserToRoleID() + " on " + date);
		auditMasterDetail.setEventSrc("CREATE USER");
		auditMasterDetail.setServerIp(getPrincipal().getCurrentLoginIPAddress());
		auditMasterDetail.setExtRef(user.getUserID() + "CU");
		Response insertIntoAuditmaster = auditService.insertIntoAdminAudit(auditMasterDetail, getPrincipal());

		return "redirect:/authoriseuser";

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/rejpenduser", method = RequestMethod.POST)
	public String rejPendUser(@Valid UserClient user, RedirectAttributes redirectAttributes, ModelMap model)
			throws Exception {
		LOGGER.info("user attribute" + user);

		if (!getPrincipal().getUserRolesStr().contains("GROUPADMIN") && "GROUPADMIN".equalsIgnoreCase(user.getRole())) {

			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("authuser.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ " - AFFILIATE ADMIN CANNOT REJECT GROUP ADMIN ");
			return "redirect:/authoriseuser";

		}

		if (!getPrincipal().getUserRolesStr().contains("GROUPADMIN")
				&& !user.getAffiliate().equalsIgnoreCase(getPrincipal().getAffiliate())) {

			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("authuser.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ " - AFFILIATE ADMIN CANNOT REJECT USER IN ANOTHER AFFILIATE ");
			return "redirect:/authoriseuser";

		}

		Response authuserresp = adminService.authpenduser(user.getUserID(), "REJECT", getPrincipal());

		if (authuserresp == null || !("00".equals(authuserresp.getResponseCode()))) {
			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("rejectuser.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ " - "
							+ (authuserresp != null && authuserresp.getResponseMessage() != null
									? authuserresp.getResponseMessage()
									: ""));

			model.addAttribute("loggedinuser", getPrincipal());

			return "redirect:/authoriseuser";

		}

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.rejectuser", new Object[] { "" }, Locale.getDefault())));

		LocalDate date1 = LocalDate.now();
		java.util.Date date2 = java.sql.Date.valueOf(date1);
		String date = MARSHARLLERDATEFORMAT.format(date2);

		AuditMasterDetail auditMasterDetail = new AuditMasterDetail();
		auditMasterDetail.setUserName(getPrincipal().getAdUsername());
		auditMasterDetail.setEventDesc(getPrincipal().getAdUsername() + " of affiliate " + getPrincipal().getAffiliate()
				+ " rejected the user with user ID " + " ' " + user.getUserID() + " ' " + ", and user to role ID - "
				+ " ' " + user.getUserToRoleID() + " on " + date);
		auditMasterDetail.setEventSrc("CREATE USER");
		auditMasterDetail.setServerIp(getPrincipal().getCurrentLoginIPAddress());
		auditMasterDetail.setExtRef(user.getUserID() + "CU");
		Response insertIntoAuditmaster = auditService.insertIntoAdminAudit(auditMasterDetail, getPrincipal());

		return "redirect:/authoriseuser";

	}
	
	@RequestMapping(value = { "/phoneOTPs" }, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Response> storeOTP(@RequestParam String phoneNo)
			throws Exception {

		System.out.println("am here to talk");
		
		HttpSession session = request.getSession();
		  String sessionId = session.getId();
		  String otpp = "12345";
		  
		  System.out.println("am otpp "+otpp);
		  
		Otp otp = new Otp();
		otp.setPhoneNo(phoneNo);
		otp.setSessionID(sessionId);
		otp.setOtp(otpp);
		
		Sms request = new Sms();
		request.setMessage(otpp);
		request.setPhoneNo(phoneNo);
		

		//LOGGER.info("affiliate " + getPrincipal().getAffiliate() + " acc -" + phoneNo);
		Response phoneOTP = adminService.saveOTP(otp);
		LOGGER.info("phoneOTP " + phoneOTP);
		
//		Response response = inquiryService.sendSMS(request);
//
//		LOGGER.info("response " + response);
		
		return new ResponseEntity<>(phoneOTP, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/validateOTPs", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Response> validateOTP(@RequestParam String otp)
			throws Exception {
		
		LOGGER.info("otp " + otp);
		Response resp = new Response();
		LOGGER.info("otp " + otp);
		
		HttpSession session = request.getSession();
		  String sessionId = session.getId();
		  LOGGER.info("otp 2" + otp);
		  Otp req = new Otp();
		  req.setSessionID(sessionId);
		  LOGGER.info("otp 3" + otp);
		List<Otp> response = inquiryService.getOTP(req);

		LOGGER.info("response " + response);
		
		if(otp.equals(response.get(0).getOtp())&&sessionId.equals(response.get(0).getSessionID())) {
			resp.setResponseCode("00");
			resp.setResponseMessage("Success");

			LOGGER.info("resp " + resp);
		}

		else{
		resp.setResponseCode("99");
		resp.setResponseMessage("Failed");
		LOGGER.info("resp failed " + resp);
		}

		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/enableuser-{userID}", method = RequestMethod.GET)
	public String enableuser(@PathVariable String userID, RedirectAttributes redirectAttributes, ModelMap model)
			throws Exception {

		Response authuserresp = adminService.authpenduser(userID, "ENABLE", getPrincipal());

		if (authuserresp == null || !("00".equals(authuserresp.getResponseCode()))) {
			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("enableuser.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ (" - ")
							+ ((authuserresp != null && authuserresp.getResponseMessage() != null)
									? authuserresp.getResponseMessage()
									: ""));

			List<UserDetails> pendusers = inquiryService.getpenduser(getPrincipal().getAffiliate());
			model.addAttribute("pendingUsers", pendusers);

			model.addAttribute("loggedinuser", getPrincipal());

			return "redirect:/edituser";
		}

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.enableuser", new Object[] { "" }, Locale.getDefault())));

		return "redirect:/edituser";

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/diasableuser-{userID}", method = RequestMethod.GET)
	public String diasableuser(@PathVariable String userID, RedirectAttributes redirectAttributes, ModelMap model)
			throws Exception {

		Response authuserresp = adminService.authpenduser(userID, "DISABLE", getPrincipal());

		if (authuserresp == null || !("00".equals(authuserresp.getResponseCode()))) {
			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("disableuser.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ (" - ")
							+ ((authuserresp != null && authuserresp.getResponseMessage() != null)
									? authuserresp.getResponseMessage()
									: ""));

			List<UserDetails> pendusers = inquiryService.getpenduser(getPrincipal().getAffiliate());
			model.addAttribute("pendingUsers", pendusers);
			model.addAttribute("loggedinuser", getPrincipal());

			return "redirect:/edituser";
		}

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.disableuser", new Object[] { "" }, Locale.getDefault())));

		return "redirect:/edituser";

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/edituser" }, method = RequestMethod.GET)
	public String editUser(ModelMap model) throws Exception {

		String pflag = "ALL";
		if (isPrincipalGlobalAdmin()) {
			pflag = "ALL";
		} else {
			pflag = "USER";
		}

		List<User> usersroledetails = inquiryService.getuser("1", pflag, getPrincipal().getAffiliate());
		model.addAttribute("usersroledetails", usersroledetails);

		model.addAttribute("loggedinuser", getPrincipal());

		return "editusers";
	}

	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/edituserdetails-{userID}" }, method = RequestMethod.GET)
	public String edituserdetails(@PathVariable String userID, RedirectAttributes redirectAttributes, ModelMap model)
			throws Exception {


		LOGGER.info("+++ userID ==> " + userID);
		List<User> userroledetails = inquiryService.getuser(userID, "PERUSER", getPrincipal().getAffiliate());
		User user = userroledetails.get(0);
		LOGGER.info("+++ user ==> " + user);

		if (user == null) {
			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("user.not.found", new Object[] { "" }, Locale.getDefault())));

			return "redirect:/edituser";
		}

		List<Role> userroles = inquiryService.getroles();
		model.addAttribute("userroles", userroles);

		model.addAttribute("user", user);
		model.addAttribute("userRole", getPrincipal().getUserRolesStr());
		model.addAttribute("loggedinuser", getPrincipal());

		return "edituserdetails";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/edituserdetails" }, method = RequestMethod.GET)
	public String edituserdetailsMember(RedirectAttributes redirectAttributes, ModelMap model)
			throws Exception {


		LOGGER.info("+++ userID ==> " + getPrincipal().getUserID());
		List<User> userroledetails = inquiryService.getuser(String.valueOf(getPrincipal().getUserID()), "PERUSER", getPrincipal().getAffiliate());
		User user = userroledetails.get(0);
		LOGGER.info("+++ user ==> " + user);

		if (user == null) {
			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("user.not.found", new Object[] { "" }, Locale.getDefault())));

			return "redirect:/edituser";
		}

		List<Role> userroles = inquiryService.getroles();
		model.addAttribute("userroles", userroles);

		model.addAttribute("user", user);
		model.addAttribute("userRole", getPrincipal().getUserRolesStr());
		model.addAttribute("loggedinuser", getPrincipal());

		return "edituserdetails";
	}

	/**
	 * This method will handle daily volume
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/edituserdetails" }, method = RequestMethod.POST)
	public String edituserdetails(@Valid EditUserDetails user, RedirectAttributes redirectAttributes, ModelMap model)
			throws Exception {
		LOGGER.info("++i  got here ==> " + user);

		List<UserDetails> getUserDetails = inquiryService.getUserDetails(user.getUserName());

		if (!getPrincipal().getUserRolesStr().contains("GROUPADMIN") && getUserDetails.size() > 0
				&& !(getPrincipal().getAffiliate().equalsIgnoreCase(getUserDetails.get(0).getAffiliateCode()))) {

			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("system.error", new Object[] { "" }, Locale.getDefault()))
							+ " - AFFILIATE ADMIN CANNOT EDIT USER DETAILS FOR A USER IN ANOTHER AFFILIATE");
			return "redirect:/edituserdetails-" + user.getUserID();

		}
		Response validateEmail = BasicUtil.validateEmail(user.getUserEmailAdd());

		if (!"00".equals(validateEmail.getResponseCode())) {

			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("system.error", new Object[] { "" }, Locale.getDefault()))
							+ " - EMAIL NOT IN RIGHT FORMAT ");
			return "redirect:/edituserdetails-" + user.getUserID();

		}

		Response Userroleresp = adminService.edituser(user, getPrincipal());

		if (Userroleresp == null || !("00".equals(Userroleresp.getResponseCode()))) {
			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("edituser.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ (" - ")
							+ ((Userroleresp != null && Userroleresp.getResponseMessage() != null)
									? Userroleresp.getResponseMessage()
									: ""));

			model.addAttribute("loggedinuser", getPrincipal());

			List<Role> userroles = inquiryService.getroles();
			model.addAttribute("userroles", userroles);
			model.addAttribute("user", user);

			// return "edituserdetails";
			return "redirect:/edituserdetails-" + user.getUserID();

		}

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser", new Object[] { "" }, Locale.getDefault())));

		LocalDate date1 = LocalDate.now();
		java.util.Date date2 = java.sql.Date.valueOf(date1);
		String date = MARSHARLLERDATEFORMAT.format(date2);

		AuditMasterDetail auditMasterDetail = new AuditMasterDetail();
		auditMasterDetail.setUserName(getPrincipal().getAdUsername());
		auditMasterDetail.setEventDesc(getPrincipal().getAdUsername() + " of affiliate " + getPrincipal().getAffiliate()
				+ " edited the details of user ID" + " ' " + user.getUserID() + " ' " + " on " + date);
		auditMasterDetail.setEventSrc("EDIT USER DETAILS");
		auditMasterDetail.setServerIp(getPrincipal().getCurrentLoginIPAddress());
		auditMasterDetail.setExtRef(user.getUserID() + "EUD");
		Response insertIntoAuditmaster = auditService.insertIntoAdminAudit(auditMasterDetail, getPrincipal());

		return "redirect:/edituserdetails-" + user.getUserID();
	}


	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/", "/dashboard" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model, HttpServletRequest httpRequest) throws Exception {
		LOGGER.info("+++ DASHBOARD ==> ");

		model.addAttribute("loggedinuser", getPrincipal());

		return "dashboard";
	}
	///////////////////// END OF
	///////////////////// ADMIN///////////////////////////////////////////////

	///////////////////// REPORT

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/weeklyReport", method = RequestMethod.GET)
	public String weeklyReport1(ModelMap model, HttpServletRequest httpRequest) throws Exception {


		model.addAttribute("loggedinuser", getPrincipal());

		return "weeklyReport";
	}
	
	@RequestMapping(value = "/weeklyReport", method = RequestMethod.POST)
	public String weeklyReport(@Valid WeeklyReport request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		request.setUserName(getPrincipal().getAdUsername());
		Response response = adminService.weeklyReport(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/weeklyReport";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/monthlyReport", method = RequestMethod.GET)
	public String monthlyReport(ModelMap model, HttpServletRequest httpRequest) throws Exception {

		model.addAttribute("userName", getPrincipal().getAdUsername());
		model.addAttribute("loggedinuser", getPrincipal());

		return "monthlyReport";
	}
	
	@RequestMapping(value = "/monthlyReport", method = RequestMethod.POST)
	public String monthlyReport(@Valid MonthlyReport request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		Response response = adminService.monthlyReport(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/monthlyReport";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/directorReport", method = RequestMethod.GET)
	public String directorReport(ModelMap model, HttpServletRequest httpRequest) throws Exception {
		
		model.addAttribute("loggedinuser", getPrincipal());

		return "directorReport";
	}
	
	@RequestMapping(value = "/directorReport", method = RequestMethod.POST)
	public String directorReport(@Valid DirectorReport request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		request.setName(getPrincipal().getAdUsername());
		Response response = adminService.directorReport(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/directorReport";
	}

	@RequestMapping(value = "/weeklyReportGenerate", method = RequestMethod.POST)
	public void generateWeeklyReport(@Valid WeeklyReport report, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception {
		BufferedInputStream inputStream = null;
		try {
			Date referenceDate = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(referenceDate);
			c.add(Calendar.DATE, 0);
			Date datediff = c.getTime();

			String sysDate = MARSHARLLERDATEFORMAT.format(datediff);
			LOGGER.info("bye " + sysDate);

			WordprocessingMLPackage template = BasicUtil.getTemplate(environment.getProperty("weeklyReport"));

			List<Object> texts = BasicUtil.getAllElementFromObject(template.getMainDocumentPart(), Text.class);
			BasicUtil.searchAndReplace(texts, new HashMap<String, String>() {
				{
					this.put("${a}", report.getCentre());
					this.put("${b}", report.getCentreAddress());
					this.put("${c}", report.getArea());
					this.put("${d}", report.getLeaderName());
					this.put("${e}", report.getAsstLeader());
					this.put("${f}", report.getIntern());
					this.put("${g}", report.getWorshipLeader());
					this.put("${h}", sysDate);
					this.put("${i}", report.getOutlineTopic());
					this.put("${j}", String.valueOf(report.getAdultPresent()));
					this.put("${k}", String.valueOf(report.getChildPresent()));
					this.put("${l}", String.valueOf(report.getVisitors()));
					this.put("${m}",
							String.valueOf(report.getAdultPresent() + report.getChildPresent() + report.getVisitors()));
				}

				@Override
				public String get(Object key) {
					// TODO Auto-generated method stub
					return super.get(key);
				}
			});

			File targetFile = new File(System.getProperty("java.io.tmpdir") + File.separator + "tempFile.tmp");

			BasicUtil.writeDocxToStream(template,
					System.getProperty("java.io.tmpdir") + File.separator + "tempFile.tmp");
			LOGGER.info("temp file " + System.getProperty("java.io.tmpdir"));

			inputStream = new BufferedInputStream(
					new FileInputStream(System.getProperty("java.io.tmpdir") + File.separator + "tempFile.tmp"));

			response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");

			IOUtils.copy(inputStream, response.getOutputStream());
			targetFile.delete();
			targetFile.deleteOnExit();
			// }
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("********Oops Something went wrong **********" + e);
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					LOGGER.error("********Oops Something went wrong **********" + e);
				}
		}

	}

	////////////////////// MESSAGING

	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		// Add username in web socket session
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}

	@RequestMapping(value = { "/chat" }, method = RequestMethod.GET)
	public String chatRoom(ModelMap model, HttpServletRequest request) {

		model.addAttribute("loggedinuser", getPrincipal());
		return "chatRoom";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/sendSMS" }, method = RequestMethod.GET)
	public String sendSms(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("loggedinusername", getPrincipal().getAdUsername().toLowerCase());

		return "sendsms";
	}

	@RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
	public String sendSMS(@Valid Sms request, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		String phone = request.getPhoneNo().replace(" ", "");

		LOGGER.info("phone " + request);
		LOGGER.info("phone " + phone);
		request.setPhoneNo(phone);
		Response response = inquiryService.sendSMS(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/sendSMS";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/sendMail" }, method = RequestMethod.GET)
	public String sendMail(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("loggedinusername", getPrincipal().getAdUsername().toLowerCase());

		return "sendmail";
	}

	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String sendMail(@Valid Sms request, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {
		
		LOGGER.info("phone " + request);
		Response response = inquiryService.sendMail(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/sendMail";
	}
	
	
	

	@RequestMapping(value = "/prayerRequest", method = RequestMethod.GET)
	public String prayerRequest(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "prayer";
	}

	@RequestMapping(value = "/prayerRequest", method = RequestMethod.POST)
	public String prayerRequest(@Valid PrayerRequest request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		Response response = adminService.prayerRequest(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/prayerRequest";
	}
	
	@RequestMapping(value = "/viewPrayerRequest", method = RequestMethod.GET)
	public String viewPrayerRequest(ModelMap model) throws Exception {

		PrayerRequest request = new PrayerRequest();
		List<PrayerRequest> response = inquiryService.getPrayerRequest(request);
		model.addAttribute("prayer", response);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "viewPrayer";
	}
	
	@RequestMapping(value = "/viewPrayerPerUser", method = RequestMethod.GET)
	public String viewPrayerPerUser(@Valid PrayerRequest request, ModelMap model) throws Exception {

		request.setName(getPrincipal().getAdUsername());
		List<PrayerRequest> response = inquiryService.getPrayerRequest(request);
		model.addAttribute("prayer", response);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "viewPrayerUser";
	}
	
	@RequestMapping(value = "/sharePrayer", method = RequestMethod.POST)
	public String sharePrayer(@Valid PrayerRequest request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		request.setName(getPrincipal().getAdUsername());
		LOGGER.info("request " + request);
		
		Response response = adminService.updatePrayerRequest(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/viewPrayerRequest";
	}
	
	
	
	
	@RequestMapping(value = "/welfareRequest", method = RequestMethod.GET)
	public String welfareRequest(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "welfare";
	}

	@RequestMapping(value = "/welfareRequest", method = RequestMethod.POST)
	public String welfareRequest(@Valid Welfare request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		Response response = adminService.welfareRequest(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/welfareRequest";
	}
	
	@RequestMapping(value = "/viewWelfareRequest", method = RequestMethod.GET)
	public String viewWelfareRequest(ModelMap model) throws Exception {

		Welfare request = new Welfare();
		List<Welfare> response = inquiryService.getWelfareRequest(request);
		model.addAttribute("welfare", response);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "viewWelfare";
	}
	
	@RequestMapping(value = "/viewWelfarePerUser", method = RequestMethod.GET)
	public String viewWelfareUser(@Valid Welfare request, ModelMap model) throws Exception {

		request.setName(getPrincipal().getAdUsername());
		List<Welfare> response = inquiryService.getWelfareRequest(request);
		model.addAttribute("welfare", response);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "viewWelfareUser";
	}
	
	@RequestMapping(value = "/updateWelfare", method = RequestMethod.POST)
	public String updateWelfare(@Valid Welfare request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		request.setName(getPrincipal().getAdUsername());
		LOGGER.info("request " + request);
		
		Response response = adminService.updateWelfareRequest(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/viewWelfareRequest";
	}
	
	
	
	@RequestMapping(value = "/announcement", method = RequestMethod.GET)
	public String announcement(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "announcement";
	}
	

	@RequestMapping(value = "/loadAnnouncement", method = RequestMethod.POST)
	public String loadAnnouncement(@Valid Announcement request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		LOGGER.info("request " + request);
		if(request.getCategory().equalsIgnoreCase("MEETING")) {
			request.setCategory(request.getMeetingType());
		}
		Response response = adminService.specialAnnouncement(request);

		LOGGER.info("response " + response);
		
		if (("11").equals(response.getResponseCode())) {

			redirectAttributes.addFlashAttribute("errorMessage",
					"Username not configured");
			return "redirect:/announcement";

		}

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/announcement";
	}
	
	@RequestMapping(value = "/viewAnnouncement", method = RequestMethod.GET)
	public String viewAnnouncement(ModelMap model) throws Exception {

		List<Announcement> response = inquiryService.getAnnouncement();
		model.addAttribute("announce", response);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "viewAnnouncement";
	}
	
	
	@RequestMapping(value = "/incident", method = RequestMethod.GET)
	public String incident(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "incident";
	}
	

	@RequestMapping(value = "/loadIncident", method = RequestMethod.POST)
	public String loadIncident(@Valid Incident request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		LOGGER.info("request " + request);
		Response response = adminService.incidentRequest(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/incident";
	}
	
	@RequestMapping(value = "/viewIncident", method = RequestMethod.GET)
	public String viewIncident(@Valid Incident request, ModelMap model) throws Exception {

		List<Incident> response = inquiryService.getIncident(request);
		
		LOGGER.info("response " + response);
		model.addAttribute("incident", response);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "viewIncident";
	}
	
	@RequestMapping(value = "/viewIncidentPerUser", method = RequestMethod.GET)
	public String viewIncidentPerUser(@Valid Incident request, ModelMap model) throws Exception {

		request.setName(getPrincipal().getAdUsername());
		List<Incident> response = inquiryService.getIncident(request);
		LOGGER.info("response " + response);
		model.addAttribute("incident", response);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "viewIncidentUser";
	}
	
	@RequestMapping(value = "/updateIncident", method = RequestMethod.POST)
	public String updateIncident(@Valid Incident request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		request.setName(getPrincipal().getAdUsername());
		LOGGER.info("request " + request);
		
		Response response = adminService.updateIncidentRequest(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/viewIncident";
	}
	
	@RequestMapping(value = "/whistleBlowing", method = RequestMethod.GET)
	public String whistleBlowing(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "whistleBlowing";
	}
	

	@RequestMapping(value = "/loadWhistleBlowing", method = RequestMethod.POST)
	public String loadWhistleBlowing(@Valid Incident request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		LOGGER.info("request " + request);
		Response response = adminService.whistleBlowing(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/whistleBlowing";
	}
	
	@RequestMapping(value = "/viewWhistleBlowing", method = RequestMethod.GET)
	public String viewWhistleBlowing(@Valid Incident request, ModelMap model) throws Exception {

		List<Incident> response = inquiryService.getWhistleBlowing(request);
		
		LOGGER.info("response " + response);
		model.addAttribute("whistleBlowing", response);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "viewWhistleBlowing";
	}
	
	@RequestMapping(value = "/viewWhistleBlowingPerUser", method = RequestMethod.GET)
	public String viewWhistleBlowingPerUser(@Valid Incident request, ModelMap model) throws Exception {

		request.setName(getPrincipal().getAdUsername());
		List<Incident> response = inquiryService.getWhistleBlowing(request);
		LOGGER.info("response " + response);
		model.addAttribute("whistleBlowing", response);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "viewWhistleBlowingUser";
	}
	
	@RequestMapping(value = "/updateWhistleBlowing", method = RequestMethod.POST)
	public String updateWhistleBlowing(@Valid Incident request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		request.setName(getPrincipal().getAdUsername());
		LOGGER.info("request " + request);
		
		Response response = adminService.updateWhistleBlowingRequest(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/viewWhistleBlowing";
	}
	
	@RequestMapping(value = "/donation", method = RequestMethod.GET)
	public String financial(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "paymentpage";
	}
	
	@RequestMapping(value = "/expenseRequest", method = RequestMethod.GET)
	public String expenseRequest(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "expense";
	}

	@RequestMapping(value = "/expenseRequest", method = RequestMethod.POST)
	public String expenseRequest(@Valid Expenses request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		
		
		request.setAmount(BigDecimal.valueOf(Double.valueOf(request.getAmountStr())));
		Response response = adminService.expenseRequest(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/expenseRequest";
	}
	
	@RequestMapping(value = "/viewExpenseRequest", method = RequestMethod.GET)
	public String viewExpenseRequest(ModelMap model) throws Exception {

		Expenses request = new Expenses();
		List<Expenses> response = inquiryService.getExpenses(request);
		model.addAttribute("expense", response);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "viewExpense";
	}
	
	@RequestMapping(value = "/communityProject", method = RequestMethod.GET)
	public String communityProject(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "communityproject";
	}

	@RequestMapping(value = "/communityProject", method = RequestMethod.POST)
	public String communityProject(@Valid Expenses request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		request.setAmount(BigDecimal.valueOf(Double.valueOf(request.getAmountStr())));
		Response response = adminService.communityProject(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/communityProject";
	}
	
	@RequestMapping(value = "/viewCommunityProject", method = RequestMethod.GET)
	public String viewCommunityProject(ModelMap model) throws Exception {

		Expenses request = new Expenses();
		List<Expenses> response = inquiryService.getCommunityProject(request);
		model.addAttribute("project", response);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "viewCommProject";
	}
	
	@RequestMapping(value = "/weeklyOutline", method = RequestMethod.GET)
	public String weeklyOutline(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "weeklyOutline";
	}

	@RequestMapping(value = "/loadWeeklyOutline", method = RequestMethod.POST)
	public String weeklyOutline(@Valid WeeklyOutline request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception, IOException {
		
		
		MultipartFile file = request.getOutlineFile();
		request.setName(getPrincipal().getAdUsername());
        if (file != null && !file.isEmpty()){
        	request.setUploadFlag("Y");
        }
        
        Response response = adminService.weeklyOutline(request);
		LOGGER.info("response " + response);
		
		if (!"00".equals(response.getResponseCode())) {
        	redirectAttributes.addAttribute("errorMessage", "Error Submitting Form");
        	return "redirect:/weeklyOutline";
        }
        
        if ("00".equals(response.getResponseCode())) {
            if (file != null && !file.isEmpty()) {
                String fileName = file.getOriginalFilename();
                String douniqueid = BasicUtil.getFilename(fileName);
                long length = file.getSize();
                InputStream is = file.getInputStream();
                String contentType = file.getContentType();
                long id = Long.parseLong(response.getResponseMessage());
                DocManagerRequest docMangerRequest = new DocManagerRequest();
                docMangerRequest.setDocumentID(id);
                docMangerRequest.setDocumentUniqueID(douniqueid);
                docMangerRequest.setUserName(getPrincipal().getAdUsername());
                docMangerRequest.setDocName(fileName);
                docMangerRequest.setFiletype(contentType);
                //docMangerRequest.setId(response.getResponseMessage());

                byte[] bytes = BasicUtil.loadFile(file.getInputStream(), length, fileName);
                LOGGER.info(">>> bytes " + bytes.toString() + ". <<< ");

                byte[] encodedBase64Bytes = Base64.encodeBase64(bytes);
                String encodedBase64String = new String(encodedBase64Bytes, StandardCharsets.US_ASCII);
                docMangerRequest.setInputStreamStr(encodedBase64String);

                Response logit = adminService.createOutlineDocument(docMangerRequest);
                LOGGER.info("<<logit>>" + logit);


            }

            model.addAttribute("successMessage", "GE Form Submitted Sucessfully .");
        }

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/weeklyOutline";
	}
	
	@RequestMapping(value = "/viewWeeklyOutline", method = RequestMethod.GET)
	public String viewWeeklyOutline(ModelMap model) throws Exception {

		WeeklyOutline request = new WeeklyOutline();
		List<WeeklyOutline> response = inquiryService.getWeeklyOutline(request);
		model.addAttribute("weeklyOutline", response);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "viewWeeklyOutline";
	}
	
	
	
	
	@RequestMapping(value = "/downloadOutline-{outlineID}", method = RequestMethod.GET)
    public synchronized void getDoc(@PathVariable String outlineID, ModelMap model, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

    LocalDate date1 = LocalDate.now();
    java.util.Date date2 = java.sql.Date.valueOf(date1);
    String date = MARSHARLLERDATEFORMAT.format(date2);
    //LOGGER.info("** dfdf ==> " + df + " **");
    InputStream inputStream = null;
    try {

        DocManagerRequest document = inquiryService.getOutlineDocument(outlineID);
        LOGGER.info("** document ==> " + document + " **");
        response.setContentType(document.getFiletype());
        response.setHeader("Content-Disposition", "attachment; filename=" + document.getDocName());
        LOGGER.info("** inputStream ==> " + document.getInputStream() + " **");
        String encodedString = document.getInputStreamStr();
        LOGGER.info("** document2 ==> " + encodedString + " **");
        encodedString = encodedString != null ? encodedString : "";

        byte[] decodedBase64Bytes = Base64.decodeBase64(encodedString);
        inputStream = new ByteArrayInputStream(decodedBase64Bytes);
        IOUtils.copy(inputStream, response.getOutputStream());
        // }
    } catch (Exception e) {
        e.printStackTrace();
        LOGGER.error("********Oops Something went wrong **********" + e);
    } finally {
        if (inputStream != null)
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("********Oops Something went wrong **********" + e);
            }
    }

}

	
	
	
	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public String calendar(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "calendarEvent";
	}

	@RequestMapping(value = "/loadCalendar", method = RequestMethod.POST)
	public String loadCalendar(@Valid CalendarDetail request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		Response response = adminService.calendarEvent(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/calendar";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getCalendarEvent", method = RequestMethod.GET)
	public String getCalendarEvent(ModelMap model, HttpServletRequest httpRequest) throws Exception {

		CalendarDetail request = new CalendarDetail();
		List<CalendarDetail> response = inquiryService.getCalendarEvent(request);

		model.addAttribute("calendarEvent", response);
		model.addAttribute("loggedinuser", getPrincipal());

		return "viewCalendarEvent";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getCalendarEventToUpdate-{calendarID}", method = RequestMethod.GET)
	public String getCalendarEventToUpdate(@PathVariable String calendarID, ModelMap model, HttpServletRequest httpRequest) throws Exception {

		CalendarDetail request = new CalendarDetail();
		request.setCalendarID(calendarID);
		List<CalendarDetail> response = inquiryService.getCalendarEvent(request);

		LOGGER.info("response " + response);
		
		model.addAttribute("response", response.get(0));

		model.addAttribute("loggedinuser", getPrincipal());

		return "calendarEventToUpdate";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/deleteCalendarEvent-{calendarID}", method = RequestMethod.GET)
	public String deleteCalendarEvent(@PathVariable String calendarID, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		CalendarDetail request = new CalendarDetail();
		request.setCalendarID(calendarID);
		request.setStatus("DELETE");
		Response response = adminService.updateCalendarEvent(request);
		LOGGER.info("response " + response);
		
		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/getCalendarEvent";
	}
	
	@RequestMapping(value = "/updateCalendarEvent", method = RequestMethod.POST)
	public String updateCalendarEVent(@Valid CalendarDetail request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		request.setCreatedBy(getPrincipal().getAdUsername());
		LOGGER.info("request " + request);
		
		Response response = adminService.updateCalendarEvent(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/getCalendarEvent";
	}
	
	
	@RequestMapping(value = "/socialEvents", method = RequestMethod.GET)
	public String socialEvents(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "socialEvents";
	}

//	@RequestMapping(value = "/loadSocialEvent", method = RequestMethod.POST)
//	public String loadSocialEvent(@Valid SocialEvent request, ModelMap model, RedirectAttributes redirectAttributes)
//			throws Exception, IOException {
//		
//		
//		MultipartFile file = request.getGallery();
//		request.setName(getPrincipal().getAdUsername());
//        if (file != null && !file.isEmpty()){
//        	request.setUploadFlag("Y");
//        }
//        
//        Response response = adminService.createSocialEvent(request);
//		LOGGER.info("response " + response);
//		
//		if (!"00".equals(response.getResponseCode())) {
//        	redirectAttributes.addAttribute("errorMessage", "Error Submitting Form");
//        	return "redirect:/socialEvents";
//        }
//        
//        if ("00".equals(response.getResponseCode())) {
//            if (file != null && !file.isEmpty()) {
//                String fileName = file.getOriginalFilename();
//                String douniqueid = BasicUtil.getFilename(fileName);
//                long length = file.getSize();
//                InputStream is = file.getInputStream();
//                String contentType = file.getContentType();
//                long id = Long.parseLong(response.getResponseMessage());
//                DocManagerRequest docMangerRequest = new DocManagerRequest();
//                docMangerRequest.setDocumentID(id);
//                docMangerRequest.setDocumentUniqueID(douniqueid);
//                docMangerRequest.setUserName(getPrincipal().getAdUsername());
//                docMangerRequest.setDocName(fileName);
//                docMangerRequest.setFiletype(contentType);
//                //docMangerRequest.setId(response.getResponseMessage());
//
//                byte[] bytes = BasicUtil.loadFile(file.getInputStream(), length, fileName);
//                LOGGER.info(">>> bytes " + bytes.toString() + ". <<< ");
//
//                byte[] encodedBase64Bytes = Base64.encodeBase64(bytes);
//                String encodedBase64String = new String(encodedBase64Bytes, StandardCharsets.US_ASCII);
//                docMangerRequest.setInputStreamStr(encodedBase64String);
//
//                Response logit = adminService.createSocialEventDocument(docMangerRequest);
//                LOGGER.info("<<logit>>" + logit);
//
//
//            }
//
//            model.addAttribute("successMessage", "GE Form Submitted Sucessfully .");
//        }
//
//		redirectAttributes.addFlashAttribute("successMessage",
//				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
//		return "redirect:/socialEvents";
//	}
	
	
	@RequestMapping(value = "/loadSocialEvent", method = RequestMethod.POST)
	public String loadSocialEvent(@Valid SocialEvent request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception, IOException {
		LOGGER.info("response " + request);
		
		MultipartFile[] file = request.getGallerys();
		request.setName(getPrincipal().getAdUsername());
        if (file != null && !file[0].isEmpty()){
        	request.setUploadFlag("Y");
        }
        
        Response response = adminService.createSocialEvent(request);
		LOGGER.info("response " + response);
		
		if (!"00".equals(response.getResponseCode())) {
        	redirectAttributes.addAttribute("errorMessage", "Error Submitting Form");
        	return "redirect:/socialEvents";
        }
        
        if ("00".equals(response.getResponseCode())) {
        	
            if (file != null && !file[0].isEmpty()) {
            	
            	for(int i=0; i<file.length;i++) {
            		
                String fileName = file[i].getOriginalFilename();
                String douniqueid = BasicUtil.getFilename(fileName);
                long length = file[i].getSize();
                InputStream is = file[i].getInputStream();
                String contentType = file[i].getContentType();
                long id = Long.parseLong(response.getResponseMessage());
                DocManagerRequest docMangerRequest = new DocManagerRequest();
                docMangerRequest.setDocumentID(id);
                docMangerRequest.setDocumentUniqueID(douniqueid);
                docMangerRequest.setUserName(getPrincipal().getAdUsername());
                docMangerRequest.setDocName(fileName);
                docMangerRequest.setFiletype(contentType);
                docMangerRequest.setTopic(request.getTopic());

                byte[] bytes = BasicUtil.loadFile(file[i].getInputStream(), length, fileName);
                LOGGER.info(">>> bytes " + bytes.toString() + ". <<< ");

                byte[] encodedBase64Bytes = Base64.encodeBase64(bytes);
                String encodedBase64String = new String(encodedBase64Bytes, StandardCharsets.US_ASCII);
                docMangerRequest.setInputStreamStr(encodedBase64String);

                Response logit = adminService.createSocialEventDocument(docMangerRequest);
                LOGGER.info("<<logit>>" + logit);

            	}
            }

            model.addAttribute("successMessage", "GE Form Submitted Sucessfully .");
        }

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/socialEvents";
	}
	
	@RequestMapping(value = "/viewSocialEvent", method = RequestMethod.GET)
	public String viewSocialEvent(ModelMap model) throws Exception {

		SocialEvent request = new SocialEvent();
		List<SocialEvent> response = inquiryService.getSocialEvent(request);
		
		model.addAttribute("socialEvent", response);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("UserRole", getPrincipal().getUserRolesStr());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "viewSocialEvent";
	}
	
	@RequestMapping(value = "/viewSocialEventDoc-{eventID}", method = RequestMethod.GET)
	public String viewSocialEvent(@PathVariable String eventID, ModelMap model) throws Exception {
		
		List<DocManagerRequest> document = inquiryService.getSocialEventDocument(eventID);
		 LOGGER.info(">>> document " + document + ". <<< ");
		 
		 String topic = document.get(0).getTopic();
		
		model.addAttribute("topic", topic);
		model.addAttribute("socialEventDoc", document);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "viewSocialEventDocument";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/deleteSocialEvent-{eventID}", method = RequestMethod.GET)
	public String deleteSocialEvent(@PathVariable String eventID, ModelMap model, RedirectAttributes redirectAttributes) throws Exception {

		SocialEvent request = new SocialEvent();
		request.setEventID(eventID);
		request.setStatus("DELETE");
		Response response = adminService.updateSocialEvent(request);
		LOGGER.info("response " + response);
		
		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/viewSocialEvent";
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String changePassword(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal()); 

		return "changePassword";
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String changePassword(@Valid ChangePassword request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		LOGGER.info("response " + request.getOldPassword()+"   "+ getPrincipal().getPassword());
		if(!(request.getOldPassword()).equals(getPrincipal().getPassword())) {
			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("password.incorrect", new Object[] { "" }, Locale.getDefault())));
			return "redirect:/changePassword";
		}
		
		request.setUserID(getPrincipal().getUserID());
		Response response = adminService.changePassword(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/changePassword";
	}
	
	@RequestMapping(value = "/getWeeklyReport", method = RequestMethod.GET)
	public String getWeeklyReport(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal()); 

		return "viewWeeklyReport";
	}
	
	@RequestMapping(value = "/getWeeklyReport", method = RequestMethod.POST)
	public String getWeeklyReport(@Valid Filter request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		
		
		request.setName(getPrincipal().getAdUsername());
		List<WeeklyReport> response = inquiryService.getWeeklyReport(request);

		LOGGER.info("response " + response);

		model.addAttribute("loggedinuser", getPrincipal()); 
		model.addAttribute("weeklyReport", response); 

		return "viewWeeklyReport";
	}
	
	@RequestMapping(value = "/getMonthlyReport", method = RequestMethod.GET)
	public String getMonthlyReport(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal()); 

		return "viewMonthlyReport";
	}
	
	@RequestMapping(value = "/getMonthlyReport", method = RequestMethod.POST)
	public String getMonthlyReport(@Valid Filter request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		
		
		request.setName(getPrincipal().getAdUsername());
		request.setDateFrom(MARSHARLLERDATEFORMAT.parse(request.getDateFromStr()));
		request.setDateTo(MARSHARLLERDATEFORMAT.parse(request.getDateToStr()));
		List<MonthlyReport> response = inquiryService.getMonthlyReport(request);

		LOGGER.info("response " + response);

		model.addAttribute("loggedinuser", getPrincipal()); 
		model.addAttribute("monthlyReport", response); 

		return "viewMonthlyReport";
	}
	
	@RequestMapping(value = "/getDirectorReport", method = RequestMethod.GET)
	public String getDirectorReport(ModelMap model) throws Exception {

		Filter request = new Filter();
		model.addAttribute("loggedinuser", getPrincipal()); 
		
		List<DirectorReport> response = inquiryService.getDirectorReport(request);

		LOGGER.info("response " + response); 
		model.addAttribute("directorReport", response); 

		return "viewDirectorReport";
	}
	
	@RequestMapping(value = "/getDirectorReport", method = RequestMethod.POST)
	public String getDirectorReport(@Valid Filter request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		
		
		request.setName(getPrincipal().getAdUsername());
		request.setDateFrom(MARSHARLLERDATEFORMAT.parse(request.getDateFromStr()));
		request.setDateTo(MARSHARLLERDATEFORMAT.parse(request.getDateToStr()));
		List<DirectorReport> response = inquiryService.getDirectorReport(request);

		LOGGER.info("response " + response);

		model.addAttribute("loggedinuser", getPrincipal()); 
		model.addAttribute("directorReport", response); 

		return "viewDirectorReport";
	}
	
	@RequestMapping(value = "/liveStreaming", method = RequestMethod.GET)
	public String liveStreaming(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());

		return "liveStreaming";
	}
	
	@RequestMapping(value = "/centreDirectory", method = RequestMethod.GET)
	public String centreDirectory(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal()); 

		return "centreDirectory";
	}
	
	@RequestMapping(value = "/centreDirectory", method = RequestMethod.POST)
	public String centreDirectory(@Valid Filter request, ModelMap model, RedirectAttributes redirectAttributes)
			throws Exception {
		
		List<UserDetails> response = inquiryService.getUserDetails(request.getCentre());

		LOGGER.info("response " + response);

		model.addAttribute("loggedinuser", getPrincipal()); 
		model.addAttribute("directory", response); 
		model.addAttribute("fellowshipCentre", request.getCentre()); 

		return "centreDirectory";
	}
	
	
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = { "/uploadaccount" }, method = RequestMethod.POST)
//	public String uploadAccount(@Valid CentreDirectory request, BindingResult result,
//			RedirectAttributes redirectAttributes, ModelMap model) throws Exception {
//
//		model.addAttribute("loggedinuser", getPrincipal());
//
//		LOGGER.info("here h");
//
//		MultipartFile file = (request.getDocuments() != null && request.getDocuments().length > 0)
//				? request.getDocuments()[0]
//				: null;
//
//		if (file != null && !file.isEmpty()) {
//			String fileName = file.getOriginalFilename();
//			String douniqueid = BasicUtil.getFilename(fileName);
//			long length = file.getSize();
//			InputStream is = file.getInputStream();
//			String contentType = file.getContentType();
//
//			File targetFile = new File(System.getProperty("java.io.tmpdir") + File.separator + "tempFileBSS.tmp");
//
//			java.nio.file.Files.copy(is, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
//			IOUtils.closeQuietly(is);
//
//			List<String[]> data = new ArrayList<>();
//			try {
//				if (!file.isEmpty()) {
//					if ((fileName.substring(fileName.length() - 3).equalsIgnoreCase("xls")
//							&& contentType.equalsIgnoreCase("application/vnd.ms-excel"))
//							|| (fileName.substring(fileName.length() - 4).equalsIgnoreCase("xlsx")
//									&& contentType.equalsIgnoreCase(
//											"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))) {
//						// start to read from file
//
//						BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
//								System.getProperty("java.io.tmpdir") + File.separator + "tempFileBSS.tmp"));
//						data = BasicUtil.readXcelAccounts(bis, 1);
//
//						LOGGER.info("FirstRowMan" + data.get(1));
//
//						LOGGER.info("data" + data);
//						LOGGER.info("data.size()" + data.size());
//
//						if (data.size() == 0) {
//							redirectAttributes.addFlashAttribute("errorMessage",
//									(messageSource.getMessage("no.data", new Object[] { "" },
//											Locale.forLanguageTag(RequestContextUtils.getLocaleResolver(request)
//													.resolveLocale(request).getLanguage()))));
//							LOGGER.info("Excel file has no data");
//							return "redirect:/account";
//						}
//						LOGGER.info("here 22");
//						if (!"ACCOUNT NUMBER".equals(data.get(0)[0]) || !"GL NAME".equals(data.get(0)[1])
//								|| !"GL CURRENCY".equals(data.get(0)[2])
//								|| !"GL TYPE".equals(data.get(0)[3]) | !"BRANCH".equals(data.get(0)[4])
//								|| !"CATEGORY".equals(data.get(0)[5])) {
//							redirectAttributes.addFlashAttribute("errorMessage",
//									(messageSource.getMessage("wrong.excel.upload", new Object[] { "" },
//											Locale.forLanguageTag(RequestContextUtils.getLocaleResolver(request)
//													.resolveLocale(request).getLanguage()))));
//							LOGGER.info("Excel file value null");
//							return "redirect:/account";
//						}
//
//						else {
//
//							LOGGER.info("here 33");
//							List<String> hasErrors = new ArrayList<>();
//
//							for (int i = 1; i < data.size(); i++) {
//
//								UserToAccount account = new UserToAccount();
//								account.setAccountNumber(data.get(i)[0]);
//								account.setGlName(data.get(i)[1]);
//								account.setGlCurrency(data.get(i)[2]);
//								account.setGlType(data.get(i)[3]);
//								account.setBranch(data.get(i)[4]);
//								account.setCategory(data.get(i)[5]);
//								account.setAccountType(data.get(i)[6]);
//								account.setAffiliateCode(data.get(i)[7]);
//
//								LOGGER.info("createAccount e" + account);
//								Response createAccount = bssaService.createAccount(account, getPrincipal());
//								LOGGER.info("createAccount e" + createAccount);
//
//								if (createAccount == null || ("99".equals(createAccount.getResponseCode()))) {
//
//									hasErrors.add((messageSource.getMessage("unsuccessful.acc", new Object[] { "" },
//											Locale.getDefault())).replace("[account]", account.getAccountNumber())
//											+ (" - ") + createAccount.getResponseMessage());
//
//								}
//
//								if (!"00".equals(createAccount.getResponseCode())) {
//
//									hasErrors.add((messageSource.getMessage("unsuccessful.acc", new Object[] { "" },
//											Locale.getDefault())).replace("[account]", account.getAccountNumber())
//											+ (" - ") + createAccount.getResponseMessage());
//
//								}
//
//								if ("00".equals(createAccount.getResponseCode())) {
//									LocalDate date1 = LocalDate.now();
//									java.util.Date date2 = java.sql.Date.valueOf(date1);
//									String date = MARSHARLLERDATEFORMAT.format(date2);
//
//									account.setAccountNumber(data.get(i)[0]);
//									account.setGlName(data.get(i)[1]);
//									account.setGlCurrency(data.get(i)[2]);
//									account.setGlType(data.get(i)[3]);
//									account.setBranch(data.get(i)[4]);
//									account.setCategory(data.get(i)[5]);
//									AuditMasterDetail auditMasterDetail = new AuditMasterDetail();
//									auditMasterDetail.setUserName(getPrincipal().getAdUsername());
//									auditMasterDetail.setEventDesc(getPrincipal().getAdUsername() + " of affiliate "
//											+ getPrincipal().getAffiliate()
//											+ " created GL account with the following attributes. Account Number: "
//											+ account.getAccountNumber() + ", Branch: " + account.getBranch()
//											+ ", Category: " + account.getCategory() + ", GL Currency: "
//											+ account.getGlCurrency() + ", GL Name: " + account.getGlCurrency()
//											+ " and GL Type: " + account.getGlType() + " with account ID of "
//											+ createAccount.getResponseMessage() + " on " + new Date());
//									auditMasterDetail.setEventSrc("CREATE ACCOUNT");
//									auditMasterDetail.setServerIp(getPrincipal().getCurrentLoginIPAddress());
//									auditMasterDetail.setExtRef(createAccount.getResponseMessage() + "CA");
//									Response insertIntoAuditmaster = bssaService.insertIntoAdminAudit(auditMasterDetail,
//											getPrincipal());
//								}
//
//							}
//
//							LOGGER.info("** hasErrors ==> " + hasErrors.size() + " **");
//
//							if (hasErrors.size() > 0 && hasErrors.size() == data.size()) {
//
//								redirectAttributes.addFlashAttribute("errorMessage",
//										(messageSource.getMessage("unsuccessful.acc", new Object[] { "" },
//												Locale.getDefault())) + (" because ") + hasErrors.toString());
//
//								return "redirect:/account";
//							}
//
//							if (hasErrors.size() > 0 && hasErrors.size() < data.size()) {
//
//								redirectAttributes.addFlashAttribute("successMessage",
//										(messageSource.getMessage("uploadacc.success", new Object[] { "" },
//												Locale.getDefault())) + (" but ") + hasErrors.toString());
//
//								return "redirect:/account";
//							}
//
//							redirectAttributes.addFlashAttribute("successMessage", (messageSource
//									.getMessage("uploadacc.success", new Object[] { "" }, Locale.getDefault())));
//
//							if (targetFile.exists())
//								targetFile.delete();
//							targetFile.deleteOnExit();
//							return "redirect:/account";
//
//						}
//					} else {
//						redirectAttributes.addFlashAttribute("errorMessage",
//								(messageSource.getMessage("not.excel", new Object[] { "" },
//										Locale.forLanguageTag(RequestContextUtils.getLocaleResolver(request)
//												.resolveLocale(request).getLanguage()))));
//						LOGGER.info("Not an Excel File");
//						return "redirect:/account";
//					}
//				} else {
//					redirectAttributes.addFlashAttribute("errorMessage",
//							(messageSource.getMessage("no.data", new Object[] { "" },
//									Locale.forLanguageTag(RequestContextUtils.getLocaleResolver(request)
//											.resolveLocale(request).getLanguage()))));
//					LOGGER.info("Excel file has no data");
//					return "redirect:/account";
//				}
//			}
//
//			catch (Exception e) {
//				e.printStackTrace();
//				model.addAttribute("errorMessage",
//						(messageSource.getMessage("wrong.excel.upload", new Object[] { "" }, Locale.forLanguageTag(
//								RequestContextUtils.getLocaleResolver(request).resolveLocale(request).getLanguage()))));
//				return "redirect:/account";
//			}
//
//		}
//
//		redirectAttributes.addFlashAttribute("successMessage",
//				(messageSource.getMessage("uploadacc.success", new Object[] { "" }, Locale.forLanguageTag(
//						RequestContextUtils.getLocaleResolver(request).resolveLocale(request).getLanguage()))));
//
//		return "redirect:/account";
//
//	}
//
//	@RequestMapping(value = "/UserRoleTemplate", method = RequestMethod.POST)
//	public void getImageAsByteArray1(ModelMap model, HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		InputStream inputStream = null;
//		try {
//			inputStream = WelcomeController.class.getResourceAsStream("/UserRoleTemplate.xlsx");
//
//			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//			IOUtils.copy(inputStream, response.getOutputStream());
//			// }
//		} catch (Exception e) {
//			e.printStackTrace();
//			LOGGER.error("********Oops Something went wrong **********" + e);
//		} finally {
//			if (inputStream != null)
//				try {
//					inputStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//					LOGGER.error("********Oops Something went wrong **********" + e);
//				}
//		}
//
//	}
	
}
