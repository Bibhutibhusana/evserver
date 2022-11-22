package com.nic.ev.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nic.dto.BankInfoDTO;
import com.nic.dto.IfscDetailsDTO;
import com.nic.ev.exception.BusinessException;
import com.nic.ev.exception.ResourceNotFoundException;
import com.nic.ev.model.BankDetails;
import com.nic.ev.model.IFMS_master;
import com.nic.ev.model.UserStatus;
import com.nic.ev.repo.BankDetailsRepo;
import com.nic.ev.repo.IFMSMasterRepo;
import com.nic.ev.service.RegistrationService;

@RestController
@CrossOrigin
@RequestMapping("/")
public class BankDetailsInsertController {

	@Autowired
	BankDetailsRepo bankDetailsRepo;
	@Autowired
	RegistrationService registrationService;
	@Autowired
	IFMSMasterRepo ifmsMasterRepo;
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/insertBankDetails")
	private ResponseEntity<BankInfoDTO> insertBankDetails(@Valid @RequestBody BankInfoDTO BankInfodto) throws BusinessException {
		// convert DTO to entity
		BankDetails bankDetails=modelMapper.map(BankInfodto, BankDetails.class);
		if (bankDetails.getBankName().isEmpty() || bankDetails.getBankName().length() == 0) {
			throw new BusinessException("Please provide Bank Name.It is Blank");
		}
		if (bankDetails.getBranchName().isEmpty() || bankDetails.getBranchName().length() == 0) {
			throw new BusinessException("Please provide Branch Name.It is Blank");
		}
		if (bankDetails.getAccNo().isEmpty() || bankDetails.getAccNo().length() == 0) {
			throw new BusinessException("Please provide Account No.It is Blank");
		}
		if (bankDetails.getIfscCode().isEmpty() || bankDetails.getIfscCode().length() == 0) {
			throw new BusinessException("Please provide IFSC Code.It is Blank");
		}
		if (bankDetails.getName().isEmpty() || bankDetails.getName().length() == 0) {
			throw new BusinessException("Please provide Account Holder Name.It is Blank");
		}
		try {
			bankDetailsRepo.save(bankDetails);
			// convert entity to DTO
			BankInfoDTO BankInfoResponse = modelMapper.map(bankDetails, BankInfoDTO.class);
			return new ResponseEntity<BankInfoDTO>(BankInfoResponse, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("Given Bank Details are null" + e.getMessage());
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}

	}

	@GetMapping("/showImage")
	private ResponseEntity<List<BankInfoDTO>> getImage() throws ResourceNotFoundException, BusinessException {
		try {
			List<BankInfoDTO> listofBankDetails = bankDetailsRepo.findAll().stream().map(bankdetails -> modelMapper.map(bankdetails, BankInfoDTO.class))
					.collect(Collectors.toList());
			if (listofBankDetails == null) {
				throw new ResourceNotFoundException("Bank Details not found");
			}
			return new ResponseEntity<List<BankInfoDTO>>(listofBankDetails, HttpStatus.OK);
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

	@SuppressWarnings("deprecation")
	@GetMapping("/applNoGeneration/{regn}")
	private Map<String, String> getApplNo(@PathVariable(value = "regn") String regn) throws BusinessException {
//		String applNo="";
		try {
			Map<String, String> applNo = new HashMap<String, String>();
			String temp = "";
			Date d = new Date();
			int year = d.getYear() + 1900;
			// String lastFourDigits = regn.substring(regn.length() - 4);
			temp = "OD" + year + "EV" + System.currentTimeMillis();
			applNo.put("applNo", temp);

			return applNo;
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

	@GetMapping("/checkExistBankDetails/{id}")
	private ResponseEntity<BankInfoDTO> checkIfExists(@PathVariable(value = "id") String regn)
			throws ResourceNotFoundException, BusinessException {
		try {
			BankDetails bankDetails = bankDetailsRepo.findById(regn)
					.orElseThrow(() -> new ResourceNotFoundException("BankDetails not found for this id:" + regn));
			BankInfoDTO bankInfoDetails=modelMapper.map(bankDetails, BankInfoDTO.class);
			return ResponseEntity.ok().body(bankInfoDetails);
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

	@GetMapping("/checkExistBankAndStatusDetails/{id}")
	private ResponseEntity<Map<String, Object>> checkIfBankAndStatusExists(@PathVariable(value = "id") String regn)
			throws BusinessException {
		try {
			Map<String, Object> b = bankDetailsRepo.findByStatusAndBankId(regn);
			return ResponseEntity.ok().body(b);
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

	@DeleteMapping("/deleteBankDetailsByRegn/{regn}")
	ResponseEntity<Map<String, Boolean>> deleteByRegnNo(@PathVariable(value = "regn") String regn)
			throws ResourceNotFoundException, BusinessException {
		try {
			bankDetailsRepo.findById(regn)
					.orElseThrow(() -> new ResourceNotFoundException("BankDetails not found for this id:" + regn));

			bankDetailsRepo.deleteById(regn);

			Map<String, Boolean> rs = new HashMap<>();

			rs.put("deleted", true);

			return ResponseEntity.ok().body(rs); 
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}

	}

	@PostMapping(value = "/totalApplied", produces = MediaType.APPLICATION_JSON_VALUE)
	private List<Map<String, Object>> getTotalApplied(@Valid @RequestBody Map<String, String> mp)
			throws ParseException, BusinessException {

		try {
			java.util.Date fd = new SimpleDateFormat("yyyy-MM-dd").parse(mp.get("fromdate"));
			java.util.Date td = new SimpleDateFormat("yyyy-MM-dd").parse(mp.get("todate"));
			java.sql.Date dt = new java.sql.Date(td.getTime() + (1 * 24 * 60 * 60 * 1000));
			String off_cd = mp.get("off_cd");
			if (mp.get("type").equals("taar")) {
				return bankDetailsRepo.getTotalApplied(fd, dt, off_cd);
			} else if (mp.get("type").equals("tvar")) {
				return bankDetailsRepo.getTotalVerified(fd, dt, off_cd);
			} else if (mp.get("type").equals("plvs")) {
				return bankDetailsRepo.getPendingToVerify(fd, dt, off_cd);
			} else if (mp.get("type").equals("tapr")) {
				return bankDetailsRepo.getTotalApproved(fd, dt, off_cd);
			} else if (mp.get("type").equals("plas")) {
				return bankDetailsRepo.getPendingToApprove(fd, dt, off_cd);
			} else if (mp.get("type").equals("plad")) {
				return bankDetailsRepo.getPendingToDisbursement(fd, dt);
			} else if (mp.get("type").equals("tdar")) {
				return bankDetailsRepo.getTotalDisbursement(fd, dt);
			} else {
				return null;
			}
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}

	}

	@PostMapping(value = "/getReport", produces = MediaType.APPLICATION_JSON_VALUE)
	private List<Map<String, Object>> getReport(@Valid @RequestBody Map<String, String> mp)
			throws ParseException, BusinessException {
		try {

			java.util.Date fd = new SimpleDateFormat("yyyy-MM-dd").parse(mp.get("fromdate"));
			java.util.Date td = new SimpleDateFormat("yyyy-MM-dd").parse(mp.get("todate"));
			java.sql.Date dt = new java.sql.Date(td.getTime() + (1 * 24 * 60 * 60 * 1000));
			if (mp.get("type").equals("taar")) {
				return bankDetailsRepo.getTotalAppliedReport(fd, dt);
			} else if (mp.get("type").equals("tvar")) {
				return bankDetailsRepo.getTotalVerifiedReport(fd, dt);
			} else if (mp.get("type").equals("plvs")) {
				return bankDetailsRepo.getPendingToVerifyReport(fd, dt);
			} else if (mp.get("type").equals("tapr")) {
				return bankDetailsRepo.getTotalApprovedReport(fd, dt);
			} else if (mp.get("type").equals("plas")) {
				return bankDetailsRepo.getPendingToApproveReport(fd, dt);
			} else if (mp.get("type").equals("plad")) {
				return bankDetailsRepo.getPendingToDisbursementReport(fd, dt);
			} else if (mp.get("type").equals("tdar")) {
				return bankDetailsRepo.getTotalDisbursementReport(fd, dt);
			} else {
				return null;
			}
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}

	}

	@PostMapping("/api/knowApplicationNumber")
	private List<Map<String, Object>> knowApplNo(@Valid @RequestBody String regn) throws BusinessException {
		try
		{
		return bankDetailsRepo.getToKnowApplNo(regn);
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}

	}

	@PostMapping("/insertBankAndStatusDetails")
	private Map<String, Object> updateBankAndStatusDetails(@Valid @RequestBody Map<String, Object> obj)  throws BusinessException{
		try
		{
		ObjectMapper mapper = new ObjectMapper();
		UserStatus u = mapper.convertValue(obj.get("userStatus"), UserStatus.class);
		BankDetails b = mapper.convertValue(obj.get("bankDetails"), BankDetails.class);
		registrationService.updateBankAndStatusDetails(u, b);
		return obj;
		}
		catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

	@PostMapping("/ifscVerification")
	private ResponseEntity<IfscDetailsDTO> getifscVerified(@RequestBody String ifscCode) throws BusinessException {
		try
		{
		IFMS_master ifmsmaster=ifmsMasterRepo.findByIfsc(ifscCode);
		IfscDetailsDTO ifscdetailsResponse = modelMapper.map(ifmsmaster, IfscDetailsDTO.class);
		return ResponseEntity.ok().body(ifscdetailsResponse);
		}
		catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}
	
}
