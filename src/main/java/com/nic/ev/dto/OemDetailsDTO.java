package com.nic.ev.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class OemDetailsDTO {
	private String modelName;
	private int minTopSpeed;
	private float minAcceleration;
	private int maxEnergyConsumption;
	private int warranty;
}