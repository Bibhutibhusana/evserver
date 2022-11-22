package com.nic.ev.ifms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="auth_table")
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getAppKey() {
		return appKey;
	}
	public void setAppKey(byte[] appKey) {
		this.appKey = appKey;
	}
	public String getEncryptedAppKey() {
		return encryptedAppKey;
	}
	public void setEncryptedAppKey(String encryptedAppKey) {
		this.encryptedAppKey = encryptedAppKey;
	}
	public String getSek() {
		return sek;
	}
	public void setSek(String sek) {
		this.sek = sek;
	}
	public byte[] getDecryptedSek() {
		return decryptedSek;
	}
	public void setDecryptedSek(byte[] decryptedSek) {
		this.decryptedSek = decryptedSek;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getRek() {
		return rek;
	}
	public void setRek(String rek) {
		this.rek = rek;
	}
	public byte[] getDecryptedRek() {
		return decryptedRek;
	}
	public void setDecryptedRek(byte[] decryptedRek) {
		this.decryptedRek = decryptedRek;
	}
	public Date getOpDate() {
		return opDate;
	}
	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}
}
