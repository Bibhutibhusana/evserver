package com.nic.ev.livedbcontroller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.DatabaseConfig.LiveDbConnect;
import com.nic.ev.exception.BusinessException;
import com.nic.ev.livedbmodel.DealerListModel;

@RestController
@CrossOrigin
@RequestMapping("/")
public class DealerListController {

	public List<DealerListModel> dealerList;
	private ResultSet rs;
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	LiveDbConnect liveDb = new LiveDbConnect();

	@GetMapping(value="/dealerList",produces = MediaType.APPLICATION_JSON_VALUE)
	private List<DealerListModel> getDealerList()  throws BusinessException, SQLException {

		String queryToGetDealerList = "select distinct m.dealer_Cd as dealerCd, m.dealer_name as dealerName from vw_vahan.vt_owner o "
				+ "left join vw_vahan.vm_dealer_mast m "
				+ "on o.dealer_Cd = m.dealer_Cd "
				+ "where fuel = 4 and o.state_cd = 'OR' and m.dealer_Cd is not null "
				+ "order by m.dealer_name";
		
		dealerList = new ArrayList<DealerListModel>();

		try {
			conn=liveDb.getConnection();
			pstmt = conn.prepareStatement(queryToGetDealerList);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				DealerListModel dlm = new DealerListModel();
				dlm.setDealerCd(rs.getString("dealerCd"));
				dlm.setDealerName(rs.getString("dealerName"));
				dealerList.add(dlm);
			}			

		}catch(SQLException e) {
			throw new BusinessException("Something Went Wrong in Data Access Layer" + e.getMessage());
		}finally {
            try {
            	if (pstmt != null){pstmt.close();}
    			if (conn != null){conn.close();}
    			if (rs != null){rs.close();}
            } catch (BusinessException ex) { 
            	throw new BusinessException("Something Went Wrong in Data Access Layer" + ex.getMessage());
            }
        }

		return dealerList;

	}
}