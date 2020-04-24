package com.portal.homeFellowship.model;

public class StepOperRequest {
	
	private long operationID;
    private String operationName;
    private long stepID;
	
	 public long getOperationID() {
		return operationID;
	}
	public void setOperationID(long operationID) {
		this.operationID = operationID;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	
	public long getStepID() {
		return stepID;
	}
	public void setStepID(long stepID) {
		this.stepID = stepID;
	}
	@Override
	public String toString() {
		return "StepOperRequest [operationID=" + operationID + ", operationName=" + operationName + ", stepID=" + stepID
				+ "]";
	}
	
	

}
