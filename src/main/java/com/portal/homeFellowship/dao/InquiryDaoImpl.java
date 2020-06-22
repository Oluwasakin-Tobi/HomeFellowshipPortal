package com.portal.homeFellowship.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.portal.homeFellowship.model.*;
import com.portal.homeFellowship.utility.BasicUtil;

@Repository("inquiryDao")
public class InquiryDaoImpl implements InquiryDao {

	static final Logger LOGGER = LoggerFactory.getLogger(InquiryDaoImpl.class);
	final static DateFormat MARSHARLLERDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private Environment environment;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${baseUtilPackage}")
	private String baseUtilityPackage;


	
	
	@SuppressWarnings("unchecked")
	@Override
	public UserResp get_user_profile(UserProfile userProfile) {
		SimpleJdbcCall get_user_profileSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		get_user_profileSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_user_profile")
				.withoutProcedureColumnMetaDataAccess().declareParameters(new SqlParameter("pUSER_ID", Types.NUMERIC),
						new SqlParameter("p_USERNAME", Types.VARCHAR), new SqlParameter("p_SERVERIP", Types.VARCHAR),
						new SqlOutParameter("p_code", Types.VARCHAR), new SqlOutParameter("p_message", Types.VARCHAR),
						new SqlOutParameter("r_user_profile", Types.REF_CURSOR))
				.returningResultSet("r_user_profile", new RowMapper<UserDetails>() {
					@Override
					public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserDetails user = new UserDetails();
						user.setUserID(rs.getLong("USER_ID"));
						user.setAffiliateCode(rs.getString("AFFILIATE_CODE"));
						user.setPhoneNo(rs.getString("PHONE_NO"));
						user.setActive(rs.getBoolean("IS_ACTIVE"));
						user.setUserRoles(rs.getString("USER_ROLES"));
						user.setOperationUser(rs.getBoolean("IS_OPERATION_USER"));
						user.setUserFullName(rs.getString("USER_FULL_NAME"));
						user.setUserEmailAdd(rs.getString("USER_EMAIL_ADD"));
						user.setAuthorisedUserFlag(rs.getBoolean("AUTHORISED_USER_FLAG"));
						user.setUserName(rs.getString("USERNAME"));
						user.setEditedFlag(rs.getBoolean("EDITED_FLAG"));
						user.setEditedRoleFlag(rs.getBoolean("EDIT_ROLE_FLAG"));
						user.setFirstName(rs.getString("FIRST_NAME"));
						user.setSurname(rs.getString("SURNAME"));
						user.setPassword(rs.getString("USER_PASSWORD"));

						return user;

					}
				});
		get_user_profileSimpleJdbcCall.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("pUSER_ID", userProfile.getUserID())
				.addValue("p_USERNAME", userProfile.getUserName()).addValue("p_SERVERIP", userProfile.getServerIP());

		Map<String, Object> returningResultSet = get_user_profileSimpleJdbcCall.execute(inParams);

		List<UserDetails> dbResponse = (List<UserDetails>) returningResultSet.get("r_user_profile");

