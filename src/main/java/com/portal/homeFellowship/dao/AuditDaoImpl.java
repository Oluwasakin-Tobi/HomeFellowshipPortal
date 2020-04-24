package com.portal.homeFellowship.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

@Repository("auditDao")
public class AuditDaoImpl implements AuditDao{
	
final static Logger LOGGER = LoggerFactory.getLogger(AuditDaoImpl.class);
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${baseUtilPackage}")
	private String baseUtilityPackage;

	@Override
	public Response insertAdminAudit(AuditLog auditLog) { 
		SimpleJdbcCall insertAuditLogSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		insertAuditLogSimpleJdbcCall.withProcedureName(baseUtilityPackage +".insertintoadminauditlog")
	            .withoutProcedureColumnMetaDataAccess()
	            .declareParameters(
	                    new SqlParameter("pEventDesc", Types.VARCHAR),
	                    new SqlParameter("pEventSrc", Types.VARCHAR),
	                    new SqlParameter("pExt_Ref", Types.VARCHAR),
	                    new SqlParameter("PInitiatorUsername", Types.VARCHAR),
	                    new SqlParameter("pServerIp", Types.VARCHAR),
	                    new SqlOutParameter("pResponseCode", Types.VARCHAR),
	                    new SqlOutParameter("pResponseMsg", Types.VARCHAR))
	            .compile();

	    SqlParameterSource inParams = new MapSqlParameterSource().addValue("pEventDesc", auditLog.getEventDesc()).addValue("pEventSrc",auditLog.getEventSrc())
	            .addValue("PInitiatorUsername",auditLog.getUserName()).addValue("pServerIp",auditLog.getServerIp())
	            .addValue("pExt_Ref",auditLog.getExtRef());


	    Map<String, Object> returningResult = insertAuditLogSimpleJdbcCall.execute(inParams);
	    String responseCode = (String) returningResult.get("pResponseCode");
	    String validResponseCode = responseCode != null ? responseCode : "99";
	    String responseMsg = (String) returningResult.get("pResponseMsg");
	    String validResponseMsg = responseMsg != null ? responseMsg : "";

	    Response response = new Response();
	    response.setResponseCode(validResponseCode);
	    response.setResponseMessage(validResponseMsg);

	    return response;
	} 
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AuditLog> getAuditLog(Affiliate request) {
		SimpleJdbcCall getAccountDetailsSimpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		getAccountDetailsSimpleJdbcCall
				.withProcedureName(baseUtilityPackage + ".get_audit_log")
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

		getAccountDetailsSimpleJdbcCall.compile();

		SqlParameterSource inparams = new MapSqlParameterSource().addValue("p_affiliate", request.getAffiliateCode());

		Map<String, Object> returningResultSet = getAccountDetailsSimpleJdbcCall.execute(inparams);
		

		List<AuditLog> response = (List<AuditLog>) returningResultSet.get("r_details");

		return response==null||response.isEmpty()?new ArrayList<>():response;

	}

}
