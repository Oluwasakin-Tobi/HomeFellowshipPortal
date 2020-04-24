package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class User implements Serializable {
 
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 8587129268154294109L;
	private long userID;
    private long affiliateID;
    private BigDecimal userTransactionLimit;
    private boolean active;
    private String userRoles;
    private boolean operationUser;
    private String userFullName;
    private String userEmailAdd;
    private String userBranch;
    private String passwordExpiryPolicy;
    private boolean authorisedUserFlag;
    private String userName;
    private String serverIP;
    private String serviceClient;
    private String affiliateCode;
	private String tokenGroup;
	private String createdBy;
	private String deleteFlag;
	private Date dateCreated;
	private boolean editedFlag;
	private boolean editedRoleFlag;
	private String password;


 
	
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEditedFlag() {
		return editedFlag;
	}

	public void setEditedFlag(boolean editedFlag) {
		this.editedFlag = editedFlag;
	}

	public boolean isEditedRoleFlag() {
		return editedRoleFlag;
	}

	public void setEditedRoleFlag(boolean editedRoleFlag) {
		this.editedRoleFlag = editedRoleFlag;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getTokenGroup() {
		return tokenGroup;
	}

	public void setTokenGroup(String tokenGroup) {
		this.tokenGroup = tokenGroup;
	}

	public String getAffiliateCode() {
		return affiliateCode;
	}

	public void setAffiliateCode(String affiliateCode) {
		this.affiliateCode = affiliateCode;
	}

	public String getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(String serviceClient) {
		this.serviceClient = serviceClient;
	}

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getAffiliateID() {
        return affiliateID;
    }

    public void setAffiliateID(long affiliateID) {
        this.affiliateID = affiliateID;
    }

    public BigDecimal getUserTransactionLimit() {
        return userTransactionLimit;
    }

    public void setUserTransactionLimit(BigDecimal userTransactionLimit) {
        this.userTransactionLimit = userTransactionLimit;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String userRoles) {
        this.userRoles = userRoles;
    }

    public boolean isOperationUser() {
        return operationUser;
    }

    public void setOperationUser(boolean operationUser) {
        this.operationUser = operationUser;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmailAdd() {
        return userEmailAdd;
    }

    public void setUserEmailAdd(String userEmailAdd) {
        this.userEmailAdd = userEmailAdd;
    }

    public String getUserBranch() {
        return userBranch;
    }

    public void setUserBranch(String userBranch) {
        this.userBranch = userBranch;
    }

    public String getPasswordExpiryPolicy() {
        return passwordExpiryPolicy;
    }

    public void setPasswordExpiryPolicy(String passwordExpiryPolicy) {
        this.passwordExpiryPolicy = passwordExpiryPolicy;
    }

    public boolean isAuthorisedUserFlag() {
        return authorisedUserFlag;
    }

    public void setAuthorisedUserFlag(boolean authorisedUserFlag) {
        this.authorisedUserFlag = authorisedUserFlag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

	@Override
	public String toString() {
		return "User [userID=" + userID + ", affiliateID=" + affiliateID + ", userTransactionLimit="
				+ userTransactionLimit + ", active=" + active + ", userRoles=" + userRoles + ", operationUser="
				+ operationUser + ", userFullName=" + userFullName + ", userEmailAdd=" + userEmailAdd + ", userBranch="
				+ userBranch + ", passwordExpiryPolicy=" + passwordExpiryPolicy + ", authorisedUserFlag="
				+ authorisedUserFlag + ", userName=" + userName + ", serverIP=" + serverIP + ", serviceClient="
				+ serviceClient + ", affiliateCode=" + affiliateCode + ", tokenGroup=" + tokenGroup + ", createdBy="
				+ createdBy + ", deleteFlag=" + deleteFlag + ", dateCreated=" + dateCreated + ", editedFlag="
				+ editedFlag + ", editedRoleFlag=" + editedRoleFlag + ", password=" + password + "]";
	}







}
