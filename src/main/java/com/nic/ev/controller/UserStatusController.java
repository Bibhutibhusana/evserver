package com.nic.ev.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.dto.ApplicationStatusDTO;
import com.nic.dto.BankInfoDTO;
import com.nic.dto.RevertStatusDetailsDTO;
import com.nic.ev.exception.BusinessException;
import com.nic.ev.exception.ResourceNotFoundException;
import com.nic.ev.model.BankDetails;
import com.nic.ev.model.RevertStatus;
import com.nic.ev.model.UserStatus;
import com.nic.ev.model.VehicleDetailsHistoryModel;
import com.nic.ev.repo.BankDetailsRepo;
import com.nic.ev.repo.RevertStatusRepo;
import com.nic.ev.repo.UserStatusRepo;

@SuppressWarnings("unused")
@RestController
@CrossOrigin
@RequestMapping("/")
public class UserStatusController {
	@Autowired
	UserStatusRepo userStatusRepo;
	@Autowired
	BankDetailsRepo bdRepo;
	@Autowired
	MessageController mc;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired 
	RevertStatusRepo rsp;

	Date d = new Date();

	java.sql.Date today = new java.sql.Date(d.getTime());

	@PostMapping("/insertUserStatus")
	private ResponseEntity<ApplicationStatusDTO> insertUserStatus(@Valid @RequestBody ApplicationStatusDTO applStatusdto) throws BusinessException {
		// convert DTO to entity
		UserStatus userStatus=modelMapper.map(applStatusdto, UserStatus.class);
		if (userStatus.getRegnNo().isEmpty() || userStatus.getRegnNo().length() == 0) {
			throw new BusinessException("Please provide Registration No.It is Blank");
		}
		if (userStatus.getApplNo().isEmpty() || userStatus.getApplNo().length() == 0) {
			throw new BusinessException("Please provide Application No.It is Blank");
		}
		try {
			userStatus= userStatusRepo.save(userStatus);
			// convert entity to DTO
			ApplicationStatusDTO userstatusResponse = modelMapper.map(userStatus, ApplicationStatusDTO.class);
			return new ResponseEntity<ApplicationStatusDTO>(userstatusResponse, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("Given Status Details are null" + e.getMessage());
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

	@GetMapping("/findByRegn/{regn}") 
	private ResponseEntity<ApplicationStatusDTO> findByRegnNo(@PathVariable(value = "regn") String regn) throws ResourceNotFoundException, BusinessException {
		try {
			UserStatus userstatus= userStatusRepo.findById(regn).orElseThrow(() -> new ResourceNotFoundException("UserDetails not found for this id:" + regn));
			ApplicationStatusDTO userstatusResponse=modelMapper.map(userstatus, ApplicationStatusDTO.class);
			return ResponseEntity.ok().body(userstatusResponse);
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

	@DeleteMapping("/deleteByRegn/{regn}")
	ResponseEntity<Map<String, Boolean>> deleteByRegnNo(@PathVariable(value = "regn") String regn) throws ResourceNotFoundException, BusinessException {
		try {
			userStatusRepo.findById(regn)
			.orElseThrow(() -> new ResourceNotFoundException("UserDetails not found for this id:" + regn));

		userStatusRepo.deleteById(regn);

		Map<String, Boolean> rs = new HashMap<>();

		rs.put("deleted", true);

		return ResponseEntity.ok().body(rs);
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}

	}

	@PostMapping("/updateStatusVerification")
	private ResponseEntity<ApplicationStatusDTO> updateVerification(@Valid @RequestBody Map<String, String> obj) throws ParseException, ResourceNotFoundException, BusinessException {
		try {
		String RegnNo=obj.get("regn");
		UserStatus us = userStatusRepo.findByRegnNo(RegnNo).orElseThrow(() -> new ResourceNotFoundException("UserDetails not found for this id:" + RegnNo));

		java.util.Date fd = new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("date"));

		java.sql.Date dt = new java.sql.Date(fd.getTime());

		UserStatus us1 = us;
		us1.setVerification(obj.get("verify"));
		us1.setVerifyDt(dt);
		us1.setVerifyUserId(obj.get("user"));
		us1.setReason(obj.get("reason"));

		BankDetails bd = bdRepo.findByRegnNo(obj.get("regn")).orElseThrow(() -> new ResourceNotFoundException("BankDetails not found for this id:" + RegnNo));;
		BankDetails d = bd;
		
		if(obj.get("reason") != null) {
			d.setPassbookImg(null);
		}

		bdRepo.save(d);

		userStatusRepo.save(us1);
		// entity to DTO
		ApplicationStatusDTO ApplicationStatusResponse = modelMapper.map(us1, ApplicationStatusDTO.class);
		return ResponseEntity.ok().body(ApplicationStatusResponse);
		}catch (BusinessException e) {
				throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
			}

	}

	@PostMapping("/updateStatusApproval")
	private ResponseEntity<ApplicationStatusDTO> updateApproval(@Valid @RequestBody Map<String, String> obj) throws ParseException, ResourceNotFoundException, BusinessException {
		try {
		String RegnNo=obj.get("regn");
		UserStatus us = userStatusRepo.findByRegnNo(obj.get("regn")).orElseThrow(() -> new ResourceNotFoundException("UserDetails not found for this id:" + RegnNo));

		java.util.Date fd = new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("date"));

		java.sql.Date dt = new java.sql.Date(fd.getTime());

		UserStatus us1 = us;
		us1.setApproval(obj.get("verify"));
		us1.setApproveDt(dt);
		us1.setApproveUserId(obj.get("user"));
		us1.setReason(obj.get("reason"));
		us1.setCheque_no(obj.get("cheqNo"));
		System.out.println(obj.get("subAmnt"));
		us1.setSub_amnt(obj.get("subAmnt"));

	    BankDetails bd = bdRepo.findByRegnNo(obj.get("regn")).orElseThrow(() -> new ResourceNotFoundException("BankDetails not found for this id:" + RegnNo));
		BankDetails d = bd;
		if(obj.get("reason") != null) {
			d.setPassbookImg(null);
		}
		bdRepo.save(d);

		if (us1.getReason() != null) {
			us1.setVerification(null);
			us1.setVerifyDt(null);
			us1.setCheque_no(null);
			us1.setSub_amnt(null);
			us1.setVerifyUserId(null);
		}

		userStatusRepo.save(us1);
		// entity to DTO
	    ApplicationStatusDTO ApplicationStatusResponse = modelMapper.map(us1, ApplicationStatusDTO.class);
		return ResponseEntity.ok().body(ApplicationStatusResponse);
		}catch (BusinessException e) {
				throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
			}
	}

	@PostMapping("/updateDownloadStatus")
	private ResponseEntity<ApplicationStatusDTO> updateDownloadStatus(@Valid @RequestBody String regn) throws ParseException, ResourceNotFoundException, BusinessException  {
		try {
		Optional<UserStatus> us = userStatusRepo.findByRegnNo(regn);

		UserStatus us1 = us.get();
		us1.setDownloadStatus("y");
		userStatusRepo.save(us1);
		// entity to DTO
	    ApplicationStatusDTO ApplicationStatusResponse = modelMapper.map(us1, ApplicationStatusDTO.class);
		return ResponseEntity.ok().body(ApplicationStatusResponse);
		}catch (BusinessException e) {
				throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
			}
	}

	@PostMapping(value = "/applicationStatus", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<Map<String, Object>>> getApplicationStatus(@Valid @RequestBody String regn_no) throws BusinessException {
        try
        {
		Map<String, Object> so = new HashMap<>();
		Map<String, Object> so1 = new HashMap<>();

		List<Map<String, Object>> list = new ArrayList<>();

		so = userStatusRepo.getApplicationStatus(regn_no);
		
		String disbursement = (String) so.get("disbursement");
		String temp = "";
		
		if(disbursement == null) {
			temp = "Not Done";
		}else {
			temp = "Done";
		}

		String status;
		String verificationStatus = (String) so.get("verification");
		String approvalStatus = (String) so.get("approval");
		String reasonStatus = (String) so.get("reason");
		String paymentStatus = (String) so.get("payment");

		if (verificationStatus == null) {

			if (approvalStatus == null && reasonStatus == null) {
				status = "Not Verified";

			} else {

				status = "Reverted in Approval";

			}
		} else {
			if (verificationStatus.equals("y")) {
				if (approvalStatus == null) {
					
					status = "Verified";

					

				} else {
					
					if (paymentStatus == null) {
						status = "Approved";
					} else {
						status = "Payment Done";
					}
					
					
					
				}

			} else {

				status = "Reverted in Verification";
			}

		}


		so1.put("appl_no", so.get("appl_no"));
		so1.put("regn_no", so.get("regn_no"));
		so1.put("owner_name", so.get("owner_name"));
		so1.put("acc_no", so.get("acc_no"));
		so1.put("reason", so.get("reason"));
		so1.put("status", status);
		so1.put("approve_dt", so.get("approve_dt"));
		so1.put("verify_dt", so.get("verify_dt"));
		so1.put("disbursement", temp);

		list.add(so1);
		return new ResponseEntity<List<Map<String, Object>>>(list, HttpStatus.OK);
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

	@PostMapping("/updatePaymentStatus")
	private ResponseEntity<ApplicationStatusDTO> updatePaymentStatus(@Valid @RequestBody Map<String, String> obj) throws ParseException, ResourceNotFoundException, BusinessException {
		try
		{
		Optional<UserStatus> us = userStatusRepo.findByApplNo(obj.get("applNo"));
		UserStatus us1 = us.get();

		us1.setPayment("y");
		userStatusRepo.save(us1);
		// entity to DTO
	    ApplicationStatusDTO ApplicationStatusResponse = modelMapper.map(us1, ApplicationStatusDTO.class);
		return ResponseEntity.ok().body(ApplicationStatusResponse);
		}catch (BusinessException e) {
				throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
			}

	}

	@PostMapping(value = "/disbursementStatusUpdate", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Map<String, Object>> updateDisbursementStatus(@Valid @RequestBody List<String> list) throws ParseException, ResourceNotFoundException, BusinessException {
        try
        {
		Map<String, Object> mp = new HashMap<>();

		for (String element : list) {
			Optional<UserStatus> us = userStatusRepo.findByRegnNo(element);
			mc.sendDisbursedMsg(element);
			UserStatus us1 = us.get();

			us1.setDisbursement("y");
			us1.setPayment("y");
			userStatusRepo.save(us1);
			mp.put("Updated", String.valueOf("success"));
		}
		return new ResponseEntity<Map<String, Object>>(mp, HttpStatus.OK);
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}
	
	
	@PostMapping("/insertToRevertStatus")
	private ResponseEntity<RevertStatusDetailsDTO> insertUserStatus(@Valid @RequestBody Map<String,String> rs) throws ParseException, ResourceNotFoundException, BusinessException {
		try
		{
		java.util.Date fd = new SimpleDateFormat("yyyy-MM-dd").parse(rs.get("opDt"));
		java.util.Date fd2 = new SimpleDateFormat("yyyy-MM-dd").parse(rs.get("insertDt"));
		
		java.sql.Date dt = new java.sql.Date(fd.getTime());
		java.sql.Date insertDt = new java.sql.Date(fd2.getTime());
		
		RevertStatus r = new RevertStatus();
		r.setRegnNo(rs.get("regn")); 
		r.setApplNo(rs.get("applNo"));
		r.setVerification(rs.get("verification"));
		r.setApproval(rs.get("approval"));
		r.setOpDt(dt);
		r.setReason(rs.get("reason"));
		r.setVerifyUserId(rs.get("verfiyUserId"));
		r.setApproveUserId(rs.get("approveUserId"));
		r.setInsertDt(insertDt);
		rsp.save(r);
		// convert entity to DTO
		RevertStatusDetailsDTO RevertStatusDetailsResponse = modelMapper.map(r, RevertStatusDetailsDTO.class);
		return new ResponseEntity<RevertStatusDetailsDTO>(RevertStatusDetailsResponse, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("Given Revert Status Details are null" + e.getMessage());
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
		}
	
	@PostMapping(value = "/applicationStatusByAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<Map<String, Object>>> getApplicationStatusByAdmin(@Valid @RequestBody Map<String, String> obj)  
			throws ParseException, BusinessException {
		try {
		Map<String, Object> so1;

		List<Map<String, Object>> list = new ArrayList<>();
		List<Map<String, Object>> list1 = new ArrayList<>();
		if ((obj.get("fr_date") != "") && (obj.get("to_date") != "")) {
			java.util.Date fd =  new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("fr_date"));
			java.util.Date td = new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("to_date"));
			java.sql.Date dt = new java.sql.Date(td.getTime() + (1 * 24 * 60 * 60 * 1000));
			list1 = userStatusRepo.getApplicationStatusByAdminDateWise(fd, dt);
		} else {
			list1 = userStatusRepo.getApplicationStatusByAdmin(obj.get("regn"), obj.get("appl"));
		}
		for (Map<String, Object> so : list1) {
			so1 = new HashMap<>();
			String status;
			if (so.get("verification") == null) {
				status = "Not Verified";
			} else {
				if (so.get("verification").equals("rev")) {
					status = "Reverted in Verification";
				} else {
					if (so.get("approval") == null) {
						status = "Verified";

					} else {
						if (so.get("approval").equals("rev")) {
							status = "Reverted in Approval";
						} else {
							if (so.get("payment") == null) {
								status = "Approved";
							} else {
								status = "Payment Done";
							}
						}
					}
				}
			}

			so1.putAll(so);
			so1.put("appl_no", so.get("appl_no"));
			so1.put("regn_no", so.get("regn_no"));
			so1.put("owner_name", so.get("owner_name"));
			so1.put("acc_no", so.get("acc_no"));
			so1.put("reason", so.get("reason"));
			so1.put("status", status); 

			list.add(so1);
		}
		return new ResponseEntity<List<Map<String, Object>>>(list, HttpStatus.OK);
	} catch (BusinessException e) {
		throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
	}
	}
}
