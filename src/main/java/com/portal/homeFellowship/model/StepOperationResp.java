package com.portal.homeFellowship.model;

import java.util.List;

public class StepOperationResp {
	 private List<StepOperRequest> operations;
	 private String responseCode;
		private String responseMessage;
		public List<StepOperRequest> getOperations() {
			return operations;
		}
		public void setOperations(List<StepOperRequest> operations) {
			this.operations = operations;
		}
		public String getResponseCode() {
			return responseCode;
		}
		public void setResponseCode(String responseCode) {
			this.responseCode = responseCode;
		}
		public String getResponseMessage() {
			return responseMessage;
		}
		public void setResponseMessage(String responseMessage) {
			this.responseMessage = responseMessage;
		}
		
		
		
}
