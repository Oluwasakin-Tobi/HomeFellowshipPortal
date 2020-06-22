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
    private boolean active;
    private String userRolesStr;
    private boolean operationUser;
    private String fullName; 
    private String firstName;
    private String lastName;
    private String email; 
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
	
	private String phoneNo;

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getAffiliate() {
		return affiliate;
	}

	public void setAffiliate(String affiliate) {
		this.affiliate = affiliate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAuthorised() {
		return authorised;
	}

	public void setAuthorised(boolean authorised) {
		this.authorised = authorised;
	}

	public String getAdUsername() {
		return adUsername;
	}

	public void setAdUsername(String adUsername) {
		this.adUsername = adUsername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<UserProfileAdmin> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfileAdmin> userProfiles) {
		this.userProfiles = userProfiles;
	}

	public String getCurrentLoginIPAddress() {
		return currentLoginIPAddress;
	}

	public void setCurrentLoginIPAddress(String currentLoginIPAddress) {
		this.currentLoginIPAddress = currentLoginIPAddress;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public List<String> getCustomActivities() {
		return customActivities;
	}

	public void setCustomActivities(List<String> customActivities) {
		this.customActivities = customActivities;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "UserAdmin [userID=" + userID + ", affiliate=" + affiliate + ", active=" + active + ", userRolesStr="
				+ userRolesStr + ", operationUser=" + operationUser + ", fullName=" + fullName + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", authorised=" + authorised
				+ ", adUsername=" + adUsername + ", password=" + password + ", userProfiles=" + userProfiles
				+ ", currentLoginIPAddress=" + currentLoginIPAddress + ", userFullName=" + userFullName
				+ ", customActivities=" + customActivities + ", dateCreated=" + dateCreated + ", editedFlag="
				+ editedFlag + ", editedRoleFlag=" + editedRoleFlag + ", phoneNo=" + phoneNo + "]";
	}
	
	


	
	
     
    
	

 
 
     

    /*
     * DO-NOT-INCLUDE passwords in toString function.
     * It is done here just for convenience purpose.
     */
  
}
