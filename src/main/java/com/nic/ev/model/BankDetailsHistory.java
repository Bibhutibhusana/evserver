package com.nic.ev.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nic.ev.ifms.model.webservice.ByTransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="evh_bank_details")
public class BankDetailsHistory {


	@Id
	private String regnNo;
	
	private String name;
	
	private String bankName;
	
	private String branchName;
	
	private String ifscCode;
	
	private String accNo;
	
	private Date op_dt;
	
	private byte[] passbookImg;

	private String applNo;
	
	private Date inserDt;

	
}
