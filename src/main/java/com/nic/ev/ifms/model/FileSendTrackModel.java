package com.nic.ev.ifms.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "evt_files_track")
public class FileSendTrackModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(name = "op_dt", unique = true, nullable = false)
	public Date fileSendDate;

	@Column(name = "count")
//	@Size(max=3)
	public int noOfFilesSend;

	@Column(name = "files",length=10000) 
	public String fileNames;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFileSendDate() {
		return fileSendDate;
	}

	public void setFileSendDate(Date fileSendDate) {
		this.fileSendDate = fileSendDate;
	}

	public int getNoOfFilesSend() {
		return noOfFilesSend;
	}

	public void setNoOfFilesSend(int noOfFilesSend) {
		this.noOfFilesSend = noOfFilesSend;
	}

	public String getFileNames() {
		return fileNames;
	}

	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}

}
