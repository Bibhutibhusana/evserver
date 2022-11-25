package com.nic.ev.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class VahanDetailsDTO {
private String regnNo;

	private String ownerName;

	private String chasiNo;

	private String engNo;

	private String address;

	private String vmake;

	private String vmodel;

	private String vClass;

	private Date regnDt;

	private String fuel;

	private Date op_dt;

	private String off_cd;

	private String mobNo;

	private Long sale_amnt;

	private Date purchaseDt;
}