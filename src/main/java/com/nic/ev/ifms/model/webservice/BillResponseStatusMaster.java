package com.nic.ev.ifms.model.webservice;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class BillResponseStatusMaster {
	@Id
	private Long id;
	private String statusCode;
	private String statusDesc;

}
