package com.nic.ev.ifms.iservice;

import java.util.List;

import com.nic.ev.ifms.model.AuthenticationTokenAndSEKTable;

public interface IAuthenticationTokenAndSEKTableService {
	public List<AuthenticationTokenAndSEKTable> getListOfAuthentication();
	public AuthenticationTokenAndSEKTable saveAuthentication(AuthenticationTokenAndSEKTable authData);
}
