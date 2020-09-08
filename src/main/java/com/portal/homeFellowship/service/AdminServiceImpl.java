package com.portal.homeFellowship.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.commons.codec.binary.Base64;
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
		return adminDao.authenticateUser(username, pwd);
	}
	
//	@Override
//	public Response createUser(User1 user, UserAdmin principal) {
//		User newuser = new User();
//		newuser.setUserID(1);
//		newuser.setUserFullName(user.getUserFullName());
//		newuser.setUserName(user.getUserName());
//		newuser.setUserRoles(user.getUserRoles());
//		String userrole = user.getUserRoles() != null ? user.getUserRoles().trim() : "";
//		LOGGER.info("++user role ==> " + userrole);
//		if ("OPERATIONS".equalsIgnoreCase(userrole))
//			newuser.setOperationUser(true);
//		else
//			newuser.setOperationUser(false);
//		//newuser.setUserTransactionLimit(user.getUserTransactionLimit());
//		//newuser.setPasswordExpiryPolicy("password unique to user");
//		newuser.setPassword(user.getPassword());
//		newuser.setServerIP(BasicUtil.getClientIp());
//		newuser.setAuthorisedUserFlag(false);
//		newuser.setUserEmailAdd(user.getUserEmailAdd());
//		newuser.setAffiliateCode(user.getAffiliateCode());
//		newuser.setCreatedBy(principal.getAdUsername());
//		//newuser.setServiceClient(environment.getRequiredProperty("moduleID"));
//		Response response = adminDao.create_user(newuser);
//		return new Response(response.getResponseCode(), response.getResponseMessage());
//	}
	
	@Override
	public Response createUser(UserDetails userDetails) {
		if ("OPERATIONS".equalsIgnoreCase(userDetails.getUserRoles()))
			userDetails.setOperationUser(true);
		else
			userDetails.setOperationUser(false);
		Response response = adminDao.createUser(userDetails);
		return new Response(response.getResponseCode(), response.getResponseMessage());
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
	public Response userNameCheck(String userName) {
		return adminDao.userNameCheck(userName);
	}

	@Override
	public Response edituser(EditUserDetails user, UserAdmin principal) {
		user.setEditusername(principal.getAdUsername());
		user.setServerIP(BasicUtil.getClientIp());
		return adminDao.edit_user(user);
	}

	

	

	@Override
	public Response prayerRequest(PrayerRequest request) {
		return adminDao.prayerRequest(request);
	}
	
	@Override
	public Response welfareRequest(Welfare request) {
		return adminDao.welfareRequest(request);
	}


	@Override
	public Response specialAnnouncement(Announcement request) {
		return adminDao.specialAnnouncement(request);
	}
	
	@Override
	public Response incidentRequest(Incident request) {
		return adminDao.incidentRequest(request);
	}

	@Override
	public Response updateIncidentRequest(Incident request) {
		return adminDao.updateIncidentRequest(request);
	}

	@Override
	public Response updatePrayerRequest(PrayerRequest request) {
		return adminDao.updatePrayerRequest(request);
	}

	@Override
	public Response updateWelfareRequest(Welfare request) {
		return adminDao.updateWelfareRequest(request);
	}
	
	@Override
	public Response expenseRequest(Expenses request) {
		return adminDao.expenseRequest(request);
	}

	@Override
	public Response communityProject(Expenses request) {
		return adminDao.communityProject(request);
	}
	
	@Override
	public Response weeklyReport(WeeklyReport request) {
		return adminDao.weeklyReport(request);
	}

	@Override
	public Response weeklyOutline(WeeklyOutline request) {
		return adminDao.weeklyOutline(request);
	}

	@Override
	public Response whistleBlowing(Incident request) {
		return adminDao.whistleBlowing(request);
	}

	@Override
	public Response directorReport(DirectorReport request) {
		return adminDao.directorReport(request);
	}
	
	@Override
	public Response updateWhistleBlowingRequest(Incident request) {
		return adminDao.updateWhistleBlowingRequest(request);
	}

	@Override
	public Response saveOTP(Otp otp) {
		return adminDao.saveOTP(otp);
	}

	@Override
	public Response calendarEvent(CalendarDetail request) {
		return adminDao.calendarEvent(request);
	}

	@Override
	public Response updateCalendarEvent(CalendarDetail request) {
		return adminDao.updateCalendarEvent(request);
	}
	
	@Override
	public Response createOutlineDocument(DocManagerRequest docMangerRequest) throws IOException {
        LOGGER.info("inputStream" + docMangerRequest.getInputStream());
        if ("ON".equalsIgnoreCase(environment.getRequiredProperty("compress.document.before.save"))) {
            ByteArrayInputStream bais = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = null;
            File compressedFile = null;
            FileOutputStream fos = null;
            ZipOutputStream zipOut = null;
            FileInputStream compressedFileIS = null;
            OutputStream outputStream = null;
            InputStream inputStreams = null;
            File fileToZipActual = null;
            try {

                org.apache.commons.io.IOUtils.copy(docMangerRequest.getInputStream(), baos);
                byte[] bytesFromOutStream = baos.toByteArray();
                bais = new ByteArrayInputStream(bytesFromOutStream);

                docMangerRequest.setInputStream(bais);

                String fileToZip = System.getProperty("java.io.tmpdir") + File.separator
                        + docMangerRequest.getDocName();

                String zipFIle = System.getProperty("java.io.tmpdir") + File.separator + docMangerRequest.getDocName()
                        + ".zip";

                outputStream = new FileOutputStream(fileToZip);

                org.apache.commons.io.IOUtils.copy(docMangerRequest.getInputStream(), outputStream);

                fileToZipActual = new File(fileToZip);
                fis = new FileInputStream(fileToZipActual);
                fos = new FileOutputStream(zipFIle);
                zipOut = new ZipOutputStream(fos);
                ZipEntry zipEntry = new ZipEntry(docMangerRequest.getDocName());
                LOGGER.info("++++++++ fileToZip.getName() ==> " + docMangerRequest.getDocName());
                zipOut.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                zipOut.close();
                fis.close();
                fos.close();

                // persist the compressed document

                compressedFile = new File(zipFIle);
                LOGGER.info("++++++++ compressedFile.length() ==> " + compressedFile.length());
                compressedFileIS = new FileInputStream(compressedFile);

                byte[] bytest = BasicUtil.loadFile(compressedFileIS, compressedFile.length(),
                        docMangerRequest.getDocName());

                LOGGER.info(">>> bytes2 " + bytest.toString() + ". <<< ");

                byte[] encodedBase64Bytes = Base64.encodeBase64(bytest);
                String encodedBase64String = new String(encodedBase64Bytes, StandardCharsets.US_ASCII);
                docMangerRequest.setInputStreamStr(encodedBase64String);

                String str = encodedBase64String;
                str = str != null ? str : "";
                byte[] array = str.getBytes();

                LOGGER.info("++++ array ==> " + array + " ++++");

                inputStreams = new ByteArrayInputStream(array);

                docMangerRequest.setInputStream(inputStreams);
                docMangerRequest.setInputStreamLength(str.length());
                docMangerRequest.setCompressed(true);
                //

            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("********Oops Something went wrong **********" + e);
                if (bais != null) {
                    bais.reset();
                    docMangerRequest.setInputStream(bais);
                }
            } finally {
                if (bais != null) {
                    bais.close();
                }
                if (baos != null) {
                    baos.close();
                }
                if (inputStreams != null) {
                    inputStreams.close();
                }
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (zipOut != null) {
                    zipOut.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (compressedFileIS != null) {
                    compressedFileIS.close();
                }
                if (compressedFile != null && compressedFile.exists())
                    compressedFile.delete();
                if (fileToZipActual != null && fileToZipActual.exists())
                    fileToZipActual.delete();
            }
            LOGGER.info("++++ about saving ++++");

        }

        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream inputStream = null;
        String str = docMangerRequest.getInputStreamStr();
        str = str != null ? str : "";
        byte[] array = str.getBytes();


        LOGGER.info("++++ array ==> " + array + " ++++");

        inputStream = new ByteArrayInputStream(array);

        org.apache.commons.io.IOUtils.copy(inputStream, baos);
        byte[] bytes = baos.toByteArray();
        bais = new ByteArrayInputStream(bytes);

        docMangerRequest.setInputStreamLength((int) str.length());

        docMangerRequest.setInputStream(bais);
        LOGGER.info("++++ get input value ==> " + docMangerRequest.getInputStream() + " ++++");
        LOGGER.info("++++ get document value ==> " + docMangerRequest + " ++++");

        return adminDao.createOutlineDocument(docMangerRequest);

    }

	@Override
	public Response createSocialEvent(SocialEvent request) {
		return adminDao.createSocialEvent(request);
	}

	@Override
	public Response createSocialEventDocument(DocManagerRequest docMangerRequest) throws IOException {
		LOGGER.info("inputStream" + docMangerRequest.getInputStream());
        if ("ON".equalsIgnoreCase(environment.getRequiredProperty("compress.document.before.save"))) {
            ByteArrayInputStream bais = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = null;
            File compressedFile = null;
            FileOutputStream fos = null;
            ZipOutputStream zipOut = null;
            FileInputStream compressedFileIS = null;
            OutputStream outputStream = null;
            InputStream inputStreams = null;
            File fileToZipActual = null;
            try {

                org.apache.commons.io.IOUtils.copy(docMangerRequest.getInputStream(), baos);
                byte[] bytesFromOutStream = baos.toByteArray();
                bais = new ByteArrayInputStream(bytesFromOutStream);

                docMangerRequest.setInputStream(bais);

                String fileToZip = System.getProperty("java.io.tmpdir") + File.separator
                        + docMangerRequest.getDocName();

                String zipFIle = System.getProperty("java.io.tmpdir") + File.separator + docMangerRequest.getDocName()
                        + ".zip";

                outputStream = new FileOutputStream(fileToZip);

                org.apache.commons.io.IOUtils.copy(docMangerRequest.getInputStream(), outputStream);

                fileToZipActual = new File(fileToZip);
                fis = new FileInputStream(fileToZipActual);
                fos = new FileOutputStream(zipFIle);
                zipOut = new ZipOutputStream(fos);
                ZipEntry zipEntry = new ZipEntry(docMangerRequest.getDocName());
                LOGGER.info("++++++++ fileToZip.getName() ==> " + docMangerRequest.getDocName());
                zipOut.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                zipOut.close();
                fis.close();
                fos.close();

                // persist the compressed document

                compressedFile = new File(zipFIle);
                LOGGER.info("++++++++ compressedFile.length() ==> " + compressedFile.length());
                compressedFileIS = new FileInputStream(compressedFile);

                byte[] bytest = BasicUtil.loadFile(compressedFileIS, compressedFile.length(),
                        docMangerRequest.getDocName());

                LOGGER.info(">>> bytes2 " + bytest.toString() + ". <<< ");

                byte[] encodedBase64Bytes = Base64.encodeBase64(bytest);
                String encodedBase64String = new String(encodedBase64Bytes, StandardCharsets.US_ASCII);
                docMangerRequest.setInputStreamStr(encodedBase64String);

                String str = encodedBase64String;
                str = str != null ? str : "";
                byte[] array = str.getBytes();

                LOGGER.info("++++ array ==> " + array + " ++++");

                inputStreams = new ByteArrayInputStream(array);

                docMangerRequest.setInputStream(inputStreams);
                docMangerRequest.setInputStreamLength(str.length());
                docMangerRequest.setCompressed(true);
                //

            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("********Oops Something went wrong **********" + e);
                if (bais != null) {
                    bais.reset();
                    docMangerRequest.setInputStream(bais);
                }
            } finally {
                if (bais != null) {
                    bais.close();
                }
                if (baos != null) {
                    baos.close();
                }
                if (inputStreams != null) {
                    inputStreams.close();
                }
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (zipOut != null) {
                    zipOut.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (compressedFileIS != null) {
                    compressedFileIS.close();
                }
                if (compressedFile != null && compressedFile.exists())
                    compressedFile.delete();
                if (fileToZipActual != null && fileToZipActual.exists())
                    fileToZipActual.delete();
            }
            LOGGER.info("++++ about saving ++++");

        }

        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream inputStream = null;
        String str = docMangerRequest.getInputStreamStr();
        str = str != null ? str : "";
        byte[] array = str.getBytes();


        LOGGER.info("++++ array ==> " + array + " ++++");

        inputStream = new ByteArrayInputStream(array);

        org.apache.commons.io.IOUtils.copy(inputStream, baos);
        byte[] bytes = baos.toByteArray();
        bais = new ByteArrayInputStream(bytes);

        docMangerRequest.setInputStreamLength((int) str.length());

        docMangerRequest.setInputStream(bais);
        LOGGER.info("++++ get input value ==> " + docMangerRequest.getInputStream() + " ++++");
        LOGGER.info("++++ get document value ==> " + docMangerRequest + " ++++");

        return adminDao.createSocialEventDocument(docMangerRequest);
	}

	@Override
	public Response monthlyReport(MonthlyReport request) {
		return adminDao.monthlyReport(request);
	}

	@Override
	public Response updateSocialEvent(SocialEvent request) {
		return adminDao.updateSocialEvent(request);
	}

	@Override
	public Response changePassword(ChangePassword request) {
		return adminDao.changePassword(request);
	}

	@Override
	public Response createVisitor(WeeklyReport request) {
		return adminDao.createVisitor(request);
	}

	@Override
	public Response createTraining(Training request) {
		return adminDao.createTraining(request);
	}

	@Override
	public Response createCentre(Centre request) {
		return adminDao.createCentre(request);
	}
	
	@Override
	public Response createArea(Centre request) {
		return adminDao.createArea(request);
	}
	
	@Override
	public Response createZone(Centre request) {
		return adminDao.createZone(request);
	}
	
	@Override
	public Response createDistrict(Centre request) {
		return adminDao.createDistrict(request);
	}

	@Override
	public Response createTestimony(Testimony request) {
		return adminDao.createTestimony(request);
	}

	@Override
	public Response createMessage(Message request) {
		return adminDao.createMessage(request);
	}
	
	@Override
	public Response createLegalDocumentDetail(SocialEvent request) {
		return adminDao.createLegalDocumentDetail(request);
	}
	
	@Override
	public Response createLegalDocuments(DocManagerRequest docMangerRequest) throws IOException {
		LOGGER.info("inputStream" + docMangerRequest.getInputStream());
        if ("ON".equalsIgnoreCase(environment.getRequiredProperty("compress.document.before.save"))) {
            ByteArrayInputStream bais = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = null;
            File compressedFile = null;
            FileOutputStream fos = null;
            ZipOutputStream zipOut = null;
            FileInputStream compressedFileIS = null;
            OutputStream outputStream = null;
            InputStream inputStreams = null;
            File fileToZipActual = null;
            try {

                org.apache.commons.io.IOUtils.copy(docMangerRequest.getInputStream(), baos);
                byte[] bytesFromOutStream = baos.toByteArray();
                bais = new ByteArrayInputStream(bytesFromOutStream);

                docMangerRequest.setInputStream(bais);

                String fileToZip = System.getProperty("java.io.tmpdir") + File.separator
                        + docMangerRequest.getDocName();

                String zipFIle = System.getProperty("java.io.tmpdir") + File.separator + docMangerRequest.getDocName()
                        + ".zip";

                outputStream = new FileOutputStream(fileToZip);

                org.apache.commons.io.IOUtils.copy(docMangerRequest.getInputStream(), outputStream);

                fileToZipActual = new File(fileToZip);
                fis = new FileInputStream(fileToZipActual);
                fos = new FileOutputStream(zipFIle);
                zipOut = new ZipOutputStream(fos);
                ZipEntry zipEntry = new ZipEntry(docMangerRequest.getDocName());
                LOGGER.info("++++++++ fileToZip.getName() ==> " + docMangerRequest.getDocName());
                zipOut.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                zipOut.close();
                fis.close();
                fos.close();

                // persist the compressed document

                compressedFile = new File(zipFIle);
                LOGGER.info("++++++++ compressedFile.length() ==> " + compressedFile.length());
                compressedFileIS = new FileInputStream(compressedFile);

                byte[] bytest = BasicUtil.loadFile(compressedFileIS, compressedFile.length(),
                        docMangerRequest.getDocName());

                LOGGER.info(">>> bytes2 " + bytest.toString() + ". <<< ");

                byte[] encodedBase64Bytes = Base64.encodeBase64(bytest);
                String encodedBase64String = new String(encodedBase64Bytes, StandardCharsets.US_ASCII);
                docMangerRequest.setInputStreamStr(encodedBase64String);

                String str = encodedBase64String;
                str = str != null ? str : "";
                byte[] array = str.getBytes();

                LOGGER.info("++++ array ==> " + array + " ++++");

                inputStreams = new ByteArrayInputStream(array);

                docMangerRequest.setInputStream(inputStreams);
                docMangerRequest.setInputStreamLength(str.length());
                docMangerRequest.setCompressed(true);
                //

            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("********Oops Something went wrong **********" + e);
                if (bais != null) {
                    bais.reset();
                    docMangerRequest.setInputStream(bais);
                }
            } finally {
                if (bais != null) {
                    bais.close();
                }
                if (baos != null) {
                    baos.close();
                }
                if (inputStreams != null) {
                    inputStreams.close();
                }
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (zipOut != null) {
                    zipOut.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (compressedFileIS != null) {
                    compressedFileIS.close();
                }
                if (compressedFile != null && compressedFile.exists())
                    compressedFile.delete();
                if (fileToZipActual != null && fileToZipActual.exists())
                    fileToZipActual.delete();
            }
            LOGGER.info("++++ about saving ++++");

        }

        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream inputStream = null;
        String str = docMangerRequest.getInputStreamStr();
        str = str != null ? str : "";
        byte[] array = str.getBytes();


        LOGGER.info("++++ array ==> " + array + " ++++");

        inputStream = new ByteArrayInputStream(array);

        org.apache.commons.io.IOUtils.copy(inputStream, baos);
        byte[] bytes = baos.toByteArray();
        bais = new ByteArrayInputStream(bytes);

        docMangerRequest.setInputStreamLength((int) str.length());

        docMangerRequest.setInputStream(bais);
        LOGGER.info("++++ get input value ==> " + docMangerRequest.getInputStream() + " ++++");
        LOGGER.info("++++ get document value ==> " + docMangerRequest + " ++++");

        return adminDao.createLegalDocuments(docMangerRequest);
	}
	
	@Override
	public Response updateLegalDocument(SocialEvent request) {
		return adminDao.updateLegalDocument(request);
	}
	
	@Override
	public Response createProfilePicture(DocManagerRequest docMangerRequest) throws IOException {
        LOGGER.info("inputStream" + docMangerRequest.getInputStream());
        if ("ON".equalsIgnoreCase(environment.getRequiredProperty("compress.document.before.save"))) {
            ByteArrayInputStream bais = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            FileInputStream fis = null;
            File compressedFile = null;
            FileOutputStream fos = null;
            ZipOutputStream zipOut = null;
            FileInputStream compressedFileIS = null;
            OutputStream outputStream = null;
            InputStream inputStreams = null;
            File fileToZipActual = null;
            try {

                org.apache.commons.io.IOUtils.copy(docMangerRequest.getInputStream(), baos);
                byte[] bytesFromOutStream = baos.toByteArray();
                bais = new ByteArrayInputStream(bytesFromOutStream);

                docMangerRequest.setInputStream(bais);

                String fileToZip = System.getProperty("java.io.tmpdir") + File.separator
                        + docMangerRequest.getDocName();

                String zipFIle = System.getProperty("java.io.tmpdir") + File.separator + docMangerRequest.getDocName()
                        + ".zip";

                outputStream = new FileOutputStream(fileToZip);

                org.apache.commons.io.IOUtils.copy(docMangerRequest.getInputStream(), outputStream);

                fileToZipActual = new File(fileToZip);
                fis = new FileInputStream(fileToZipActual);
                fos = new FileOutputStream(zipFIle);
                zipOut = new ZipOutputStream(fos);
                ZipEntry zipEntry = new ZipEntry(docMangerRequest.getDocName());
                LOGGER.info("++++++++ fileToZip.getName() ==> " + docMangerRequest.getDocName());
                zipOut.putNextEntry(zipEntry);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                zipOut.close();
                fis.close();
                fos.close();

                // persist the compressed document

                compressedFile = new File(zipFIle);
                LOGGER.info("++++++++ compressedFile.length() ==> " + compressedFile.length());
                compressedFileIS = new FileInputStream(compressedFile);

                byte[] bytest = BasicUtil.loadFile(compressedFileIS, compressedFile.length(),
                        docMangerRequest.getDocName());

                LOGGER.info(">>> bytes2 " + bytest.toString() + ". <<< ");

                byte[] encodedBase64Bytes = Base64.encodeBase64(bytest);
                String encodedBase64String = new String(encodedBase64Bytes, StandardCharsets.US_ASCII);
                docMangerRequest.setInputStreamStr(encodedBase64String);

                String str = encodedBase64String;
                str = str != null ? str : "";
                byte[] array = str.getBytes();

                LOGGER.info("++++ array ==> " + array + " ++++");

                inputStreams = new ByteArrayInputStream(array);

                docMangerRequest.setInputStream(inputStreams);
                docMangerRequest.setInputStreamLength(str.length());
                docMangerRequest.setCompressed(true);
                //

            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("********Oops Something went wrong **********" + e);
                if (bais != null) {
                    bais.reset();
                    docMangerRequest.setInputStream(bais);
                }
            } finally {
                if (bais != null) {
                    bais.close();
                }
                if (baos != null) {
                    baos.close();
                }
                if (inputStreams != null) {
                    inputStreams.close();
                }
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (zipOut != null) {
                    zipOut.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (compressedFileIS != null) {
                    compressedFileIS.close();
                }
                if (compressedFile != null && compressedFile.exists())
                    compressedFile.delete();
                if (fileToZipActual != null && fileToZipActual.exists())
                    fileToZipActual.delete();
            }
            LOGGER.info("++++ about saving ++++");

        }

        ByteArrayInputStream bais = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream inputStream = null;
        String str = docMangerRequest.getInputStreamStr();
        str = str != null ? str : "";
        byte[] array = str.getBytes();


        LOGGER.info("++++ array ==> " + array + " ++++");

        inputStream = new ByteArrayInputStream(array);

        org.apache.commons.io.IOUtils.copy(inputStream, baos);
        byte[] bytes = baos.toByteArray();
        bais = new ByteArrayInputStream(bytes);

        docMangerRequest.setInputStreamLength((int) str.length());

        docMangerRequest.setInputStream(bais);
        LOGGER.info("++++ get input value ==> " + docMangerRequest.getInputStream() + " ++++");
        LOGGER.info("++++ get document value ==> " + docMangerRequest + " ++++");

        return adminDao.createProfilePicture(docMangerRequest);

    }

}
