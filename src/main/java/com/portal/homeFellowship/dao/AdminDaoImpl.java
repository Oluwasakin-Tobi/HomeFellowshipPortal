package com.portal.homeFellowship.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.stereotype.Repository;

import com.portal.homeFellowship.model.*;

@Repository("adminDao")
public class AdminDaoImpl implements AdminDao {

	final static Logger LOGGER = LoggerFactory.getLogger(AdminDaoImpl.class);

	final static DateFormat MARSHARLLERDATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private Environment environment;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${baseUtilPackage}")
	private String baseUtilityPackage;

//	@Override
//	public Response create_user(User user) {
//		SimpleJdbcCall createUser = new SimpleJdbcCall(jdbcTemplate);
//		createUser.withProcedureName(baseUtilityPackage + ".create_user").withoutProcedureColumnMetaDataAccess()
//				.declareParameters(new SqlParameter("pUSER_ID", Types.NUMERIC),
//						new SqlParameter("pAFFILIATE_CODE", Types.VARCHAR),
//						new SqlParameter("pUSER_ROLES", Types.VARCHAR),
//						new SqlParameter("pIS_OPERATION_USER", Types.NUMERIC),
//						new SqlParameter("pUSER_FULL_NAME", Types.VARCHAR),
//						new SqlParameter("pUSER_EMAIL_ADD", Types.VARCHAR),
//						new SqlParameter("ppassword", Types.VARCHAR),
//						new SqlParameter("pPHONE_NO", Types.VARCHAR),
//						new SqlParameter("AUTHORISED_USER_FLAG", Types.NUMERIC),
//						new SqlParameter("p_USERNAME", Types.VARCHAR), new SqlParameter("p_created_by", Types.VARCHAR),
//						new SqlParameter("p_SERVERIP", Types.VARCHAR), new SqlOutParameter("p_code", Types.VARCHAR),
//						new SqlOutParameter("p_message", Types.VARCHAR))
//				.compile();
//
//		SqlParameterSource inParams = new MapSqlParameterSource().addValue("pUSER_ID", user.getUserID())
//				.addValue("pAFFILIATE_CODE", user.getAffiliateCode()).addValue("pUSER_ROLES", user.getUserRoles())
//				.addValue("pIS_OPERATION_USER", user.isOperationUser())
//				.addValue("pUSER_FULL_NAME", user.getUserFullName()).addValue("pUSER_EMAIL_ADD", user.getUserEmailAdd())
//				.addValue("ppassword", user.getPassword())
//				.addValue("AUTHORISED_USER_FLAG", user.isAuthorisedUserFlag())
//				.addValue("p_USERNAME", user.getUserName()).addValue("p_created_by", user.getCreatedBy())
//				.addValue("p_SERVERIP", user.getServerIP()).addValue("pPHONE_NO", user.getPhoneNo());
//
//		Map<String, Object> returningResult = createUser.execute(inParams);
//		String responseCode = (String) returningResult.get("p_code");
//		String validResponseCode = responseCode != null ? responseCode : "99";
//		String responseMsg = (String) returningResult.get("p_message");
//		String validResponseMsg = responseMsg != null ? responseMsg : "";
//
//		Response response = new Response();
//		response.setResponseCode(validResponseCode);
//		response.setResponseMessage(validResponseMsg);
//
//		return response;
//	}
	
