package com.nic.ev.controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.repo.VehicleDetailsRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class VerifyController {
	
    @Autowired VehicleDetailsRepo vehicleDetailsRepo;
    
	private Statement st;
	private ResultSet rs;
	
	
	@PostMapping(value="/verify",produces = MediaType.APPLICATION_JSON_VALUE)
	private List<Map<String, Object>> getToVerify(@Valid @RequestBody String off_cd){
		
//		System.out.println((String) "verify");
		System.out.println(off_cd);
		
		return vehicleDetailsRepo.getToVerify(off_cd);
	}
	
	@PostMapping(value="/approve",produces = MediaType.APPLICATION_JSON_VALUE)
	private List<Map<String, Object>> getToApprove(@Valid @RequestBody String off_cd){
		
		
		return vehicleDetailsRepo.getToApprove(off_cd);
	}
	@PostMapping(value="/approveListFinal",produces = MediaType.APPLICATION_JSON_VALUE)
	private List<Map<String, Object>> getFinalApproveList(@Valid @RequestBody Map<String, String> obj) throws ParseException{
		
		
		
		java.util.Date fd = new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("date"));
//		java.util.Date td = new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("toDate"));
//		java.sql.Date dt = new java.sql.Date(fd.getTime());
		java.sql.Date dt = new java.sql.Date(fd.getTime()+(1*24*60*60*1000));
		
		String off_cd = obj.get("off_cd");
		System.out.println(fd);
		
		System.out.println(dt);
		
		
		return vehicleDetailsRepo.getFinalApproveList(dt,off_cd);
	}
	
	@PostMapping(value="/disbursementListFinal",produces = MediaType.APPLICATION_JSON_VALUE)
	private List<Map<String, Object>> getFinalDisbursementList(@Valid @RequestBody Map<String, String> obj) throws ParseException{
		
		
		
		java.util.Date fd = new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("date"));
//		java.util.Date td = new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("toDate"));
//		java.sql.Date dt = new java.sql.Date(fd.getTime());
		java.sql.Date dt = new java.sql.Date(fd.getTime()+(1*24*60*60*1000));
		
		String off_cd = obj.get("off_cd");
		System.out.println(fd);
		
		System.out.println(dt);
		
		
		return vehicleDetailsRepo.getFinalDisbursementList(dt,off_cd); 
	}
	

}
