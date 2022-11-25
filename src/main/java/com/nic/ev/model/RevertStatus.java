package com.nic.ev.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "evt_revert_status")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RevertStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private int id;

	private String regnNo;

	private String applNo;


	private String verification;

	private String approval;

	private Date opDt;

	private String reason;

	private String verifyUserId;

	private String approveUserId;

	private Date insertDt;

}