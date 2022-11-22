package com.nic.ev.ifms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.ifms.model.IFMSErrorModel;
import com.nic.ev.ifms.repo.IFMSErrorResponseRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class IFMSReasonStatusController {
	
	@Autowired
	private IFMSErrorResponseRepo ifmsErrorRepo;
	
	@PostMapping("findbycode")
	public ResponseEntity<IFMSErrorModel> findByErrorCode(@Valid @RequestBody String err_cd) {
		System.out.println(err_cd);
		IFMSErrorModel response =  ifmsErrorRepo.findByErrCode(err_cd);
		return ResponseEntity.ok(response);
	}

}
