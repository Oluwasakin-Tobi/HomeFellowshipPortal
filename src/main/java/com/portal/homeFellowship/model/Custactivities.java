package com.portal.homeFellowship.model;

public class Custactivities {
	
	private long id;
	private String actName;
	private String flag ;
	private boolean selected;
	
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Custactivities [id=" + id + ", actName=" + actName + ", flag=" + flag + ", selected=" + selected + "]";
	}
	 
	

}
