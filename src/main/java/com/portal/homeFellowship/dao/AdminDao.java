package com.portal.homeFellowship.dao;


import com.portal.homeFellowship.model.*;

public interface AdminDao {
	

	public Response edit_user(EditUserDetails details);

	Response userNameCheck(String userName);

	Response authorise_user(AuthoriseUser authoriseUser);
	
	
	
	
	
	
	

	Response prayerRequest(PrayerRequest request);

	Response welfareRequest(Welfare request);

	Response specialAnnouncement(Announcement request);

	Response incidentRequest(Incident request);

	Response updateIncidentRequest(Incident request);

	Response updatePrayerRequest(PrayerRequest request);

	Response updateWelfareRequest(Welfare request);

	Response expenseRequest(Expenses request);

	Response communityProject(Expenses request);

	Response weeklyReport(WeeklyReport request);

	Response weeklyOutline(WeeklyOutline request);

	Response whistleBlowing(Incident request);

	Response directorReport(DirectorReport request);

	Response saveOTP(Otp otp);

	Response updateWhistleBlowingRequest(Incident request);

	Response calendarEvent(CalendarDetail request);

	Response updateCalendarEvent(CalendarDetail request);

	Response createOutlineDocument(DocManagerRequest docMangerRequest);

	Response createSocialEvent(SocialEvent request);

	Response createSocialEventDocument(DocManagerRequest docMangerRequest);

	Response createUser(UserDetails userDetails);

	Response monthlyReport(MonthlyReport request);

	Response updateSocialEvent(SocialEvent request);

	Response changePassword(ChangePassword request);

	Response authenticateUser(String username, String pwd);

	
}
