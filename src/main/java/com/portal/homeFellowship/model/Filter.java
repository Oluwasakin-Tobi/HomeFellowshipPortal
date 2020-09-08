package com.portal.homeFellowship.model;

import java.io.Serializable;
import java.util.Date;

public class Filter implements Serializable{
	
	private String dateFromStr;
	private String dateToStr;
	private String centre;
	private String month;
	private String name;
	private Date dateFrom;
	private Date dateTo;
	private String area;
	private String zone;
	private String district;
	private String type;
	private String value;
	
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
	public String getDateFromStr() {
		return dateFromStr;
	}
	public void setDateFromStr(String dateFromStr) {
		this.dateFromStr = dateFromStr;
	}
	public String getDateToStr() {
		return dateToStr;
	}
	public void setDateToStr(String dateToStr) {
		this.dateToStr = dateToStr;
	}
	public String getCentre() {
		return centre;
	}
	public void setCentre(String centre) {
		this.centre = centre;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	@Override
	public String toString() {
		return "Filter [dateFromStr=" + dateFromStr + ", dateToStr=" + dateToStr + ", centre=" + centre + ", month="
				+ month + ", name=" + name + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", area=" + area
				+ ", zone=" + zone + ", district=" + district + ", type=" + type + ", value=" + value + "]";
	}
	


	
	
	


	
	

}
