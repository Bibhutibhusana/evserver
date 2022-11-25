package com.nic.ev.model;

import java.sql.Date;

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
@Table(name = "evh_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleDetailsHistoryModel {

	@Id
	private String regnNo;

	private String ownerName;

	private String chasiNo;

	private String engNo;

	private String address;

	private String vmake;

	private String vmodel;

	private String vClass;

	private Date regnDt;

	private String fuel;

	private Date op_dt;

	private String off_cd;

	private String mobNo;

	private Date insertDt;

}