		UserDetails rs1 = (dbResponse == null || dbResponse.isEmpty()) ? new UserDetails() : dbResponse.get(0);
		String responseCode = (String) returningResultSet.get("p_code");
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) returningResultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		UserResp response = new UserResp();
		response.setUserProfile(rs1);
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		return response;

	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDetails> get_auth_users(String affiliateCode) {
		SimpleJdbcCall get_auth_users = new SimpleJdbcCall(jdbcTemplate);
		get_auth_users.withProcedureName(baseUtilityPackage + ".get_pend_auth_users")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_AFFILIATE_CODE", Types.VARCHAR),
						new SqlOutParameter("r_users", Types.REF_CURSOR))
				.returningResultSet("r_users", new RowMapper<UserDetails>() {

					@Override
					public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserDetails user = new UserDetails();
						user.setUserID(rs.getLong("USER_ID"));
						user.setAffiliateCode(rs.getString("AFFILIATE_CODE"));
						user.setUserRoles(rs.getString("USER_ROLES"));
						user.setOperationUser(rs.getBoolean("IS_OPERATION_USER"));
						user.setUserFullName(rs.getString("USER_FULL_NAME"));
						user.setUserEmailAdd(rs.getString("USER_EMAIL_ADD"));
						user.setUserName(rs.getString("USERNAME"));
						user.setCentre(rs.getString("CENTRE"));
						user.setDateCreated(rs.getString("DATE_CREATED"));
						return user;
					}
				});
		get_auth_users.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("p_AFFILIATE_CODE", affiliateCode);

		Map<String, Object> returningResultSet = get_auth_users.execute(inParams);
		List<UserDetails> response = (List<UserDetails>) returningResultSet.get("r_users");

		return response == null || response.isEmpty() ? new ArrayList<UserDetails>() : response;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> get_roles() {
		SimpleJdbcCall get_roles = new SimpleJdbcCall(jdbcTemplate);
		get_roles.withProcedureName(baseUtilityPackage + ".get_roles").withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlOutParameter("r_roles", Types.REF_CURSOR))
				.returningResultSet("r_roles", new RowMapper<Role>() {

					@Override
					public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
						Role role = new Role();
						role.setRoleName(rs.getString(2));
						role.setRoleID(rs.getLong(1));
						role.setOperationRole(rs.getBoolean(3));
						return role;
					}
				});
		get_roles.compile();

		Map<String, Object> returningResultSet = get_roles.execute();
		List<Role> response = (List<Role>) returningResultSet.get("r_roles");

		return response == null || response.isEmpty() ? new ArrayList<Role>() : response;

	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<User> get_users(UserReq req) {
		SimpleJdbcCall get_affiliate_currencySimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		get_affiliate_currencySimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_users")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("pflag", Types.VARCHAR), new SqlParameter("puserid", Types.NUMERIC),
						new SqlParameter("paffiliate", Types.VARCHAR), new SqlOutParameter("r_users", Types.REF_CURSOR))
				.returningResultSet("r_users", new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User user = new User();
						String username = rs.getString("USERNAME");
						user.setUserID(rs.getLong("USER_ID"));
						user.setAffiliateCode(rs.getString("AFFILIATE_CODE"));
						user.setActive(rs.getBoolean("IS_ACTIVE"));
						user.setUserRoles(rs.getString("USER_ROLES"));
						user.setOperationUser(rs.getBoolean("IS_OPERATION_USER"));
						user.setUserFullName(rs.getString("USER_FULL_NAME"));
						user.setUserEmailAdd(rs.getString("USER_EMAIL_ADD"));
						user.setAuthorisedUserFlag(rs.getBoolean("AUTHORISED_USER_FLAG"));
						user.setUserName(username == null ? "" : username.toUpperCase());
						user.setDeleteFlag(rs.getString("DISABLE_FLAG"));
						return user;
					}
				});
		get_affiliate_currencySimpleJdbcCall.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("pflag", req.getPflag())
				.addValue("puserid", req.getUserID()).addValue("paffiliate", req.getAffiliate());

		Map<String, Object> returningResultSet = get_affiliate_currencySimpleJdbcCall.execute(inParams);
		List<User> response = (List<User>) returningResultSet.get("r_users");

		return response == null || response.isEmpty() ? new ArrayList<User>() : response;

	}


	

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UserDetails> getUserDetails(String username) {
		SimpleJdbcCall getUserDetails = new SimpleJdbcCall(jdbcTemplate);
		getUserDetails.withProcedureName(baseUtilityPackage + ".get_user_detail").withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_username", Types.VARCHAR),
						new SqlOutParameter("r_details", Types.REF_CURSOR))
				.returningResultSet("r_details", new RowMapper<UserDetails>() {

					@Override
					public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {

						UserDetails affiliate = new UserDetails();
						affiliate.setAffiliateCode(rs.getString("AFFILIATE_CODE"));
						affiliate.setUserRoles(rs.getString("USER_ROLES"));
						affiliate.setUserFullName(rs.getString("USER_FULL_NAME"));
						return affiliate;
					}
				});

		getUserDetails.compile();

		SqlParameterSource inparams = new MapSqlParameterSource().addValue("p_username", username);

		Map<String, Object> returningResultSet = getUserDetails.execute(inparams);

		List<UserDetails> response = (List<UserDetails>) returningResultSet.get("r_details");

		return response == null || response.isEmpty() ? new ArrayList<UserDetails>() : response;

	}
	
	
	////////////////////////////// END OF ADMIN
	////////////////////////////// PART/////////////////////////////////////////////////////
	
	/////////////////////////REPORTS/////////////////////////////////////////////////////////
	

	@SuppressWarnings("unchecked")
	@Override
	public List<UserReport> getAllUserReport() {
		SimpleJdbcCall getAllUserReport = new SimpleJdbcCall(jdbcTemplate);
		getAllUserReport.withProcedureName(baseUtilityPackage + ".get_users_report")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlOutParameter("r_users", Types.REF_CURSOR))
				.returningResultSet("r_users", new RowMapper<UserReport>() {

					@Override
					public UserReport mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserReport userReport = new UserReport();
						String username = rs.getString(12);
						userReport.setUserID(rs.getLong(1));
						userReport.setUserFullName(rs.getString(7));
						userReport.setAffiliateCode(rs.getString(2));
						userReport.setUserName(username == null ? "" : username.toUpperCase());
						userReport.setUserRoles(rs.getString(5));
						userReport.setEmailAdd(rs.getString(8));
						userReport.setLastLoginTime(rs.getString(25));
						userReport.setDateCreated(rs.getDate(16));
						return userReport;
					}
				});

		getAllUserReport.compile();

		Map<String, Object> returningResultSet = getAllUserReport.execute();

		List<UserReport> response = (List<UserReport>) returningResultSet.get("r_users");

		return response == null || response.isEmpty() ? new ArrayList<>() : response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserReport> getAllUserReportByAffiliate(String affiliate) {
		SimpleJdbcCall getAllUserReportByAffiliate = new SimpleJdbcCall(jdbcTemplate);
		getAllUserReportByAffiliate.withProcedureName(baseUtilityPackage + ".get_users_report_aff")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_affiliate", Types.VARCHAR),
						new SqlOutParameter("r_details", Types.REF_CURSOR))
				.returningResultSet("r_details", new RowMapper<UserReport>() {

					@Override
					public UserReport mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserReport userReport = new UserReport();
						String username = rs.getString(12);
						userReport.setUserID(rs.getLong(1));
						userReport.setUserFullName(rs.getString(7));
						userReport.setAffiliateCode(rs.getString(2));
						userReport.setUserName(username == null ? "" : username.toUpperCase());
						userReport.setUserRoles(rs.getString(5));
						userReport.setEmailAdd(rs.getString(8));
						userReport.setLastLoginTime(rs.getString(25));
						userReport.setDateCreated(rs.getDate(16));
						return userReport;
					}
				});

		getAllUserReportByAffiliate.compile();

		SqlParameterSource inparams = new MapSqlParameterSource().addValue("p_affiliate", affiliate);

		Map<String, Object> returningResultSet = getAllUserReportByAffiliate.execute(inparams);

		List<UserReport> response = (List<UserReport>) returningResultSet.get("r_details");

		return response == null || response.isEmpty() ? new ArrayList<>() : response;
	}
	
	@SuppressWarnings("all")
	@Override
	public List<AuditLog> getAuditReportByAffiliate(String affiliate) {
		SimpleJdbcCall getAuditReportByAffiliate = new SimpleJdbcCall(jdbcTemplate);
		getAuditReportByAffiliate.withProcedureName(baseUtilityPackage + ".get_audit_log")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_affiliate", Types.VARCHAR),
						new SqlOutParameter("r_details", Types.REF_CURSOR))
				.returningResultSet("r_details", new RowMapper<AuditLog>() {

					@Override
					public AuditLog mapRow(ResultSet rs, int rowNum) throws SQLException {
						AuditLog auditLog = new AuditLog();
						auditLog.setTransRef(rs.getLong(1));
						auditLog.setEventDesc(rs.getString(2));
						auditLog.setEventSrc(rs.getString(3));
						auditLog.setExtRef(rs.getString(4));
						auditLog.setUserName(rs.getString(5));
						auditLog.setServerIp(rs.getString(6));
						auditLog.setDateCreated(rs.getDate(7));
						auditLog.setAffiliateCode(rs.getString(8));
						return auditLog;
					}
				});

		getAuditReportByAffiliate.compile();

		SqlParameterSource inparams = new MapSqlParameterSource().addValue("p_affiliate", affiliate);

		Map<String, Object> returningResultSet = getAuditReportByAffiliate.execute(inparams);

		List<AuditLog> response = (List<AuditLog>) returningResultSet.get("r_details");

		return response == null || response.isEmpty() ? new ArrayList<>() : response;
	}
	
	

	
	
	
	
	
	@SuppressWarnings("all")
	@Override
	public List<PrayerRequest> getPrayerRequest(PrayerRequest request) {
		SimpleJdbcCall getPrayerRequest = new SimpleJdbcCall(jdbcTemplate);
		getPrayerRequest.withProcedureName(baseUtilityPackage + ".get_prayer_request")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_value", Types.VARCHAR),
						new SqlOutParameter("r_details", Types.REF_CURSOR))
				.returningResultSet("r_details", new RowMapper<PrayerRequest>() {

					@Override
					public PrayerRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
						PrayerRequest request = new PrayerRequest();
						request.setPrayerId(rs.getString("prayer_id"));
						request.setStatus(rs.getString("status"));
						request.setName(rs.getString("requester"));
						request.setPrayer(rs.getString("prayer_request"));
						return request;
					}
				});

		getPrayerRequest.compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_value", request.getName());

		Map<String, Object> returningResultSet = getPrayerRequest.execute(inparam);

		List<PrayerRequest> response = (List<PrayerRequest>) returningResultSet.get("r_details");

		return response == null || response.isEmpty() ? new ArrayList<>() : response;
	}
	

	@SuppressWarnings("all")
	@Override
	public List<Welfare> getWelfareRequest(Welfare request) {
		SimpleJdbcCall getPrayerRequest = new SimpleJdbcCall(jdbcTemplate);
		getPrayerRequest.withProcedureName(baseUtilityPackage + ".get_welfare_request")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_value", Types.VARCHAR),
								   new SqlOutParameter("r_details", Types.REF_CURSOR))
				.returningResultSet("r_details", new RowMapper<Welfare>() {

					@Override
					public Welfare mapRow(ResultSet rs, int rowNum) throws SQLException {
						Welfare request = new Welfare();
						request.setName(rs.getString("requester"));
						request.setWelfare(rs.getString("welfare_request"));
						request.setWelfareId(rs.getString("WELFARE_ID"));
						request.setStatus(rs.getString("STATUS"));
						request.setComment(rs.getString("STATUS_COMMENT"));
						return request;
					}
				});

		getPrayerRequest.compile();
		

		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_value", request.getName());

		Map<String, Object> returningResultSet = getPrayerRequest.execute(inparam);

		List<Welfare> response = (List<Welfare>) returningResultSet.get("r_details");

		return response == null || response.isEmpty() ? new ArrayList<>() : response;
	}
	
	@SuppressWarnings("all")
	@Override
	public List<Announcement> getAnnouncement() {
		SimpleJdbcCall getPrayerRequest = new SimpleJdbcCall(jdbcTemplate);
		getPrayerRequest.withProcedureName(baseUtilityPackage + ".get_special_announcement")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlOutParameter("r_details", Types.REF_CURSOR))
				.returningResultSet("r_details", new RowMapper<Announcement>() {

					@Override
					public Announcement mapRow(ResultSet rs, int rowNum) throws SQLException {
						Announcement request = new Announcement();
						request.setName(rs.getString("REQUESTER"));
						request.setAnnounce(rs.getString("ANNOUNCEMENT"));
						request.setCategory(rs.getString("CATEGORY_ID").replace(",", ""));
						request.setEventDate(rs.getString("EVENT_DATE"));
						request.setMeetingLink(rs.getString("MEETING_LINK"));
						return request;
					}
				});

		getPrayerRequest.compile();

		Map<String, Object> returningResultSet = getPrayerRequest.execute();

		List<Announcement> response = (List<Announcement>) returningResultSet.get("r_details");

		return response == null || response.isEmpty() ? new ArrayList<>() : response;
	}
	
	@SuppressWarnings("all")
	@Override
	public List<Incident> getIncident(Incident request) {
		SimpleJdbcCall getIncident = new SimpleJdbcCall(jdbcTemplate);
		getIncident.withProcedureName(baseUtilityPackage + ".get_incident_request")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_value", Types.VARCHAR),
						new SqlOutParameter("r_details", Types.REF_CURSOR))
				.returningResultSet("r_details", new RowMapper<Incident>() {

					@Override
					public Incident mapRow(ResultSet rs, int rowNum) throws SQLException {
						Incident request = new Incident();
						request.setIncidentId(rs.getString("INCIDENT_ID"));
						request.setName(rs.getString("REQUESTER"));
						request.setIncident(rs.getString("INCIDENT_REQUEST"));
						request.setStatus(rs.getString("STATUS"));
						request.setComment(rs.getString("STATUS_COMMENT"));
						return request;
					}
				});

		getIncident.compile();
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_value", request.getName());

		Map<String, Object> returningResultSet = getIncident.execute(inparam);

		List<Incident> response = (List<Incident>) returningResultSet.get("r_details");

		return response == null || response.isEmpty() ? new ArrayList<>() : response;
	}

	@SuppressWarnings("all")
	@Override
	public List<Expenses> getCommunityProject(Expenses request) {
		SimpleJdbcCall getIncident = new SimpleJdbcCall(jdbcTemplate);
		getIncident.withProcedureName(baseUtilityPackage + ".get_community_project")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_value", Types.VARCHAR),
						new SqlOutParameter("r_details", Types.REF_CURSOR))
				.returningResultSet("r_details", new RowMapper<Expenses>() {

					@Override
					public Expenses mapRow(ResultSet rs, int rowNum) throws SQLException {
						Expenses request = new Expenses();
						request.setExpenseId(rs.getString("PROJECT_ID"));
						request.setName(rs.getString("REQUESTER"));
						request.setExpense(rs.getString("COMMUNITY_PROJECT"));
						request.setCategory(rs.getString("CATEGORY"));
						request.setAmount(rs.getBigDecimal("AMOUNT"));
						return request;
					}
				});

		getIncident.compile();
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_value", request.getName());

		Map<String, Object> returningResultSet = getIncident.execute(inparam);

		List<Expenses> response = (List<Expenses>) returningResultSet.get("r_details");

		return response == null || response.isEmpty() ? new ArrayList<>() : response;
	}

	@SuppressWarnings("all")
	@Override
	public List<Expenses> getExpenses(Expenses request) {
		SimpleJdbcCall getIncident = new SimpleJdbcCall(jdbcTemplate);
		getIncident.withProcedureName(baseUtilityPackage + ".get_expenses")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_value", Types.VARCHAR),
						new SqlOutParameter("r_details", Types.REF_CURSOR))
				.returningResultSet("r_details", new RowMapper<Expenses>() {

					@Override
					public Expenses mapRow(ResultSet rs, int rowNum) throws SQLException {
						Expenses request = new Expenses();
						request.setExpenseId(rs.getString("EXPENSE_ID"));
						request.setName(rs.getString("REQUESTER"));
						request.setExpense(rs.getString("EXPENSE"));
						request.setCategory(rs.getString("CATEGORY"));
						request.setAmount(rs.getBigDecimal("AMOUNT"));
						return request;
					}
				});

		getIncident.compile();
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_value", request.getName());

		Map<String, Object> returningResultSet = getIncident.execute(inparam);

		List<Expenses> response = (List<Expenses>) returningResultSet.get("r_details");

		return response == null || response.isEmpty() ? new ArrayList<>() : response;
	}

	@SuppressWarnings("all")
	@Override
	public List<WeeklyOutline> getWeeklyOutline(WeeklyOutline request) {
		SimpleJdbcCall getIncident = new SimpleJdbcCall(jdbcTemplate);
		getIncident.withProcedureName(baseUtilityPackage + ".get_weekly_outline")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_value", Types.VARCHAR),
						new SqlOutParameter("r_details", Types.REF_CURSOR))
				.returningResultSet("r_details", new RowMapper<WeeklyOutline>() {

					@Override
					public WeeklyOutline mapRow(ResultSet rs, int rowNum) throws SQLException {
						WeeklyOutline request = new WeeklyOutline();
						request.setOutline(rs.getString("WEEKLY_OUTLINE"));
						request.setName(rs.getString("REQUESTER"));
						request.setTopic(rs.getString("TOPIC").toUpperCase());
						request.setUploadFlag(rs.getString("UPLOAD_FLAG"));
						request.setOutlineID(rs.getString("OUTLINE_ID"));
						request.setDateCreated(MARSHARLLERDATEFORMAT.format(rs.getDate("DATE_CREATED")));
						return request;
					}
				});

		getIncident.compile();
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_value", request.getName());

		Map<String, Object> returningResultSet = getIncident.execute(inparam);

		List<WeeklyOutline> response = (List<WeeklyOutline>) returningResultSet.get("r_details");

		return response == null || response.isEmpty() ? new ArrayList<>() : response;
	}
	
	@SuppressWarnings("all")
	@Override
	public List<Incident> getWhistleBlowing(Incident request) {
		SimpleJdbcCall getIncident = new SimpleJdbcCall(jdbcTemplate);
		getIncident.withProcedureName(baseUtilityPackage + ".get_whistle_blow")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_value", Types.VARCHAR),
						new SqlOutParameter("r_details", Types.REF_CURSOR))
				.returningResultSet("r_details", new RowMapper<Incident>() {

					@Override
					public Incident mapRow(ResultSet rs, int rowNum) throws SQLException {
						Incident request = new Incident();
						request.setIncidentId(rs.getString("WHISTLE_BLOW_ID"));
						request.setName(rs.getString("REQUESTER"));
						request.setIncident(rs.getString("WHISTLE_BLOW"));
						request.setStatus(rs.getString("STATUS"));
						request.setComment(rs.getString("STATUS_COMMENT"));
						return request;
					}
				});

		getIncident.compile();
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_value", request.getName());

		Map<String, Object> returningResultSet = getIncident.execute(inparam);

		List<Incident> response = (List<Incident>) returningResultSet.get("r_details");

		return response == null || response.isEmpty() ? new ArrayList<>() : response;
	}

		@SuppressWarnings("all")
		@Override
		public List<Otp> getOTP(Otp req) {
			SimpleJdbcCall getIncident = new SimpleJdbcCall(jdbcTemplate);
			getIncident.withProcedureName(baseUtilityPackage + ".get_otp")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(new SqlParameter("p_value", Types.VARCHAR),
							new SqlOutParameter("r_details", Types.REF_CURSOR))
					.returningResultSet("r_details", new RowMapper<Otp>() {

						@Override
						public Otp mapRow(ResultSet rs, int rowNum) throws SQLException {
							Otp request = new Otp();
							request.setOtp(rs.getString("OTP"));
							request.setPhoneNo(rs.getString("PHONE_NO"));
							request.setSessionID(rs.getString("SESSION_ID"));
							return request;
						}
					});

			getIncident.compile();
			SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_value", req.getSessionID());

			Map<String, Object> returningResultSet = getIncident.execute(inparam);

			List<Otp> response = (List<Otp>) returningResultSet.get("r_details");

			return response == null || response.isEmpty() ? new ArrayList<>() : response;
		}
		
		@SuppressWarnings("all")
		@Override
		public List<CalendarDetail> getCalendarEvent(CalendarDetail req) {
			SimpleJdbcCall getIncident = new SimpleJdbcCall(jdbcTemplate);
			getIncident.withProcedureName(baseUtilityPackage + ".get_calendar_event")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(new SqlParameter("p_value", Types.VARCHAR),
							new SqlOutParameter("r_details", Types.REF_CURSOR))
					.returningResultSet("r_details", new RowMapper<CalendarDetail>() {

						@Override
						public CalendarDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
							CalendarDetail request = new CalendarDetail();
							request.setCalendarID(rs.getString("CALENDAR_ID"));
							request.setEvent(rs.getString("EVENT"));
							request.setEventDateD(rs.getDate("EVENT_DATE"));
							request.setCreatedBy(rs.getString("CREATED_BY") +" on "+rs.getString("DATE_CREATED"));
							request.setSendTo(rs.getString("SEND_TO"));
							return request;
						}
					});

			getIncident.compile();
			SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_value", req.getCalendarID());

			Map<String, Object> returningResultSet = getIncident.execute(inparam);

			List<CalendarDetail> response = (List<CalendarDetail>) returningResultSet.get("r_details");

			return response == null || response.isEmpty() ? new ArrayList<>() : response;
		}
		
		@SuppressWarnings("all")
		@Override
		public List<CalendarDetail> getCalendarEventToSend() {
			SimpleJdbcCall getIncident = new SimpleJdbcCall(jdbcTemplate);
			getIncident.withProcedureName(baseUtilityPackage + ".get_calendar_event_to_send")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(new SqlOutParameter("r_details", Types.REF_CURSOR))
					.returningResultSet("r_details", new RowMapper<CalendarDetail>() {

						@Override
						public CalendarDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
							CalendarDetail request = new CalendarDetail();
							request.setCalendarID(rs.getString("CALENDAR_ID"));
							request.setEvent(rs.getString("EVENT"));
							request.setEventDate(rs.getString("EVENT_DATE"));
							request.setCreatedBy(rs.getString("CREATED_BY") +" on "+rs.getString("DATE_CREATED"));
							request.setSendTo(rs.getString("SEND_TO"));
							request.setSendToUser(rs.getString("SEND_TO_USER"));
							return request;
						}
					});

			getIncident.compile();
			Map<String, Object> returningResultSet = getIncident.execute();

			List<CalendarDetail> response = (List<CalendarDetail>) returningResultSet.get("r_details");

			return response == null || response.isEmpty() ? new ArrayList<>() : response;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public List<UserDetails> userToSendRemainder(String value, String user) {
			SimpleJdbcCall get_user_profileSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			get_user_profileSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_userto_sendreminder")
					.withoutProcedureColumnMetaDataAccess().declareParameters(
							new SqlParameter("p_value", Types.VARCHAR), 
							new SqlOutParameter("r_details", Types.REF_CURSOR))
					.returningResultSet("r_details", new RowMapper<UserDetails>() {
						@Override
						public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
							UserDetails user = new UserDetails();
							user.setUserID(rs.getLong("USER_ID"));
							user.setAffiliateCode(rs.getString("AFFILIATE_CODE"));
							user.setPhoneNo(rs.getString("PHONE_NO"));
							user.setActive(rs.getBoolean("IS_ACTIVE"));
							user.setUserRoles(rs.getString("USER_ROLES"));
							user.setOperationUser(rs.getBoolean("IS_OPERATION_USER"));
							user.setUserFullName(rs.getString("USER_FULL_NAME"));
							user.setUserEmailAdd(rs.getString("USER_EMAIL_ADD"));
							user.setAuthorisedUserFlag(rs.getBoolean("AUTHORISED_USER_FLAG"));
							user.setUserName(rs.getString("USERNAME"));
							user.setEditedFlag(rs.getBoolean("EDITED_FLAG"));
							user.setEditedRoleFlag(rs.getBoolean("EDIT_ROLE_FLAG"));
							user.setFirstName(rs.getString("FIRST_NAME"));
							user.setSurname(rs.getString("SURNAME"));

							return user;

						}
					});
			get_user_profileSimpleJdbcCall.compile();

			SqlParameterSource inParams = new MapSqlParameterSource().addValue("p_value", value).addValue("p_value_user", user);

			Map<String, Object> returningResultSet = get_user_profileSimpleJdbcCall.execute(inParams);


			List<UserDetails> response = (List<UserDetails>) returningResultSet.get("r_details");

			return response == null || response.isEmpty() ? new ArrayList<>() : response;

		}
		
		
		@SuppressWarnings("unchecked")
	    @Override
	    public DocManagerRequest getOutlineDocument(String id) {

	        SimpleJdbcCall get_documentby_docuniqueidSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
	        get_documentby_docuniqueidSimpleJdbcCall.withProcedureName(baseUtilityPackage+ ".get_outline_document")
	                .withoutProcedureColumnMetaDataAccess()
	                .declareParameters(new SqlParameter("p_value", Types.VARCHAR), 
							new SqlOutParameter("r_details", Types.REF_CURSOR))
	                .returningResultSet("r_details", new RowMapper<DocManagerRequest>() {

	                    @Override
	                    public DocManagerRequest mapRow(ResultSet rs, int rowNum) throws SQLException {

	                        LobHandler lobHandler = new DefaultLobHandler();
	                        DocManagerRequest docMangerRequest = new DocManagerRequest();

	                        docMangerRequest.setDocName(rs.getString("DOCUMENT_NAME"));
	                        docMangerRequest.setDocumentUniqueID(rs.getString("DOCUMENT_UNIQUE_ID"));
	                        docMangerRequest.setInputStream(lobHandler.getBlobAsBinaryStream(rs, "INPUTSTREAM"));
	                        docMangerRequest.setFiletype(rs.getString("CONTENT_TYPE"));
	                        docMangerRequest.setInputStreamLength(rs.getInt("DOCUMENT_LENGTH"));
	                        docMangerRequest.setCompressed(rs.getBoolean("COMPRESSED"));
	                        LOGGER.info(
	                                "++++ Document Manager Response ==> " + docMangerRequest.getInputStream() + " ++++");

	                        return docMangerRequest;
	                    }
	                });
	        get_documentby_docuniqueidSimpleJdbcCall.compile();

	        SqlParameterSource inParams = new MapSqlParameterSource()
	                .addValue("p_value", id);


	        Map<String, Object> returningResultSet = get_documentby_docuniqueidSimpleJdbcCall.execute(inParams);
	        String responseCode = (String) returningResultSet.get("p_code");
	        String validResponseCode = responseCode != null ? responseCode : "99";
	        LOGGER.info("+++ validResponseCode ==> " + validResponseCode + " +++");
	        String responseMsg = (String) returningResultSet.get("p_message");
	        String validResponseMsg = responseMsg != null ? responseMsg : "";
	        LOGGER.info("+++ validResponseMsg ==> " + validResponseMsg + " +++");
	        LOGGER.info("+++ returningResultSet ==> \n" + returningResultSet + " +++");

	        List<DocManagerRequest> dbResponse1 = ((List<DocManagerRequest>) returningResultSet
	                .get("r_details"));

	        return dbResponse1==null||dbResponse1.isEmpty()?new DocManagerRequest():dbResponse1.get(0);

	    }

		
		@SuppressWarnings("all")
		@Override
		public List<SocialEvent> getSocialEvent(SocialEvent request) {
			SimpleJdbcCall getIncident = new SimpleJdbcCall(jdbcTemplate);
			getIncident.withProcedureName(baseUtilityPackage + ".get_social_event")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(new SqlParameter("p_value", Types.VARCHAR),
							new SqlOutParameter("r_details", Types.REF_CURSOR))
					.returningResultSet("r_details", new RowMapper<SocialEvent>() {

						@Override
						public SocialEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
							SocialEvent request = new SocialEvent();
							request.setEvent(rs.getString("SOCIAL_EVENT"));
							request.setName(rs.getString("REQUESTER"));
							request.setTopic(rs.getString("TOPIC"));
							request.setUploadFlag(rs.getString("UPLOAD_FLAG"));
							request.setEventID(rs.getString("EVENT_ID"));
							return request;
						}
					});

			getIncident.compile();
			SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_value", request.getName());

			Map<String, Object> returningResultSet = getIncident.execute(inparam);

			List<SocialEvent> response = (List<SocialEvent>) returningResultSet.get("r_details");

			return response == null || response.isEmpty() ? new ArrayList<>() : response;
		}
		
		
		@SuppressWarnings("unchecked")
	    @Override
	    public List<DocManagerRequest> getSocialEventDocument(String outlineID)  {

	        SimpleJdbcCall get_documentby_docuniqueidSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
	        get_documentby_docuniqueidSimpleJdbcCall.withProcedureName(baseUtilityPackage+ ".get_event_document")
	                .withoutProcedureColumnMetaDataAccess()
	                .declareParameters(new SqlParameter("p_value", Types.VARCHAR), 
							new SqlOutParameter("r_details", Types.REF_CURSOR))
	                .returningResultSet("r_details", new RowMapper<DocManagerRequest>() {

	                    @Override
	                    public DocManagerRequest mapRow(ResultSet rs, int rowNum) throws SQLException {

	                        LobHandler lobHandler = new DefaultLobHandler();
	                        DocManagerRequest docMangerRequest = new DocManagerRequest();

	                        docMangerRequest.setDocName(rs.getString("DOCUMENT_NAME"));
	                        docMangerRequest.setDocumentUniqueID(rs.getString("DOCUMENT_UNIQUE_ID"));
	                        docMangerRequest.setInputStream(lobHandler.getBlobAsBinaryStream(rs, "INPUTSTREAM"));
	                        docMangerRequest.setFiletype(rs.getString("CONTENT_TYPE"));
	                        docMangerRequest.setInputStreamLength(rs.getInt("DOCUMENT_LENGTH"));
	                        docMangerRequest.setCompressed(rs.getBoolean("COMPRESSED"));
	                        docMangerRequest.setTopic(rs.getString("INPUT"));
	                        LOGGER.info(
	                                "++++ Document Manager Response ==> " + docMangerRequest.getInputStream() + " ++++");

	                        return docMangerRequest;
	                    }
	                });
	        get_documentby_docuniqueidSimpleJdbcCall.compile();

	        SqlParameterSource inParams = new MapSqlParameterSource()
	                .addValue("p_value", outlineID);


	        Map<String, Object> returningResultSet = get_documentby_docuniqueidSimpleJdbcCall.execute(inParams);
	        String responseCode = (String) returningResultSet.get("p_code");
	        String validResponseCode = responseCode != null ? responseCode : "99";
	        LOGGER.info("+++ validResponseCode ==> " + validResponseCode + " +++");
	        String responseMsg = (String) returningResultSet.get("p_message");
	        String validResponseMsg = responseMsg != null ? responseMsg : "";
	        LOGGER.info("+++ validResponseMsg ==> " + validResponseMsg + " +++");
	        LOGGER.info("+++ returningResultSet ==> \n" + returningResultSet + " +++");

	        List<DocManagerRequest> dbResponse1 = ((List<DocManagerRequest>) returningResultSet
	                .get("r_details"));

	        return dbResponse1==null||dbResponse1.isEmpty()?new ArrayList<>():dbResponse1;

	    }

		@SuppressWarnings("all")
		@Override
		public List<WeeklyReport> getWeeklyReport(Filter request) {
			SimpleJdbcCall getIncident = new SimpleJdbcCall(jdbcTemplate);
			getIncident.withProcedureName(baseUtilityPackage + ".get_weekly_report")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(new SqlParameter("p_from", Types.DATE),
							new SqlParameter("p_to", Types.DATE),
							new SqlParameter("p_value", Types.VARCHAR),
							new SqlOutParameter("r_details", Types.REF_CURSOR))
					.returningResultSet("r_details", new RowMapper<WeeklyReport>() {

						@Override
						public WeeklyReport mapRow(ResultSet rs, int rowNum) throws SQLException {
							WeeklyReport request = new WeeklyReport();
//							request.setAdultPresent(rs.getString("ADULT_PRESENT"));
//							request.setArea(rs.getString("AREA"));
//							request.setCentre(rs.getString("CENTRE"));
//							request.setCentreAddress(rs.getString("CENTRE_ADDRESS"));
//							request.setChildPresent(rs.getString("CHILD_PRESENT"));
//							
//							request.setHostName(rs.getString("HOST_NAME"));
//							request.setLeaderName(rs.getString("LEADER_NAME"));
//							request.setVisitors(rs.getString("VISITORS"));
//							request.setOffering(rs.getString("OFFERING"));
							
							request.setCentre(rs.getString(1));
							request.setCentreAddress(rs.getString(2));
							request.setHostName(rs.getString(3));
							request.setLeaderName(rs.getString(4));
							request.setOffering(rs.getString(5));
							return request;
						}
					});

			getIncident.compile();
			SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_from", request.getDateFrom())
					.addValue("p_to", request.getDateTo())
					.addValue("p_value", request.getDateToStr());

			Map<String, Object> returningResultSet = getIncident.execute(inparam);

			List<WeeklyReport> response = (List<WeeklyReport>) returningResultSet.get("r_details");

			return response == null || response.isEmpty() ? new ArrayList<>() : response;
		}

		@SuppressWarnings("all")
		@Override
		public List<MonthlyReport> getMonthlyReport(Filter request) {
			SimpleJdbcCall getIncident = new SimpleJdbcCall(jdbcTemplate);
			getIncident.withProcedureName(baseUtilityPackage + ".get_monthly_report")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(new SqlParameter("p_from", Types.DATE),
							new SqlParameter("p_to", Types.DATE),
							new SqlParameter("p_value", Types.VARCHAR),
							new SqlOutParameter("r_details", Types.REF_CURSOR))
					.returningResultSet("r_details", new RowMapper<MonthlyReport>() {

						@Override
						public MonthlyReport mapRow(ResultSet rs, int rowNum) throws SQLException {
							MonthlyReport request = new MonthlyReport();
//							request.setAdultPresent(rs.getString("ADULT_PRESENT"));
//							request.setArea(rs.getString("AREA"));
//							request.setCentre(rs.getString("CENTRE"));
//							request.setCentreAddress(rs.getString("CENTRE_ADDRESS"));
//							request.setChildPresent(rs.getString("CHILD_PRESENT"));
//							
//							request.setHostName(rs.getString("HOST_NAME"));
//							request.setLeaderName(rs.getString("LEADER_NAME"));
//							request.setVisitors(rs.getString("VISITORS"));
//							request.setOffering(rs.getString("OFFERING"));
							return request;
						}
					});

			getIncident.compile();
			SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_from", request.getDateFrom())
					.addValue("p_to", request.getDateTo())
					.addValue("p_value", request.getDateToStr());

			Map<String, Object> returningResultSet = getIncident.execute(inparam);

			List<MonthlyReport> response = (List<MonthlyReport>) returningResultSet.get("r_details");

			return response == null || response.isEmpty() ? new ArrayList<>() : response;
		}

		@SuppressWarnings("all")
		@Override
		public List<DirectorReport> getDirectorReport(Filter request) {
			SimpleJdbcCall getIncident = new SimpleJdbcCall(jdbcTemplate);
			getIncident.withProcedureName(baseUtilityPackage + ".get_director_report")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(new SqlParameter("p_from", Types.DATE),
							new SqlParameter("p_to", Types.DATE),
							new SqlParameter("p_value", Types.VARCHAR),
							new SqlOutParameter("r_details", Types.REF_CURSOR))
					.returningResultSet("r_details", new RowMapper<DirectorReport>() {

						@Override
						public DirectorReport mapRow(ResultSet rs, int rowNum) throws SQLException {
							DirectorReport request = new DirectorReport();
							request.setCenter(rs.getString("CENTER_NAME"));
							request.setNoOfCenter(rs.getString("NO_OF_CENTER"));
							request.setNoOfIndependentMember(rs.getString("NO_OF_INDPNDNT_MEMBER"));
							request.setAvAttendanceOfMember(rs.getString("AV_ATTNDNCE_OF_MEMBER"));
							request.setTotalNoOfNewConvert(rs.getString("TOTAL_NO_NEW_CONVERT"));
							request.setNoOfFirstTimerFollowUp(rs.getString("NO_FIRST_TIMER_FOLLOWUP"));
							request.setNoOfFirstTimerCoverted(rs.getString("NO_FIRST_TIMER_CONVERTED"));
							request.setNoOfWeeklyReportSubmitted(rs.getString("NO_WEEKLY_REP_SUBMITTED"));
							request.setNoOfEvanProject(rs.getString("NO_OF_EVANG_PROJECT"));
							request.setNoOfcommImpactProject(rs.getString("NO_OF_COMM_IMP_PROJECT"));
							request.setNoOfNamingCeremony(rs.getString("NO_OF_NAMING_CEREMONY"));
							request.setNoOfburial(rs.getString("NO_OF_BURIAL"));
							request.setNoOfArea(rs.getString("NO_OF_AREA"));
							request.setNoOfIntern(rs.getString("NO_OF_INTERN"));
							request.setNoOfAsstLeader(rs.getString("NO_OF_ASST_LEADER"));
							request.setNoOfLeader(rs.getString("NO_OF_LEADER"));
							request.setNoOfAreaSupervisor(rs.getString("NO_OF_AREA_SUPERVISOR"));
							request.setTotalOffering(rs.getString("TOTAL_OFFERING"));
							request.setNoOfNewLeaderIntroduced(rs.getString("NO_NEW_LEADER_INTRODUCED"));
							request.setNoOfNewLeaderTrained(rs.getString("NO_NEW_LEADER_TRAINED"));
							request.setNoOfNewHostIntroduced(rs.getString("NO_NEW_HOST_INTRODUCED"));
							request.setNoOfNewHostTrained(rs.getString("NO_NEW_HOST_TRAINED"));
							request.setDateCreated(rs.getDate("DATE_CREATED"));
							return request;
						}
					});

			getIncident.compile();
			SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_from", request.getDateFrom())
					.addValue("p_to", request.getDateTo())
					.addValue("p_value", request.getDateToStr());

			Map<String, Object> returningResultSet = getIncident.execute(inparam);

			List<DirectorReport> response = (List<DirectorReport>) returningResultSet.get("r_details");

			return response == null || response.isEmpty() ? new ArrayList<>() : response;
		}

		@Override
		public Response updateSentToCalendar(String calendarID) {
			SimpleJdbcCall incidentRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
			incidentRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".update_sendreminder")
			.declareParameters(new SqlParameter("p_calendar_id", Types.NUMERIC),
							   new SqlOutParameter("p_code", Types.VARCHAR),
							   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
			
			SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_calendar_id", calendarID);
			
			Map<String, Object> resultSet = incidentRequestJdbc.execute(inparam);
			String responseCode = (String) resultSet.get("p_code");
			
			String validResponseCode = responseCode != null ? responseCode : "99";
			String responseMsg = (String) resultSet.get("p_message");
			String validResponseMsg = responseMsg != null ? responseMsg : "";

			Response response = new Response();
			response.setResponseCode(validResponseCode);
			response.setResponseMessage(validResponseMsg);
			
			return response;
		}

		
	

}
