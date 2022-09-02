package com.nic.ev.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="evh_details")
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

	public String getRegnNo() {
		return regnNo;
	}

	public void setRegnNo(String regnNo) {
		this.regnNo = regnNo;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getChasiNo() {
		return chasiNo;
	}

	public void setChasiNo(String chasiNo) {
		this.chasiNo = chasiNo;
	}

	public String getEngNo() {
		return engNo;
	}

	public void setEngNo(String engNo) {
		this.engNo = engNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVmake() {
		return vmake;
	}

	public void setVmake(String vmake) {
		this.vmake = vmake;
	}

	public String getVmodel() {
		return vmodel;
	}

	public void setVmodel(String vmodel) {
		this.vmodel = vmodel;
	}

	public String getvClass() {
		return vClass;
	}

	public void setvClass(String vClass) {
		this.vClass = vClass;
	}

	public Date getRegnDt() {
		return regnDt;
	}

	public void setRegnDt(Date regnDt) {
		this.regnDt = regnDt;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public Date getOp_dt() {
		return op_dt;
	}

	public void setOp_dt(Date op_dt) {
		this.op_dt = op_dt;
	}

	public String getOff_cd() {
		return off_cd;
	}

	public void setOff_cd(String off_cd) {
		this.off_cd = off_cd;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public Date getInsertDt() {
		return insertDt;
	}

	public void setInsertDt(Date insertDt) {
		this.insertDt = insertDt;
	}
	
	

}
