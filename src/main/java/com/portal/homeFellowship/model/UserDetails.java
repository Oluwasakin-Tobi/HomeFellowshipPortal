package com.portal.homeFellowship.model;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetails implements Serializable{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 2227110557661979719L;
		private long userID;
		private boolean active;
		private String userRoles;
		private boolean operationUser;
		private String userFullName;
		private String userEmailAdd;
		private boolean authorisedUserFlag;
		private String userName;
		private String serverIP;
		private String createdBy;
		private String dateCreated;
		private String password;
		private String phoneNo;
		private String affiliateCode;
		private boolean editedFlag;
		private boolean editedRoleFlag;
		private String otp;
		
		
		private String surname;
		private String firstName;
		private String middleName;
		private String gender;
		private String dateOfBirth;
		private String weddingAnniversary;
		
		private String dateJoinedHOTR;
		private String dateJoinedCITH;
		private String completedFindingTheRock;
		private String completedSpiritualAuthority;
		private String homeAddress;
		private String placeOfWork;
		private String placeOfWorkAddress;
		
		private String churchDepartment;
		private String occupation;
		private String dateJoinedChurchDept;
		private String maritalStatus;
		private String spouseName;
		private String spousePhoneNumber;
		
		private String centre;
		private String area;
		private String zone;
		private String district;
		private String state;
		private String country;
		
		private MultipartFile profilePicture;
		
		private String adminTeam;
		
		private String baptised;
		private String dateBaptised;
		private String typeOfBaptism;
		private String lastChurch;
		private String lastChurchAddress;
		private String dateBornAgain;
		private String userTitle;
		private String uniqueID;
		
		@JsonIgnore
		private InputStream inputStream;
		private String inputStreamStr;
		
		
		
		
		
		
		public String getUniqueID() {
			return uniqueID;
		}
		public void setUniqueID(String uniqueID) {
			this.uniqueID = uniqueID;
		}
		public String getUserTitle() {
			return userTitle;
		}
		public void setUserTitle(String userTitle) {
			this.userTitle = userTitle;
		}
		public InputStream getInputStream() {
			return inputStream;
		}
		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}
		public String getInputStreamStr() {
			return inputStreamStr;
		}
		public void setInputStreamStr(String inputStreamStr) {
			this.inputStreamStr = inputStreamStr;
		}
		public String getBaptised() {
			return baptised;
		}
		public void setBaptised(String baptised) {
			this.baptised = baptised;
		}
		public String getDateBaptised() {
			return dateBaptised;
		}
		public void setDateBaptised(String dateBaptised) {
			this.dateBaptised = dateBaptised;
		}
		public String getTypeOfBaptism() {
			return typeOfBaptism;
		}
		public void setTypeOfBaptism(String typeOfBaptism) {
			this.typeOfBaptism = typeOfBaptism;
		}
		public String getLastChurch() {
			return lastChurch;
		}
		public void setLastChurch(String lastChurch) {
			this.lastChurch = lastChurch;
		}
		public String getLastChurchAddress() {
			return lastChurchAddress;
		}
		public void setLastChurchAddress(String lastChurchAddress) {
			this.lastChurchAddress = lastChurchAddress;
		}
		public String getDateBornAgain() {
			return dateBornAgain;
		}
		public void setDateBornAgain(String dateBornAgain) {
			this.dateBornAgain = dateBornAgain;
		}
		public String getAdminTeam() {
			return adminTeam;
		}
		public void setAdminTeam(String adminTeam) {
			this.adminTeam = adminTeam;
		}
		public MultipartFile getProfilePicture() {
			return profilePicture;
		}
		public void setProfilePicture(MultipartFile profilePicture) {
			this.profilePicture = profilePicture;
		}
		public String getOtp() {
			return otp;
		}
		public void setOtp(String otp) {
			this.otp = otp;
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
		public String getAffiliateCode() {
			return affiliateCode;
		}
		public void setAffiliateCode(String affiliateCode) {
			this.affiliateCode = affiliateCode;
		}
		public long getUserID() {
			return userID;
		}
		public void setUserID(long userID) {
			this.userID = userID;
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
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}
		public String getDateCreated() {
			return dateCreated;
		}
		public void setDateCreated(String dateCreated) {
			this.dateCreated = dateCreated;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPhoneNo() {
			return phoneNo;
		}
		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}
		public String getSurname() {
			return surname;
		}
		public void setSurname(String surname) {
			this.surname = surname;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getMiddleName() {
			return middleName;
		}
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}
		public String getWeddingAnniversary() {
			return weddingAnniversary;
		}
		public void setWeddingAnniversary(String weddingAnniversary) {
			this.weddingAnniversary = weddingAnniversary;
		}
		public String getDateJoinedHOTR() {
			return dateJoinedHOTR;
		}
		public void setDateJoinedHOTR(String dateJoinedHOTR) {
			this.dateJoinedHOTR = dateJoinedHOTR;
		}
		public String getDateJoinedCITH() {
			return dateJoinedCITH;
		}
		public void setDateJoinedCITH(String dateJoinedCITH) {
			this.dateJoinedCITH = dateJoinedCITH;
		}
		public String getCompletedFindingTheRock() {
			return completedFindingTheRock;
		}
		public void setCompletedFindingTheRock(String completedFindingTheRock) {
			this.completedFindingTheRock = completedFindingTheRock;
		}
		public String getCompletedSpiritualAuthority() {
			return completedSpiritualAuthority;
		}
		public void setCompletedSpiritualAuthority(String completedSpiritualAuthority) {
			this.completedSpiritualAuthority = completedSpiritualAuthority;
		}
		public String getHomeAddress() {
			return homeAddress;
		}
		public void setHomeAddress(String homeAddress) {
			this.homeAddress = homeAddress;
		}
		public String getPlaceOfWork() {
			return placeOfWork;
		}
		public void setPlaceOfWork(String placeOfWork) {
			this.placeOfWork = placeOfWork;
		}
		public String getPlaceOfWorkAddress() {
			return placeOfWorkAddress;
		}
		public void setPlaceOfWorkAddress(String placeOfWorkAddress) {
			this.placeOfWorkAddress = placeOfWorkAddress;
		}
		public String getChurchDepartment() {
			return churchDepartment;
		}
		public void setChurchDepartment(String churchDepartment) {
			this.churchDepartment = churchDepartment;
		}
		public String getOccupation() {
			return occupation;
		}
		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}
		public String getDateJoinedChurchDept() {
			return dateJoinedChurchDept;
		}
		public void setDateJoinedChurchDept(String dateJoinedChurchDept) {
			this.dateJoinedChurchDept = dateJoinedChurchDept;
		}
		public String getMaritalStatus() {
			return maritalStatus;
		}
		public void setMaritalStatus(String maritalStatus) {
			this.maritalStatus = maritalStatus;
		}
		public String getSpouseName() {
			return spouseName;
		}
		public void setSpouseName(String spouseName) {
			this.spouseName = spouseName;
		}
		public String getSpousePhoneNumber() {
			return spousePhoneNumber;
		}
		public void setSpousePhoneNumber(String spousePhoneNumber) {
			this.spousePhoneNumber = spousePhoneNumber;
		}
		public String getCentre() {
			return centre;
		}
		public void setCentre(String centre) {
			this.centre = centre;
		}
		public String getArea() {
			return area;
		}
		public void setArea(String area) {
			this.area = area;
		}
		public String getZone() {
			return zone;
		}
		public void setZone(String zone) {
			this.zone = zone;
		}
		public String getDistrict() {
			return district;
		}
		public void setDistrict(String district) {
			this.district = district;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		@Override
		public String toString() {
			return "UserDetails [userID=" + userID + ", active=" + active + ", userRoles=" + userRoles
					+ ", operationUser=" + operationUser + ", userFullName=" + userFullName + ", userEmailAdd="
					+ userEmailAdd + ", authorisedUserFlag=" + authorisedUserFlag + ", userName=" + userName
					+ ", serverIP=" + serverIP + ", createdBy=" + createdBy + ", dateCreated=" + dateCreated
					+ ", password=" + password + ", phoneNo=" + phoneNo + ", affiliateCode=" + affiliateCode
					+ ", editedFlag=" + editedFlag + ", editedRoleFlag=" + editedRoleFlag + ", otp=" + otp
					+ ", surname=" + surname + ", firstName=" + firstName + ", middleName=" + middleName + ", gender="
					+ gender + ", dateOfBirth=" + dateOfBirth + ", weddingAnniversary=" + weddingAnniversary
					+ ", dateJoinedHOTR=" + dateJoinedHOTR + ", dateJoinedCITH=" + dateJoinedCITH
					+ ", completedFindingTheRock=" + completedFindingTheRock + ", completedSpiritualAuthority="
					+ completedSpiritualAuthority + ", homeAddress=" + homeAddress + ", placeOfWork=" + placeOfWork
					+ ", placeOfWorkAddress=" + placeOfWorkAddress + ", churchDepartment=" + churchDepartment
					+ ", occupation=" + occupation + ", dateJoinedChurchDept=" + dateJoinedChurchDept
					+ ", maritalStatus=" + maritalStatus + ", spouseName=" + spouseName + ", spousePhoneNumber="
					+ spousePhoneNumber + ", centre=" + centre + ", area=" + area + ", zone=" + zone + ", district="
					+ district + ", state=" + state + ", country=" + country + ", profilePicture=" + profilePicture
					+ ", adminTeam=" + adminTeam + ", baptised=" + baptised + ", dateBaptised=" + dateBaptised
					+ ", typeOfBaptism=" + typeOfBaptism + ", lastChurch=" + lastChurch + ", lastChurchAddress="
					+ lastChurchAddress + ", dateBornAgain=" + dateBornAgain + ", userTitle=" + userTitle
					+ ", uniqueID=" + uniqueID + ", inputStream=" + inputStream + ", inputStreamStr=" + inputStreamStr
					+ "]";
		}
		

		

	

		

		
		
		
		








	 

	 


}
