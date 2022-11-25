package com.nic.ev.ifms.model.webservice;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="auth_table")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationTokenAndSEKTable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(name="appkey")
	private byte[] appKey;
	@Column(name="encrypted_appkey")
	private String encryptedAppKey;
	@Column(name="sek")
	private String sek;
	@Column(name="decrypted_sek")
	private byte[] decryptedSek;
	@Column(name="authtoken")
	private String authToken;
	@Column(name="clientid")
	private String clientId;
	@Column(name="clientsecret")
	private String clientSecret;
	@Column(name="rek")
	private String rek;
	@Column(name="decrypted_rek")
	private byte[] decryptedRek;
	@Column(name="opdate") 
	private Date opDate = new Date();


}
