package com.portal.homeFellowship.dao;

import java.util.List;

import com.portal.homeFellowship.model.*;

public interface InquiryDao {
	
	public UserToRoleResponse get_user_role_opps(RoleForUser roleforuser);

	public UserResp get_user_profile(UserProfile userProfile);

	public List<Affiliate> getAuthorisedAffiliates();
	
	public UserStepPositionResp get_user_step_position(UserStepPosition userStepPosition);
	
	public List<Branch> get_branch(String affiliateCode);
	
	public List<User> get_auth_users(String affiliateCode);

	public List<Role> get_roles();

	public List<UserToRoleResp> get_users_roles(UserProfile user);

	public List<User> get_users(UserReq req);

	public Response getbranchname(String branchcode);
	
	public List<User> get_auth_edit_users(Flag flag);

	public List<EditUser> get_edit_users1(Flag flag);

	public List<UserToRoleApp> get_PEND_USERTOROLE(String userName);
	
	public List<UserRemoveRole> getPendingRemoveUser(String affiliateCode);
	
	public List<UserReport> getAllUserReport();
	
	public List<UserStepPositionResp> getSteps();
	
	public List<Affiliate> getAffiliateDetails(String username);

	public List<User1> getUserDetails(String username);

	public List<UserReport> getAllUserReportByAffiliate(String affiliate);

	StepOperationResp getUserEnabledActivitiesnew(String username);

	public List<String> getActivities(String string, String flag);


	public List<AuditLog> getAuditReportByAffiliate(String affiliate);

	List<Custactivities> getcustactivities(String flag);
	
	
	

	List<PrayerRequest> getPrayerRequest();

	List<Welfare> getWelfareRequest();

	

}
