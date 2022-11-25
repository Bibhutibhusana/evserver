package com.nic.ev.livedbcontroller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.DatabaseConfig.LiveDbConnect;
import com.nic.ev.dto.VahanDetailsDTO;
import com.nic.ev.exception.BusinessException;
import com.nic.ev.livedbmodel.VehicleDetailsModel;

@RestController
@CrossOrigin
@RequestMapping("/")
public class VehicleDetailsController {
	
	@Autowired
	private ModelMapper modelMapper;
	private ResultSet rs;

	public List<VehicleDetailsModel> vehicleDetailsModel;
	boolean result;
	LiveDbConnect liveDb = new LiveDbConnect();
	private Long sale_amnt;

	@PostMapping("/vehicleDetails")
	private ResponseEntity<VahanDetailsDTO> getVehicleDetails(@RequestBody String regn)  throws BusinessException, SQLException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String queryToGetVehicleDetails = "select o.regn_no,o.owner_name, o.chasi_no, o.eng_no, vm.descr as vmaker, "
				+ " model_name,vc.descr as vclass, c_add1,regn_dt,vf.descr as vfuel,o.off_cd,i.mobile_no, o.sale_amt, o.purchase_dt "
				+ " from vw_vahan.vt_owner o "
				+ " left join vw_vahan.vm_vh_class vc on o.vh_class = vc.vh_class "
				+ " left join vw_vahan.vm_fuel vf on o.fuel = vf.code "
				+ " left join vw_vahan.vm_maker vm on o.maker = vm.code "
				+ " left join vw_vahan.vt_owner_identification i on o.regn_no = i.regn_no "
				+  "where o.regn_no = ?";
		VehicleDetailsModel vdm = new VehicleDetailsModel();
		try {
			conn = liveDb.getConnection();
			pstmt=conn.prepareStatement(queryToGetVehicleDetails);
			pstmt.setString(1, regn);
			rs = pstmt.executeQuery();
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
			}else {

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
		VahanDetailsDTO VehicleDetails=modelMapper.map(vdm, VahanDetailsDTO.class);
		return ResponseEntity.ok().body(VehicleDetails);


	}

	@PostMapping("/getSaleAmout")
	private Long getSaleAmount(@RequestBody String regn) throws BusinessException, SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String q = "SELECT sale_amt FROM vw_vahan.vt_owner where regn_no=?";

		try {
			conn = liveDb.getConnection();
			pstmt=conn.prepareStatement(q);
			pstmt.setString(1, regn);
			rs = pstmt.executeQuery(q);
			if(rs.next()) {
				sale_amnt = rs.getLong("sale_amt");
				}
		    }
			catch(SQLException e) {
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


		return sale_amnt;
	}
}