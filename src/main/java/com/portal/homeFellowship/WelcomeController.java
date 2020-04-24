package com.portal.homeFellowship;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
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
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portal.homeFellowship.model.*;
import com.portal.homeFellowship.service.*;
import com.portal.homeFellowship.utility.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
// @RequestMapping("/admin")
public class WelcomeController {

	// @RequestMapping(value = { "/" }, method = RequestMethod.GET)
	// public String home() throws Exception{
	// System.out.println("here");
	// return "index";
	// }

	// @RequestMapping("/")
	// public String home(Map<String, Object> model) {
	// System.out.println("here I am2");
	// //model.put("message", "HowToDoInJava Reader !!");
	// return "index";
	// }

	// @RequestMapping("/next")
	// public String next(Map<String, Object> model) {
	// System.out.println("here I am");
	// //model.put("message", "You are in new page !!");
	// return "dashboard";
	// }

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

	public static Response ADAuthenticateUserName(String username) {
		try {
			String urlx = "LDAP://10.2.104.10:389";
			String domain = "@ecobank.group";
			LOGGER.info("++++++ urlx ==> " + urlx + " ++++++");
			LOGGER.info("++++++ domain ==> " + domain + " ++++++");
			Hashtable<String, Object> ldapEnv = new Hashtable<String, Object>();
			ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
			ldapEnv.put(Context.SECURITY_PRINCIPAL, "RAFIKI" + domain);
			ldapEnv.put(Context.SECURITY_CREDENTIALS, "EPH1kif@RNgnk68#");
			ldapEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			ldapEnv.put(Context.PROVIDER_URL, urlx);
			ldapEnv.put("java.naming.ldap.attributes.binary", "objectSID");
			ldapEnv.put(Context.REFERRAL, "follow");

			DirContext ctx = new InitialDirContext(ldapEnv);
			String searchFilter = "(&(objectClass=user)(sAMAccountName=" + username + "))";
			String searchBase = "OU=Usr,DC=ecobank,DC=group";

			SearchControls searchControls = new SearchControls();
			searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			NamingEnumeration<SearchResult> results = ctx.search(searchBase, searchFilter, searchControls);
			if (results != null && results.hasMoreElements()) {
				LOGGER.info("results" + results.next());
				return new Response("00", "User " + username + " Authenticated Successfully.");
			}
		} catch (AuthenticationException e) {
			LOGGER.warn("*** Invalid Login Credentials for username ==> " + username + ". ***");
			return new Response("97", "Invalid Login Credentials.");
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("*** Error Performing LDAP Authentication for " + username + ". ***", e);
			return new Response("99", "Oops Something went wrong ==> " + e.toString());
		} finally {

		}
		return new Response();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/createuser" }, method = RequestMethod.GET)
	public String createUser(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());

		List<Role> userroles = inquiryService.getroles();
		model.addAttribute("userroles", userroles);

		if (getPrincipal().getUserRolesStr().contains("GROUPADMIN")) {
			model.addAttribute("groupAdmin", true);

		}
		return "createuser";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/loadcreateuser" }, method = RequestMethod.POST)
	public String loadCreateUser(@Valid UserWithRole userRoleAccount, RedirectAttributes redirectAttributes,
			ModelMap model) throws Exception {
		LOGGER.info("** loadcreateuser ==> " + userRoleAccount.getUserBranch() + " **");
		LOGGER.info("** loadcreateuser ==> " + userRoleAccount + " **");

		// Response validateEmail =
		// BasicUtil.validateEmail(userRoleAccount.getUserEmailAdd());
		//
		// if (!"00".equals(validateEmail.getResponseCode())) {
		//
		// redirectAttributes.addFlashAttribute("errorMessage",
		// (messageSource.getMessage("system.error", new Object[] { "" },
		// Locale.getDefault()))
		// + " - EMAIL NOT IN RIGHT FORMAT ");
		// return "redirect:/createuser";
		//
		// }

		userRoleAccount.setStepName("VALIDATOR");
		User1 user = new User1();
		user.setUserFullName(userRoleAccount.getUserFullName());
		user.setUserName(userRoleAccount.getUserName());
		user.setPassword(userRoleAccount.getPassword());
		user.setUserEmailAdd(userRoleAccount.getUserEmailAdd());
		user.setUserTransactionLimit(BigDecimal.ZERO);
		user.setUserRoles(userRoleAccount.getUserRoles());
		user.setAffiliateCode(userRoleAccount.getAffiliateCode() == null || userRoleAccount.getAffiliateCode().isEmpty()
				? getPrincipal().getAffiliate()
				: userRoleAccount.getAffiliateCode());
		Response creatUserroleresp = adminService.createUser(user, getPrincipal());
		LOGGER.info("** creatUserroleresp ==> " + creatUserroleresp + " **");

		if (creatUserroleresp == null || !("00".equals(creatUserroleresp.getResponseCode()))) {
			model.addAttribute("errorMessage",
					(messageSource.getMessage("createrole.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ " - "
							+ (creatUserroleresp != null && creatUserroleresp.getResponseMessage() != null
									? creatUserroleresp.getResponseMessage()
									: ""));

			model.addAttribute("loggedinuser", getPrincipal());

			List<Role> userroles = inquiryService.getroles();
			model.addAttribute("userroles", userroles);

			return "createuser";

		}

		// UsertoRoleReq userToRole = new UsertoRoleReq();
		// userToRole.setUserRoles(userRoleAccount.getUserRoles());
		// //userToRole.setStepId(BasicUtil.splitTrim(userRoleAccount.getStepName()));
		// userToRole.setUserName(userRoleAccount.getUserName());
		//
		// LOGGER.info("** Userroleresp ==> " + userToRole + " **");
		// Response Userroleresp = adminService.assignUsertoRole(userToRole,
		// getPrincipal());
		// LOGGER.info("** Userroleresp ==> " + Userroleresp + " **");
		//
		// if (Userroleresp == null || !("00".equals(Userroleresp.getResponseCode()))) {
		// model.addAttribute("errorMessage",
		// (messageSource.getMessage("successful.createuser", new Object[] { "" },
		// Locale.getDefault()))
		// + " but "
		// + (messageSource
		// .getMessage("assignrole.unsuccessful", new Object[] { "" },
		// Locale.getDefault()))
		// + " - "
		// + (Userroleresp != null && Userroleresp.getResponseMessage() != null
		// ? Userroleresp.getResponseMessage()
		// : ""));
		//
		// model.addAttribute("loggedinuser", getPrincipal());
		// List<Role> userroles = inquiryService.getroles();
		// model.addAttribute("userroles", userroles);
		//
		// return "createuser";
		//
		// }

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.createuser", new Object[] { "" }, Locale.getDefault())));

		LocalDate date1 = LocalDate.now();
		java.util.Date date2 = java.sql.Date.valueOf(date1);
		String date = MARSHARLLERDATEFORMAT.format(date2);

		AuditMasterDetail auditMasterDetail = new AuditMasterDetail();
		auditMasterDetail.setUserName(getPrincipal().getAdUsername());
		auditMasterDetail.setEventDesc(getPrincipal().getAdUsername() + " of affiliate " + getPrincipal().getAffiliate()
				+ " created a user with the following attributes; Full name is - " + " ' "
				+ userRoleAccount.getUserFullName() + " ' " + ", UserName - " + " ' " + userRoleAccount.getUserName()
				+ " ' " + ", Email Address - " + " ' " + userRoleAccount.getUserEmailAdd() + " ' " + ", User Branch - "
				+ " ' " + userRoleAccount.getUserBranch() == null
						? ""
						: userRoleAccount.getUserBranch() + " ' " + ", Transaction Limit - " + " ' "
								+ user.getUserTransactionLimit() + " ' " + " and configured on Role - " + " ' "
								+ userRoleAccount.getUserRoles() + " ' " + " with User ID "
								+ creatUserroleresp.getResponseMessage() + " and role " +

								// (("OPERATIONS").equalsIgnoreCase(userRoleAccount.getUserRoles())
								// ? (" with step ID " + userRoleAccount.getStepName())
								// : (userRoleAccount.getUserRoles()))
								(userRoleAccount.getUserRoles())

								+ " on " + date);
		auditMasterDetail.setEventSrc("CREATE USER");
		auditMasterDetail.setServerIp(getPrincipal().getCurrentLoginIPAddress());
		auditMasterDetail.setExtRef(creatUserroleresp.getResponseMessage() + "CU");
		Response insertIntoAuditmaster = auditService.insertIntoAdminAudit(auditMasterDetail, getPrincipal());

		return "redirect:/createuser";
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

			List<User> pendusers = inquiryService.getpenduser(getPrincipal().getAffiliate());
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

			List<User> pendusers = inquiryService.getpenduser(getPrincipal().getAffiliate());
			model.addAttribute("pendingUsers", pendusers);
			model.addAttribute("loggedinuser", getPrincipal());

			return "redirect:/edituser";
		}

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.disableuser", new Object[] { "" }, Locale.getDefault())));

		return "redirect:/edituser";

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/approveenableuser", method = RequestMethod.GET)
	public String approveenableuser(RedirectAttributes redirectAttributes, ModelMap model) throws Exception {

		List<User> pendusers = inquiryService.getPendAuthEditUser("ENABLE", getPrincipal().getAffiliate());
		model.addAttribute("pendingUsers", pendusers);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("enableUser", true);
		return "pendingenable";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/approvedisableuser", method = RequestMethod.GET)
	public String approvedisableuser(RedirectAttributes redirectAttributes, ModelMap model) throws Exception {
		List<User> pendusers = inquiryService.getPendAuthEditUser("DISABLE", getPrincipal().getAffiliate());
		model.addAttribute("pendingUsers", pendusers);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("disableUser", true);
		return "pendingenable";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/authenablependuser-{userID}", method = RequestMethod.GET)
	public String authenablependuser(@PathVariable String userID, RedirectAttributes redirectAttributes, ModelMap model)
			throws Exception {
		Response authuserresp = adminService.authpenduser(userID, "APPENABLE", getPrincipal());
		if (authuserresp == null || !("00".equals(authuserresp.getResponseCode()))) {
			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("enableuser.unsuccessful1", new Object[] { "" }, Locale.getDefault()))
							+ (" - ")
							+ ((authuserresp != null && authuserresp.getResponseMessage() != null)
									? authuserresp.getResponseMessage()
									: ""));
			List<User> pendusers = inquiryService.getpenduser(getPrincipal().getAffiliate());
			model.addAttribute("pendingUsers", pendusers);
			model.addAttribute("loggedinuser", getPrincipal());
			return "redirect:/approveenableuser";
		}
		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.enableuser1", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/approveenableuser";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/authdisablependuser-{userID}", method = RequestMethod.GET)
	public String authdisablependuser(@PathVariable String userID, RedirectAttributes redirectAttributes,
			ModelMap model) throws Exception {
		Response authuserresp = adminService.authpenduser(userID, "APPDISABLE", getPrincipal());
		if (authuserresp == null || !("00".equals(authuserresp.getResponseCode()))) {
			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("disableuser.unsuccessful1", new Object[] { "" }, Locale.getDefault()))
							+ (" - ")
							+ ((authuserresp != null && authuserresp.getResponseMessage() != null)
									? authuserresp.getResponseMessage()
									: ""));
			List<User> pendusers = inquiryService.getpenduser(getPrincipal().getAffiliate());
			model.addAttribute("pendingUsers", pendusers);
			model.addAttribute("loggedinuser", getPrincipal());
			return "redirect:/approvedisableuser";
		}

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.disableuser1", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/approvedisableuser";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/createusertorole" }, method = RequestMethod.GET)
	public String createUserToRole(ModelMap model) throws Exception {

		model.addAttribute("loggedinuser", getPrincipal());

		List<Role> userroles = inquiryService.getroles();
		model.addAttribute("userroles", userroles);

		List<UserStepPositionResp> getSteps = inquiryService.getSteps();

		model.addAttribute("getSteps", getSteps);

		return "createusertorole";
	}

	/**
	 * This method will handle createuserrole
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/createusertorole" }, method = RequestMethod.POST)
	public String loadUserToRole(@Valid UsertoRoleReq user, RedirectAttributes redirectAttributes, ModelMap model)
			throws Exception {

		Response resp = adminService.userNameCheck(user.getUserName());

		if ("11".equals(resp.getResponseCode())) {
			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("system.error", new Object[] { "" }, Locale.getDefault()))
							+ " - USERNAME NOT FOUND");
			return "redirect:/createusertorole";
		}

		List<User1> getUserDetails = inquiryService.getUserDetails(user.getUserName());

		if (!getPrincipal().getUserRolesStr().contains("GROUPADMIN") && getUserDetails.size() > 0
				&& !(getPrincipal().getAffiliate().equalsIgnoreCase(getUserDetails.get(0).getAffiliateCode()))) {

			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("system.error", new Object[] { "" }, Locale.getDefault()))
							+ " - AFFILIATE ADMIN CANNOT ASSIGN USER IN ANOTHER AFFILIATE TO A ROLE ");
			return "redirect:/createusertorole";

		}

		LOGGER.info("++loadusertorole ==> " + user);
		user.setStepName("VALIDATOR");
		Response Userroleresp = adminService.assignUsertoRole(user, getPrincipal());

		if (Userroleresp == null || !("00".equals(Userroleresp.getResponseCode()))) {
			model.addAttribute("errorMessage",
					(messageSource.getMessage("assignrole.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ (" - ")
							+ ((Userroleresp != null && Userroleresp.getResponseMessage() != null)
									? Userroleresp.getResponseMessage()
									: ""));

			model.addAttribute("loggedinuser", getPrincipal());

			List<UserStepPositionResp> getSteps = inquiryService.getSteps();

			model.addAttribute("getSteps", getSteps);

			List<Role> userroles = inquiryService.getroles();
			model.addAttribute("userroles", userroles);
			return "createusertorole";

		}

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.assignrole", new Object[] { "" }, Locale.getDefault())));

		LocalDate date1 = LocalDate.now();
		java.util.Date date2 = java.sql.Date.valueOf(date1);
		String date = MARSHARLLERDATEFORMAT.format(date2);

		AuditMasterDetail auditMasterDetail = new AuditMasterDetail();
		auditMasterDetail.setUserName(getPrincipal().getAdUsername());
		auditMasterDetail.setEventDesc(getPrincipal().getAdUsername() + " of affiliate " + getPrincipal().getAffiliate()
				+ " assigned another role " +

				(("OPERATIONS").equalsIgnoreCase(user.getRoleID()) ? " OPERATIONS on step " + user.getStepName()
						: user.getRoleID())
				+ " to the user " + user.getUserName() + " with user to role ID " + " ' "
				+ Userroleresp.getResponseMessage() + " ' " + " on " + date);
		auditMasterDetail.setEventSrc("ADD USER TO ROLE");
		auditMasterDetail.setServerIp(getPrincipal().getCurrentLoginIPAddress());
		auditMasterDetail.setExtRef(Userroleresp.getResponseMessage() + "AUTR");
		Response insertIntoAuditmaster = auditService.insertIntoAdminAudit(auditMasterDetail, getPrincipal());
		return "redirect:/createusertorole";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/authusertorole" }, method = RequestMethod.GET)
	public String authusertorole(ModelMap model) throws Exception {
		model.addAttribute("loggedinuser", getPrincipal());

		List<UserToRoleApp> pendingUsertorole = inquiryService.getpendusertorole(getPrincipal().getAdUsername());
		model.addAttribute("pendingUsertorole", pendingUsertorole);
		model.addAttribute("loggedinusername", getPrincipal().getAdUsername().toLowerCase());

		return "pendinguserstorole";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/authpendusertorole-{usertoroleID}", method = RequestMethod.GET)
	public String authpendusertorole(@PathVariable String usertoroleID, RedirectAttributes redirectAttributes,
			ModelMap model) throws Exception {
		List<UserToRoleApp> pendingUsertorole = inquiryService.getpendusertorole(getPrincipal().getAdUsername());

		UserToRoleApp editUser = BasicUtil.getEditUserRoleFromList(usertoroleID, pendingUsertorole);

		List<User1> getUserDetails = inquiryService.getUserDetails(editUser.getUserName());

		if (!getPrincipal().getUserRolesStr().contains("GROUPADMIN") && getUserDetails.size() > 0
				&& !(getPrincipal().getAffiliate().equalsIgnoreCase(getUserDetails.get(0).getAffiliateCode()))) {

			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("system.error", new Object[] { "" }, Locale.getDefault()))
							+ " - AFFILIATE ADMIN CANNOT AUTHORIZE USER TO ROLE FOR A USER IN ANOTHER AFFILIATE");
			return "redirect:/authusertorole";

		}

		LOGGER.info("++ usertoroleID ==> " + usertoroleID + " ++");
		Response authuserresp = adminService.authpendusertorole(usertoroleID, "APPROVE", getPrincipal());

		if (authuserresp == null || !("00".equals(authuserresp.getResponseCode()))) {
			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("assignrole.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ (" - ")
							+ ((authuserresp != null && authuserresp.getResponseMessage() != null)
									? authuserresp.getResponseMessage()
									: ""));

			List<User> pendusers = inquiryService.getpenduser(getPrincipal().getAffiliate());
			model.addAttribute("pendingUsers", pendusers);

			model.addAttribute("loggedinuser", getPrincipal());
			model.addAttribute("pendingUsertorole", pendingUsertorole);
			return "redirect:/authusertorole";
		}

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.assignrole1", new Object[] { "" }, Locale.getDefault())));

		LocalDate date1 = LocalDate.now();
		java.util.Date date2 = java.sql.Date.valueOf(date1);
		String date = MARSHARLLERDATEFORMAT.format(date2);

		AuditMasterDetail auditMasterDetail = new AuditMasterDetail();
		auditMasterDetail.setUserName(getPrincipal().getAdUsername());
		auditMasterDetail.setEventDesc(getPrincipal().getAdUsername() + " of affiliate " + getPrincipal().getAffiliate()
				+ " approved the newly assigned role " +

				(("OPERATIONS").equalsIgnoreCase(editUser.getRoleName())
						? " OPERATIONS configured  on step " + editUser.getStepName()
						: editUser.getRoleName())
				+ "to the user " + editUser.getUserName() + " with user to role ID " + " ' " + usertoroleID + " ' "
				+ " on " + date);
		auditMasterDetail.setEventSrc("ADD USER TO ROLE");
		auditMasterDetail.setServerIp(getPrincipal().getCurrentLoginIPAddress());
		auditMasterDetail.setExtRef(usertoroleID + "AUTR");
		Response insertIntoAuditmaster = auditService.insertIntoAdminAudit(auditMasterDetail, getPrincipal());

		return "redirect:/authusertorole";

	}

	/**
	 * This method will handle daily volume
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/editusertorole" }, method = RequestMethod.POST)
	public String editusertorole(@Valid EditUserDetails user, RedirectAttributes redirectAttributes, ModelMap model)
			throws Exception {
		LOGGER.info("++EditUserDetails ==> " + user);

		List<User1> getUserDetails = inquiryService.getUserDetails(user.getUserName());

		if (!getPrincipal().getUserRolesStr().contains("GROUPADMIN") && getUserDetails.size() > 0
				&& !(getPrincipal().getAffiliate().equalsIgnoreCase(getUserDetails.get(0).getAffiliateCode()))) {

			model.addAttribute("errorMessage",
					(messageSource.getMessage("system.error", new Object[] { "" }, Locale.getDefault()))
							+ " - AFFILIATE ADMIN CANNOT REMOVE ROLE FROM A USER FOR A USER IN ANOTHER AFFILIATE");
			return "redirect:/editusertorole-" + user.getUserID();

		}
		user.setStepId(1);
		Response Userroleresp = adminService.disableusertorole(user, getPrincipal());

		if (Userroleresp == null || !("00".equals(Userroleresp.getResponseCode()))) {
			model.addAttribute("errorMessage",
					(messageSource.getMessage("disablerole.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ " - "
							+ ((Userroleresp != null && Userroleresp.getResponseMessage() != null)
									? Userroleresp.getResponseMessage()
									: ""));

			model.addAttribute("loggedinuser", getPrincipal());

			List<Role> userroles = inquiryService.getroles();
			model.addAttribute("userroles", userroles);
			return "edituserroles";

		}

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.editrole", new Object[] { "" }, Locale.getDefault())));

		LocalDate date1 = LocalDate.now();
		java.util.Date date2 = java.sql.Date.valueOf(date1);
		String date = MARSHARLLERDATEFORMAT.format(date2);

		AuditMasterDetail auditMasterDetail = new AuditMasterDetail();
		auditMasterDetail.setUserName(getPrincipal().getAdUsername());
		auditMasterDetail.setEventDesc(getPrincipal().getAdUsername() + " of affiliate " + getPrincipal().getAffiliate()
				+ " removed the role " + user.getUserRoles() + " from user of ID" + " ' " + user.getUserID() + " ' "
				+ " with user to role ID of " + " ' " + Userroleresp.getResponseMessage() + " ' " + " on " + date);
		auditMasterDetail.setEventSrc("REMOVE USER ROLE");
		auditMasterDetail.setServerIp(getPrincipal().getCurrentLoginIPAddress());
		auditMasterDetail.setExtRef(Userroleresp.getResponseMessage() + "RUR");
		Response insertIntoAuditmaster = auditService.insertIntoAdminAudit(auditMasterDetail, getPrincipal());

		return "redirect:/removeusertorole";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/approveeditrole" }, method = RequestMethod.GET)
	public String approveeditrole(ModelMap model) throws Exception {
		List<User> pendusers = inquiryService.getPendAuthEditUser("APPEDITROLE", getPrincipal().getAffiliate());

		List<UserRemoveRole> responses = inquiryService.getPendingRemoveUser(getPrincipal().getAffiliate());
		LOGGER.info("+++ responses ==> " + responses);
		model.addAttribute("pendingUsers", responses);
		model.addAttribute("approveeditrole", true);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("loggedinusername", getPrincipal().getAdUsername().toLowerCase());

		return "pendingusers";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/approveeditrole-{userID}|{roleName}", method = RequestMethod.GET)
	public String approveeditrole(@PathVariable String userID, @PathVariable String roleName,
			RedirectAttributes redirectAttributes, ModelMap model) throws Exception {

		List<User1> getUserDetails = inquiryService.getUserDetails(userID);

		if (!getPrincipal().getUserRolesStr().contains("GROUPADMIN") && getUserDetails.size() > 0
				&& !(getPrincipal().getAffiliate().equalsIgnoreCase(getUserDetails.get(0).getAffiliateCode()))) {

			redirectAttributes.addFlashAttribute("errorMessage", (messageSource.getMessage("system.error",
					new Object[] { "" }, Locale.getDefault()))
					+ " - AFFILIATE ADMIN CANNOT AUTHORIZE ROLE REMOVAL FROM A USER FOR A USER IN ANOTHER AFFILIATE");

			return "redirect:/approveeditrole";

		}

		Response authuserresp = adminService.authdisableusertorole(userID, roleName, "APPROVE", getPrincipal());

		if (authuserresp == null || !("00".equals(authuserresp.getResponseCode()))) {
			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("edituser.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ (" - ")
							+ ((authuserresp != null && authuserresp.getResponseMessage() != null)
									? authuserresp.getResponseMessage()
									: ""));

			List<User> pendusers = inquiryService.getpenduser(getPrincipal().getAffiliate());
			model.addAttribute("pendingUsers", pendusers);
			model.addAttribute("approveeditrole", true);
			model.addAttribute("loggedinuser", getPrincipal());

			return "redirect:/approveeditrole";
		}
		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser2", new Object[] { "" }, Locale.getDefault())));

		LocalDate date1 = LocalDate.now();
		java.util.Date date2 = java.sql.Date.valueOf(date1);
		String date = MARSHARLLERDATEFORMAT.format(date2);

		List<UserRemoveRole> responses = inquiryService.getPendingRemoveUser(getPrincipal().getAffiliate());
		UserRemoveRole edituser = BasicUtil.getPendingUser(userID, responses);

		AuditMasterDetail auditMasterDetail = new AuditMasterDetail();
		auditMasterDetail.setUserName(getPrincipal().getAdUsername());
		auditMasterDetail.setEventDesc(getPrincipal().getAdUsername() + " of affiliate " + getPrincipal().getAffiliate()
				+ " approved the removal of the role " + edituser.getRoleID() + " from user of ID" + " ' " + userID
				+ " ' " + " and user to role ID" + " ' " + authuserresp.getResponseMessage() + " ' " + " on " + date);
		auditMasterDetail.setEventSrc("REMOVE USER ROLE");
		auditMasterDetail.setServerIp(getPrincipal().getCurrentLoginIPAddress());
		auditMasterDetail.setExtRef(authuserresp.getResponseMessage() + "RUR");
		Response insertIntoAuditmaster = auditService.insertIntoAdminAudit(auditMasterDetail, getPrincipal());

		return "redirect:/approveeditrole";

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

	/**
	 * This method will handle daily volume
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/editusertorole-{userID}" }, method = RequestMethod.GET)
	public String editUsertorole(@PathVariable String userID, RedirectAttributes redirectAttributes, ModelMap model)
			throws Exception {

		LOGGER.info("+++ userID ==> " + userID);
		List<User> userroledetails = inquiryService.getuser(userID, "PERUSER", getPrincipal().getAffiliate());
		User user = userroledetails.get(0);
		LOGGER.info("+++ user ==> " + user.toString());
		List<String> finalUserroles = new ArrayList<>();
		UserToRoleResponse rolesperusers = inquiryService.getRolesForUser(getPrincipal().getAdUsername(),
				getPrincipal().getCurrentLoginIPAddress(), Long.valueOf(userID));
		if (rolesperusers != null && rolesperusers.getUserRole() != null && !rolesperusers.getUserRole().isEmpty()) {
			for (UserRole userrole : rolesperusers.getUserRole()) {
				finalUserroles.add(BasicUtil.removeCustom(userrole.getRoleName()));
			}
			LOGGER.info("+++ userroles ==> " + finalUserroles);
			model.addAttribute("userroles", finalUserroles);
		}
		model.addAttribute("user", user);

		model.addAttribute("loggedinuser", getPrincipal());

		return "edituserroles";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/edituserdetails-{userID}" }, method = RequestMethod.GET)
	public String edituserdetails(@PathVariable String userID, RedirectAttributes redirectAttributes, ModelMap model)
			throws Exception {

		LOGGER.info("++EditUserDetails ==> " + getPrincipal().getUserRolesStr().contains("GROUPADMIN"));
		if (getPrincipal().getUserRolesStr().contains("GROUPADMIN")) {
			model.addAttribute("groupAdmin", true);
		}

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
		String[] emailArr = (user.getUserEmailAdd() != null ? user.getUserEmailAdd() : "").split(",");
		model.addAttribute("emailArr", emailArr);
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

		List<User1> getUserDetails = inquiryService.getUserDetails(user.getUserName());

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

			model.addAttribute("adduserroles", userroles);
			Response branchname = inquiryService.getbranchname(user.getUserBranch());
			model.addAttribute("brancheinfo", branchname);
			List<Branch> branches = inquiryService.getAuthorisedBranchesForAffiliate(getPrincipal().getAffiliate());
			model.addAttribute("branches", branches);

			List<Affiliate> affiliate = inquiryService.getAuthorisedAffiliates();
			model.addAttribute("affiliate", affiliate);
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

	/**
	 * This method will handle edit user
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/approveuserdetails" }, method = RequestMethod.GET)
	public String approveuserdetails(ModelMap model) throws Exception {

		List<EditUser> pendusers = inquiryService.getpendedituser1("APPUSERDETAILS", getPrincipal().getAffiliate());
		LOGGER.info("pendinguser " + pendusers);
		model.addAttribute("pendingUsers", pendusers);
		model.addAttribute("appuserdetails", true);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("loggedinusername", getPrincipal().getAdUsername().toLowerCase());

		return "editpendingusers";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/authapproveuserdetails-{userID}", method = RequestMethod.GET)
	public String authapproveuserdetails(@PathVariable String userID, RedirectAttributes redirectAttributes,
			ModelMap model) throws Exception {

		List<EditUser> editPendusers = inquiryService.getpendedituser1("APPUSERDETAILS", getPrincipal().getAffiliate());

		EditUser editUser = BasicUtil.getEditUserFromList(userID, editPendusers);

		List<User1> getUserDetails = inquiryService.getUserDetails(userID);

		if (!getPrincipal().getUserRolesStr().contains("GROUPADMIN") && getUserDetails.size() > 0
				&& !(getPrincipal().getAffiliate().equalsIgnoreCase(getUserDetails.get(0).getAffiliateCode()))) {

			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("system.error", new Object[] { "" }, Locale.getDefault()))
							+ " - AFFILIATE ADMIN CANNOT APPROVE EDITED USER DETAILS FOR A USER IN ANOTHER AFFILIATE");
			return "redirect:/edituserdetails-" + user.getUserID();

		}

		Response authuserresp = adminService.authedituser(userID, "APPROVE", getPrincipal());

		if (authuserresp == null || !("00".equals(authuserresp.getResponseCode()))) {
			redirectAttributes.addFlashAttribute("errorMessage",
					(messageSource.getMessage("edituser.unsuccessful", new Object[] { "" }, Locale.getDefault()))
							+ (" - ") + authuserresp.getResponseMessage());

			List<User> pendusers = inquiryService.getpenduser(getPrincipal().getAffiliate());
			model.addAttribute("pendingUsers", pendusers);
			model.addAttribute("appuserdetails", true);
			model.addAttribute("loggedinuser", getPrincipal());

			return "redirect:/approveuserdetails";
		}

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));

		LocalDate date1 = LocalDate.now();
		java.util.Date date2 = java.sql.Date.valueOf(date1);
		String date = MARSHARLLERDATEFORMAT.format(date2);

		AuditMasterDetail auditMasterDetail = new AuditMasterDetail();
		auditMasterDetail.setUserName(getPrincipal().getAdUsername());
		auditMasterDetail.setEventDesc(getPrincipal().getAdUsername() + " of affiliate " + getPrincipal().getAffiliate()
				+ " approved the edited details of user ID" + " ' " + userID + " ' "
				+ " that changed from old full name - " + editUser.getOldUserFullName() + " to new full name of - "
				+ editUser.getNewUserFullName() + ", old user branch of - " + editUser.getOldUserBranch()
				+ " to new user branch of - " + editUser.getNewUserBranch() + ", old user email address of - "
				+ editUser.getOldUserEmailAdd() + " to new user email address of - " + editUser.getNewUserEmailAdd()
				+ ", old user transaction limit of - " + editUser.getOldUserTransactionLimit()
				+ " to new user transaction limit of - " + editUser.getNewUserTransactionLimit()
				+ ", old affiliate code of - " + editUser.getOldAffiliateCode() + " to new affiliate code of - "
				+ editUser.getNewAffiliateCode()

				+ " on " + date);
		auditMasterDetail.setEventSrc("EDIT USER DETAILS");
		auditMasterDetail.setServerIp(getPrincipal().getCurrentLoginIPAddress());
		auditMasterDetail.setExtRef(userID + "EUD");
		Response insertIntoAuditmaster = auditService.insertIntoAdminAudit(auditMasterDetail, getPrincipal());

		return "redirect:/approveuserdetails";

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/", "/dashboard" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model, HttpServletRequest httpRequest) throws Exception {
		LOGGER.info("+++ DASHBOARD ==> ");

		UserStepPositionResp response = inquiryService.getUserStepPosition(getPrincipal().getAdUsername());

		LOGGER.info("responseStep" + response);

		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("stepname", response.getStepName());

		return "dashboard";
	}
	///////////////////// END OF
	///////////////////// ADMIN///////////////////////////////////////////////

	///////////////////// REPORT

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/weeklyReport", method = RequestMethod.GET)
	public String weeklyReport1(ModelMap model, HttpServletRequest httpRequest) throws Exception {

		UserStepPositionResp response = inquiryService.getUserStepPosition(getPrincipal().getAdUsername());

		model.addAttribute("stepname", response.getStepName());

		return "weeklyReport";
	}

	@RequestMapping(value = "/weeklyReport", method = RequestMethod.POST)
	public void weeklyReport(@Valid WeeklyReport report, ModelMap model, HttpServletRequest request,
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
		Response response = inquiryService.sendSMS(request);

		LOGGER.info("response " + response);

		redirectAttributes.addFlashAttribute("successMessage",
				(messageSource.getMessage("successful.edituser.auth", new Object[] { "" }, Locale.getDefault())));
		return "redirect:/sendSMS";
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

		List<PrayerRequest> response = inquiryService.getPrayerRequest();
		model.addAttribute("prayer", response);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("username", getPrincipal().getAdUsername());

		return "viewPrayer";
	}
}
