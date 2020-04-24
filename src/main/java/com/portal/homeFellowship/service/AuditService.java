package com.portal.homeFellowship.service;

import com.portal.homeFellowship.model.*;

public interface AuditService {

	Response insertIntoAdminAudit(AuditMasterDetail auditMasterDetail, UserAdmin principal);

}
