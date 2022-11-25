package com.nic.ev.ifms.model.webservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseForSekAndAuthToken{
	public String sek;
	public String authToken;
	public String getSek() {
		return sek;
	}
	public void setSek(String sek) {
		this.sek = sek;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	
}