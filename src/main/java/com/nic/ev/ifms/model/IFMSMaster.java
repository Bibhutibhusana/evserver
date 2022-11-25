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
@Table(name="evt_ifms_master")
public class IFMSMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id = 0l;
	
	private String bank;
	
	private String ifsc;
	private String branch;
	private String address;
	private String city1;
	private String city2;
	private String state;
	private String stateCd;
	private String phone;
}
