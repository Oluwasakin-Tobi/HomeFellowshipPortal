package com.portal.homeFellowship.dao;

import java.util.List;

import com.portal.homeFellowship.model.*;

public interface InquiryDao {
	

	public UserResp get_user_profile(UserProfile userProfile);
	

	public List<Role> get_roles();

	public List<User> get_users(UserReq req);
	
	
	public List<UserReport> getAllUserReport();

	//public List<User1> getUserDetails(String username);

	public List<UserDetails> getUserDetails(String username);

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



}
