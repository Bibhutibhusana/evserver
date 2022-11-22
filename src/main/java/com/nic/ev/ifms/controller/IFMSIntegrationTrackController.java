package com.nic.ev.ifms.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.dto.IFMSIntegrationTrackDTO;
import com.nic.dto.RectifiedBeneficialDetailsDTO;
import com.nic.ev.exception.BusinessException;
import com.nic.ev.ifms.model.IFMSIntegrationTrack;
import com.nic.ev.ifms.repo.IFMSIntegrationTrackRepo;
import com.nic.ev.model.UserStatus;
import com.nic.ev.repo.UserStatusRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class IFMSIntegrationTrackController {
	
	@Autowired
	private IFMSIntegrationTrackRepo ifmsRepo;
	
	@Autowired
	private UserStatusRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
		
	@GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<IFMSIntegrationTrack> getAllInfo() {
		return ifmsRepo.getAllPendingRevertCases();
	}
	
	@PostMapping("findall")
	public Optional<UserStatus> findByRegnNo(@Valid @RequestBody String regn_no) {
		System.out.println(regn_no);
		return userRepo.findByRegnNo(regn_no);
	}
	
	@PutMapping("save")
	public ResponseEntity<IFMSIntegrationTrackDTO> saveRevertStatus(@Valid @RequestBody IFMSIntegrationTrackDTO ifmsIntegrationTrackdto) throws BusinessException{
		IFMSIntegrationTrack ifmsIntegrationTrack = new IFMSIntegrationTrack();
		if (ifmsIntegrationTrackdto.getId() != null) {
			Optional<IFMSIntegrationTrack> ifmsInfo = ifmsRepo.findById(ifmsIntegrationTrackdto.getId());
			ifmsIntegrationTrack = ifmsInfo.get();
			ifmsIntegrationTrack.setRevertStatus("y");
			ifmsIntegrationTrack.setRevertStatusDate(new Date());
			ifmsIntegrationTrack.setUpdatedDatetime(new Date());
			ifmsIntegrationTrack.setVersionNo((long) 1);
		}		
		try {
			ifmsIntegrationTrack = ifmsRepo.save(ifmsIntegrationTrack);
			IFMSIntegrationTrackDTO ifmsTrackResponse = modelMapper.map(ifmsIntegrationTrack, IFMSIntegrationTrackDTO.class);
			return new ResponseEntity<IFMSIntegrationTrackDTO>(ifmsTrackResponse, HttpStatus.CREATED);
		} catch(IllegalArgumentException e) {
			throw new BusinessException("Given data is not valid" + e.getMessage());
		}
	}
	
	@PostMapping("rectifiedbenfdetails")
	public ResponseEntity<List<RectifiedBeneficialDetailsDTO>> getRectifiedBenfDetails() throws BusinessException {
		try {			
			List<Map<String, Object>> list = new ArrayList<>();
			List<RectifiedBeneficialDetailsDTO> list1 = new ArrayList<>();
			
			RectifiedBeneficialDetailsDTO rbd;
			
			list = ifmsRepo.getRectifiedDetails();
			
			for (Map<String, Object> rd : list) {
				rbd = new RectifiedBeneficialDetailsDTO();
				
				rbd.setApplNo((String) rd.get("appl_no"));
				rbd.setRegnNo((String) rd.get("regn_no"));
				rbd.setAccNo((String) rd.get("acc_no"));
				rbd.setBillNo((String) rd.get("bill_no"));
				rbd.setBillStatus((String) rd.get("bill_status"));
				rbd.setBenfBillStatus((String) rd.get("benf_bill_status"));
				rbd.setIfsc((String) rd.get("ifsc"));
				rbd.setName((String) rd.get("name"));
				rbd.setBenfPaymentStatus((String) rd.get("benf_payment_status"));
				rbd.setRevertStatus((String) rd.get("revert_status"));
				rbd.setSubmitDate((Date) rd.get("submit_date"));
				rbd.setCheckBillStatusDate((Date) rd.get("check_bill_status_date"));
				rbd.setSubAmnt((String) rd.get("sub_amnt"));
				rbd.setApproval((String) rd.get("approval"));
				rbd.setVerify((String) rd.get("verify"));
				rbd.setRtgsNo((String) rd.get("rtgs_no"));
				rbd.setEvErr((String) rd.get("ev_err"));
				rbd.setDdoCheckStatus((String) rd.get("ddo_status_check"));
				rbd.setDdoCheckStatusDate((Date) rd.get("ddo_status_check_date"));
				
				list1.add(rbd);
				
		}
			return ResponseEntity.ok(list1);
		} catch(BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}

	@PutMapping("updateDdoByRegn")
	public ResponseEntity<RectifiedBeneficialDetailsDTO> updateDdoStatus(@Valid @RequestBody Map<String, String> rectifiedDto) throws BusinessException{
		IFMSIntegrationTrack ifmsIntegrationTrack = new IFMSIntegrationTrack();
		if(!rectifiedDto.get("regnNo").isEmpty()) {
			Optional<IFMSIntegrationTrack> ifmsInfo = ifmsRepo.findByRegnNo(rectifiedDto.get("regnNo"));
			ifmsIntegrationTrack = ifmsInfo.get();
			ifmsIntegrationTrack.setDdoCheckStatus("y");
			ifmsIntegrationTrack.setDdoCheckStatusDate(new Date());
		}
		try {
			ifmsIntegrationTrack = ifmsRepo.save(ifmsIntegrationTrack);
			RectifiedBeneficialDetailsDTO rectifiedResponse = modelMapper.map(ifmsIntegrationTrack, RectifiedBeneficialDetailsDTO.class);
			System.out.println(rectifiedResponse.getRegnNo());
			return new ResponseEntity<RectifiedBeneficialDetailsDTO>(rectifiedResponse, HttpStatus.CREATED);
		} catch(IllegalArgumentException e) {
			throw new BusinessException("Given data is not valid" + e.getMessage());
		}
	}
	
	@PostMapping("findbyregn")
	public Optional<IFMSIntegrationTrack> getIfmsTransactionByRegn(@Valid @RequestBody String regn_no) {
//		System.out.println(regn_no);
		return ifmsRepo.findByRegnNo(regn_no);
	}
	
	
}
