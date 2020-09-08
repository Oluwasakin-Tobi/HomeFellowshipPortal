package com.portal.homeFellowship.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.portal.homeFellowship.model.*;

public interface InquiryService {

	List<Role> getroles();

	//List<User> getpenduser(String affiliate);
	List<UserDetails> getpenduser(String affiliate);

	List<UserDetails> getUserDetails(String userName, String role);

	List<User> getuser(String userID, String pflag, String affiliate);
	//////////////////////////////////END OF ADMIN/////////////////////////////////////////////////

	List<UserReport> getAllUserReportByAffiliate(String affiliate);

	List<AuditLog> getAuditReportByAffiliate(String affiliate);
	
	
	

	Response sendSMS(Sms request);

	Response sendMail(@Valid Sms request) throws MessagingException;
	
	
	

	List<PrayerRequest> getPrayerRequest(PrayerRequest request);

	List<Welfare> getWelfareRequest(Welfare request);

	List<Announcement> getAnnouncement();

	List<Incident> getIncident(Incident request);

	List<Expenses> getCommunityProject(Expenses request);
	
	List<Expenses> getExpenses(Expenses request);

	List<WeeklyOutline> getWeeklyOutline(WeeklyOutline request);

	List<Incident> getWhistleBlowing(Incident request);

	List<Otp> getOTP(Otp req);

	List<CalendarDetail> getCalendarEvent(CalendarDetail req);

	Response getCalendarEventToSend();

	DocManagerRequest getOutlineDocument(String id) throws IOException ;

	List<SocialEvent> getSocialEvent(SocialEvent request);

	List<DocManagerRequest> getSocialEventDocument(String outlineID) throws IOException ;

	List<WeeklyReport> getWeeklyReport(Filter request);

	List<MonthlyReport> getMonthlyReport(Filter request);

	List<DirectorReport> getDirectorReport(Filter request);

	List<WeeklyReport> getCentreReport(Filter request);

	List<WeeklyReport> getAreaReport(Filter request);

	List<WeeklyReport> getZoneReport(Filter request);

	List<WeeklyReport> getDistrictReport(Filter request);

	List<WeeklyReport> getVisitorDetail(Filter request);

	List<Training> getTraining(Training training);

	List<Centre> getCentreDetail(Centre request);

	List<Centre> viewCentre(Centre request);

	List<Testimony> viewTestimony(Testimony request);

	List<Message> viewMessage(Message request);

	List<Country> getCountry();

	List<SocialEvent> getLegalDocumentDetails(SocialEvent request);

	DocManagerRequest getLegalDocuments(String docID) throws IOException;

	List<Centre> getCentreDetails(Centre request);

	List<Quarterly> quarterlyCentre();

	List<Quarterly> quarterlyMember();

	List<Quarterly> quarterlyOffering();

}
