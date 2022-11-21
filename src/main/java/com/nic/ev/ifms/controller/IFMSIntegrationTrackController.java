package com.nic.ev.ifms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.ifms.model.IFMSIntegrationTrack;
import com.nic.ev.ifms.repo.IFMSIntegrationTrackRepo;

@CrossOrigin
@RequestMapping("/")
@RestController
public class IFMSIntegrationTrackController {
	@Autowired IFMSIntegrationTrackRepo iFMSIntegrationTrackRepo;
	
	@PostMapping("ifmsIntegrationTrackList")
	public List<IFMSIntegrationTrack> getIFMSIntegrationTrackList(){
		List<IFMSIntegrationTrack> ifmsTransactionList = iFMSIntegrationTrackRepo.findAll();
		return ifmsTransactionList;
	}
	
	@PostMapping("pedingIfmsIntegrationTrackList")
	public List<IFMSIntegrationTrack> getIFMSIntegrationTrackRevertPendingList(){
		List<IFMSIntegrationTrack> ifmsTransactionList = iFMSIntegrationTrackRepo.getIFMSIntegrationTrackRevertPendingList();
		System.out.println(ifmsTransactionList.size());
		return ifmsTransactionList;
	}

}
