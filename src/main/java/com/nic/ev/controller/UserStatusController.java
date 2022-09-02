package com.nic.ev.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.model.UserStatus;
import com.nic.ev.repo.UserStatusRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class UserStatusController {
	@Autowired UserStatusRepo userStatusRepo;
	
	@Autowired MessageController mc;

	Date d = new Date();
	
	java.sql.Date today = new java.sql.Date(d.getTime());
	@PostMapping("/insertUserStatus")
	private UserStatus insertUserStatus(@Valid @RequestBody UserStatus userStatus) {
		
		return userStatusRepo.save(userStatus);
	}
	
	@GetMapping("/findByRegn/{regn}")
	private Optional<UserStatus> findByRegnNo( @PathVariable(value = "regn") String regn) {
		return userStatusRepo.findById(regn);
	}
	
	@DeleteMapping("/deleteByRegn/{regn}")
	 Map<String, Boolean> deleteByRegnNo( @PathVariable(value = "regn") String regn) {
		userStatusRepo.deleteById(regn);
		
		 Map<String, Boolean> rs = new HashMap<>();
		 
		 rs.put("deleted",true);
		  
		 return rs;
		
	}
	
	@PostMapping("/updateStatusVerification")
	private UserStatus updateVerification(@Valid @RequestBody Map<String, String> obj) {
		
		Optional<UserStatus> us = userStatusRepo.findByRegnNo(obj.get("regn"));
		
		System.out.println("verify date:"+today);
		
		UserStatus us1 = us.get();
		us1.setVerification(obj.get("verify"));
		us1.setVerifyDt(today);
		us1.setVerifyUserId(obj.get("user"));
		us1.setReason(obj.get("reason"));
		
		
		return userStatusRepo.save(us1);
	}
	@PostMapping("/updateStatusApproval")
	private UserStatus updateApproval(@Valid @RequestBody  Map<String, String> obj) {
		
		Optional<UserStatus> us = userStatusRepo.findByRegnNo(obj.get("regn"));
		
		UserStatus us1 = us.get();
		us1.setApproval(obj.get("verify"));
		us1.setApproveDt(today);
		us1.setApproveUserId(obj.get("user"));
		us1.setReason(obj.get("reason"));
		us1.setCheque_no(obj.get("cheqNo"));
		us1.setSub_amnt(obj.get("subAmnt"));
		
		if(us1.getReason() != null) {
			us1.setVerification(null);
			us1.setVerifyDt(null);
			us1.setCheque_no(null);
			us1.setSub_amnt(null);
			us1.setVerifyUserId(null);
		}
		
		return userStatusRepo.save(us1);
	}
	@PostMapping("/updateDownloadStatus")
	private UserStatus updateDownloadStatus(@Valid @RequestBody  String regn) {
		
		Optional<UserStatus> us = userStatusRepo.findByRegnNo(regn);
		
		UserStatus us1 = us.get();
		us1.setDownloadStatus("y");
		
		System.out.println("At download");
		
		return userStatusRepo.save(us1);
	}
	
	
    @PostMapping(value="/applicationStatus",produces = MediaType.APPLICATION_JSON_VALUE)
	private List<Map<String, Object>> getApplicationStatus(@Valid @RequestBody String regn_no){
    	
    	Map<String, Object> so = new HashMap<>();
    	Map<String, Object> so1 = new HashMap<>();
    	
    	List<Map<String, Object>> list = new ArrayList<>();
		
    	so = userStatusRepo.getApplicationStatus(regn_no);
    	
    	String status;
    	
    	if(so.get("verification") == null) {
    		status = "Not Verified";
    	}else {
    		if(so.get("verification").equals("rev")) {
    			status = "Reverted in Verification";
    		}else {
    			if(so.get("approval") == null) {
    				status = "Verified";
    				
    			}else {
    				System.out.println(so.get("approval"));
    				if(so.get("approval").equals("rev")) {
    	    			status = "Reverted in Approval";
    	    		}else {
    	    			if(so.get("payment") == null) {
    	    				status = "Approved";
    	    			}else {
    	    				status = "Payment Done";
    	    			}
    	    		}
    			}
    		}
    	}
    	
//    	so1.putAll(so);
    	so1.put("appl_no", so.get("appl_no"));
    	so1.put("regn_no", so.get("regn_no"));
    	so1.put("owner_name", so.get("owner_name"));
    	so1.put("acc_no", so.get("acc_no"));
    	so1.put("reason", so.get("reason"));
    	so1.put("status", status);
    	
    	
    	list.add(so1);
		
		return list;
	}
	
	@PostMapping("/updatePaymentStatus")
	private UserStatus updatePaymentStatus(@Valid @RequestBody  Map<String, String> obj) {
		
		Optional<UserStatus> us = userStatusRepo.findByApplNo(obj.get("applNo"));
		UserStatus us1 = us.get();
		
		us1.setPayment("y");		
		return userStatusRepo.save(us1);
		
	}
	
	@PostMapping(value="/disbursementStatusUpdate",produces = MediaType.APPLICATION_JSON_VALUE)
	private Map<String, Object> updateDisbursementStatus(@Valid @RequestBody List<String> list){
		
		Map<String, Object> mp = new HashMap<>();
		
		
		
		for(String element: list) {
			Optional<UserStatus> us = userStatusRepo.findByRegnNo(element);
			
			System.out.println(element);
			 mc.sendDisbursedMsg(element);
			UserStatus us1 = us.get();
			
			us1.setDisbursement("y");		
			userStatusRepo.save(us1);
			mp.put("Updated", String.valueOf("success"));
		}
		
		
		return mp;
	}
	
	
	
}
