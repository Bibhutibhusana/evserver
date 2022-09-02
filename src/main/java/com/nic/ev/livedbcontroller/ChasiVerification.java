package com.nic.ev.livedbcontroller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.DatabaseConfig.LiveDbConnect;

@RestController
@CrossOrigin
@RequestMapping("/")
public class ChasiVerification {
	
	private Statement st;
	private ResultSet rs;
	
	String result;
	LiveDbConnect liveDb = new LiveDbConnect();
	
	
	@PostMapping("/chasiVerification")
	private String getChasiVerified(@RequestBody Map<String, String> vehicle){
		String chasi = vehicle.get("chasi");
		String regn = vehicle.get("regn");
		
		
		String q = "select o.regn_no,i.mobile_no from vw_vahan.vt_owner o "
				+ " left join vw_vahan.vt_owner_identification i on o.regn_no = i.regn_no "
				+ " where o.regn_no='"+regn+"' and o.chasi_no like '%"+chasi+"'";
		
		try {
			
			st = liveDb.getConnection().createStatement();
			rs = st.executeQuery(q);
			if(rs.next()) {
				result = rs.getString("mobile_no");
			}else {
				result = null;
			}
			
			st.close();
			rs.close();
			liveDb.getConnection().close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
