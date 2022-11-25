package com.nic.ev.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class ApplicationStatusDTO {

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

	private String cheque_no; 

	private String sub_amnt;

	private String disbursement;

	private String rtgsNo;

	private String bankNameDisbursement;

	private Date disburseDt;
}