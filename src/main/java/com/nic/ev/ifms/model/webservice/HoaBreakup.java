package com.nic.ev.ifms.model.webservice;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "hoaBreakup")
public class HoaBreakup {
	private String hoa;
	private BigDecimal amount;

}
