package com.nic.ev.controller;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nic.ev.dto.BankInfoHistoryDTO;
import com.nic.ev.exception.BusinessException;
import com.nic.ev.model.BankDetailsHistory;
import com.nic.ev.repo.BankDetailsHistoryRepo;

@RequestMapping("/")
public class BankDetailsHistoryController {

	@Autowired 
	BankDetailsHistoryRepo bdHistoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("/insertToBankDetailsHistory")
	private ResponseEntity<BankInfoHistoryDTO> inserToHistory(@Valid @RequestBody BankInfoHistoryDTO bankinfohistorydto) throws BusinessException {
		BankDetailsHistory bd=modelMapper.map(bankinfohistorydto, BankDetailsHistory.class);
		if (bd.getBankName().isEmpty() || bd.getBankName().length() == 0) {
			throw new BusinessException("Please provide Bank Name.It is Blank");
		}
		if (bd.getBranchName().isEmpty() || bd.getBranchName().length() == 0) {
			throw new BusinessException("Please provide Branch Name.It is Blank");
		}
		if (bd.getAccNo().isEmpty() || bd.getAccNo().length() == 0) {
			throw new BusinessException("Please provide Account No.It is Blank");
		}
		if (bd.getIfscCode().isEmpty() || bd.getIfscCode().length() == 0) {
			throw new BusinessException("Please provide IFSC Code.It is Blank");
		}
		if (bd.getName().isEmpty() || bd.getName().length() == 0) {
			throw new BusinessException("Please provide Account Holder Name.It is Blank");
		}
		try {
			bdHistoryRepo.save(bd);
			// convert entity to DTO
			BankInfoHistoryDTO BankInfoResponse = modelMapper.map(bd, BankInfoHistoryDTO.class);

			return new ResponseEntity<BankInfoHistoryDTO>(BankInfoResponse, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			throw new BusinessException("Given Bank History Details are null" + e.getMessage());
		} catch (BusinessException e) {
			throw new BusinessException("Something Went Wrong in Service Layer" + e.getMessage());
		}
	}
}