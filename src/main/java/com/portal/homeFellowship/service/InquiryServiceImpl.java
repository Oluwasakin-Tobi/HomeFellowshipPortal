package com.portal.homeFellowship.service;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.portal.homeFellowship.dao.InquiryDao;
import com.portal.homeFellowship.model.*;
import com.portal.homeFellowship.utility.*;

@Service("inquiryService")
public class InquiryServiceImpl implements InquiryService {
	static final Logger LOGGER = LoggerFactory.getLogger(InquiryServiceImpl.class);

	@Autowired
	Environment environment;

	@Autowired
	private InquiryDao dao;

	@Autowired
	RestTemplate restTemplate;

	static {
		disableSslVerification();
	}

	private static void disableSslVerification() {
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}

				public boolean verify(String arg0, String arg1) {
					// TODO Auto-generated method stub
					return false;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Role> getroles() {
		LOGGER.info("logg");
		return dao.get_roles();
	}

	@Override
	public List<Branch> getAuthorisedBranchesForAffiliate(String affiliateCode) {
		return dao.get_branch(affiliateCode);
	}

	@Override
	public List<User> getpenduser(String affiliate) {
		return dao.get_auth_users(affiliate);
	}

	@Override
	public List<UserToRoleApp> getpendusertorole(String adUsername) {
		return dao.get_PEND_USERTOROLE(adUsername);
	}

	@Override
	public List<User> getPendAuthEditUser(String flag, String affiliate) {
		Flag pflag = new Flag();
		pflag.setPflag(flag);
		pflag.setAffiliate(affiliate);
		return dao.get_auth_edit_users(pflag);
	}

	@Override
	public List<User1> getUserDetails(String userName) {
		return dao.getUserDetails(userName);
	}

	@Override
	public List<UserStepPositionResp> getSteps() {
		return dao.getSteps();
	}

	@Override
	public List<User> getuser(String userID, String pflag, String affiliate) {
		UserReq req = new UserReq();
		req.setPflag(pflag);
		req.setAffiliate(affiliate);
		req.setUserID(Long.valueOf(userID));
		return dao.get_users(req);
	}

	@Override
	public List<UserRemoveRole> getPendingRemoveUser(String affiliate) {
		return dao.getPendingRemoveUser(affiliate);
	}

	@Override
	public UserToRoleResponse getRolesForUser(String adUsername, String currentLoginIPAddress, long userID) {
		RoleForUser roleforuser = new RoleForUser();
		roleforuser.setServerIP(currentLoginIPAddress);
		roleforuser.setUserID(userID);
		roleforuser.setUserName(adUsername);
		return dao.get_user_role_opps(roleforuser);
	}

	@Override
	public Response getbranchname(String userBranch) {
		return dao.getbranchname(userBranch);
	}

	@Override
	public List<Affiliate> getAuthorisedAffiliates() {
		return dao.getAuthorisedAffiliates();
	}
	
	@Override
	public List<UserToRoleResp> getUserRoles(UserProfile user) {
		return dao.get_users_roles(user);
	}

	@Override
	public List<EditUser> getpendedituser1(String pflag, String affiliate) {
		Flag flag = new Flag();
		flag.setPflag(pflag);
		flag.setAffiliate(affiliate);
		return dao.get_edit_users1(flag);
	}

	@Override
	public UserStepPositionResp getUserStepPosition(String adUsername) {
		UserStepPosition request = new UserStepPosition();
		request.setUserName(adUsername);
		request.setServerIP(BasicUtil.getClientIp());
		return dao.get_user_step_position(request);
		
	}
	
	@Override
	public List<Affiliate> getAffiliateDetails(String username){
		return dao.getAffiliateDetails(username);
	}

	@Override
	public List<Custactivities> getcustactivities(String flag) {
		return dao.getcustactivities(flag);
	}
	///////////////////////////////////////// END OF
	///////////////////////////////////////// ADMIN//////////////////////////////////////
	
	///////////////////REPORTS////////////////////////////
	@Override
	public List<UserReport> getAllUserReportByAffiliate(String affiliate) {
		return dao.getAllUserReportByAffiliate(affiliate);
	}
	
	@Override
	public List<AuditLog> getAuditReportByAffiliate(String affiliate) {
		return dao.getAuditReportByAffiliate(affiliate);
	}
	//////////////////REPORT ENDS///////////////////////////////

	/////////////////////////////////////// NOTIFICATION
	/////////////////////////////////////// STARTS//////////////////////////////////////////////////

	////////////////////////////////////// NOTIFICATION
	////////////////////////////////////// ENDS///////////////////////////////////////////////

	@Override
	public Response sendSMS(Sms request) {
		Sms req = new Sms();
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
		HttpEntity<?> requestEntity = new HttpEntity<>(request, header);
		String url = "https://www.bulksmsnigeria.com/api/v1/sms/create?api_token=mg4agSmzVy42Z5f6DeJGYhNUrI1ddIIatcXKjNM21byE335IwAzGBo4CHvP9&from=CITH&to=08101899045&body=HI, WELCOME TO CHURCH";
		
		LOGGER.info("sms response "+request);
		
		ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Response.class);
		LOGGER.info("sms response "+response.getBody());
		
		return new Response("00", "Message sent");
	}
	
	
	@Override
	public List<PrayerRequest> getPrayerRequest(){
		return dao.getPrayerRequest();
	}
	
	



}
