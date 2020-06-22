package com.portal.homeFellowship.model;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SocialEvent implements Serializable{
	
	private String topic;
	private String event;
	private String name;
	private MultipartFile gallery;
	private MultipartFile[] gallerys;
	private String uploadFlag;
	private String eventID;
	private String docName; 
	@JsonIgnore
	private InputStream inputStream;
	private String filetype;
	private int inputStreamLength;
	private String inputStreamStr;
	private String documentType; 
	private boolean compressed;
	private String status;
	
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public MultipartFile[] getGallerys() {
		return gallerys;
	}
	public void setGallerys(MultipartFile[] gallerys) {
		this.gallerys = gallerys;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MultipartFile getGallery() {
		return gallery;
	}
	public void setGallery(MultipartFile gallery) {
		this.gallery = gallery;
	}
	public String getUploadFlag() {
		return uploadFlag;
	}
	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
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
	public boolean isCompressed() {
		return compressed;
	}
	public void setCompressed(boolean compressed) {
		this.compressed = compressed;
	}
	@Override
	public String toString() {
		return "SocialEvent [topic=" + topic + ", event=" + event + ", name=" + name + ", gallery=" + gallery
				+ ", gallerys=" + Arrays.toString(gallerys) + ", uploadFlag=" + uploadFlag + ", eventID=" + eventID
				+ ", docName=" + docName + ", inputStream=" + inputStream + ", filetype=" + filetype
				+ ", inputStreamLength=" + inputStreamLength + ", inputStreamStr=" + inputStreamStr + ", documentType="
				+ documentType + ", compressed=" + compressed + ", status=" + status + "]";
	}



	
	

}
