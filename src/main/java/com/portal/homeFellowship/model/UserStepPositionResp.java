package com.portal.homeFellowship.model;

import java.io.Serializable;

public class UserStepPositionResp implements Serializable{
	
	private long stepPosition;
	private long stepid;
	private String stepName;
	
	public long getStepPosition() {
		return stepPosition;
	}
	public void setStepPosition(long stepPosition) {
		this.stepPosition = stepPosition;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public long getStepid() {
		return stepid;
	}
	public void setStepid(long stepid) {
		this.stepid = stepid;
	}
	@Override
	public String toString() {
		return "UserStepPositionResp [stepPosition=" + stepPosition + ", stepid=" + stepid + ", stepName=" + stepName
				+ "]";
	}
	
	

}
