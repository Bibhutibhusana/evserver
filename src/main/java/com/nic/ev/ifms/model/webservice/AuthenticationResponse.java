package com.nic.ev.ifms.model.webservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
	public Boolean status;
	public String errorCode ;
	public String errorMessage;
	public AuthenticationResponseForSekAndAuthToken data;
	
}
