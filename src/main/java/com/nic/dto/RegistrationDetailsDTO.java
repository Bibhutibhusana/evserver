package com.nic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RegistrationDetailsDTO {
	
    private Long id;
    private String username;
	private String password;
	private String role;
	private String off_cd;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getOff_cd() {
		return off_cd;
	}
	public void setOff_cd(String off_cd) {
		this.off_cd = off_cd;
	}
	
}
