package com.nic.ev.ifms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
