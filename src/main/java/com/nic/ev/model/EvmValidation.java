package com.nic.ev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	



	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public int getMinTopSpeed() {
		return minTopSpeed;
	}

	public void setMinTopSpeed(int minTopSpeed) {
		this.minTopSpeed = minTopSpeed;
	}

	

	public float getMinAcceleration() {
		return minAcceleration;
	}

	public void setMinAcceleration(float minAcceleration) {
		this.minAcceleration = minAcceleration;
	}

	public int getMaxEnergyConsumption() {
		return maxEnergyConsumption;
	}

	public void setMaxEnergyConsumption(int maxEnergyConsumption) {
		this.maxEnergyConsumption = maxEnergyConsumption;
	}

	public int getWarranty() {
		return warranty;
	}

	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}
	
	
	

}
