package com.nic.ev.ifms.model;

public class AuthenticationResponse {

	public Boolean status;
	public String errorCode ;
	public String errorMessage;
	public AuthenticationResponseForSekAndAuthToken data;
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public AuthenticationResponseForSekAndAuthToken getData() {
		return data;
	}
	public void setData(AuthenticationResponseForSekAndAuthToken data) {
		this.data = data;
	}
}
