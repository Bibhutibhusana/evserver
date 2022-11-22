package com.nic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class OemDetailsDTO {
	private String modelName;
	private int minTopSpeed;
	private float minAcceleration;
	private int maxEnergyConsumption;
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
