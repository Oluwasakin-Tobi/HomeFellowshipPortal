package com.portal.homeFellowship.model;

import java.io.Serializable;



public class UserProfile implements Serializable { 
        /**
	 * 
	 */
	private static final long serialVersionUID = 8871284471604247872L;
		private long userID;
        private String userName;
        private String serverIP;
        private String serviceClient;
        private String plag;
        
        

public String getPlag() {
			return plag;
		}




		public void setPlag(String plag) {
			this.plag = plag;
		}




public long getUserID() {
			return userID;
		}




		public void setUserID(long userID) {
			this.userID = userID;
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




		public String getServiceClient() {
			return serviceClient;
		}




		public void setServiceClient(String serviceClient) {
			this.serviceClient = serviceClient;
		}




}