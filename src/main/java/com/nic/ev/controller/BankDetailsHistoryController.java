package com.nic.ev.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.model.BankDetailsHistory;
import com.nic.ev.repo.BankDetailsHistoryRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class BankDetailsHistoryController {

	@Autowired BankDetailsHistoryRepo bdHistoryRepo;
	
	@PostMapping("/insertToBankDetailsHistory")
	private BankDetailsHistory inserToHistory(@Valid @RequestBody BankDetailsHistory bd) {
		return bdHistoryRepo.save(bd);
	}
	
	
}
