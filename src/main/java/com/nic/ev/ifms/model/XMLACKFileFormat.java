package com.nic.ev.ifms.model;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

//@JacksonXmlRootElement(localName = "AckFile")
//, namespace="http://global.aon.bz/schema/cbs/archive/errorresource/0"

@XmlRootElement(name="AckFile")
public class XMLACKFileFormat {
	
	private XMLFileDetails fileDetails;
	
	private List<beneficiary> beneficiaries;
	
	@XmlElement(name = "fileDetails")
	public XMLFileDetails getFileDetails() {
		return fileDetails;
	}
	public void setFileDetails(XMLFileDetails fileDetails) {
		this.fileDetails = fileDetails;
	}

	 @XmlElementWrapper(name = "beneficiaries")
	 @XmlElement(name = "beneficiary")
	@JsonInclude(Include.NON_NULL)
	public List<beneficiary> getBeneficiaries() {
		return beneficiaries;
	}
	public void setBeneficiaries(List<beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}
}
