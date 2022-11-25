package com.nic.ev.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="evh_status")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserStatusHistory {
	
	@Id
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
