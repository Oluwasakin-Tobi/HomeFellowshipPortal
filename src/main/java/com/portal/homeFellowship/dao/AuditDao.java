package com.portal.homeFellowship.dao;

import java.util.List;

import com.portal.homeFellowship.model.*;

public interface AuditDao {
	

	public Response insertAdminAudit(AuditLog auditLog);
	
	List<AuditLog> getAuditLog(Affiliate request);
	

}
