package com.portal.homeFellowship.model;

import java.io.InputStream;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DocManagerRequest {
	private long documentID;
	private String docName; 
	private String documentUniqueID;
	private String userName;
	private String serverIP;
	private MultipartFile file; 
	@JsonIgnore
	private InputStream inputStream;
	private String filetype;
	private int inputStreamLength;
	private String inputStreamStr;
	private String documentType; 
	private Date dateCreated; 
	@JsonIgnore
	private InputStream inputStreamer;
	private boolean compressed;
	private String dateCreatedStr;
	private String topic;
	
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public long getDocumentID() {
		return documentID;
	}
	public void setDocumentID(long documentID) {
		this.documentID = documentID;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDocumentUniqueID() {
		return documentUniqueID;
	}
	public void setDocumentUniqueID(String documentUniqueID) {
		this.documentUniqueID = documentUniqueID;
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
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public int getInputStreamLength() {
		return inputStreamLength;
	}
	public void setInputStreamLength(int inputStreamLength) {
		this.inputStreamLength = inputStreamLength;
	}
	public String getInputStreamStr() {
		return inputStreamStr;
	}
	public void setInputStreamStr(String inputStreamStr) {
		this.inputStreamStr = inputStreamStr;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public InputStream getInputStreamer() {
		return inputStreamer;
	}
	public void setInputStreamer(InputStream inputStreamer) {
		this.inputStreamer = inputStreamer;
	}
	public boolean isCompressed() {
		return compressed;
	}
	public void setCompressed(boolean compressed) {
		this.compressed = compressed;
	}
	public String getDateCreatedStr() {
		return dateCreatedStr;
	}
	public void setDateCreatedStr(String dateCreatedStr) {
		this.dateCreatedStr = dateCreatedStr;
	}
	@Override
	public String toString() {
		return "DocManagerRequest [documentID=" + documentID + ", docName=" + docName + ", documentUniqueID="
				+ documentUniqueID + ", userName=" + userName + ", serverIP=" + serverIP + ", file=" + file
				+ ", inputStream=" + inputStream + ", filetype=" + filetype + ", inputStreamLength=" + inputStreamLength
				+ ", inputStreamStr=" + inputStreamStr + ", documentType=" + documentType + ", dateCreated="
				+ dateCreated + ", inputStreamer=" + inputStreamer + ", compressed=" + compressed + ", dateCreatedStr="
				+ dateCreatedStr + ", topic=" + topic + "]";
	}


	

	
}
