package com.nic.ev.model;

import javax.persistence.Column;
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
@Table(name="evt_oem_model_validation")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EvmValidation {
	
	@Id
	@Column
	private String modelName;
	
	@Column
	private int minTopSpeed;
	
	@Column
	private float minAcceleration;
	
	@Column
	private int maxEnergyConsumption;
	
	@Column
	private int warranty;
	

}
