package com.nic.ev.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class IFMSIntegrationTrackDTO {

	private Long id;

	private String fileRefId;

	private String billNo;

	private String billRefNo;

	private String resFileName;

	private String applNo;

	private String regnNo;

	private String accNo;

	private String name;

	private String ifsc;

	private String submitStatus;

	private Date submitDate;

	private String submitErr;

	private String ackStatus;

	private Date ackDate;

	private String ackErr;

	private String billStatus;


	private String billStatusErr;

	private String checkStatus;

	private Date checkStatusErr;

	private Date checkStatusDate;

	private String voucherNo;

	private Date voucherDate;

	private String billStatusString;

	private String utrNo;

	private Date utrDate;

	private String benfPaymentStatus;

	private String benefBillStatus;

	private String revertStatus;

	private Date revertStatusDate;

	private String ddoCheckStatus;

	private Date ddoCheckStatusDate;

	private String rtoCode;
}