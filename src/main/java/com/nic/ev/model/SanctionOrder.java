package com.nic.ev.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SanctionOrder {

	private Date approveDt;
	private int sub_amnt;
	private String inWords;
	private String ownerName;
	private String regnNo;
	private String chequeNo;

}