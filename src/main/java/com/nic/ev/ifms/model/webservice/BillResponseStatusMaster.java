package com.nic.ev.ifms.model.webservice;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.nic.ev.ifms.model.beneficiary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BillResponseStatusMaster {
	@Id
	private Long id;
	private String statusCode;
	private String statusDesc;

}
