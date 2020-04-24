package com.portal.homeFellowship.service;

import java.util.List;

import com.portal.homeFellowship.model.*;

public interface UserAdminService {

	Administration performBasicADAuthentication(String userID, String password, String ip) throws Exception;

	//List<String> getactivities(String roleid, String flag) throws Exception;

}
