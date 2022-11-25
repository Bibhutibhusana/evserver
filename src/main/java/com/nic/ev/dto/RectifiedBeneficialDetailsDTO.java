package com.nic.ev.dto;

import java.util.Date;

import lombok.Data;

@Data
public class RectifiedBeneficialDetailsDTO {

	private Long id;

	private String accNo;

	private String applNo;

	private String billNo;

	private String billStatus;

	private String benfBillStatus;

	private String ifsc;

	private String name;

	private String regnNo;

	private String benfPaymentStatus;

	private String revertStatus;

	private Date submitDate;

	private Date checkBillStatusDate;

	private String subAmnt;

	private String approval;

	private String verify;

	private String rtgsNo;

	private String evErr;

	private String ddoCheckStatus;

	private Date ddoCheckStatusDate;
}