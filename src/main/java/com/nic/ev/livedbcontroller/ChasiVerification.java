package com.nic.ev.livedbcontroller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.DatabaseConfig.LiveDbConnect;
import com.nic.ev.exception.BusinessException;

@RestController
@CrossOrigin
@RequestMapping("/")
public class ChasiVerification {
	
	private ResultSet rs;
	
	String result;
	LiveDbConnect liveDb = new LiveDbConnect();
	
	@PostMapping("/chasiVerification")
	private String getChasiVerified(@RequestBody Map<String, String> vehicle) throws BusinessException, SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String chasi = vehicle.get("chasi");
		String regn = vehicle.get("regn");
		
		
		String q = "select o.regn_no,i.mobile_no from vw_vahan.vt_owner o "
				+ " left join vw_vahan.vt_owner_identification i on o.regn_no = i.regn_no "
				+ " where o.regn_no=? and o.chasi_no like CONCAT( '%',?,'%')";
		
		try {
			conn=liveDb.getConnection();
			pstmt = conn.prepareStatement(q);
			pstmt.setString(1, regn);
			pstmt.setString(2, chasi);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString("mobile_no");
			}else {
				result = null;
			}
		}catch(SQLException e) {
			throw new BusinessException("Something Went Wrong in Data Access Layer" + e.getMessage());
		}finally
		{
			try
			{
			if (pstmt != null){pstmt.close();}
			if (conn != null){conn.close();}
			if (rs != null){rs.close();}
			}
			catch(BusinessException e) {
				throw new BusinessException("Something Went Wrong in Data Access Layer" + e.getMessage());
			}
			
		}
		
		return result;
	}

}
