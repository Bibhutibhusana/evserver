package com.nic.ev.ifms.model.webservice;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "byTransfer")
public class ByTransfer {
	private String btHoa;
	private String bgTreasury;
	private Double btAmount;
}
