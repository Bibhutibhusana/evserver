package com.nic.ev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nic.ev.ifms.model.webservice.ByTransfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class BankNames {
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;

}
