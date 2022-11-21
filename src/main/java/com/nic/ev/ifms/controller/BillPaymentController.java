package com.nic.ev.ifms.controller;

import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.ifms.model.webservice.AuthenticationResponse;
import com.nic.ev.ifms.service.AuthenticationTokenAndSEKTableService;
import com.nic.ev.ifms.service.BillPaymentService;

@RestController
@CrossOrigin
@RequestMapping("/")
public class BillPaymentController {

	@Autowired BillPaymentService billPaymentService;
	@Autowired AuthenticationTokenAndSEKTableService authService;
	
	@PostMapping(value = "/authenticateForBillPayment")
	public AuthenticationResponse  getAuthTokenAndSEKForBillPayment() throws Exception {
		AuthenticationResponse authResponse = new AuthenticationResponse() ;
		authResponse = authService.getAuthToken();
		
		return authResponse;
	}
	
	@GetMapping(value="/generateAESKey")
	public String generateAESKey() throws Exception {
		String encodedKey =  authService.createAppkey();
		return encodedKey;
	}

	@PostMapping(value="/sendBillPaymentFileToIFMS")
	@ResponseBody
	public Map<String,String> sendBillPaymentFileToIFMS(@RequestBody Map<String,String> date) throws Exception {
//		String encodedKey =  authService.createAppkey();
//		Thread.sleep(1000);
		return billPaymentService.sendBillPaymentFileToIFMS(date.get("date"));
	}
	
	@PostMapping(value="/billPaymentStatusCheck")
	@ResponseBody
	public Map<String,String> billPaymentStatusCheck(@RequestBody Map<String,String> data) throws InvalidKeyException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		return billPaymentService.billPaymetStatusCheck(data.get("dataString"));
	}
	
	
//	@GetMapping(value="/getDecryptedSEK")
//	private String getDecryptedSEK() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, IOException {
//		return new String(billPaymentService.getDecryptedSEK());
//	}

}
