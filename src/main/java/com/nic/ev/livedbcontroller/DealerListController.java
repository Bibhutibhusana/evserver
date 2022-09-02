package com.nic.ev.livedbcontroller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.DatabaseConfig.LiveDbConnect;
import com.nic.ev.livedbmodel.DealerListModel;

@RestController
@CrossOrigin
@RequestMapping("/")
public class DealerListController {
	
	public List<DealerListModel> dealerList;
	
	private Statement st;
	private ResultSet rs;
	
	LiveDbConnect liveDb = new LiveDbConnect();
	
	@GetMapping("/dealerList")
	private List<DealerListModel> getDealerList(){
		
		String queryToGetDealerList = "select distinct m.dealer_Cd as dealerCd, m.dealer_name as dealerName from vw_vahan.vt_owner o "
				+ "left join vw_vahan.vm_dealer_mast m "
				+ "on o.dealer_Cd = m.dealer_Cd "
				+ "where fuel = 4 and o.state_cd = 'OR' and m.dealer_Cd is not null "
				+ "order by m.dealer_name";
		
		dealerList = new ArrayList<DealerListModel>();
		
		try {
			st = liveDb.getConnection().createStatement();
			rs = st.executeQuery(queryToGetDealerList);
			
			while(rs.next()) {
				DealerListModel dlm = new DealerListModel();
				dlm.setDealerCd(rs.getString("dealerCd"));
				dlm.setDealerName(rs.getString("dealerName"));
				dealerList.add(dlm);
			}
			
		    st.close();
		    rs.close();
		    liveDb.getConnection().close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return dealerList;
		
	}

}
