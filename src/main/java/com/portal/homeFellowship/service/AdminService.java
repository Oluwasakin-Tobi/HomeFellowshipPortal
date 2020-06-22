package com.portal.homeFellowship.service;

import java.io.IOException;

import javax.validation.Valid;

import com.portal.homeFellowship.model.*;

public interface AdminService {
	
	Response ADAuthenticateUser(String username, String pwd);

	Response userNameCheck(String userName);

	Response edituser(EditUserDetails user, UserAdmin principal);

	Response authpenduser(String userID, String flag, UserAdmin principal);
	
	
	
	
	

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

	Response createOutlineDocument(DocManagerRequest docMangerRequest) throws IOException;

	Response createSocialEvent(SocialEvent request);

	Response createSocialEventDocument(DocManagerRequest docMangerRequest) throws IOException;

	Response createUser(UserDetails userDetails);

	Response monthlyReport(MonthlyReport request);

	Response updateSocialEvent(SocialEvent request);

	Response changePassword(ChangePassword request);

}
