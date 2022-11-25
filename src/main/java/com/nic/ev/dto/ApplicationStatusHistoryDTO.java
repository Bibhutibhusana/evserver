package com.nic.ev.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class ApplicationStatusHistoryDTO {

    private String regnNo;

	private String applNo;


	private String userRegistered;


	private String verification;

	private String approval;

	private String payment;

	private Date opDt;

	private Date verifyDt;

	private Date approveDt;

	private String reason;

	private String verifyUserId;

	private String approveUserId;

	private String downloadStatus;

	private Date insertDt;
}