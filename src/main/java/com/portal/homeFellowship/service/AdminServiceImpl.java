package com.portal.homeFellowship.service;

import java.util.Arrays;
import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.portal.homeFellowship.dao.AdminDao;
import com.portal.homeFellowship.model.*;
import com.portal.homeFellowship.utility.BasicUtil;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@Override
	public Response ADAuthenticateUser(String username, String pwd) {
		try {
			String urlx = environment.getRequiredProperty("ariba.ldap.url") == null ? ""
					: environment.getRequiredProperty("ariba.ldap.url");
			String domain = environment.getRequiredProperty("ariba.ldap.part") == null ? ""
					: environment.getRequiredProperty("ariba.ldap.part");
			LOGGER.info("++++++ urlx ==> " + urlx + " ++++++");
			LOGGER.info("++++++ domain ==> " + domain + " ++++++");
			Hashtable<String, Object> ldapEnv = new Hashtable<String, Object>();
			ldapEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
			ldapEnv.put(Context.SECURITY_PRINCIPAL, username + domain);
			ldapEnv.put(Context.SECURITY_CREDENTIALS, pwd);
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
	
	@Override
	public Response createUser(User1 user, UserAdmin principal) {
		User newuser = new User();
		newuser.setUserID(1);
		newuser.setUserFullName(user.getUserFullName());
		newuser.setUserName(user.getUserName());
		newuser.setUserRoles(user.getUserRoles());
		String userrole = user.getUserRoles() != null ? user.getUserRoles().trim() : "";
		LOGGER.info("++user role ==> " + userrole);
		if ("OPERATIONS".equalsIgnoreCase(userrole))
			newuser.setOperationUser(true);
		else
			newuser.setOperationUser(false);
		//newuser.setUserTransactionLimit(user.getUserTransactionLimit());
		//newuser.setPasswordExpiryPolicy("password unique to user");
		newuser.setPassword(user.getPassword());
		newuser.setServerIP(BasicUtil.getClientIp());
		newuser.setAuthorisedUserFlag(false);
		newuser.setUserEmailAdd(user.getUserEmailAdd());
		newuser.setAffiliateCode(user.getAffiliateCode());
		newuser.setCreatedBy(principal.getAdUsername());
		//newuser.setServiceClient(environment.getRequiredProperty("moduleID"));
		Response response = adminDao.create_user(newuser);
		return new Response(response.getResponseCode(), response.getResponseMessage());
	}
	
	@Override
	public Response assignUsertoRole(UsertoRoleReq userToRole, UserAdmin principal) {
		UserToRole newUserRole = new UserToRole();
		newUserRole.setRoleID(userToRole.getUserRoles());
		newUserRole.setStepID((userToRole.getStepId()==null||userToRole.getStepId().isEmpty()) ? "0" : userToRole.getStepId());
		newUserRole.setUserName1(userToRole.getUserName());
		newUserRole.setServerIP(BasicUtil.getClientIp());
		newUserRole.setUserName(principal.getAdUsername());
		//newuser.setServiceClient(environment.getRequiredProperty("moduleID"));
		LOGGER.info("** newUserRole ==> " + newUserRole + " **");
		return adminDao.create_user_to_role(newUserRole);
	}


	@Override
	public Response authpenduser(String userID, String flag, UserAdmin principal) {
		
		AuthoriseUser authuser = new AuthoriseUser();
		authuser.setUserID(Long.valueOf(userID));
		authuser.setAuthoriseUser(flag);
		authuser.setUserName(principal.getAdUsername());
		authuser.setServerIP(BasicUtil.getClientIp());
		//authuser.setServiceClient(environment.getRequiredProperty("moduleID"));
		return adminDao.authorise_user(authuser);
	}
	

	@Override
	public Response authpendusertorole(String userToRoleID, String flag, UserAdmin principal) {
		AuthUsertoroleReq user = new AuthUsertoroleReq();
		user.setPflag(flag);
		user.setUserName(principal.getAdUsername());
		user.setUserToroleId(Long.valueOf(userToRoleID));
		return adminDao.auth_user_to_role(user);
	}


	@Override
	public Response userNameCheck(String userName) {
		return adminDao.userNameCheck(userName);
	}


	@Override
	public Response disableusertorole(EditUserDetails user, UserAdmin principal) {
		user.setEditusername(principal.getAdUsername());
		return adminDao.disable_user_to_role(user);
	}


	@Override
	public Response authdisableusertorole(String userID, String roleName, String flag, UserAdmin principal) {
		EditUserDetails edituser = new EditUserDetails();
		edituser.setEditusername(principal.getAdUsername());
		edituser.setUserID(userID);
		edituser.setFlag(flag);
		edituser.setUserRoles(roleName);
		return adminDao.auth_disable_user_to_role(edituser);
	}


	@Override
	public Response edituser(EditUserDetails user, UserAdmin principal) {
		user.setEditusername(principal.getAdUsername());
		user.setServerIP(BasicUtil.getClientIp());
		return adminDao.edit_user(user);
	}


	@Override
	public Response authedituser(String userID, String flag, UserAdmin principal) {
		EditUserDetails user = new EditUserDetails();
		user.setUserID(userID);
		user.setEditusername(principal.getAdUsername());
		user.setFlag(flag);
		user.setServerIP(BasicUtil.getClientIp());
		return adminDao.auth_edit_user(user);
	}
	

	

	@Override
	public Response prayerRequest(PrayerRequest request) {
		return adminDao.prayerRequest(request);
	}



}
