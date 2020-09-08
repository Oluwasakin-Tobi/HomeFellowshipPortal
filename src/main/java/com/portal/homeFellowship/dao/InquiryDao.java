package com.portal.homeFellowship.dao;

import java.util.List;

import com.portal.homeFellowship.model.*;

public interface InquiryDao {
	

	public UserResp get_user_profile(UserProfile userProfile);
	

	public List<Role> get_roles();

	public List<User> get_users(UserReq req);
	
	
	public List<UserReport> getAllUserReport();

	//public List<User1> getUserDetails(String username);

	public List<UserDetails> getUserDetails(String username, String role);

	public List<UserReport> getAllUserReportByAffiliate(String affiliate);


	public List<AuditLog> getAuditReportByAffiliate(String affiliate);
	
	
	

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

	List<CalendarDetail> getCalendarEventToSend();

	List<UserDetails> userToSendRemainder(String value, String user);

	DocManagerRequest getOutlineDocument(String id);

	List<SocialEvent> getSocialEvent(SocialEvent request);


	List<UserDetails> get_auth_users(String affiliateCode);

	List<DocManagerRequest> getSocialEventDocument(String outlineID);

	List<WeeklyReport> getWeeklyReport(Filter request);

	List<MonthlyReport> getMonthlyReport(Filter request);

	List<DirectorReport> getDirectorReport(Filter request);


	Response updateSentToCalendar(String calendarID);


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


	DocManagerRequest getLegalDocuments(String id);


	List<Centre> getCentreDetails(Centre request);


	List<Quarterly> quarterlyCentre();


	List<Quarterly> quarterlyMember();


	List<Quarterly> quarterlyOffering();



}
