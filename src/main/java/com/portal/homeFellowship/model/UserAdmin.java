package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;


@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class UserAdmin implements Serializable {
	

	static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    private static final long serialVersionUID = 2285461287386274373L; 
    private Long userID;
    private String affiliate;
    private BigDecimal limitAmount;
    private boolean active;
    private String userRolesStr;
    private boolean operationUser;
    private String fullName; 
    private String firstName;
    private String lastName;
    private String email; 
    private String branchCode;
    private String passwordExpiryPolicy;  
    private boolean authorised;
    private String adUsername; 
    private String password;
    private Set<UserProfileAdmin> userProfiles = new HashSet<>();
    private String currentLoginIPAddress;
	private String userFullName;
	private List<String> customActivities = new ArrayList<>();
	private Date dateCreated;
	private boolean editedFlag;
	private boolean editedRoleFlag;
	
	
     
    
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

	public List<String> getCustomActivities() {
		return customActivities;
	}

	public void setCustomActivities(List<String> customActivities) {
		this.customActivities = customActivities;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAffiliate() {
		return affiliate;
	}

	public void setAffiliate(String affiliate) {
		this.affiliate = affiliate;
	}

	public BigDecimal getLimitAmount() {
		return limitAmount;
	}

	public void setLimitAmount(BigDecimal limitAmount) {
		this.limitAmount = limitAmount;
	}

	public String getUserRolesStr() {
		return userRolesStr;
	}

	public void setUserRolesStr(String userRolesStr) {
		this.userRolesStr = userRolesStr;
	}

	public boolean isOperationUser() {
		return operationUser;
	}

	public void setOperationUser(boolean operationUser) {
		this.operationUser = operationUser;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPasswordExpiryPolicy() {
		return passwordExpiryPolicy;
	}

	public void setPasswordExpiryPolicy(String passwordExpiryPolicy) {
		this.passwordExpiryPolicy = passwordExpiryPolicy;
	}

	public boolean isAuthorised() {
		return authorised;
	}

	public void setAuthorised(boolean authorised) {
		this.authorised = authorised;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getCurrentLoginIPAddress() {
		return currentLoginIPAddress;
	}

	public void setCurrentLoginIPAddress(String currentLoginIPAddress) {
		this.currentLoginIPAddress = currentLoginIPAddress;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}  

    public boolean isActive() {
		return active;
	}

	public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
 
    public String getAdUsername() {
        return adUsername;
    }

    public void setAdUsername(String adUsername) {
        this.adUsername = adUsername;
    } 

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserProfileAdmin> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfileAdmin> userProfiles) {
        this.userProfiles = userProfiles;
    }
     
 

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adUsername == null) ? 0 : adUsername.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAdmin other = (UserAdmin) obj;
		if (adUsername == null) {
			if (other.adUsername != null)
				return false;
		} else if (!adUsername.equals(other.adUsername))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", affiliate=" + affiliate + ", limitAmount=" + limitAmount + ", active="
				+ active + ", userRolesStr=" + userRolesStr + ", operationUser=" + operationUser + ", fullName="
				+ fullName + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", branchCode=" + branchCode + ", passwordExpiryPolicy=" + passwordExpiryPolicy + ", authorised="
				+ authorised + ", adUsername=" + adUsername + ", password=" + password + ", userProfiles="
				+ userProfiles + ", currentLoginIPAddress=" + currentLoginIPAddress + ", userFullName=" + userFullName
				+ ", customActivities=" + customActivities + ", dateCreated=" + dateCreated + ", editedFlag="
				+ editedFlag + ", editedRoleFlag=" + editedRoleFlag + "]";
	}




 
 
     

    /*
     * DO-NOT-INCLUDE passwords in toString function.
     * It is done here just for convenience purpose.
     */
  
}
