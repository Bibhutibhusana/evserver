package com.nic.ev.ifms.model.webservice;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.nic.ev.ifms.model.webservice.dto.BillPaymentFileDetails;

import lombok.Data;

@JacksonXmlRootElement(localName = "bulkPayment")
@XmlRootElement(name="bulkPayment")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class XMLBillPaymentFile implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	public BillPaymentFileDetails fileDetail;
    public BillDetail billDetail;
    
    @JsonInclude(Include.NON_NULL)
    public BillDetails billDetails;
    
    @JacksonXmlElementWrapper(localName="hoaBreakups")
    public List<HoaBreakup> hoaBreakup;
    
    
    @JacksonXmlElementWrapper(localName="byTransfers")
    public List<ByTransfer> byTransfer;
    
    @JsonInclude(Include.NON_NULL)
    @JacksonXmlElementWrapper(localName="billAbstracts")
    public List<Object> billAbstract;
    
    
    @JacksonXmlElementWrapper(localName="beneficiaries")
    public List<Beneficiary> beneficiary;
	

}
