package com.nic.ev.ifms.model;


import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "avFile")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="avFile", namespace="http://global.aon.bz/schema/cbs/archive/errorresource/0")
public class XMLAVFileFormat implements Serializable {

    private static final long serialVersionUID = 22L;
    
    public XMLFileDetails fileDetails;
    
    @JacksonXmlElementWrapper(localName="beneficiaries")
    public List<XMLBankDetailsModel> beneficiary;
	public XMLFileDetails getFileDetails() {
		return fileDetails;
	}
	public void setFileDetails(XMLFileDetails fileDetails) {
		this.fileDetails = fileDetails;
	}
	public List<XMLBankDetailsModel> getBeneficiary() {
		return beneficiary;
	}
	public void setBeneficiary(List<XMLBankDetailsModel> bankDetails) {
		this.beneficiary = bankDetails;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public XMLAVFileFormat(XMLFileDetails fileDetails, List<XMLBankDetailsModel> bankDetails) {
		super();
		this.fileDetails = fileDetails;
		this.beneficiary = bankDetails;
	}
    public XMLAVFileFormat() {
    	
    }
    
    
    
//
//    @JacksonXmlProperty(localName = "BankDetails")
//    @JacksonXmlElementWrapper(useWrapping = false)
//    private List<Object> beneficiary = new ArrayList<>();
//
//    public List<Object> getBankDetails() {
//        return beneficiary;
//    }
//
//    public void setBankDetails(List<Object> bankDetails1) {
//        this.bankDetails = bankDetails1;
//    }
    
    
   
}