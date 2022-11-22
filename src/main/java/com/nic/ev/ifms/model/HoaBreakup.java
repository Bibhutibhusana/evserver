package com.nic.ev.ifms.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name = "hoaBreakup")
public class HoaBreakup {
	private String hoa;
	private BigDecimal amount;
	
	public String getHoa() {
		return hoa;
	}
	public void setHoa(String hoa) {
		this.hoa = hoa;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
