package com.nic.ev.ifms.model.webservice.dto;


public class BillPaymentResponse {
	private Boolean status;
	private String billStatusString;
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getBillStatusString() {
		return billStatusString;
	}
	public void setBillStatusString(String billStatusString) {
		this.billStatusString = billStatusString;
	}
	
	

}
