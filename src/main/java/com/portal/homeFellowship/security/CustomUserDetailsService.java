package com.portal.homeFellowship.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.homeFellowship.dao.InquiryDao;
import com.portal.homeFellowship.model.*;
import com.portal.homeFellowship.service.*;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements AuthenticationProvider { // UserDetailsService{

	static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);
	@Autowired
	private Administration administration;
	@Autowired
	private UserAdmin user;
	@Autowired
	@Qualifier("userAdminService")
	private UserAdminService userAdminService;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	private HttpServletRequest req; 
	@Autowired
	private Environment environment;
	@Autowired
	private InquiryService service;
	@Autowired
	private InquiryDao inquiryDao;

	//@Transactional(readOnly = true)
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication authenticate = null;
		WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails(); 
		LOGGER.info("********** User top : " + authentication.getName() + " *************");
		LOGGER.info("********** IP Address : " + details.getRemoteAddress() + " *************");
		try {
			

			LOGGER.info("**********credentials " + (String) authentication.getCredentials() + " *************");
			administration = userAdminService.performBasicADAuthentication(authentication.getName(),
					(String) authentication.getCredentials(), details.getRemoteAddress());
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("********Oops Something went wrong **********" + e);
			throw new BadCredentialsException(e.getMessage());

		}
		user = administration.getUser();
		if (user == null) {
			LOGGER.info("********* Invalid Login Credentials Specified. ***********");
			throw new BadCredentialsException("Invalid Login Credentials Specified.");
		}

		if (administration.isValidate()) {

		}
		if (administration.isAuthenticate() && administration.isValidate() && (administration.isEdited()||administration.isEditedRole())) {
			LOGGER.info("********* User is Under Maintenance. ***********");
			throw new BadCredentialsException("User is Under Maintenance.");
		}

		if (administration.isAuthenticate() && administration.isValidate()) {
			LOGGER.info("XXXX User before Adding ==> "+user+" XXXX");
			authenticate = new UsernamePasswordAuthenticationToken(authentication.getName(), (String) authentication.getCredentials(),
					getGrantedAuthorities(user)); 
		     
			
			return authenticate;
		} else if (administration.isAuthenticate() && !administration.isValidate()) {
			LOGGER.info("********* User not profiled *********");
			throw new BadCredentialsException("Username not profiled for this application");
		} else if (!administration.isAuthenticate() && !administration.isValidate()) {
			LOGGER.info("********* Invalid User Credentials Specified. ***********");
			throw new BadCredentialsException("Invalid User Credentials Specified.");
		} 
		return null;
	}

	private List<GrantedAuthority> getGrantedAuthorities(UserAdmin user) {
		List<GrantedAuthority> authorities = new ArrayList<>();

		for (UserProfileAdmin userProfile : user.getUserProfiles()) {
			LOGGER.info("************ UserProfile : " + userProfile + " *************");
			authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
		}
		
		LOGGER.info("************ authorities : " + authorities + " **************");
		return authorities;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	} 
}