	@Override
	public Response createUser(UserDetails user) {
		SimpleJdbcCall createUser = new SimpleJdbcCall(jdbcTemplate);
		createUser.withProcedureName(baseUtilityPackage + ".create_user").withoutProcedureColumnMetaDataAccess()
				.declareParameters(
						new SqlParameter("pAFFILIATE_CODE", Types.VARCHAR),
						new SqlParameter("pUSER_ROLES", Types.VARCHAR),
						new SqlParameter("pIS_OPERATION_USER", Types.NUMERIC),
						new SqlParameter("pUSER_FULL_NAME", Types.VARCHAR),
						new SqlParameter("pUSER_EMAIL_ADD", Types.VARCHAR),
						new SqlParameter("ppassword", Types.VARCHAR),
						new SqlParameter("pPHONE_NO", Types.VARCHAR),
						new SqlParameter("AUTHORISED_USER_FLAG", Types.NUMERIC),
						new SqlParameter("p_USERNAME", Types.VARCHAR), 
						
						
						new SqlParameter("pSURNAME", Types.VARCHAR),
						new SqlParameter("pFIRST_NAME", Types.VARCHAR),
						new SqlParameter("pMIDDLE_NAME", Types.VARCHAR),
						new SqlParameter("pGENDER", Types.VARCHAR),
						new SqlParameter("pDATE_OF_BIRTH", Types.DATE),
						new SqlParameter("pDATE_JOINED_HOTR", Types.DATE),
						new SqlParameter("pDATE_JOINED_CITH", Types.DATE),
						new SqlParameter("pCOMPLETED_FIND_THE_ROCK", Types.VARCHAR), 
						new SqlParameter("pCOMPLETED_SPIRIT_AUTH", Types.VARCHAR),
						new SqlParameter("pHOME_ADDRESS", Types.VARCHAR),
						new SqlParameter("pPLACE_OF_WORK", Types.VARCHAR),
						new SqlParameter("pPLACE_OF_WORK_ADD", Types.VARCHAR),
		
						
						
						new SqlParameter("pCHURCH_DEPARTMENT", Types.VARCHAR),
						new SqlParameter("pOCCUPATION", Types.VARCHAR),
						new SqlParameter("pDATE_JOINED_CHURCH_DEPT", Types.DATE),
						new SqlParameter("pMARITAL_STATUS", Types.VARCHAR),
						new SqlParameter("pWEDDING_ANNIVERSARY", Types.DATE),
						new SqlParameter("pSPOUSE_NAME", Types.VARCHAR),
						new SqlParameter("pSPOUSE_PHONE_NO", Types.VARCHAR),
						new SqlParameter("pCENTRE", Types.VARCHAR),
						new SqlParameter("pAREA", Types.VARCHAR),
						new SqlParameter("pZONE", Types.VARCHAR), 
						new SqlParameter("pDISTRICT", Types.VARCHAR),
						new SqlParameter("pSTATE", Types.VARCHAR),
						new SqlParameter("pCOUNTRY", Types.VARCHAR),
						new SqlParameter("pADMIN_TEAM", Types.VARCHAR),
						
						new SqlParameter("pBAPTISED", Types.VARCHAR),
						new SqlParameter("pBAPTISM_TYPE", Types.VARCHAR),
						new SqlParameter("pDATE_BAPTISED", Types.DATE),
						new SqlParameter("pLAST_CHURCH", Types.VARCHAR),
						new SqlParameter("pLAST_CHURCH_ADDRESS", Types.VARCHAR), 
						new SqlParameter("pDATE_BORN_AGAIN", Types.DATE),
						new SqlParameter("pUSER_TITLE", Types.VARCHAR), 
						new SqlOutParameter("p_code", Types.VARCHAR),
						new SqlOutParameter("p_message", Types.VARCHAR))
				.compile();
		
		LocalDate date1 = LocalDate.now();
		java.util.Date date2 = java.sql.Date.valueOf(date1);
		String date = MARSHARLLERDATEFORMAT.format(date2);
		
		if(user.getWeddingAnniversary().length()<2) {
			LOGGER.info("request22");
			user.setWeddingAnniversary(date);
		}
		if(user.getDateJoinedChurchDept().length()<2) {
			LOGGER.info("request11");
			user.setDateJoinedChurchDept(date);
		}
		if(user.getDateBaptised().length()<2) {
			LOGGER.info("request33");
			user.setDateBaptised(date);
		}
		

		SqlParameterSource inParams = new MapSqlParameterSource()
				.addValue("pUSER_ROLES", user.getUserRoles())
				.addValue("pIS_OPERATION_USER", user.isOperationUser())
				.addValue("pUSER_FULL_NAME", user.getSurname()+" "+user.getMiddleName()+" "+user.getFirstName())
				.addValue("pUSER_EMAIL_ADD", user.getUserEmailAdd())
				.addValue("ppassword", user.getPassword())
				.addValue("AUTHORISED_USER_FLAG", user.isAuthorisedUserFlag())
				.addValue("p_USERNAME", user.getUserName())
				.addValue("pPHONE_NO", user.getPhoneNo())
				.addValue("pAFFILIATE_CODE", user.getCountry())
				.addValue("pSURNAME", user.getSurname())
				.addValue("pFIRST_NAME", user.getFirstName())
				.addValue("pMIDDLE_NAME", user.getMiddleName())
				.addValue("pGENDER", user.getGender())
				.addValue("pDATE_OF_BIRTH", user.getDateOfBirth())
				.addValue("pWEDDING_ANNIVERSARY", user.getWeddingAnniversary())
				.addValue("pDATE_JOINED_HOTR", user.getDateJoinedHOTR())
				.addValue("pDATE_JOINED_CITH", user.getDateJoinedCITH())
				.addValue("pCOMPLETED_FIND_THE_ROCK", user.getCompletedFindingTheRock())
				.addValue("pCOMPLETED_SPIRIT_AUTH", user.getCompletedSpiritualAuthority())
				.addValue("pHOME_ADDRESS", user.getHomeAddress())
				.addValue("pPLACE_OF_WORK", user.getPlaceOfWork())
				.addValue("pPLACE_OF_WORK_ADD", user.getPlaceOfWorkAddress())
				.addValue("pCHURCH_DEPARTMENT", user.getChurchDepartment())
				.addValue("pOCCUPATION", user.getOccupation())
				.addValue("pDATE_JOINED_CHURCH_DEPT", user.getDateJoinedChurchDept())
				.addValue("pMARITAL_STATUS", user.getMaritalStatus())
				.addValue("pSPOUSE_NAME", user.getSpouseName())
				.addValue("pSPOUSE_PHONE_NO", user.getSpousePhoneNumber())
				.addValue("pCENTRE", user.getCentre())
				.addValue("pAREA", user.getArea())
				.addValue("pZONE", user.getZone())
				.addValue("pDISTRICT", user.getDistrict())
				.addValue("pSTATE", user.getState())
				.addValue("pCOUNTRY", user.getCountry())
				.addValue("pADMIN_TEAM", user.getAdminTeam())

				.addValue("pBAPTISED", user.getBaptised())
				.addValue("pBAPTISM_TYPE", user.getTypeOfBaptism())
				.addValue("pDATE_BAPTISED", user.getDateBaptised())
				.addValue("pLAST_CHURCH", user.getLastChurch())
				.addValue("pLAST_CHURCH_ADDRESS", user.getLastChurchAddress())
				.addValue("pDATE_BORN_AGAIN", user.getDateBornAgain())
				.addValue("pUSER_TITLE", user.getUserTitle())
				;

		
		LOGGER.info("request " + user);
		
		Map<String, Object> returningResult = createUser.execute(inParams);
		String responseCode = (String) returningResult.get("p_code");
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) returningResult.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);

		return response;
	}
	
	@Override
	public Response authorise_user(AuthoriseUser authoriseUser) {
		SimpleJdbcCall authorise_user = new SimpleJdbcCall(jdbcTemplate);
		authorise_user.withProcedureName(baseUtilityPackage + ".authorise_user").withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_USER_ID", Types.NUMERIC),
						new SqlParameter("pAUTHORIZE_USER", Types.VARCHAR),
						new SqlParameter("p_USERNAME", Types.VARCHAR), new SqlParameter("p_SERVERIP", Types.VARCHAR),
						new SqlOutParameter("p_code", Types.VARCHAR), new SqlOutParameter("p_message", Types.VARCHAR))
				.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("p_USER_ID", authoriseUser.getUserID())
				.addValue("pAUTHORIZE_USER", authoriseUser.getAuthoriseUser())
				.addValue("p_USERNAME", authoriseUser.getUserName())
				.addValue("p_SERVERIP", authoriseUser.getServerIP());

		Map<String, Object> returningResult = authorise_user.execute(inParams);
		String responseCode = (String) returningResult.get("p_code");
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) returningResult.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);

		return response;
	}



	@Override
	public Response edit_user(EditUserDetails details) {
		SimpleJdbcCall edit_user = new SimpleJdbcCall(jdbcTemplate);
		edit_user.withProcedureName(baseUtilityPackage + ".edit_user").withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_userid", Types.NUMERIC),
						new SqlParameter("puser_full_name", Types.VARCHAR),
						new SqlParameter("puser_email_add", Types.VARCHAR),
						new SqlParameter("puser_role", Types.VARCHAR),
						new SqlParameter("paffiliate_code", Types.VARCHAR),
						new SqlParameter("p_username", Types.VARCHAR), new SqlParameter("p_serverip", Types.VARCHAR),
						new SqlOutParameter("p_code", Types.VARCHAR), new SqlOutParameter("p_message", Types.VARCHAR))
				.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("p_userid", details.getUserID())
				.addValue("puser_full_name", details.getUserFullName())
				.addValue("puser_email_add", details.getUserEmailAdd())
				.addValue("puser_role", details.getUserRoles())
				.addValue("paffiliate_code", details.getAffiliateCode())
				.addValue("p_username", details.getEditusername()).addValue("p_serverip", details.getServerIP());

		Map<String, Object> returningResult = edit_user.execute(inParams);

		String responseCode = (String) returningResult.get("p_code");
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) returningResult.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);

		return response;
	}

	
	@Override
	public Response userNameCheck(String userName) {
		SimpleJdbcCall insertAuditLogSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		insertAuditLogSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".USER_NAME_CHECK")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("P_USERNAME", Types.VARCHAR),
						new SqlOutParameter("p_code", Types.VARCHAR), new SqlOutParameter("p_message", Types.VARCHAR))
				.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("P_USERNAME", userName);

		Map<String, Object> returningResult = insertAuditLogSimpleJdbcCall.execute(inParams);
		String responseCode = (String) returningResult.get("p_code");
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) returningResult.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);

		return response;
	}
	
	
	/////////////////////////// END OF
	/////////////////////////// ADMIN//////////////////////////////////////////////////////////////////
	
	@Override
	public Response prayerRequest(PrayerRequest request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".prayer_request")
		.declareParameters(new SqlParameter("p_name", Types.VARCHAR),
						   new SqlParameter("p_prayer", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_name", request.getName())
									 .addValue("p_prayer", request.getPrayer());
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}
	
	@Override
	public Response welfareRequest(Welfare request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".welfare_request")
		.declareParameters(new SqlParameter("p_name", Types.VARCHAR),
						   new SqlParameter("p_welfare", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_name", request.getName())
									 .addValue("p_welfare", request.getWelfare());
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}
	
	@Override
	public Response specialAnnouncement(Announcement request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".special_announcement")
		.declareParameters(new SqlParameter("p_name", Types.VARCHAR),
						   new SqlParameter("p_announcement", Types.VARCHAR),
						   new SqlParameter("p_category", Types.VARCHAR),
						   new SqlParameter("p_username", Types.VARCHAR),
						   new SqlParameter("p_event_date", Types.DATE),
						   new SqlParameter("p_meeting_link", Types.VARCHAR),
						   new SqlParameter("p_send_to", Types.VARCHAR),
						   new SqlParameter("p_sendto_user", Types.VARCHAR),
						   new SqlParameter("p_meeting_type", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_name", request.getName())
									 .addValue("p_announcement", request.getAnnounce())
									 .addValue("p_category", request.getCategory())
									 .addValue("p_event_date", request.getEventDate())
									 .addValue("p_username", request.getCreatedBy())
									 .addValue("p_meeting_link", request.getMeetingLink())
									 .addValue("p_send_to", request.getSendTo())
									 .addValue("p_sendto_user", request.getSendToAUser())
									 .addValue("p_meeting_type", request.getMeetingType());
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}
	
	@Override
	public Response incidentRequest(Incident request) {
		SimpleJdbcCall incidentRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		incidentRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".incident_request")
		.declareParameters(new SqlParameter("p_name", Types.VARCHAR),
						   new SqlParameter("p_incident", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_name", request.getName())
									 .addValue("p_incident", request.getIncident());
		
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

	@Override
	public Response updateIncidentRequest(Incident request) {
		SimpleJdbcCall incidentRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		incidentRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".update_incident_status")
		.declareParameters(new SqlParameter("p_incident_id", Types.NUMERIC),
						   new SqlParameter("p_status", Types.VARCHAR),
						   new SqlParameter("p_status_comment", Types.VARCHAR),
						   new SqlParameter("p_name", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_incident_id", request.getIncidentId())
									 .addValue("p_status", request.getStatus())
									 .addValue("p_status_comment", request.getComment())
									 .addValue("p_name", request.getName());
		
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

	@Override
	public Response updatePrayerRequest(PrayerRequest request) {
		SimpleJdbcCall incidentRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		incidentRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".update_prayer_status")
		.declareParameters(new SqlParameter("p_prayer_id", Types.NUMERIC),
						   new SqlParameter("p_status", Types.VARCHAR),
						   new SqlParameter("p_status_comment", Types.VARCHAR),
						   new SqlParameter("p_name", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_prayer_id", request.getPrayerId())
									 .addValue("p_status", request.getStatus())
									 .addValue("p_status_comment", request.getComment())
									 .addValue("p_name", request.getName());
		
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

	@Override
	public Response updateWelfareRequest(Welfare request) {
		SimpleJdbcCall incidentRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		incidentRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".update_welfare_status")
		.declareParameters(new SqlParameter("p_welfare_id", Types.NUMERIC),
						   new SqlParameter("p_status", Types.VARCHAR),
						   new SqlParameter("p_status_comment", Types.VARCHAR),
						   new SqlParameter("p_name", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_welfare_id", request.getWelfareId())
									 .addValue("p_status", request.getStatus())
									 .addValue("p_status_comment", request.getComment())
									 .addValue("p_name", request.getName());
		
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

	@Override
	public Response expenseRequest(Expenses request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".expense_request")
		.declareParameters(new SqlParameter("p_name", Types.VARCHAR),
						   new SqlParameter("p_expense", Types.VARCHAR),
						   new SqlParameter("p_category", Types.VARCHAR),
						   new SqlParameter("p_amount", Types.NUMERIC),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_name", request.getName())
									 .addValue("p_expense", request.getExpense())
									 .addValue("p_category", request.getCategory())
									 .addValue("p_amount", request.getAmount());
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}
	
	@Override
	public Response communityProject(Expenses request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".community_project")
		.declareParameters(new SqlParameter("p_name", Types.VARCHAR),
						   new SqlParameter("p_project", Types.VARCHAR),
						   new SqlParameter("p_category", Types.VARCHAR),
						   new SqlParameter("p_amount", Types.NUMERIC),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_name", request.getName())
									 .addValue("p_project", request.getExpense())
									 .addValue("p_category", request.getCategory())
									 .addValue("p_amount", request.getAmount());
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}
	
	
	@Override
	public Response weeklyReport(WeeklyReport request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".weekly_report")
		.declareParameters(new SqlParameter("p_name", Types.VARCHAR),
						   new SqlParameter("p_centre", Types.VARCHAR),
						   new SqlParameter("p_centreAddress", Types.VARCHAR),
						   new SqlParameter("p_area", Types.VARCHAR),
						   new SqlParameter("p_leaderName", Types.VARCHAR),
						   new SqlParameter("p_asstLeader", Types.VARCHAR),
						   new SqlParameter("p_intern", Types.VARCHAR),
						   new SqlParameter("p_worshipLeader", Types.VARCHAR),
						   new SqlParameter("p_outlineTopic", Types.VARCHAR),
						   new SqlParameter("p_noOfMale", Types.NUMERIC),
						   new SqlParameter("p_noOfFemale", Types.NUMERIC),
						   new SqlParameter("p_childPresent", Types.NUMERIC),
						   new SqlParameter("p_visitors", Types.NUMERIC),
						   new SqlParameter("p_hostName", Types.VARCHAR),
						   new SqlParameter("p_hostPhoneNo", Types.VARCHAR),
						   new SqlParameter("p_hostEmail", Types.VARCHAR),
						   new SqlParameter("p_offering", Types.NUMERIC),
						   new SqlParameter("p_zone", Types.VARCHAR),
						   new SqlParameter("p_district", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_name", request.getUserName())
									 .addValue("p_centre", request.getCentre())
									 .addValue("p_centreAddress", request.getCentreAddress())
									 .addValue("p_area", request.getArea())
									 .addValue("p_leaderName", request.getLeaderName())
									 .addValue("p_asstLeader", request.getAsstLeader())
									 .addValue("p_intern", request.getIntern())
									 .addValue("p_worshipLeader", request.getWorshipLeader())
									 .addValue("p_outlineTopic", request.getOutlineTopic())
									 .addValue("p_noOfMale", request.getNoOfMale())
									 .addValue("p_noOfFemale", request.getNoOfFemale())
									 .addValue("p_childPresent", request.getChildPresent())
									 .addValue("p_visitors", request.getVisitors())
									 
									 .addValue("p_hostName", request.getHostName())
									 .addValue("p_hostPhoneNo", request.getHostPhoneNo())
									 .addValue("p_hostEmail", request.getHostEmail())
									 .addValue("p_offering", request.getOffering())
									 .addValue("p_zone", request.getZone())
									 .addValue("p_district", request.getDistrict());
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}

	@Override
	public Response weeklyOutline(WeeklyOutline request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".weekly_outline")
		.declareParameters(new SqlParameter("p_name", Types.VARCHAR),
						   new SqlParameter("p_topic", Types.VARCHAR),
						   new SqlParameter("p_outline", Types.VARCHAR),
						   new SqlParameter("p_uploadFlag", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_name", request.getName())
									 .addValue("p_topic", request.getTopic())
									 .addValue("p_outline", request.getOutline())
									 .addValue("p_uploadFlag", request.getUploadFlag());
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}
	
	@Override
	public Response whistleBlowing(Incident request) {
		SimpleJdbcCall incidentRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		incidentRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".whistle_blowing")
		.declareParameters(new SqlParameter("p_name", Types.VARCHAR),
						   new SqlParameter("p_whistle_blow", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_name", request.getName())
									 .addValue("p_whistle_blow", request.getIncident());
		
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
	
	@Override
	public Response directorReport(DirectorReport request) {
		SimpleJdbcCall incidentRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		incidentRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".director_report")
		.declareParameters(new SqlParameter("p_createdBy", Types.VARCHAR),
						   new SqlParameter("p_center", Types.VARCHAR),
						   new SqlParameter("p_noOfCenter", Types.NUMERIC),
						   new SqlParameter("p_noOfIndependentMember", Types.NUMERIC),
						   new SqlParameter("p_avAttendanceOfMember", Types.NUMERIC),
						   new SqlParameter("p_totalNoOfNewConvert", Types.NUMERIC),
						   new SqlParameter("p_noOfFirstTimerFollowUp", Types.NUMERIC),
						   new SqlParameter("p_noOfFirstTimerCoverted", Types.NUMERIC),
						   new SqlParameter("p_noOfWeeklyReportSubmitted", Types.NUMERIC),
						   new SqlParameter("p_noOfEvanProject", Types.NUMERIC),
						   new SqlParameter("p_noOfcommImpactProject", Types.NUMERIC),
						   new SqlParameter("p_noOfNamingCeremony", Types.NUMERIC),
						   new SqlParameter("p_noOfburial", Types.NUMERIC),
						   new SqlParameter("p_noOfArea", Types.NUMERIC),
						   new SqlParameter("p_noOfIntern", Types.NUMERIC),
						   new SqlParameter("p_noOfAsstLeader", Types.NUMERIC),
						   new SqlParameter("p_noOfLeader", Types.NUMERIC),
						   new SqlParameter("p_noOfAreaSupervisor", Types.NUMERIC),
						   new SqlParameter("p_totalOffering", Types.NUMERIC),
						   new SqlParameter("p_noOfNewLeaderIntroduced", Types.NUMERIC),
						   new SqlParameter("p_noOfNewLeaderTrained", Types.NUMERIC),
						   new SqlParameter("p_noOfNewHostIntroduced", Types.NUMERIC),
						   new SqlParameter("p_noOfNewHostTrained", Types.NUMERIC),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_createdBy", request.getName())
									 .addValue("p_center", request.getCenter())
									 .addValue("p_noOfCenter", request.getNoOfCenter())
									 .addValue("p_noOfIndependentMember", request.getNoOfIndependentMember())
									 .addValue("p_avAttendanceOfMember", request.getAvAttendanceOfMember())
									 .addValue("p_totalNoOfNewConvert", request.getTotalNoOfNewConvert())
									 .addValue("p_noOfFirstTimerFollowUp", request.getNoOfFirstTimerFollowUp())
									 .addValue("p_noOfFirstTimerCoverted", request.getNoOfFirstTimerCoverted())
									 .addValue("p_noOfWeeklyReportSubmitted", request.getNoOfWeeklyReportSubmitted())
									 .addValue("p_noOfEvanProject", request.getNoOfEvanProject())
									 .addValue("p_noOfcommImpactProject", request.getNoOfcommImpactProject())
									 .addValue("p_noOfNamingCeremony", request.getNoOfNamingCeremony())
									 .addValue("p_noOfburial", request.getNoOfburial())
									 .addValue("p_noOfArea", request.getNoOfArea())
									 .addValue("p_noOfIntern", request.getNoOfIntern())
									 .addValue("p_noOfAsstLeader", request.getNoOfAsstLeader())
									 .addValue("p_noOfLeader", request.getNoOfLeader())
									 .addValue("p_noOfAreaSupervisor", request.getNoOfAreaSupervisor())
									 .addValue("p_totalOffering", request.getTotalOffering())
									 .addValue("p_noOfNewLeaderIntroduced", request.getNoOfNewLeaderIntroduced())
									 .addValue("p_noOfNewLeaderTrained", request.getNoOfNewLeaderTrained())
									 .addValue("p_noOfNewHostIntroduced", request.getNoOfNewHostIntroduced())
									 .addValue("p_noOfNewHostTrained", request.getNoOfNewHostTrained());
		
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
	
	@Override
	public Response updateWhistleBlowingRequest(Incident request) {
		SimpleJdbcCall incidentRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		incidentRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".update_whistleblowing_status")
		.declareParameters(new SqlParameter("p_whistle_id", Types.NUMERIC),
						   new SqlParameter("p_status", Types.VARCHAR),
						   new SqlParameter("p_status_comment", Types.VARCHAR),
						   new SqlParameter("p_name", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_whistle_id", request.getIncidentId())
									 .addValue("p_status", request.getStatus())
									 .addValue("p_status_comment", request.getComment())
									 .addValue("p_name", request.getName());
		
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

	@Override
	public Response saveOTP(Otp otp) {
		SimpleJdbcCall incidentRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		incidentRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".save_otp")
		.declareParameters(new SqlParameter("p_phoneNo", Types.VARCHAR),
						   new SqlParameter("p_sessionID", Types.VARCHAR),
						   new SqlParameter("p_otp", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_phoneNo", otp.getPhoneNo())
									 .addValue("p_sessionID", otp.getSessionID())
									 .addValue("p_otp", otp.getOtp());
		
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

	@Override
	public Response calendarEvent(CalendarDetail request) {
		SimpleJdbcCall calendarEvent = new SimpleJdbcCall(jdbcTemplate);
		calendarEvent.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".calendar_event")
		.declareParameters(new SqlParameter("p_event", Types.VARCHAR),
						   new SqlParameter("p_eventDate", Types.DATE),
						   new SqlParameter("p_createdBy", Types.VARCHAR),
						   new SqlParameter("p_sendTo", Types.VARCHAR),
						   new SqlParameter("p_meetingLink", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_event", request.getEvent())
									 .addValue("p_eventDate", request.getEventDate())
									 .addValue("p_createdBy", request.getCreatedBy())
									 .addValue("p_sendTo", request.getSendTo())
									 .addValue("p_meetingLink", request.getMeetingLink());
		
		Map<String, Object> resultSet = calendarEvent.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}

	@Override
	public Response updateCalendarEvent(CalendarDetail request) {
		SimpleJdbcCall incidentRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		incidentRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".update_calendar_event")
		.declareParameters(new SqlParameter("p_calendar_id", Types.NUMERIC),
						   new SqlParameter("p_status", Types.VARCHAR),
						   new SqlParameter("p_event", Types.VARCHAR),
						   new SqlParameter("p_event_date", Types.DATE),
						   new SqlParameter("p_name", Types.VARCHAR),
						   new SqlParameter("p_sendTo", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_calendar_id", request.getCalendarID())
									 .addValue("p_status", request.getStatus())
									 .addValue("p_event", request.getEvent())
									 .addValue("p_event_date", request.getEventDate())
									 .addValue("p_name", request.getCreatedBy())
									 .addValue("p_sendTo", request.getSendTo());
		
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
	
	@Override
	public Response createOutlineDocument(DocManagerRequest docMangerRequest) {

        SimpleJdbcCall create_save_documentSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        create_save_documentSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".create_outline_document")
                .withoutProcedureColumnMetaDataAccess().declareParameters(
                new SqlParameter("p_doc_id", Types.VARCHAR),
                new SqlParameter("p_document_name", Types.VARCHAR),
                new SqlParameter("p_document_unique_id", Types.VARCHAR),
                new SqlParameter("p_inputstream", Types.BLOB),
                new SqlParameter("p_content_type", Types.VARCHAR),
                new SqlParameter("p_document_length", Types.NUMERIC),
                new SqlParameter("p_compressed", Types.NUMERIC),
                new SqlParameter("p_username", Types.VARCHAR),
                new SqlParameter("p_serverip", Types.VARCHAR),
                new SqlOutParameter("p_code", Types.VARCHAR),
                new SqlOutParameter("p_message", Types.VARCHAR))
                .compile();

        SqlLobValue xmlSerializedBlob = new SqlLobValue(docMangerRequest.getInputStream(), (int) docMangerRequest.getInputStreamLength());//, lobHandler);
        // CLOB creation

        LOGGER.info("xmlSerializedBlob"+xmlSerializedBlob);

        SqlParameterSource inparams = new MapSqlParameterSource()
                .addValue("p_doc_id", docMangerRequest.getDocumentID())
                .addValue("p_document_name", docMangerRequest.getDocName())
                .addValue("p_document_unique_id", docMangerRequest.getDocumentUniqueID())
                .addValue("P_inputstream", xmlSerializedBlob)
                .addValue("p_content_type", docMangerRequest.getFiletype())
                .addValue("p_document_length", docMangerRequest.getInputStreamLength())
                .addValue("p_compressed", docMangerRequest.isCompressed())
                .addValue("p_username", docMangerRequest.getUserName())
                .addValue("p_serverip", docMangerRequest.getServerIP());

        Map<String, Object> returningResult = create_save_documentSimpleJdbcCall.execute(inparams);
        String responseCode = (String) returningResult.get("p_code");
        String validResponseCode = responseCode != null ? responseCode : "99";
        String responseMsg = (String) returningResult.get("p_message");
        String validResponseMsg = responseMsg != null ? responseMsg : "";

        Response response = new Response();
        response.setResponseCode(validResponseCode);
        response.setResponseMessage(validResponseMsg);

        return response;

    }

	@Override
	public Response createSocialEvent(SocialEvent request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".social_event")
		.declareParameters(new SqlParameter("p_name", Types.VARCHAR),
						   new SqlParameter("p_topic", Types.VARCHAR),
						   new SqlParameter("p_event", Types.VARCHAR),
						   new SqlParameter("p_uploadFlag", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_name", request.getName())
									 .addValue("p_topic", request.getTopic())
									 .addValue("p_event", request.getEvent())
									 .addValue("p_uploadFlag", request.getUploadFlag());
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}

	@Override
	public Response createSocialEventDocument(DocManagerRequest docMangerRequest) {

        SimpleJdbcCall create_save_documentSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        create_save_documentSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".create_event_document")
                .withoutProcedureColumnMetaDataAccess().declareParameters(
                new SqlParameter("p_doc_id", Types.VARCHAR),
                new SqlParameter("p_document_name", Types.VARCHAR),
                new SqlParameter("p_document_unique_id", Types.VARCHAR),
                new SqlParameter("p_inputstream", Types.BLOB),
                new SqlParameter("p_content_type", Types.VARCHAR),
                new SqlParameter("p_document_length", Types.NUMERIC),
                new SqlParameter("p_compressed", Types.NUMERIC),
                new SqlParameter("p_username", Types.VARCHAR),
                new SqlParameter("p_serverip", Types.VARCHAR),
                new SqlParameter("p_topic", Types.VARCHAR),
                new SqlOutParameter("p_code", Types.VARCHAR),
                new SqlOutParameter("p_message", Types.VARCHAR))
                .compile();

        SqlLobValue xmlSerializedBlob = new SqlLobValue(docMangerRequest.getInputStream(), (int) docMangerRequest.getInputStreamLength());//, lobHandler);
        // CLOB creation

        LOGGER.info("xmlSerializedBlob"+xmlSerializedBlob);

        SqlParameterSource inparams = new MapSqlParameterSource()
                .addValue("p_doc_id", docMangerRequest.getDocumentID())
                .addValue("p_document_name", docMangerRequest.getDocName())
                .addValue("p_document_unique_id", docMangerRequest.getDocumentUniqueID())
                .addValue("P_inputstream", xmlSerializedBlob)
                .addValue("p_content_type", docMangerRequest.getFiletype())
                .addValue("p_document_length", docMangerRequest.getInputStreamLength())
                .addValue("p_compressed", docMangerRequest.isCompressed())
                .addValue("p_username", docMangerRequest.getUserName())
                .addValue("p_serverip", docMangerRequest.getServerIP())
                .addValue("p_topic", docMangerRequest.getTopic());

        Map<String, Object> returningResult = create_save_documentSimpleJdbcCall.execute(inparams);
        String responseCode = (String) returningResult.get("p_code");
        String validResponseCode = responseCode != null ? responseCode : "99";
        String responseMsg = (String) returningResult.get("p_message");
        String validResponseMsg = responseMsg != null ? responseMsg : "";

        Response response = new Response();
        response.setResponseCode(validResponseCode);
        response.setResponseMessage(validResponseMsg);

        return response;
	}

	@Override
	public Response monthlyReport(MonthlyReport request) {
		SimpleJdbcCall incidentRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		incidentRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".monthly_report")
		.declareParameters(new SqlParameter("p_createdBy", Types.VARCHAR),
						   new SqlParameter("p_dateVisited", Types.DATE),
						   new SqlParameter("p_visitedBy", Types.VARCHAR),
						   new SqlParameter("p_visitationReport", Types.VARCHAR),
						   new SqlParameter("p_leaderRemark", Types.VARCHAR),
						   new SqlParameter("p_week", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_createdBy", request.getName())
									 .addValue("p_dateVisited", request.getDateVisited())
									 .addValue("p_visitedBy", request.getVisitedBy())
									 .addValue("p_visitationReport", request.getVisitationReport())
									 .addValue("p_leaderRemark", request.getLeaderRemark())
									 .addValue("p_week", request.getWeek());
		
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

	@Override
	public Response updateSocialEvent(SocialEvent request) {
		SimpleJdbcCall incidentRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		incidentRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".update_social_event")
		.declareParameters(new SqlParameter("p_event_id", Types.NUMERIC),
						   new SqlParameter("p_status", Types.VARCHAR),
						   new SqlParameter("p_event", Types.VARCHAR),
						   new SqlParameter("p_topic", Types.VARCHAR),
						   new SqlParameter("p_name", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_event_id", request.getEventID())
									 .addValue("p_status", request.getStatus())
									 .addValue("p_event", request.getEvent())
									 .addValue("p_topic", request.getTopic())
									 .addValue("p_name", request.getName());
		
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

	@Override
	public Response changePassword(ChangePassword request) {
		SimpleJdbcCall incidentRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		incidentRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".change_password")
		.declareParameters(new SqlParameter("p_new_password", Types.VARCHAR),
						   new SqlParameter("p_user_id", Types.NUMERIC),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_new_password", request.getNewPassword())
									 .addValue("p_user_id", request.getUserID());
		
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

	@Override
	public Response authenticateUser(String username, String pwd) {
		SimpleJdbcCall incidentRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		incidentRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".authenticate_user")
		.declareParameters(new SqlParameter("pUsername", Types.VARCHAR),
						   new SqlParameter("pPassword", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("pUsername", username)
									 .addValue("pPassword", pwd);
		
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

	@Override
	public Response createVisitor(WeeklyReport request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".create_visitor")
		.declareParameters(new SqlParameter("p_visitorName", Types.VARCHAR),
						   new SqlParameter("p_visitorPhoneNo", Types.VARCHAR),
						   new SqlParameter("p_visitorEmailAdd", Types.VARCHAR),
						   new SqlParameter("p_visitorGender", Types.VARCHAR),
						   new SqlParameter("p_leaderName", Types.VARCHAR),
						   new SqlParameter("p_centre", Types.VARCHAR),
						   new SqlParameter("p_area", Types.VARCHAR),
						   new SqlParameter("p_zone", Types.VARCHAR),
						   new SqlParameter("p_district", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_visitorName", request.getVisitorName())
									 .addValue("p_visitorPhoneNo", request.getVisitorPhoneNo())
									 .addValue("p_visitorEmailAdd", request.getVisitorEmail())
									 .addValue("p_visitorGender", request.getVisitorGender())
									 .addValue("p_leaderName", request.getLeaderName())
									 .addValue("p_centre", request.getCentre())
									 .addValue("p_area", request.getArea())
									 .addValue("p_zone", request.getZone())
									 .addValue("p_district", request.getDistrict());
									 
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}

	//////////////////////////////////////NO DATABASE
	@Override
	public Response createTraining(Training request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".create_training")
		.declareParameters(new SqlParameter("p_trainingName", Types.VARCHAR),
						   new SqlParameter("p_trainingDescription", Types.VARCHAR),
						   new SqlParameter("p_userName", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_trainingName", request.getTrainingName())
									 .addValue("p_userName", request.getUserName())
									 .addValue("p_trainingDescription", request.getTrainingDescription());
									 
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}

	@Override
	public Response createCentre(Centre request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".create_centre")
		.declareParameters(new SqlParameter("p_centre", Types.VARCHAR),
						   new SqlParameter("p_parentCentre", Types.VARCHAR),
						   new SqlParameter("p_leader", Types.VARCHAR),
						   new SqlParameter("p_userName", Types.VARCHAR),
						   new SqlParameter("p_area", Types.VARCHAR),
						   new SqlParameter("p_zone", Types.VARCHAR),
						   new SqlParameter("p_district", Types.VARCHAR),
						   new SqlParameter("p_centre_address", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_centre", request.getCentre())
									 .addValue("p_parentCentre", request.getParentCentre())
									 .addValue("p_leader", request.getLeaderInCharge())
									 .addValue("p_userName", request.getUserName())
									 .addValue("p_area", request.getArea())
									 .addValue("p_zone", request.getZone())
									 .addValue("p_district", request.getDistrict())
									 .addValue("p_centre_address", request.getCentreAddress());
									 
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}
	
	@Override
	public Response createArea(Centre request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".create_area")
		.declareParameters(new SqlParameter("p_area", Types.VARCHAR),
						   new SqlParameter("p_parentArea", Types.VARCHAR),
						   new SqlParameter("p_leader", Types.VARCHAR),
						   new SqlParameter("p_userName", Types.VARCHAR),
						   new SqlParameter("p_zone", Types.VARCHAR),
						   new SqlParameter("p_district", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_area", request.getCentre())
									 .addValue("p_parentArea", request.getParentCentre())
									 .addValue("p_leader", request.getLeaderInCharge())
									 .addValue("p_userName", request.getUserName())
									 .addValue("p_zone", request.getZone())
									 .addValue("p_district", request.getDistrict());
									 
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}
	
	@Override
	public Response createZone(Centre request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".create_zone")
		.declareParameters(new SqlParameter("p_zone", Types.VARCHAR),
						   new SqlParameter("p_parentZone", Types.VARCHAR),
						   new SqlParameter("p_leader", Types.VARCHAR),
						   new SqlParameter("p_userName", Types.VARCHAR),
						   new SqlParameter("p_district", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_zone", request.getCentre())
									 .addValue("p_parentZone", request.getParentCentre())
									 .addValue("p_leader", request.getLeaderInCharge())
									 .addValue("p_userName", request.getUserName())
									 .addValue("p_district", request.getDistrict());
									 
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}
	
	@Override
	public Response createDistrict(Centre request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".create_district")
		.declareParameters(new SqlParameter("p_district", Types.VARCHAR),
						   new SqlParameter("p_parentDistrict", Types.VARCHAR),
						   new SqlParameter("p_leader", Types.VARCHAR),
						   new SqlParameter("p_userName", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_district", request.getCentre())
									 .addValue("p_parentDistrict", request.getParentCentre())
									 .addValue("p_leader", request.getLeaderInCharge())
									 .addValue("p_userName", request.getUserName());
									 
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}

	@Override
	public Response createTestimony(Testimony request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".create_testimony")
		.declareParameters(new SqlParameter("p_centre", Types.VARCHAR),
						   new SqlParameter("p_testifier", Types.VARCHAR),
						   new SqlParameter("p_topic", Types.VARCHAR),
						   new SqlParameter("p_testimony", Types.VARCHAR),
						   new SqlParameter("p_userName", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_centre", request.getCentre())
									 .addValue("p_testifier", request.getTestifierName())
									 .addValue("p_topic", request.getTestimonyTopic())
									 .addValue("p_testimony", request.getTestimony())
									 .addValue("p_userName", request.getUserName());
									 
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}

	@Override
	public Response createMessage(Message request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".create_message")
		.declareParameters(new SqlParameter("p_sender", Types.VARCHAR),
						   new SqlParameter("p_sendTo", Types.VARCHAR),
						   new SqlParameter("p_message_content", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_sender", request.getSender())
									 .addValue("p_message_content", request.getMessage())
									 .addValue("p_sendTo", request.getSendTo());
									 
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}
	
	@Override
	public Response createLegalDocumentDetail(SocialEvent request) {
		SimpleJdbcCall prayerRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		prayerRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".legal_document")
		.declareParameters(new SqlParameter("p_name", Types.VARCHAR),
						   new SqlParameter("p_title", Types.VARCHAR),
						   new SqlParameter("p_about", Types.VARCHAR),
						   new SqlParameter("p_uploadFlag", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_name", request.getName())
									 .addValue("p_title", request.getTopic())
									 .addValue("p_about", request.getEvent())
									 .addValue("p_uploadFlag", request.getUploadFlag());
		
		Map<String, Object> resultSet = prayerRequestJdbc.execute(inparam);
		String responseCode = (String) resultSet.get("p_code");
		
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) resultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		
		return response;
	}

	@Override
	public Response createLegalDocuments(DocManagerRequest docMangerRequest) {

        SimpleJdbcCall create_save_documentSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        create_save_documentSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".create_legal_document")
                .withoutProcedureColumnMetaDataAccess().declareParameters(
                new SqlParameter("p_doc_id", Types.VARCHAR),
                new SqlParameter("p_document_name", Types.VARCHAR),
                new SqlParameter("p_document_unique_id", Types.VARCHAR),
                new SqlParameter("p_inputstream", Types.BLOB),
                new SqlParameter("p_content_type", Types.VARCHAR),
                new SqlParameter("p_document_length", Types.NUMERIC),
                new SqlParameter("p_compressed", Types.NUMERIC),
                new SqlParameter("p_username", Types.VARCHAR),
                new SqlParameter("p_serverip", Types.VARCHAR),
                new SqlParameter("p_topic", Types.VARCHAR),
                new SqlOutParameter("p_code", Types.VARCHAR),
                new SqlOutParameter("p_message", Types.VARCHAR))
                .compile();

        SqlLobValue xmlSerializedBlob = new SqlLobValue(docMangerRequest.getInputStream(), (int) docMangerRequest.getInputStreamLength());//, lobHandler);
        // CLOB creation

        LOGGER.info("xmlSerializedBlob"+xmlSerializedBlob);

        SqlParameterSource inparams = new MapSqlParameterSource()
                .addValue("p_doc_id", docMangerRequest.getDocumentID())
                .addValue("p_document_name", docMangerRequest.getDocName())
                .addValue("p_document_unique_id", docMangerRequest.getDocumentUniqueID())
                .addValue("P_inputstream", xmlSerializedBlob)
                .addValue("p_content_type", docMangerRequest.getFiletype())
                .addValue("p_document_length", docMangerRequest.getInputStreamLength())
                .addValue("p_compressed", docMangerRequest.isCompressed())
                .addValue("p_username", docMangerRequest.getUserName())
                .addValue("p_serverip", docMangerRequest.getServerIP())
                .addValue("p_topic", docMangerRequest.getTopic());

        Map<String, Object> returningResult = create_save_documentSimpleJdbcCall.execute(inparams);
        String responseCode = (String) returningResult.get("p_code");
        String validResponseCode = responseCode != null ? responseCode : "99";
        String responseMsg = (String) returningResult.get("p_message");
        String validResponseMsg = responseMsg != null ? responseMsg : "";

        Response response = new Response();
        response.setResponseCode(validResponseCode);
        response.setResponseMessage(validResponseMsg);

        return response;
	}
	
	@Override
	public Response updateLegalDocument(SocialEvent request) {
		SimpleJdbcCall incidentRequestJdbc = new SimpleJdbcCall(jdbcTemplate);
		incidentRequestJdbc.withoutProcedureColumnMetaDataAccess().withProcedureName(baseUtilityPackage+".update_legal_document")
		.declareParameters(new SqlParameter("p_legal_id", Types.NUMERIC),
						   new SqlParameter("p_status", Types.VARCHAR),
						   new SqlParameter("p_about", Types.VARCHAR),
						   new SqlParameter("p_topic", Types.VARCHAR),
						   new SqlParameter("p_name", Types.VARCHAR),
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_legal_id", request.getEventID())
									 .addValue("p_status", request.getStatus())
									 .addValue("p_about", request.getEvent())
									 .addValue("p_topic", request.getTopic())
									 .addValue("p_name", request.getName());
		
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
	
	@Override
	public Response createProfilePicture(DocManagerRequest docMangerRequest) {

        SimpleJdbcCall create_save_documentSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        create_save_documentSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".create_profile_picture")
                .withoutProcedureColumnMetaDataAccess().declareParameters(
                new SqlParameter("p_doc_id", Types.VARCHAR),
                new SqlParameter("p_document_name", Types.VARCHAR),
                new SqlParameter("p_document_unique_id", Types.VARCHAR),
                new SqlParameter("p_inputstream", Types.BLOB),
                new SqlParameter("p_content_type", Types.VARCHAR),
                new SqlParameter("p_document_length", Types.NUMERIC),
                new SqlParameter("p_compressed", Types.NUMERIC),
                new SqlParameter("p_username", Types.VARCHAR),
                new SqlParameter("p_serverip", Types.VARCHAR),
                new SqlOutParameter("p_code", Types.VARCHAR),
                new SqlOutParameter("p_message", Types.VARCHAR))
                .compile();

        SqlLobValue xmlSerializedBlob = new SqlLobValue(docMangerRequest.getInputStream(), (int) docMangerRequest.getInputStreamLength());//, lobHandler);
        // CLOB creation

        LOGGER.info("xmlSerializedBlob"+xmlSerializedBlob);

        SqlParameterSource inparams = new MapSqlParameterSource()
                .addValue("p_doc_id", docMangerRequest.getDocumentID())
                .addValue("p_document_name", docMangerRequest.getDocName())
                .addValue("p_document_unique_id", docMangerRequest.getDocumentUniqueID())
                .addValue("P_inputstream", xmlSerializedBlob)
                .addValue("p_content_type", docMangerRequest.getFiletype())
                .addValue("p_document_length", docMangerRequest.getInputStreamLength())
                .addValue("p_compressed", docMangerRequest.isCompressed())
                .addValue("p_username", docMangerRequest.getUserName())
                .addValue("p_serverip", docMangerRequest.getServerIP());

        Map<String, Object> returningResult = create_save_documentSimpleJdbcCall.execute(inparams);
        String responseCode = (String) returningResult.get("p_code");
        String validResponseCode = responseCode != null ? responseCode : "99";
        String responseMsg = (String) returningResult.get("p_message");
        String validResponseMsg = responseMsg != null ? responseMsg : "";

        Response response = new Response();
        response.setResponseCode(validResponseCode);
        response.setResponseMessage(validResponseMsg);

        return response;

    }

	
}
