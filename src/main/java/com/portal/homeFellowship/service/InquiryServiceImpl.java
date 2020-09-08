package com.portal.homeFellowship.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.mail.MessagingException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;
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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.portal.homeFellowship.dao.InquiryDao;
import com.portal.homeFellowship.filter.GlobalRestException;
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
	
	@Autowired
	MailAgent mailAgent;

	@Autowired
	Mailer mailer;
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
	public List<UserDetails> getpenduser(String affiliate) {
		return dao.get_auth_users(affiliate);
	}

	@Override
	public List<UserDetails> getUserDetails(String userName, String role) {
		return dao.getUserDetails(userName, role);
	}

	@Override
	public List<User> getuser(String userID, String pflag, String affiliate) {
		UserReq req = new UserReq();
		req.setPflag(pflag);
		req.setAffiliate(affiliate);
		req.setUserID(Long.valueOf(userID));
		return dao.get_users(req);
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
		String url = "https://www.bulksmsnigeria.com/api/v1/sms/create?api_token=NwDWcQ9Vtoff5grSLsb52XzEcOlBYPnDLRcuGfW22fxG8AiPCZ67NqU2VI6L&from=BULKSMS.ng&to="+request.getPhoneNo()+"&body="+request.getMessage();
		
		LOGGER.info("sms response "+request);//support@bulksmsnigeria.com
		
		ResponseEntity<SmsResponse> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, SmsResponse.class);
		LOGGER.info("sms response "+response.getBody());
		if("success".equalsIgnoreCase(response.getBody().getData().getStatus())) {
			return new Response("00", "Message Sent");
		}
		
		return new Response("99", "Message Not Sent");
	}
	
	@Override
	public Response sendMail(Sms request) throws MessagingException {
		
		//mailAgent.generateAndSendEmail(BasicUtil.sendMailMessage(request.getMessage(), environment.getProperty("cithMessage")),"CITH ADMIN MESSAGE", request.getPhoneNo(),null);
		mailer.Send("cithPortal@noReply.com", request.getPhoneNo(), null, null, "CITH ADMIN MESSAGE", BasicUtil.sendMailMessage(request.getMessage(), environment.getProperty("cithMessage")), null, null);
			return new Response("00", "Message Sent");
	}
	
	
	@Override
	public List<PrayerRequest> getPrayerRequest(PrayerRequest request){
		return dao.getPrayerRequest(request);
	}

	@Override
	public List<Welfare> getWelfareRequest(Welfare request) {
		return dao.getWelfareRequest(request);
	}
	
	
	@Override
	public List<Announcement> getAnnouncement() {
		return dao.getAnnouncement();
	}
	
	@Override
	public List<Incident> getIncident(Incident request) {
		return dao.getIncident(request);
	}

	@Override
	public List<Expenses> getCommunityProject(Expenses request) {
		return dao.getCommunityProject(request);
	}

	@Override
	public List<Expenses> getExpenses(Expenses request) {
		return dao.getExpenses(request);
	}

	@Override
	public List<WeeklyOutline> getWeeklyOutline(WeeklyOutline request) {
		return dao.getWeeklyOutline(request);
	}
	
	@Override
	public DocManagerRequest getOutlineDocument(String id) throws IOException {


        DocManagerRequest response = dao.getOutlineDocument(id);
        //return dao.getDocument(id);

        String inputStreamStr = "";

        if (response != null && response.getInputStream() != null) {
            String returnedEncodedBase64String = BasicUtil.getStringFromInputStream(response.getInputStream());
            // LOGGER.info("++++ returnedEncodedBase64String ==> " +
            // returnedEncodedBase64String + " ++++");
            
            if (response.isCompressed()) {
				ZipInputStream zis = null;
				InputStream inputStream =null;
				FileOutputStream fosUnzip =null;
				FileInputStream fis = null;
				File unZippedFile = null;
				try { 
					byte[] decodedBase64Bytes = Base64.decodeBase64(returnedEncodedBase64String);
					inputStream = new ByteArrayInputStream(decodedBase64Bytes);

					File destDir = new File(System.getProperty("java.io.tmpdir"));
					byte[] buffer = new byte[1024];
					zis = new ZipInputStream(inputStream);
					ZipEntry zipEntryUnzip = zis.getNextEntry();
					while (zipEntryUnzip != null) {
						File newFile = BasicUtil.newFile(destDir, zipEntryUnzip);
						fosUnzip = new FileOutputStream(newFile);
						int len;
						while ((len = zis.read(buffer)) > 0) {
							fosUnzip.write(buffer, 0, len);
						}
						fosUnzip.close();
						zipEntryUnzip = zis.getNextEntry();
					}
					zis.closeEntry();
					zis.close();

					try {
						unZippedFile = new File(destDir + File.separator + response.getDocName());
						fis = new FileInputStream(unZippedFile);
						inputStreamStr = BasicUtil.getStringFromInputStream(fis);
					} catch (Exception e) { 
						LOGGER.error("********Oops Something went wrong - maybe not found zip document because not able to zip **********" + e);
						inputStreamStr = returnedEncodedBase64String;
					}
					if (unZippedFile != null && unZippedFile.exists())
						unZippedFile.delete();

				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error("********Oops Something went wrong **********" + e);
					inputStreamStr = returnedEncodedBase64String;
				} finally {
					if (unZippedFile != null && unZippedFile.exists())
						unZippedFile.delete();
					if (zis != null) {
						zis.close();
					}
					if (fosUnzip != null) {
						fosUnzip.close();
					}
					if (inputStream != null) {
						inputStream.close();
					}
					if (fis != null) {
						fis.close();
					}
				}

			} else {
				inputStreamStr = returnedEncodedBase64String;
			}

            //if (response != null) {
                response.setInputStreamStr(inputStreamStr);
                return response;
               // return new ResponseEntity<>(response, HttpStatus.OK);
            } else
                throw new GlobalRestException("99", "Oops Something went wrong while getting document.");
        }

	

	@Override
	public List<Incident> getWhistleBlowing(Incident request) {
		return dao.getWhistleBlowing(request);
	}

	@Override
	public List<Otp> getOTP(Otp req) {
		return dao.getOTP(req);
	}
	
	@Override
	public List<CalendarDetail> getCalendarEvent(CalendarDetail req) {
		return dao.getCalendarEvent(req);
	}

	@Override
	@Scheduled(cron="${cron.expression.remainder}")
	public Response getCalendarEventToSend() {
		LOGGER.info("Here");
		List<CalendarDetail> resp = dao.getCalendarEventToSend();
		
		//TODO A SERVICE TO GET ALL THE PHONE NUMBERS AND MAILS OF ALL USERS
		LOGGER.info("Here2");
		
		Response response = new Response();
		for(int i=0; i<resp.size();i++) {
			
			LOGGER.info("event"+resp);
			
			List<UserDetails> userDetails = dao.userToSendRemainder(resp.get(i).getSendTo(), resp.get(i).getSendToUser());
			LOGGER.info("Here2"+userDetails);
			for (int j=0; j<userDetails.size(); j++) {
				Sms req = new Sms();
				req.setMessage(resp.get(i).getEvent());
				req.setPhoneNo(userDetails.get(j).getPhoneNo());
				response = sendSMS(req);
				LOGGER.info("sms"+response);
				
				Sms mailRequest = new Sms();
				mailRequest.setPhoneNo(userDetails.get(j).getUserEmailAdd());
				mailRequest.setMessage(resp.get(i).getEvent());
				LOGGER.info("mail"+response);
				try {
					response = sendMail(mailRequest);
				} catch (MessagingException e) {
					e.printStackTrace();
				} 
			}
			
			//To update the event to sent
			dao.updateSentToCalendar(resp.get(i).getCalendarID());
		}
		return response;
	}
	
	@Override
	public List<SocialEvent> getSocialEvent(SocialEvent request){
		return dao.getSocialEvent(request);
	}

	@Override
	public List<DocManagerRequest> getSocialEventDocument(String outlineID) throws IOException {
		List<DocManagerRequest> response = dao.getSocialEventDocument(outlineID);
	        //return dao.getDocument(id);

	        String inputStreamStr = "";

	        
	        if (response != null && response.get(0).getInputStream() != null) {
	        	for(int i = 0; i<response.size(); i++) {
	            String returnedEncodedBase64String = BasicUtil.getStringFromInputStream(response.get(i).getInputStream());
	            // LOGGER.info("++++ returnedEncodedBase64String ==> " +
	            // returnedEncodedBase64String + " ++++");
	            
	            if (response.get(i).isCompressed()) {
					ZipInputStream zis = null;
					InputStream inputStream =null;
					FileOutputStream fosUnzip =null;
					FileInputStream fis = null;
					File unZippedFile = null;
					try { 
						byte[] decodedBase64Bytes = Base64.decodeBase64(returnedEncodedBase64String);
						inputStream = new ByteArrayInputStream(decodedBase64Bytes);

						File destDir = new File(System.getProperty("java.io.tmpdir"));
						byte[] buffer = new byte[1024];
						zis = new ZipInputStream(inputStream);
						ZipEntry zipEntryUnzip = zis.getNextEntry();
						while (zipEntryUnzip != null) {
							File newFile = BasicUtil.newFile(destDir, zipEntryUnzip);
							fosUnzip = new FileOutputStream(newFile);
							int len;
							while ((len = zis.read(buffer)) > 0) {
								fosUnzip.write(buffer, 0, len);
							}
							fosUnzip.close();
							zipEntryUnzip = zis.getNextEntry();
						}
						zis.closeEntry();
						zis.close();

						try {
							unZippedFile = new File(destDir + File.separator + response.get(i).getDocName());
							fis = new FileInputStream(unZippedFile);
							inputStreamStr = BasicUtil.getStringFromInputStream(fis);
						} catch (Exception e) { 
							LOGGER.error("********Oops Something went wrong - maybe not found zip document because not able to zip **********" + e);
							inputStreamStr = returnedEncodedBase64String;
						}
						if (unZippedFile != null && unZippedFile.exists())
							unZippedFile.delete();

					} catch (Exception e) {
						e.printStackTrace();
						LOGGER.error("********Oops Something went wrong **********" + e);
						inputStreamStr = returnedEncodedBase64String;
					} finally {
						if (unZippedFile != null && unZippedFile.exists())
							unZippedFile.delete();
						if (zis != null) {
							zis.close();
						}
						if (fosUnzip != null) {
							fosUnzip.close();
						}
						if (inputStream != null) {
							inputStream.close();
						}
						if (fis != null) {
							fis.close();
						}
					}

				} else {
					inputStreamStr = returnedEncodedBase64String;
				}

	                response.get(i).setInputStreamStr(inputStreamStr);
	        }       
	                return response;
	            } else
	                throw new GlobalRestException("99", "Oops Something went wrong while getting document.");
	}

	@Override
	public List<WeeklyReport> getWeeklyReport(Filter request) {
		return dao.getWeeklyReport(request);
	}

	@Override
	public List<MonthlyReport> getMonthlyReport(Filter request) {
		return dao.getMonthlyReport(request);
	}

	@Override
	public List<DirectorReport> getDirectorReport(Filter request) {
		return dao.getDirectorReport(request);
	}
	
	
	@Override
	public List<WeeklyReport> getCentreReport(Filter request) {
		return dao.getCentreReport(request);
	}

	@Override
	public List<WeeklyReport> getAreaReport(Filter request) {
		return dao.getAreaReport(request);
	}

	@Override
	public List<WeeklyReport> getZoneReport(Filter request) {
		return dao.getZoneReport(request);
	}

	@Override
	public List<WeeklyReport> getDistrictReport(Filter request) {
		return dao.getDistrictReport(request);
	}

	@Override
	public List<WeeklyReport> getVisitorDetail(Filter request) {
		
		if(("CENTRE").equalsIgnoreCase(request.getType())) {
			request.setValue(request.getCentre());
		}
		else if(("AREA").equalsIgnoreCase(request.getType())) {
			request.setValue(request.getArea());
		}
		else if(("ZONE").equalsIgnoreCase(request.getType())) {
			request.setValue(request.getZone());
		}
		else{
			request.setValue(request.getDistrict());
		}
		
		return dao.getVisitorDetail(request);
	}

	@Override
	public List<Training> getTraining(Training training) {
		return dao.getTraining(training);
	}

	@Override
	public List<Centre> getCentreDetail(Centre request) {
		return dao.getCentreDetail(request);
	}

	@Override
	public List<Centre> viewCentre(Centre request) {
		return dao.viewCentre(request);
	}

	@Override
	public List<Testimony> viewTestimony(Testimony request) {
		return dao.viewTestimony(request);
	}
	
	@Override
	public List<Message> viewMessage(Message request) {
		return dao.viewMessage(request);
	}
	
	@Override
	public List<Country> getCountry() {
		return dao.getCountry();
	}
	
	
	@Override
	public List<SocialEvent> getLegalDocumentDetails(SocialEvent request){
		return dao.getLegalDocumentDetails(request);
	}

	

	@Override
	public DocManagerRequest getLegalDocuments(String id) throws IOException {


        DocManagerRequest response = dao.getLegalDocuments(id);
        //return dao.getDocument(id);

        String inputStreamStr = "";

        if (response != null && response.getInputStream() != null) {
            String returnedEncodedBase64String = BasicUtil.getStringFromInputStream(response.getInputStream());
            // LOGGER.info("++++ returnedEncodedBase64String ==> " +
            // returnedEncodedBase64String + " ++++");
            
            if (response.isCompressed()) {
				ZipInputStream zis = null;
				InputStream inputStream =null;
				FileOutputStream fosUnzip =null;
				FileInputStream fis = null;
				File unZippedFile = null;
				try { 
					byte[] decodedBase64Bytes = Base64.decodeBase64(returnedEncodedBase64String);
					inputStream = new ByteArrayInputStream(decodedBase64Bytes);

					File destDir = new File(System.getProperty("java.io.tmpdir"));
					byte[] buffer = new byte[1024];
					zis = new ZipInputStream(inputStream);
					ZipEntry zipEntryUnzip = zis.getNextEntry();
					while (zipEntryUnzip != null) {
						File newFile = BasicUtil.newFile(destDir, zipEntryUnzip);
						fosUnzip = new FileOutputStream(newFile);
						int len;
						while ((len = zis.read(buffer)) > 0) {
							fosUnzip.write(buffer, 0, len);
						}
						fosUnzip.close();
						zipEntryUnzip = zis.getNextEntry();
					}
					zis.closeEntry();
					zis.close();

					try {
						unZippedFile = new File(destDir + File.separator + response.getDocName());
						fis = new FileInputStream(unZippedFile);
						inputStreamStr = BasicUtil.getStringFromInputStream(fis);
					} catch (Exception e) { 
						LOGGER.error("********Oops Something went wrong - maybe not found zip document because not able to zip **********" + e);
						inputStreamStr = returnedEncodedBase64String;
					}
					if (unZippedFile != null && unZippedFile.exists())
						unZippedFile.delete();

				} catch (Exception e) {
					e.printStackTrace();
					LOGGER.error("********Oops Something went wrong **********" + e);
					inputStreamStr = returnedEncodedBase64String;
				} finally {
					if (unZippedFile != null && unZippedFile.exists())
						unZippedFile.delete();
					if (zis != null) {
						zis.close();
					}
					if (fosUnzip != null) {
						fosUnzip.close();
					}
					if (inputStream != null) {
						inputStream.close();
					}
					if (fis != null) {
						fis.close();
					}
				}

			} else {
				inputStreamStr = returnedEncodedBase64String;
			}

            //if (response != null) {
                response.setInputStreamStr(inputStreamStr);
                return response;
               // return new ResponseEntity<>(response, HttpStatus.OK);
            } else
                throw new GlobalRestException("99", "Oops Something went wrong while getting document.");
        }
	
	@Override
	public List<Centre> getCentreDetails(Centre request) {
		return dao.getCentreDetails(request);
	}
	
	@Override
	public List<Quarterly> quarterlyCentre() {
		return dao.quarterlyCentre();
	}
	
	@Override
	public List<Quarterly> quarterlyMember() {
		return dao.quarterlyMember();
	}
	
	@Override
	public List<Quarterly> quarterlyOffering() {
		return dao.quarterlyOffering();
	}
}
