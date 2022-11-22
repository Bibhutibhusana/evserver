package com.nic.ev.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.dto.ApplicationStatusHistoryDTO;
import com.nic.ev.exception.BusinessException;
import com.nic.ev.model.UserStatusHistory;
import com.nic.ev.repo.UserStatusHistoryRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class UserStatusHistoryController {
	
	@Autowired UserStatusHistoryRepo userStatusHistoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/insertUserStatusHistory")
	private ResponseEntity<ApplicationStatusHistoryDTO> insertUserStatus(@Valid @RequestBody ApplicationStatusHistoryDTO ApplicationStatusHistory) throws BusinessException  {
		// convert DTO to entity
		UserStatusHistory userStatus=modelMapper.map(ApplicationStatusHistory, UserStatusHistory.class);
		if (userStatus.getRegnNo().isEmpty() || userStatus.getRegnNo().length() == 0) {
			throw new BusinessException("Please provide Registration No.It is Blank");
		}
		if (userStatus.getApplNo().isEmpty() || userStatus.getApplNo().length() == 0) {
			throw new BusinessException("Please provide Application No.It is Blank");
		}
		try {
		userStatusHistoryRepo.save(userStatus);	 
		// convert entity to DTO
		ApplicationStatusHistoryDTO ApplicationStatusHistoryResponse = modelMapper.map(userStatus, ApplicationStatusHistoryDTO.class);		
		return new ResponseEntity<ApplicationStatusHistoryDTO>(ApplicationStatusHistoryResponse, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("Given Status History Details are null" + e.getMessage());
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
		}

	
	  @PostMapping(value="/api/applicationStatusFromHistory",produces = MediaType.APPLICATION_JSON_VALUE)
		private ResponseEntity<List<Map<String, Object>>> getApplicationStatus(@Valid @RequestBody String regn_no) throws ParseException, BusinessException{
	    	try {
	    	Map<String, Object> so = new HashMap<>();
	    	Map<String, Object> so1 = new HashMap<>();
	    	
	    	List<Map<String, Object>> list = new ArrayList<>();
			
	    	so = userStatusHistoryRepo.getApplicationStatus(regn_no);
	    	
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
	    	
//	    	so1.putAll(so);
	    	so1.put("appl_no", so.get("appl_no"));
	    	so1.put("regn_no", so.get("regn_no"));
	    	so1.put("owner_name", so.get("owner_name"));
	    	so1.put("acc_no", so.get("acc_no"));
	    	so1.put("reason", so.get("reason"));
	    	so1.put("status", status);
	    	
	    	list.add(so1);
			return new ResponseEntity<List<Map<String, Object>>>(list, HttpStatus.OK);
	    	}
	    	catch (BusinessException e) {
	    		throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
	    	}
		}
	
}
