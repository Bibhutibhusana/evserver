package com.nic.ev.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class RegistrationDetailsDTO {

    private Long id;
    private String username;
	private String password;
	private String role;
	private String off_cd;
}