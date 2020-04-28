package com.portal.homeFellowship.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
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
import org.springframework.stereotype.Repository;

import com.portal.homeFellowship.model.*;

@Repository("adminDao")
public class AdminDaoImpl implements AdminDao {

	final static Logger LOGGER = LoggerFactory.getLogger(AdminDaoImpl.class);

	@Autowired
	private Environment environment;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${baseUtilPackage}")
	private String baseUtilityPackage;

	@Override
	public Response create_user(User user) {
		SimpleJdbcCall createUser = new SimpleJdbcCall(jdbcTemplate);
		createUser.withProcedureName(baseUtilityPackage + ".create_user").withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("pUSER_ID", Types.NUMERIC),
						new SqlParameter("pAFFILIATE_CODE", Types.VARCHAR),
						new SqlParameter("pUSER_ROLES", Types.VARCHAR),
						new SqlParameter("pIS_OPERATION_USER", Types.NUMERIC),
						new SqlParameter("pUSER_FULL_NAME", Types.VARCHAR),
						new SqlParameter("pUSER_EMAIL_ADD", Types.VARCHAR),
						new SqlParameter("pUSER_BRANCH", Types.VARCHAR),
						new SqlParameter("AUTHORISED_USER_FLAG", Types.NUMERIC),
						new SqlParameter("p_USERNAME", Types.VARCHAR), new SqlParameter("p_created_by", Types.VARCHAR),
						new SqlParameter("p_SERVERIP", Types.VARCHAR), new SqlOutParameter("p_code", Types.VARCHAR),
						new SqlOutParameter("p_message", Types.VARCHAR))
				.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("pUSER_ID", user.getUserID())
				.addValue("pAFFILIATE_CODE", user.getAffiliateCode()).addValue("pUSER_ROLES", user.getUserRoles())
				.addValue("pIS_OPERATION_USER", user.isOperationUser())
				.addValue("pUSER_FULL_NAME", user.getUserFullName()).addValue("pUSER_EMAIL_ADD", user.getUserEmailAdd())
				.addValue("pUSER_BRANCH", user.getPassword())
				.addValue("AUTHORISED_USER_FLAG", user.isAuthorisedUserFlag())
				.addValue("p_USERNAME", user.getUserName()).addValue("p_created_by", user.getCreatedBy())
				.addValue("p_SERVERIP", user.getServerIP());

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
	public Response create_user_to_role(UserToRole userToRole) {
		SimpleJdbcCall createUserRole = new SimpleJdbcCall(jdbcTemplate);
		createUserRole.withProcedureName(baseUtilityPackage + ".create_user_to_role")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("pROLE_ID", Types.VARCHAR),
						new SqlParameter("puser_name", Types.VARCHAR), new SqlParameter("p_STEP_ID", Types.NUMERIC),
						new SqlParameter("p_USERNAME", Types.VARCHAR), new SqlParameter("p_SERVERIP", Types.VARCHAR),
						new SqlOutParameter("p_code", Types.VARCHAR), new SqlOutParameter("p_message", Types.VARCHAR))
				.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("pROLE_ID", userToRole.getRoleID())
				.addValue("puser_name", userToRole.getUserName1()).addValue("p_STEP_ID", userToRole.getStepID())
				.addValue("p_USERNAME", userToRole.getUserName()).addValue("p_SERVERIP", userToRole.getServerIP());

