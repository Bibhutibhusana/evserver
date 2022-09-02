package com.nic.ev.livedbcontroller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.DatabaseConfig.LiveDbConnect;
import com.nic.ev.livedbmodel.VehicleDetailsModel;

@RestController
@CrossOrigin
@RequestMapping("/")
public class VehicleDetailsController {
	
	private Statement st;
	private ResultSet rs;
	
	public List<VehicleDetailsModel> vehicleDetailsModel;
	
	boolean result;
	LiveDbConnect liveDb = new LiveDbConnect();
	private Long sale_amnt;
	
	@PostMapping("/vehicleDetails")
	private VehicleDetailsModel getVehicleDetails(@RequestBody String regn){
		
		String queryToGetVehicleDetails = "select o.regn_no,o.owner_name, o.chasi_no, o.eng_no, vm.descr as vmaker, "
				+ " model_name,vc.descr as vclass, c_add1,regn_dt,vf.descr as vfuel,o.off_cd,i.mobile_no, o.sale_amt, o.purchase_dt "
				+ " from vw_vahan.vt_owner o "
				+ " left join vw_vahan.vm_vh_class vc on o.vh_class = vc.vh_class "
				+ " left join vw_vahan.vm_fuel vf on o.fuel = vf.code "
				+ " left join vw_vahan.vm_maker vm on o.maker = vm.code "
				+ " left join vw_vahan.vt_owner_identification i on o.regn_no = i.regn_no "
				+  "where o.regn_no = '"+regn+"'";
		VehicleDetailsModel vdm = new VehicleDetailsModel();
		
//		vehicleDetailsModel = new ArrayList<VehicleDetailsModel>();
		
		try {
			st = liveDb.getConnection().createStatement();
			rs = st.executeQuery(queryToGetVehicleDetails);
			if(rs.next()) {
				
				vdm.setRegnNo(rs.getString("regn_no"));
				vdm.setOwnerName(rs.getString("owner_name"));
				vdm.setChasiNo(rs.getString("chasi_no"));
				vdm.setEngNo(rs.getString("eng_no"));
				vdm.setVmake(rs.getString("vmaker"));
				vdm.setVmodel(rs.getString("model_name"));
				vdm.setvClass(rs.getString("vclass"));
				vdm.setAddress(rs.getString("c_add1"));
				vdm.setRegnDt(rs.getDate("regn_dt"));
				vdm.setFuel(rs.getString("vfuel"));
				vdm.setOff_cd(rs.getString("off_cd"));
				vdm.setMobNo(rs.getString("mobile_no"));
				vdm.setSale_amnt(rs.getLong("sale_amt"));
				vdm.setPurchaseDt(rs.getDate("purchase_dt"));
				
//				vehicleDetailsModel.add(vdm);
			}else {
				
			}
			
			
			st.close();
			rs.close();
			liveDb.getConnection().close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return vdm;
		
		
	}
	
	@PostMapping("/getSaleAmout")
	private Long getSaleAmount(@RequestBody String regn) {
		
		
		String q = "SELECT sale_amt FROM vw_vahan.vt_owner where regn_no='"+regn+"'";
		
		try {
			st = liveDb.getConnection().createStatement();
			rs = st.executeQuery(q);
			if(rs.next()) {
				sale_amnt = rs.getLong("sale_amt");

			}
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		
		return sale_amnt;
	}

}
