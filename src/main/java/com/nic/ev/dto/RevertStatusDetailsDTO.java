package com.nic.ev.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class RevertStatusDetailsDTO {
	private int id;
	private String regnNo;
	private String applNo;
	private String verification;
	private String approval;
	private Date opDt;
	private String reason;
	private String verifyUserId;
	private String approveUserId;
	private Date insertDt;
}