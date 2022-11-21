//package com.nic.ev.ifms.controller;
//
//import java.io.IOException;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.nic.ev.ifms.model.XMLAVFileFormat;
//import com.nic.ev.ifms.service.AccountValidation;
//
//@RestController
//@CrossOrigin
//@RequestMapping("/")
//public class XMLBankDetailsController {
//	@Autowired
//	public AccountValidation accountValidator;
//
//	@GetMapping(value = "/xmlBankDetails", produces = MediaType.TEXT_XML_VALUE)
//	@ResponseBody
//	public XMLAVFileFormat getXml() throws IOException {
//		return accountValidator.getXml();
//	}
//
//	@GetMapping("/downloadAckFiles")
//	public Map<String, String> downloadAckFiles() throws IOException {
//		return accountValidator.downloadAckFiles(); 
//	}
//
//	@GetMapping("/readAckFile")
//	public Map<String, String> readAckFile() throws Exception {
//		return accountValidator.readAckFile();
//	}
//	@GetMapping("/readAckFileDemo")
//	public Map<String, String> readAckFileDemo() throws Exception {
//		return accountValidator.readXmlFileDemo();
//	}
//	
//	
//}
