package com.portal.homeFellowship.model;

import java.io.Serializable;

public class AuthoriseUser implements Serializable {
    private long userID;
    private String authoriseUser;
    private String userName;
    private String serverIP;
    private String serviceClient;


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

    public String getAuthoriseUser() {
        return authoriseUser;
    }

    public void setAuthoriseUser(String authoriseUser) {
        this.authoriseUser = authoriseUser;
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
}