		Map<String, Object> returningResult = createUserRole.execute(inParams);
		String responseCode = (String) returningResult.get("p_code");
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) returningResult.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";


		LOGGER.info("** returningResult ==> " + returningResult + " **");
		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);

		return response;
	}

	@Override
	public Response auth_user_to_role(AuthUsertoroleReq userToRole) {
		SimpleJdbcCall auth_user_to_role = new SimpleJdbcCall(jdbcTemplate);
		auth_user_to_role.withProcedureName(baseUtilityPackage + ".auth_user_to_role")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("puserrole_id", Types.NUMERIC),
						new SqlParameter("pflag", Types.VARCHAR), new SqlParameter("p_username", Types.VARCHAR),
						new SqlParameter("p_serverip", Types.VARCHAR), new SqlOutParameter("p_code", Types.VARCHAR),
						new SqlOutParameter("p_message", Types.VARCHAR))
				.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("puserrole_id", userToRole.getUserToroleId())
				.addValue("pflag", userToRole.getPflag()).addValue("p_username", userToRole.getUserName())
				.addValue("p_serverip", "");

		Map<String, Object> returningResult = auth_user_to_role.execute(inParams);
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
	public Response disable_user_to_role(EditUserDetails details) {
		SimpleJdbcCall disableRole = new SimpleJdbcCall(jdbcTemplate);
		disableRole.withProcedureName(baseUtilityPackage + ".disable_user_to_role")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_rolename", Types.VARCHAR),
						new SqlParameter("p_userid", Types.NUMERIC), new SqlParameter("p_user_name", Types.VARCHAR),
						new SqlParameter("p_step_id", Types.NUMERIC), new SqlParameter("p_username", Types.VARCHAR),
						new SqlParameter("p_serverip", Types.VARCHAR), new SqlOutParameter("p_code", Types.VARCHAR),
						new SqlOutParameter("p_message", Types.VARCHAR))
				.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("p_rolename", details.getUserRoles())
				.addValue("p_userid", details.getUserID()).addValue("p_user_name", details.getUserName())
				.addValue("p_step_id", details.getStepId()).addValue("p_username", details.getEditusername())
				.addValue("p_serverip", details.getServerIP());

		Map<String, Object> returningResult = disableRole.execute(inParams);

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
	public Response auth_disable_user_to_role(EditUserDetails details) {
		SimpleJdbcCall auth_disable_user_to_role = new SimpleJdbcCall(jdbcTemplate);
		auth_disable_user_to_role.withProcedureName(baseUtilityPackage + ".auth_dis_usertorole")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_removeroleid", Types.NUMERIC),
						new SqlParameter("pflag", Types.VARCHAR), new SqlParameter("pRoleName", Types.VARCHAR),
						new SqlParameter("p_username", Types.VARCHAR), new SqlParameter("p_serverip", Types.VARCHAR),
						new SqlOutParameter("p_code", Types.VARCHAR), new SqlOutParameter("p_message", Types.VARCHAR))
				.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("p_removeroleid", details.getUserID())
				.addValue("pflag", details.getFlag()).addValue("pRoleName", details.getUserRoles())
				.addValue("p_username", details.getEditusername()).addValue("p_serverip", details.getServerIP());

		Map<String, Object> returningResult = auth_disable_user_to_role.execute(inParams);

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
	public Response auth_edit_user(EditUserDetails details) {
		SimpleJdbcCall auth_edit_user = new SimpleJdbcCall(jdbcTemplate);
		auth_edit_user.withProcedureName(baseUtilityPackage + ".authorise_edit_user")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_userid", Types.NUMERIC),
						new SqlParameter("p_flag", Types.VARCHAR), new SqlParameter("p_username", Types.VARCHAR),
						new SqlParameter("p_serverip", Types.VARCHAR), new SqlOutParameter("p_code", Types.VARCHAR),
						new SqlOutParameter("p_message", Types.VARCHAR))
				.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("p_userid", details.getUserID())
				.addValue("p_flag", details.getFlag()).addValue("p_username", details.getEditusername())
				.addValue("p_serverip", details.getServerIP());

		Map<String, Object> returningResult = auth_edit_user.execute(inParams);

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
						   new SqlOutParameter("p_code", Types.VARCHAR),
						   new SqlOutParameter("p_message", Types.VARCHAR)).compile();
		
		SqlParameterSource inparam = new MapSqlParameterSource().addValue("p_name", request.getName())
									 .addValue("p_announcement", request.getAnnouncement())
									 .addValue("p_category", request.getCategory())
									 .addValue("p_username", request.getCreatedBy());
		
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

	
}
