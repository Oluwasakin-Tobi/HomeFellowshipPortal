package com.portal.homeFellowship.service;

import com.portal.homeFellowship.model.*;

public interface AdminService {
	
	Response ADAuthenticateUser(String username, String pwd);

	Response createUser(User1 user, UserAdmin principal);

	Response assignUsertoRole(UsertoRoleReq userToRole, UserAdmin principal);

	Response authpendusertorole(String userToRoleID, String flag, UserAdmin principal);

	Response userNameCheck(String userName);

	Response disableusertorole(EditUserDetails user, UserAdmin principal);

	Response authdisableusertorole(String userID, String roleName, String flag, UserAdmin principal);

	Response edituser(EditUserDetails user, UserAdmin principal);

	Response authedituser(String userID, String flag, UserAdmin principal);

	Response authpenduser(String userID, String flag, UserAdmin principal);
	
	
	
	
	

	Response prayerRequest(PrayerRequest request);

	Response welfareRequest(Welfare request);

	Response specialAnnouncement(Announcement request);

}
