package com.nic.ev.ifms.model;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data	
@XmlRootElement(name = "byTransfer")
public class ByTransfer {
	private String btHoa;
	
	private String bgTreasury;
	
	private Double btAmount;
	
	public String getBtHoa() {
		return btHoa;
	}
	public void setBtHoa(String btHoa) {
		this.btHoa = btHoa;
	}
	
	public String getBgTreasury() {
		return bgTreasury;
	}
	public void setBgTreasury(String bgTreasury) {
		this.bgTreasury = bgTreasury;
	}
	
	public Double getBtAmount() {
		return btAmount;
	}
	public void setBtAmount(Double btAmount) {
		this.btAmount = btAmount;
	}
}
