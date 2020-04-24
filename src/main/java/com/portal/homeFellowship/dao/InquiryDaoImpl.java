package com.portal.homeFellowship.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
import org.springframework.stereotype.Repository;

import com.portal.homeFellowship.model.*;
import com.portal.homeFellowship.utility.BasicUtil;

@Repository("inquiryDao")
public class InquiryDaoImpl implements InquiryDao {

	static final Logger LOGGER = LoggerFactory.getLogger(InquiryDaoImpl.class);

	@Autowired
	private Environment environment;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("${baseUtilPackage}")
	private String baseUtilityPackage;


	@SuppressWarnings("unchecked")
	@Override
	public UserToRoleResponse get_user_role_opps(RoleForUser roleforuser) {
		SimpleJdbcCall get_user_role_oppsSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		get_user_role_oppsSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_user_role_opps")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("pUSER_ID", Types.NUMERIC),
						new SqlParameter("p_USERNAME", Types.VARCHAR), new SqlParameter("p_SERVERIP", Types.VARCHAR),
						new SqlOutParameter("p_code", Types.VARCHAR), new SqlOutParameter("p_message", Types.VARCHAR),
						new SqlOutParameter("r_role_for_user", Types.REF_CURSOR),
						new SqlOutParameter("r_operation_flag", Types.REF_CURSOR))
				.returningResultSet("r_role_for_user", new RowMapper<UserRole>() {
					@Override
					public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserRole GetUserRole = new UserRole();
						GetUserRole.setRoleID(rs.getLong(1));
						GetUserRole.setRoleName(rs.getString(2));
						GetUserRole.setOperationRole(rs.getBoolean(3));
						return GetUserRole;
					}
				}).returningResultSet("r_operation_flag", new RowMapper<UserRoleOpp>() {
					@Override
					public UserRoleOpp mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserRoleOpp GetUserRole1 = new UserRoleOpp();
						GetUserRole1.setRoleID(rs.getLong(1));
						GetUserRole1.setRoleName(rs.getString(2));
						GetUserRole1.setOperationRole(rs.getBoolean(3));
						GetUserRole1.setStepID(rs.getLong(4));
						return GetUserRole1;
					}
				});
		get_user_role_oppsSimpleJdbcCall.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("pUSER_ID", roleforuser.getUserID())
				.addValue("p_USERNAME", roleforuser.getUserName()).addValue("p_SERVERIP", roleforuser.getServerIP());

		Map<String, Object> returningResultSet = get_user_role_oppsSimpleJdbcCall.execute(inParams);

		List<UserRole> rs1 = (List<UserRole>) returningResultSet.get("r_role_for_user");
		List<UserRoleOpp> rs2 = (List<UserRoleOpp>) returningResultSet.get("r_operation_flag");

		UserToRoleResponse response = new UserToRoleResponse();
		response.setUserRole(rs1);
		response.setUserRoleOpp(rs2);
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UserResp get_user_profile(UserProfile userProfile) {
		SimpleJdbcCall get_user_profileSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		get_user_profileSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_user_profile")
				.withoutProcedureColumnMetaDataAccess().declareParameters(new SqlParameter("pUSER_ID", Types.NUMERIC),
						// new SqlParameter("pAFFILIATE_ID", Types.NUMERIC),
						new SqlParameter("p_USERNAME", Types.VARCHAR), new SqlParameter("p_SERVERIP", Types.VARCHAR),
						new SqlOutParameter("p_code", Types.VARCHAR), new SqlOutParameter("p_message", Types.VARCHAR),
						new SqlOutParameter("r_user_profile", Types.REF_CURSOR))
				.returningResultSet("r_user_profile", new RowMapper<User1>() {
					@Override
					public User1 mapRow(ResultSet rs, int rowNum) throws SQLException {
						User1 user = new User1();
						user.setUserID(rs.getLong(1));
						user.setAffiliateCode(rs.getString(2));
						user.setUserTransactionLimit(rs.getBigDecimal(3));
						user.setActive(rs.getBoolean(4));
						user.setUserRoles(rs.getString(5));
						user.setOperationUser(rs.getBoolean(6));
						user.setUserFullName(rs.getString(7));
						user.setUserEmailAdd(rs.getString(8));
						user.setUserBranch(rs.getString(9));
						user.setPasswordExpiryPolicy(rs.getString(10));
						user.setAuthorisedUserFlag(rs.getBoolean(11));
						user.setUserName(rs.getString(12));
						user.setEditedFlag(rs.getBoolean(23));
						user.setEditedRoleFlag(rs.getBoolean(24));

						return user;

					}
				});
		get_user_profileSimpleJdbcCall.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("pUSER_ID", userProfile.getUserID())
				// .addValue("pAFFILIATE_ID", userProfile.getUserID())
				.addValue("p_USERNAME", userProfile.getUserName()).addValue("p_SERVERIP", userProfile.getServerIP());

		Map<String, Object> returningResultSet = get_user_profileSimpleJdbcCall.execute(inParams);

		List<User1> dbResponse = (List<User1>) returningResultSet.get("r_user_profile");

		User1 rs1 = (dbResponse == null || dbResponse.isEmpty()) ? new User1() : dbResponse.get(0);
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
	public List<Affiliate> getAuthorisedAffiliates() {
		SimpleJdbcCall get_affiliate_clientSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		get_affiliate_clientSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_affiliate_client")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlOutParameter("r_affiliate_client", Types.REF_CURSOR))
				.returningResultSet("r_affiliate_client", new RowMapper<Affiliate>() {
					@Override
					public Affiliate mapRow(ResultSet rs, int rowNum) throws SQLException {
						Affiliate affiliateClient = new Affiliate();
						affiliateClient.setAffiliateCode(rs.getString(1));
						return affiliateClient;
					}
				});
		get_affiliate_clientSimpleJdbcCall.compile();

		Map<String, Object> returningResultSet = get_affiliate_clientSimpleJdbcCall.execute();

		List<Affiliate> response = (List<Affiliate>) returningResultSet.get("r_affiliate_client");

		return response == null | response.isEmpty() ? new ArrayList<>() : response;
	}

	// TODO FIX REMOVE P_CODE AND P_MESSAGE
	@SuppressWarnings("unchecked")
	@Override
	public UserStepPositionResp get_user_step_position(UserStepPosition userStepPosition) {
		SimpleJdbcCall get_user_step_positionSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		get_user_step_positionSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_user_step_position")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_USERNAME", Types.VARCHAR),
						new SqlParameter("p_SERVERIP", Types.VARCHAR),
						// new SqlOutParameter("p_code", Types.VARCHAR), new
						// SqlOutParameter("p_message", Types.VARCHAR),
						new SqlOutParameter("r_user_step_position", Types.REF_CURSOR))
				.returningResultSet("r_user_step_position", new RowMapper<UserStepPositionResp>() {
					@Override
					public UserStepPositionResp mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserStepPositionResp userStepPositionResp = new UserStepPositionResp();
						userStepPositionResp.setStepPosition(rs.getLong(1));
						userStepPositionResp.setStepName(rs.getString(2));
						return userStepPositionResp;
					}
				});
		get_user_step_positionSimpleJdbcCall.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("p_USERNAME", userStepPosition.getUserName())
				.addValue("p_SERVERIP", userStepPosition.getServerIP());

		Map<String, Object> returningResultSet = get_user_step_positionSimpleJdbcCall.execute(inParams);
		LOGGER.info("returningResultSet user position" + returningResultSet);
		List<UserStepPositionResp> response = (List<UserStepPositionResp>) returningResultSet
				.get("r_user_step_position");

		return response == null || response.isEmpty() ? new UserStepPositionResp() : response.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Branch> get_branch(String affiliateCode) {
		SimpleJdbcCall get_branchSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		get_branchSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_branch")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("paffiliate_code", Types.VARCHAR),
						new SqlOutParameter("r_branch", Types.REF_CURSOR))
				.returningResultSet("r_branch", new RowMapper<Branch>() {
					@Override

					public Branch mapRow(ResultSet rs, int rowNum) throws SQLException {
						Branch branch = new Branch();
						branch.setBranchCode(rs.getString(1));
						branch.setBranchName(rs.getString(2));
						branch.setBranchEMail(rs.getString(3));
						branch.setAffiliateCode(rs.getString(4));
						branch.setCreateUser(rs.getString(5));
						branch.setCreateDate(rs.getDate(6));
						branch.setVerifyUser(rs.getString(7));
						branch.setVerifyUser(rs.getString(8));
						branch.setStatus(rs.getString(8));
						return branch;
					}
				});
		get_branchSimpleJdbcCall.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("paffiliate_code", affiliateCode);

		Map<String, Object> returningResultSet = get_branchSimpleJdbcCall.execute(inParams);

		List<Branch> response = (List<Branch>) returningResultSet.get("r_branch");

		return response == null || response.isEmpty() ? new ArrayList<Branch>() : response;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> get_auth_users(String affiliateCode) {
		SimpleJdbcCall get_auth_users = new SimpleJdbcCall(jdbcTemplate);
		get_auth_users.withProcedureName(baseUtilityPackage + ".get_pend_auth_users")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_AFFILIATE_CODE", Types.VARCHAR),
						new SqlOutParameter("r_users", Types.REF_CURSOR))
				.returningResultSet("r_users", new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User user = new User();
						user.setUserID(rs.getLong(1));
						user.setAffiliateCode(rs.getString(2));
						user.setUserTransactionLimit(rs.getBigDecimal(3));
						user.setUserRoles(rs.getString(4));
						user.setOperationUser(rs.getBoolean(5));
						user.setUserFullName(rs.getString(6));
						user.setUserEmailAdd(rs.getString(7));
						user.setUserBranch(rs.getString(8));
						user.setUserName(rs.getString(9));
						user.setCreatedBy(rs.getString(10));
						user.setDateCreated(rs.getDate(11));
						return user;
					}
				});
		get_auth_users.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("p_AFFILIATE_CODE", affiliateCode);

		Map<String, Object> returningResultSet = get_auth_users.execute(inParams);
		List<User> response = (List<User>) returningResultSet.get("r_users");

		return response == null || response.isEmpty() ? new ArrayList<User>() : response;

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
	public List<UserToRoleResp> get_users_roles(UserProfile user) {
		SimpleJdbcCall get_affiliate_currencySimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		get_affiliate_currencySimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_users_roles")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("puserid", Types.NUMERIC),
						new SqlOutParameter("r_users", Types.REF_CURSOR))
				.returningResultSet("r_users", new RowMapper<UserToRoleResp>() {

					@Override
					public UserToRoleResp mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserToRoleResp user = new UserToRoleResp();
						user.setUserID(rs.getLong(1));
						user.setUserName(rs.getString(2));
						user.setStepID(rs.getLong(5));
						user.setRoleID(rs.getLong(3));
						user.setRoleName(rs.getString(4));
						user.setStepName(rs.getString(6));

						return user;
					}
				});
		get_affiliate_currencySimpleJdbcCall.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("puserid", user.getUserID());

		Map<String, Object> returningResultSet = get_affiliate_currencySimpleJdbcCall.execute(inParams);

		List<UserToRoleResp> response = (List<UserToRoleResp>) returningResultSet.get("r_users");

		return response == null || response.isEmpty() ? new ArrayList<UserToRoleResp>() : response;

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
						String username = rs.getString(12);
						user.setUserID(rs.getLong(1));
						user.setAffiliateCode(rs.getString(2));
						user.setUserTransactionLimit(rs.getBigDecimal(3));
						user.setActive(rs.getBoolean(4));
						user.setUserRoles(rs.getString(5));
						user.setOperationUser(rs.getBoolean(6));
						user.setUserFullName(rs.getString(7));
						user.setUserEmailAdd(rs.getString(8));
						user.setUserBranch(rs.getString(9));
						user.setPasswordExpiryPolicy(rs.getString(10));
						user.setAuthorisedUserFlag(rs.getBoolean(11));
						user.setUserName(username == null ? "" : username.toUpperCase());
						user.setTokenGroup(rs.getString(13));
						user.setDeleteFlag(rs.getString(27));
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
	public Response getbranchname(String branchcode) {
		SimpleJdbcCall getbranchname = new SimpleJdbcCall(jdbcTemplate);
		getbranchname.withProcedureName(baseUtilityPackage + ".get_branch_name").withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_branchcode", Types.VARCHAR),
						new SqlOutParameter("p_code", Types.VARCHAR), new SqlOutParameter("p_message", Types.VARCHAR));

		getbranchname.compile();

		SqlParameterSource inparams = new MapSqlParameterSource().addValue("p_branchcode", branchcode);

		Map<String, Object> returningResultSet = getbranchname.execute(inparams);

		String responseCode = (String) returningResultSet.get("p_code");
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) returningResultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		Response response = new Response();
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		return response;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> get_auth_edit_users(Flag flag) {
		SimpleJdbcCall get_affiliate_currencySimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		get_affiliate_currencySimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_pend_auth_users1")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_flag", Types.VARCHAR),
						new SqlParameter("p_affiliate", Types.VARCHAR),
						new SqlOutParameter("r_users", Types.REF_CURSOR))
				.returningResultSet("r_users", new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
						User user = new User();
						user.setUserID(rs.getLong(1));
						user.setAffiliateCode(rs.getString(2));
						user.setUserTransactionLimit(rs.getBigDecimal(3));
						user.setActive(rs.getBoolean(4));
						user.setUserRoles(rs.getString(5));
						user.setOperationUser(rs.getBoolean(6));
						user.setUserFullName(rs.getString(7));
						user.setUserEmailAdd(rs.getString(8));
						user.setUserBranch(rs.getString(9));
						user.setPasswordExpiryPolicy(rs.getString(10));
						user.setAuthorisedUserFlag(rs.getBoolean(11));
						user.setUserName(rs.getString(12));
						return user;
					}
				});
		get_affiliate_currencySimpleJdbcCall.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("p_flag", flag.getPflag())
				.addValue("p_affiliate", flag.getAffiliate());

		Map<String, Object> returningResultSet = get_affiliate_currencySimpleJdbcCall.execute(inParams);
		List<User> response = (List<User>) returningResultSet.get("r_users");

		return response == null || response.isEmpty() ? new ArrayList<User>() : response;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EditUser> get_edit_users1(Flag flag) {
		SimpleJdbcCall get_affiliate_currencySimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		get_affiliate_currencySimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_pend_auth_users1")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_flag", Types.VARCHAR),
						new SqlParameter("p_affiliate", Types.VARCHAR),
						new SqlOutParameter("r_users", Types.REF_CURSOR))
				.returningResultSet("r_users", new RowMapper<EditUser>() {

					@Override
					public EditUser mapRow(ResultSet rs, int rowNum) throws SQLException {
						EditUser user = new EditUser();
						user.setUserID(rs.getLong(1));
						user.setUserName(rs.getString(2));
						user.setOldUserEmailAdd((rs.getString(3)));
						user.setNewUserEmailAdd((rs.getString(4)));
						user.setOldUserTransactionLimit(rs.getLong(5));
						user.setNewUserTransactionLimit(rs.getLong(6));
						user.setOldUserBranch(rs.getString(7));
						user.setNewUserBranch(rs.getString(8));
						user.setOldUserFullName(rs.getString(9));
						user.setNewUserFullName(rs.getString(10));
						user.setOldAffiliateCode(rs.getString(12));
						user.setNewAffiliateCode(rs.getString(13));
						user.setCreatedBy(rs.getString(14));
						return user;
					}
				});
		get_affiliate_currencySimpleJdbcCall.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("p_flag", flag.getPflag())
				.addValue("p_affiliate", flag.getAffiliate());

		Map<String, Object> returningResultSet = get_affiliate_currencySimpleJdbcCall.execute(inParams);
		List<EditUser> response = (List<EditUser>) returningResultSet.get("r_users");

		return response == null || response.isEmpty() ? new ArrayList<EditUser>() : response;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserToRoleApp> get_PEND_USERTOROLE(String userName) {
		SimpleJdbcCall get_affiliate_currencySimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		get_affiliate_currencySimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_PEND_USERTOROLE")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("PUSERNAME", Types.VARCHAR),
						new SqlOutParameter("r_users", Types.REF_CURSOR))
				.returningResultSet("r_users", new RowMapper<UserToRoleApp>() {

					@Override
					public UserToRoleApp mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserToRoleApp user = new UserToRoleApp();
						user.setRoleName(rs.getString(3));
						user.setStepName(rs.getString(4));
						user.setUsertoroleID(rs.getLong(1));
						user.setUserName(rs.getString(2));
						user.setCreatedBy(rs.getString(5));
						return user;
					}
				});
		get_affiliate_currencySimpleJdbcCall.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("PUSERNAME", userName);

		Map<String, Object> returningResultSet = get_affiliate_currencySimpleJdbcCall.execute(inParams);
		List<UserToRoleApp> response = (List<UserToRoleApp>) returningResultSet.get("r_users");

		return response == null || response.isEmpty() ? new ArrayList<UserToRoleApp>() : response;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserRemoveRole> getPendingRemoveUser(String affiliateCode) {
		SimpleJdbcCall getPendingRemoveUser = new SimpleJdbcCall(jdbcTemplate);
		getPendingRemoveUser.withProcedureName(baseUtilityPackage + ".get_pending_disableuserrole")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_affiliate", Types.VARCHAR),
						new SqlOutParameter("r_pedings", Types.REF_CURSOR))
				.returningResultSet("r_pedings", new RowMapper<UserRemoveRole>() {
					@Override

					public UserRemoveRole mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserRemoveRole userToRole = new UserRemoveRole();
						userToRole.setUserName(rs.getString(1));
						userToRole.setUserID(rs.getLong(2));
						userToRole.setRoleID(rs.getString(3));
						userToRole.setStepID(rs.getString(4));
						userToRole.setProductID(rs.getLong(5));
						userToRole.setId(rs.getLong("ID"));
						userToRole.setUserBranch(rs.getString("USER_BRANCH"));
						userToRole.setAffiliateCode(rs.getString("AFFILIATE_CODE"));
						userToRole.setUserFullName(rs.getString("USER_FULL_NAME"));
						userToRole.setUserEmailAdd(rs.getString("USER_EMAIL_ADD"));
						userToRole.setUserRoles(rs.getString("ROLENAME"));
						userToRole.setServerIP(rs.getString(6));
						userToRole.setDateCreated(rs.getString("DISABLED_DATE"));
						return userToRole;
					}
				});
		getPendingRemoveUser.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("p_affiliate", affiliateCode);

		Map<String, Object> returningResultSet = getPendingRemoveUser.execute(inParams);
		List<UserRemoveRole> response = (List<UserRemoveRole>) returningResultSet.get("r_pedings");

		return response == null || response.isEmpty() ? new ArrayList<UserRemoveRole>() : response;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<UserStepPositionResp> getSteps() {
		SimpleJdbcCall get_steps_for_productsSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		get_steps_for_productsSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_steps")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlOutParameter("r_steps", Types.REF_CURSOR),
						new SqlOutParameter("p_code", Types.VARCHAR), new SqlOutParameter("p_message", Types.VARCHAR))
				.returningResultSet("r_steps", new RowMapper<UserStepPositionResp>() {
					@Override
					public UserStepPositionResp mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserStepPositionResp userStepPositionResp = new UserStepPositionResp();
						// userStepPositionResp.setStepPosition(rs.getLong(1));
						userStepPositionResp.setStepName(rs.getString(1));
						userStepPositionResp.setStepid(rs.getLong(2));
						return userStepPositionResp;
					}
				});
		get_steps_for_productsSimpleJdbcCall.compile();

		Map<String, Object> returningResultSet = get_steps_for_productsSimpleJdbcCall.execute();

		List<UserStepPositionResp> response = (List<UserStepPositionResp>) returningResultSet.get("r_steps");

		return response == null || response.isEmpty() ? new ArrayList<>() : response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Affiliate> getAffiliateDetails(String username) {
		SimpleJdbcCall getAffiliateDetails = new SimpleJdbcCall(jdbcTemplate);
		getAffiliateDetails.withProcedureName(baseUtilityPackage + ".get_affiliate_detail")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_username", Types.VARCHAR),
						new SqlOutParameter("r_details", Types.REF_CURSOR))
				.returningResultSet("r_details", new RowMapper<Affiliate>() {

					@Override
					public Affiliate mapRow(ResultSet rs, int rowNum) throws SQLException {

						Affiliate affiliate = new Affiliate();
						affiliate.setAffiliateID(rs.getLong(1));
						affiliate.setAffiliateCode(rs.getString(2));
						affiliate.setAffiliateCurrency(rs.getString(3));
						return affiliate;
					}
				});

		getAffiliateDetails.compile();

		SqlParameterSource inparams = new MapSqlParameterSource().addValue("p_username", username);

		Map<String, Object> returningResultSet = getAffiliateDetails.execute(inparams);

		List<Affiliate> response = (List<Affiliate>) returningResultSet.get("r_details");

		return response == null || response.isEmpty() ? new ArrayList<>() : response;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User1> getUserDetails(String username) {
		SimpleJdbcCall getUserDetails = new SimpleJdbcCall(jdbcTemplate);
		getUserDetails.withProcedureName(baseUtilityPackage + ".get_user_detail").withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_username", Types.VARCHAR),
						new SqlOutParameter("r_details", Types.REF_CURSOR))
				.returningResultSet("r_details", new RowMapper<User1>() {

					@Override
					public User1 mapRow(ResultSet rs, int rowNum) throws SQLException {

						User1 affiliate = new User1();
						affiliate.setAffiliateCode(rs.getString(2));
						affiliate.setUserRoles(rs.getString(5));
						return affiliate;
					}
				});

		getUserDetails.compile();

		SqlParameterSource inparams = new MapSqlParameterSource().addValue("p_username", username);

		Map<String, Object> returningResultSet = getUserDetails.execute(inparams);

		List<User1> response = (List<User1>) returningResultSet.get("r_details");

		return response == null || response.isEmpty() ? new ArrayList<User1>() : response;

	}
	


	@SuppressWarnings("unchecked")
	@Override
	public StepOperationResp getUserEnabledActivitiesnew(String username) {
		SimpleJdbcCall get_step_operationsSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		get_step_operationsSimpleJdbcCall.withProcedureName(baseUtilityPackage + ".get_step_operations1")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_USERNAME", Types.VARCHAR),
						new SqlParameter("p_SERVERIP", Types.VARCHAR), new SqlOutParameter("p_code", Types.VARCHAR),
						new SqlOutParameter("p_message", Types.VARCHAR),
						new SqlOutParameter("r_stepoperations", Types.REF_CURSOR))
				.returningResultSet("r_stepoperations", new RowMapper<StepOperRequest>() {
					@Override
					public StepOperRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
						StepOperRequest stepOppsResp = new StepOperRequest();
						stepOppsResp.setOperationID(rs.getLong(1));
						stepOppsResp.setOperationName(rs.getString(2));
						stepOppsResp.setStepID(rs.getLong(3));
						return stepOppsResp;
					}
				});
		get_step_operationsSimpleJdbcCall.compile();

		SqlParameterSource inParams = new MapSqlParameterSource().addValue("p_USERNAME", username)
				.addValue("p_SERVERIP", BasicUtil.getClientIp());

		Map<String, Object> returningResultSet = get_step_operationsSimpleJdbcCall.execute(inParams);

		List<StepOperRequest> rs1 = (List<StepOperRequest>) returningResultSet.get("r_stepoperations");
		String responseCode = (String) returningResultSet.get("p_code");
		String validResponseCode = responseCode != null ? responseCode : "99";
		String responseMsg = (String) returningResultSet.get("p_message");
		String validResponseMsg = responseMsg != null ? responseMsg : "";

		StepOperationResp response = new StepOperationResp();
		response.setOperations(rs1);
		response.setResponseCode(validResponseCode);
		response.setResponseMessage(validResponseMsg);
		return response;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getActivities(String string, String flag) {
		SimpleJdbcCall getActivities = new SimpleJdbcCall(jdbcTemplate);
		getActivities.withProcedureName(baseUtilityPackage + ".get_cust_acctivitiesbyid")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_id", Types.NUMERIC), new SqlParameter("p_flag", Types.VARCHAR),
						new SqlOutParameter("p_code", Types.VARCHAR), new SqlOutParameter("p_message", Types.VARCHAR),
						new SqlOutParameter("r_posting", Types.REF_CURSOR))
				.returningResultSet("r_posting", new RowMapper<String>() {

					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString(1);
					}
				});

		getActivities.compile();

		SqlParameterSource inparams = new MapSqlParameterSource().addValue("p_id", string).addValue("p_flag", flag);

		Map<String, Object> returningResultSet = getActivities.execute(inparams);

		LOGGER.info("+++++show me result set===>>" + returningResultSet);
		List<String> response = (List<String>) returningResultSet.get("r_posting");
		return response == null || response.isEmpty() ? new ArrayList<>() : response;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Custactivities> getcustactivities(String flag) {
		SimpleJdbcCall get_cmpltd_trans_by_prodSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		LOGGER.info("+++++show me result set===>>" +flag);
		get_cmpltd_trans_by_prodSimpleJdbcCall.withProcedureName(baseUtilityPackage+".get_cust_acctivities")
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(new SqlParameter("p_flag", Types.VARCHAR),						
						new SqlOutParameter("p_code", Types.VARCHAR), new SqlOutParameter("p_message", Types.VARCHAR),
						new SqlOutParameter("r_posting", Types.REF_CURSOR))
				.returningResultSet("r_posting", new RowMapper<Custactivities>() {
					
					@Override
					public Custactivities mapRow(ResultSet rs, int rowNum) throws SQLException {
						Custactivities act = new Custactivities();						
						act.setId(rs.getLong(1));
						act.setActName(rs.getString(2));
						return act;
					}
				});

		get_cmpltd_trans_by_prodSimpleJdbcCall.compile();
		
		SqlParameterSource inparams = new MapSqlParameterSource().addValue("p_flag", flag);

		Map<String, Object> returningResultSet = get_cmpltd_trans_by_prodSimpleJdbcCall.execute(inparams);

		LOGGER.info("+++++show me result set===>>" + returningResultSet);
		List<Custactivities> response = (List<Custactivities>) returningResultSet.get("r_posting");

		LOGGER.info("+++++show me result set23===>>" + response);

		return response;
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
	
	

	
	

}
