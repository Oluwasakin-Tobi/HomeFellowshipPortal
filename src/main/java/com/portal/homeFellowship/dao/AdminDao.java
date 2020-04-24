package com.portal.homeFellowship.dao;

import java.util.List;

import com.portal.homeFellowship.model.*;

public interface AdminDao {
	
	public Response create_user(User user);
	
	public Response create_user_to_role(UserToRole userToRole);
	
	public Response auth_user_to_role(AuthUsertoroleReq userToRole);

	public Response disable_user_to_role(EditUserDetails details);

	public Response auth_disable_user_to_role(EditUserDetails details);

	public Response edit_user(EditUserDetails details);

	public Response auth_edit_user(EditUserDetails details);

	Response userNameCheck(String userName);

	Response authorise_user(AuthoriseUser authoriseUser);
	
	
	
	
	
	
	

	Response prayerRequest(PrayerRequest request);

	
}
