package com.portal.homeFellowship.service;

import java.util.List;

import com.portal.homeFellowship.model.*;

public interface InquiryService {

	List<Role> getroles();

	List<Branch> getAuthorisedBranchesForAffiliate(String affiliateCode);

	List<User> getpenduser(String affiliate);

	List<UserToRoleApp> getpendusertorole(String adUsername);

	List<User> getPendAuthEditUser(String flag, String affiliate);

	List<User1> getUserDetails(String userName);

	List<UserStepPositionResp> getSteps();

	List<User> getuser(String userID, String pflag, String affiliate);

	List<UserRemoveRole> getPendingRemoveUser(String affiliate);

	UserToRoleResponse getRolesForUser(String adUsername, String currentLoginIPAddress, long userID);

	Response getbranchname(String userBranch);

	List<Affiliate> getAuthorisedAffiliates();

	List<EditUser> getpendedituser1(String flag, String affiliate);

	List<UserToRoleResp> getUserRoles(UserProfile user);

	UserStepPositionResp getUserStepPosition(String adUsername);

	List<Custactivities> getcustactivities(String flag);

	List<Affiliate> getAffiliateDetails(String username);
	//////////////////////////////////END OF ADMIN/////////////////////////////////////////////////

	List<UserReport> getAllUserReportByAffiliate(String affiliate);

	List<AuditLog> getAuditReportByAffiliate(String affiliate);
	
	
	

	Response sendSMS(Sms request);
	
	
	

	List<PrayerRequest> getPrayerRequest();

	List<Welfare> getWelfareRequest();

}
