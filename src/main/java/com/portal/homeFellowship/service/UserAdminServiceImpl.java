package com.portal.homeFellowship.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.portal.homeFellowship.dao.InquiryDao;
import com.portal.homeFellowship.model.*;
import com.portal.homeFellowship.utility.BasicUtil;

@Service("userAdminService")
public class UserAdminServiceImpl implements UserAdminService{
	
	final static Logger LOGGER = LoggerFactory.getLogger(UserAdminServiceImpl.class); 
	@Autowired
	private UserAdmin user;
	@Autowired
	private Administration response;
	@Autowired
	private Environment environment; 
	@Autowired
	private InquiryDao inquiryDao;
	
	@Autowired
	AdminService adminService;
	
	

	 
	@Override
	public synchronized Administration performBasicADAuthentication(final String userID, final String password,
			final String ip) throws Exception{
		UserDetails userprofile = null;
		user.setAdUsername(userID);
		user.setCurrentLoginIPAddress(ip);
		Boolean authenticate = false;
		try {
			LOGGER.info("********** The user is:" + userID + " *********");
//			Response response = new Response();
//			
////			TODO write a method to fetch password from db and check against the current password
//			
//			response = adminService.ADAuthenticateUser(userID, password);
//			if ("00".equals(response.getResponseCode())) {
//				authenticate=true;
//			}
//			else
//				authenticate=false;
			
			authenticate=true;
			LOGGER.info("********** isAuthenticated? " + authenticate + " *********");
		} catch (Exception ex) {
			ex.printStackTrace();
			LOGGER.error("***********Oops! Something went wrong. ***************" + ex);
            throw new BadCredentialsException(ex.getMessage());
		}
		response.setAuthenticate(authenticate);
		if (authenticate) {
			Boolean validate = false;
			try {
				UserProfile userProfile = new UserProfile();
				userProfile.setUserName(userID);
				userProfile.setServerIP(ip);
				UserResp isUserExist = inquiryDao.get_user_profile(userProfile);
				if ("00".equals(isUserExist.getResponseCode())){
					validate = true;
				}
				else
					validate = false;
				response.setValidate(validate);
				LOGGER.info("********Is user Validated: " + validate + " *********");
			} catch (Exception ex) {
				LOGGER.error("Oops! Something went wrong. " + ex); 
	            throw new BadCredentialsException(ex.getMessage());
			}
			if (validate) {
				try {
					
					LOGGER.info("********got here *********");
					UserProfile userProfile = new UserProfile();
					userProfile.setUserName(userID);
					userProfile.setServerIP(ip);
					UserResp userresp = inquiryDao.get_user_profile(userProfile);
					 userprofile = (UserDetails) userresp.getUserProfile();	
			
					Boolean edited = false;
					try {
						edited = userprofile.isEditedFlag();
						response.setEdited(edited);
						LOGGER.info("********Is user Edited: " + edited + " *********");
					} catch (Exception ex) {
						LOGGER.error("Oops! Something went wrong. " + ex); 
			            throw new BadCredentialsException(ex.getMessage());
					}
					
					Boolean editedRole = false;
					try {
						editedRole = userprofile.isEditedRoleFlag();
						response.setEditedRole(editedRole);
						LOGGER.info("********Is user Edited: " + editedRole + " *********");
					} catch (Exception ex) {
						LOGGER.error("Oops! Something went wrong. " + ex); 
			            throw new BadCredentialsException(ex.getMessage());
					}
					
					userprofile=userprofile==null?new UserDetails():userprofile;
					userprofile.setUserEmailAdd(userprofile.getUserEmailAdd());
					userprofile.setAffiliateCode(userprofile.getAffiliateCode());

					LOGGER.info("********userprofile ==> "+userprofile+"    *********");
					
					user.setFirstName(userprofile.getFirstName());
					user.setLastName(userprofile.getSurname());
					user.setEmail(userprofile.getUserEmailAdd());
					user.setUserID(userprofile.getUserID());   
					user.setPhoneNo(userprofile.getPhoneNo());
					user.setActive(userprofile.isActive());
					user.setAdUsername(userprofile.getUserName());
					user.setAffiliate(userprofile.getAffiliateCode());
					user.setOperationUser(userprofile.isOperationUser());
					user.setFullName(userprofile.getSurname()+" "+userprofile.getFirstName());
					user.setUserRolesStr(userprofile.getUserRoles());
					user.setPassword(userprofile.getPassword());
					
					LOGGER.info("***********userinfo***************" + user);

				} catch (Exception ex) {
					ex.printStackTrace();
					LOGGER.error("***********Oops! Something went wrong. ***************" + ex);
		            throw new BadCredentialsException(ex.getMessage());
				}
					Set<UserProfileAdmin> rolesperuser = new HashSet<>();

						UserProfileAdmin role = new UserProfileAdmin();
						role.setRoleID(Long.valueOf(1));
						role.setType(userprofile.getUserRoles());
						rolesperuser.add(role);
						
					user.setUserProfiles(rolesperuser); 
				response.setUser(user);
			} else {

				response.setUser(user);

			}
		} else {

		}
		return response;
	}
	

//	@Override
//	public List<String> getactivities(String roleid,String flag) throws Exception {
//		Custactivities custact = new Custactivities();
//		custact.setId(Long.valueOf(roleid));
//		custact.setFlag(flag);
//		
//		List<String> act = new ArrayList<>();
//		act = (List<String>) inquiryDao.getActivities(roleid, flag);
//		LOGGER.info("** custact ==> " + act + " **");
//		return act;
//	}

}
