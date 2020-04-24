package com.portal.homeFellowship.model;

public class AuthUsertoroleReq {
	
	private String userName;
	private long userToroleId;
	private String pflag;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getUserToroleId() {
		return userToroleId;
	}
	public void setUserToroleId(long userToroleId) {
		this.userToroleId = userToroleId;
	}
	public String getPflag() {
		return pflag;
	}
	public void setPflag(String pflag) {
		this.pflag = pflag;
	}
	@Override
	public String toString() {
		return "AuthUsertoroleReq [userName=" + userName + ", userToroleId=" + userToroleId + ", pflag=" + pflag + "]";
	}
	
	
	
	

}
