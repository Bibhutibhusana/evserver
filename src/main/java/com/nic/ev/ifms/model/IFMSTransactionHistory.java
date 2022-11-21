package com.nic.ev.ifms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nic.ev.ifms.model.webservice.BaseEntity;

import lombok.Data;

@Data
@Entity
@Table(name="evh_ifms_transaction")
public class IFMSTransactionHistory extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name ="file_ref_id")
	private String fileRefId;
	
	@Column(name="bill_no")
	private String billNo;
	
	@Column(name="appl_no")
	private String applNo;
	 
	@Column(name="regn_no",unique= true, nullable = false)
	private String regnNo;

	@Column(name="acc_no")
	private String accNo;
	
	@Column(name="name")
	private String name;
	
	@Column(name="ifsc")
	private String ifsc;
	
	@Column(name="submit_status")
	private String submitStatus;
	
	@Column(name="submit_date")
	private Date submitDate;
	
	@Column(name="submit_err")
	private String submitErr;
	
	@Column(name="ack_status")
	private String ackStatus;
	
	@Column(name="ack_date")
	private Date ackDate;
	
	@Column(name="ack_err")
	private String ackErr;
	
	@Column(name="bill_status")
	private String billStatus;
	
	@Column(name="bill_status_date")
	private Date billStatusDate;
	
	@Column(name="bill_status_err")
	private String billStatusErr;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplNo() {
		return applNo;
	}

	public void setApplNo(String applNo) {
		this.applNo = applNo;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	
	public String getAckStatus() {
		return ackStatus;
	}

	public void setAckStatus(String ackStatus) {
		this.ackStatus = ackStatus;
	}

	public Date getAckDate() {
		return ackDate;
	}

	public void setAckDate(Date ackDate) {
		this.ackDate = ackDate;
	}

	public String getAckErr() {
		return ackErr;
	}

	public void setAckErr(String ackErr) {
		this.ackErr = ackErr;
	}

	
}
