package com.nic.ev.ifms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="evt_ifms_error_master")
public class IFMSErrorModel {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="err_cd")
	private String errCode;
	
	@Column(name="ifms_err")
	private String ifmsErr;
	
	@Column(name="ev_err")
	private String evErr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getIfmsErr() {
		return ifmsErr;
	}

	public void setIfmsErr(String ifmsErr) {
		this.ifmsErr = ifmsErr;
	}

	public String getEvErr() {
		return evErr;
	}

	public void setEvErr(String evErr) {
		this.evErr = evErr;
	}
	
	
	

}
