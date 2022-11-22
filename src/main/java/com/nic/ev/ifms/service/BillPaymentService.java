package com.nic.ev.ifms.service;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;

import org.apache.commons.lang.StringUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.nic.ev.ifms.model.FileSendTrackModel;
import com.nic.ev.ifms.model.IFMSIntegrationTrack;
import com.nic.ev.ifms.model.AuthenticationTokenAndSEKTable;
import com.nic.ev.ifms.model.Beneficiary;
import com.nic.ev.ifms.model.BillDetail;
import com.nic.ev.ifms.model.BillPaymentAckResponse;
import com.nic.ev.ifms.model.BillPaymentErrorMaster;
import com.nic.ev.ifms.model.BillResponseStatusMaster;
import com.nic.ev.ifms.model.ByTransfer;
import com.nic.ev.ifms.model.HoaBreakup;
import com.nic.ev.ifms.model.XMLBillPaymentFile;
import com.nic.ev.ifms.model.dto.BillPaymentFileDetails;
import com.nic.ev.ifms.model.dto.BillPaymentResponse;
import com.nic.ev.ifms.repo.AuthenticationTokenAndSEKTableRepo;
import com.nic.ev.ifms.repo.BillPaymentErrorMasterRepo;
import com.nic.ev.ifms.repo.FilesSendTranckRepo;
import com.nic.ev.ifms.repo.IFMSIntegrationTrackRepo;
import com.nic.ev.ifms.repo.XMLBankDetailsRepo;

@Service
public class BillPaymentService {

	@Value("${clientid}")
	String clientId;
	@Value("${clientsecret}")
	String clientSecret;
	@Value("${ddocode}")
	String ddoCode;
	@Value("${ddologin}")
	String ddoLogin;
	@Value("${sancauthuserloginid}")
	String sancAuthUserLoginId;
	@Value("${hoa}")
	String hoa;
	@Value("${departmentCode}")
	String deptCode;
	@Value("${serviceCode}")
	String serviceCode;
	@Value("${file.xmlFileStorePath}")
	private String xmlFileStorePath;

	@Autowired
	private AuthenticationTokenAndSEKTableService authService;
	@Autowired
	private XMLBankDetailsRepo xmlBillPaymentDetailsRepo;

	@Autowired
	private FilesSendTranckRepo fileTrackRepo;
	
	@Autowired
	private AuthenticationTokenAndSEKTableRepo authRepo;
	
	@Autowired
	private IFMSIntegrationTrackRepo ifmsTransactionRepo;
	
	@Autowired
	private BillPaymentErrorMasterRepo billPaymentErrorMasterRepo;
	public String publicKey;
	
	Logger logger
    = Logger.getLogger(BillPaymentService.class.getName());





	public Map<String,String> sendBillPaymentFileToIFMS(String dtStr) {
		BigDecimal totSubAmount = new BigDecimal(0);
		try {
			long millis = System.currentTimeMillis();
			java.sql.Date toDay = new java.sql.Date(millis);
			java.sql.Date myCustomDateForTesting = java.sql.Date.valueOf(dtStr);
			System.out.println(myCustomDateForTesting);
			List<Beneficiary> beneficiaries = new ArrayList<Beneficiary>();
			Collection<Map<String, Object>> bankDetails = xmlBillPaymentDetailsRepo
					.findByOpDate(myCustomDateForTesting);
		
			for (Map<String, Object> bankDetail : bankDetails) {
				Beneficiary beneficiary = new Beneficiary();
				beneficiary.setBenfId((String) bankDetail.get("regn_no"));
				beneficiary.setName((String) bankDetail.get("name"));
				beneficiary.setAccountNo((String) bankDetail.get("accountno"));
				beneficiary.setIfsc((String) bankDetail.get("ifsc"));
				beneficiary.setMobileNo((String) bankDetail.get("mobileno"));
				beneficiary.setAddress((String) bankDetail.get("address"));
				beneficiary.setAccountType("S");
				beneficiary.setApplNo((String) bankDetail.get("regn_no"));
//				beneficiary.setAccountType((String) bankDetail.get("acc_type"));
				BigDecimal amount = new BigDecimal(Double.parseDouble((String) bankDetail.get("amount")));
				amount = amount.setScale(2, RoundingMode.HALF_UP);
				beneficiary.setAmount(amount);
				beneficiaries.add(beneficiary);
				totSubAmount = totSubAmount.add(amount);

			}


			long today = new java.util.Date().getTime();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
			DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("yyMMddHHmm");
			LocalDateTime now = LocalDateTime.now();
			String date = dtf.format(now);
			XMLBillPaymentFile xmlBillPaymentDetails = new XMLBillPaymentFile();
			FileSendTrackModel fileTableData = new FileSendTrackModel();
			String dateTime = dtf3.format(now);
			String billNumber = "ORI_EV_"+dateTime;
			
			
			if (bankDetails.size() != 0) {
				int count = 1;
				FileSendTrackModel fileRecord = fileTrackRepo.findByFileSendDate(new java.util.Date());
				String countForFileName = "001";
				if (fileRecord != null) {
					count = fileRecord.getNoOfFilesSend() + 1;
					int length = String.valueOf(count).length();
					if (length == 1) {
						countForFileName = "00" + String.valueOf(count);
					} else if (length == 2) {
						countForFileName = "0" + String.valueOf(count);
					} else if (length == 3) {
						countForFileName = String.valueOf(count);
					} else {
						countForFileName = "001";
					}

					//// File sl no count save file
					FileSendTrackModel fileStat = new FileSendTrackModel();
					fileStat.setFileNames("D:\\EVCell\\INP\\"+billNumber+".xml");
					fileStat.setFileSendDate(toDay);
					fileStat.setNoOfFilesSend(count);
					fileStat.setId(fileRecord.getId());
					fileTableData = fileTrackRepo.save(fileStat);
				} else {
					// count = fileRecord.getNoOfFilesSend() + 1;
 
					int length = String.valueOf(count).length();
					if (length == 1) {
						countForFileName = "00" + String.valueOf(count);
					} else if (length == 2) {
						countForFileName = "0" + String.valueOf(count);
					} else if (length == 3) {
						countForFileName = String.valueOf(count);
					} else {
						countForFileName = "000";
					}
					FileSendTrackModel fileStat = new FileSendTrackModel();
					fileStat.setFileNames("D:\\EVCell\\INP\\"+billNumber+".xml");
					fileStat.setFileSendDate(toDay);
					fileStat.setNoOfFilesSend(1);
					 fileTableData = fileTrackRepo.save(fileStat);
				}

				BillPaymentFileDetails fd = new BillPaymentFileDetails();
				
//				Long refId = fileTableData.getId();
				String fileRefId="0000000001";
				int len = String.valueOf(dateTime).length();
				if(len == 0) {
					 fileRefId = "0000000001";
				}
				else {
					fileRefId= StringUtils.repeat("0", 10-len)+dateTime;
				}
				
 
				fd.setFileType("EPB");
				fd.setIntgCode("27");
				fd.setServiceCode("01");
				fd.setFileRefId(fileRefId);
				fd.setFileSlNo(countForFileName);
				String todayDt = dtf2.format(now);
				fd.setFileDate(myCustomDateForTesting);

				List<Object> bankDetails1 = new ArrayList<>(bankDetails);
				bankDetails1.add(fd);

				java.util.Date todayDate = new java.util.Date();
				// if((todayDate.getHours() == 17) && (todayDate.getMinutes() == 11) &&
				// (todayDate.getSeconds() == 10)) {
				BillDetail billDetail = new BillDetail();
				billDetail.setBillNumber(billNumber);
				java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
				billDetail.setBillDate(sqlDate);
				billDetail.setBillTypeId(80);
				billDetail.setDdoCode(ddoCode);
				billDetail.setDdoLoginId(ddoLogin);
				billDetail.setNoOfBenf(bankDetails.size());
				billDetail.setSancAuthUserLoginId(sancAuthUserLoginId);
				billDetail.setGrossAmount(totSubAmount);
				billDetail.setNetAmount(totSubAmount);
				java.sql.Date expectedPaymentDate = new java.sql.Date(System.currentTimeMillis() + 172800000);
				System.out.println(expectedPaymentDate);
				billDetail.setPaymentDate(expectedPaymentDate);

				List<HoaBreakup> hoaBreakups = new ArrayList<HoaBreakup>();
				HoaBreakup hoaBreakup = new HoaBreakup();
				hoaBreakup.setHoa(hoa);
				hoaBreakup.setAmount(totSubAmount);
				hoaBreakups.add(hoaBreakup);

				List<ByTransfer> byTransfers = new ArrayList<ByTransfer>();
				List<Object> billAbstracts = new ArrayList<Object>();
//				ByTransfer byTransfer = new ByTransfer();
//				byTransfers.add(byTransfer);
				xmlBillPaymentDetails.setFileDetail(fd);
				xmlBillPaymentDetails.setBillDetail(billDetail);
				xmlBillPaymentDetails.setHoaBreakup(hoaBreakups);
				xmlBillPaymentDetails.setByTransfer(byTransfers);
				xmlBillPaymentDetails.setBillAbstract(billAbstracts);
				xmlBillPaymentDetails.setBeneficiary(beneficiaries);
		
			
			AuthenticationTokenAndSEKTable authData = authRepo.findLatestSEK();
			String xmlString = exportToXML(xmlBillPaymentDetails);
			System.out.println(xmlString);
		
			byte[] sek =  authData.getDecryptedSek();
			String dataByte = authService.encrypt(xmlString, sek);
			
			byte[] hmacSha256 = null;
			 Mac mac = Mac.getInstance("HmacSHA256");
		      SecretKeySpec secretKeySpec = new SecretKeySpec(sek, "AES");
		      mac.init(secretKeySpec);
		      hmacSha256 = mac.doFinal(xmlString.getBytes());
			  
			String data = dataByte;
			String hmac = Base64.getEncoder().encodeToString(hmacSha256);
			
			Map<String,String> dataBody = new HashMap<>();
			dataBody.put("data", data);
			dataBody.put("hmac", hmac);

			HttpHeaders headers = new HttpHeaders();
			headers.add("clientId", clientId);
			
//			System.out.println(authData.getAuthToken());
			headers.add("authToken", authData.getAuthToken());
			headers.add("Content-Type", "application/json");
//			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//
//			TrustStrategy acceptingTrustStrategy = (x509Certificates, s) -> true;
//			SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
//					.build();
//			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
//			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
//			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//			requestFactory.setHttpClient(httpClient);
			RestTemplate restTemplate = new RestTemplate();
//			HttpEntity<JsonObject> entity = new HttpEntity<JsonObject>(jsonObject, headers);
//			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String,String>>(dataBody, headers);
			BillPaymentAckResponse billPaymentAckResponse = new BillPaymentAckResponse();
			try {
				ResponseEntity<String> res = restTemplate.exchange(
						"https://uat.odishatreasury.gov.in/bdbillreceivingws/0.1/submitbill", HttpMethod.POST, entity,
						String.class);
				System.out.println("result is" + res.getBody());
				
				billPaymentAckResponse = new ObjectMapper().readValue(res.getBody(), BillPaymentAckResponse.class);
					if ((billPaymentAckResponse.getData() != null) && (billPaymentAckResponse.getStatus() == true)) {
						
						String encryptedRek = billPaymentAckResponse.getRek();
						
						byte[] rek = authService.decrypt(encryptedRek,sek);
						byte[] responseAckDataByte = authService.decrypt(billPaymentAckResponse.getData(), rek);
						String result = new String(responseAckDataByte);
						result= result.substring(38, result.length());
						String pathName = "D:\\EVCell\\ACK\\"+xmlBillPaymentDetails.getBillDetail().getBillNumber()+".xml";
						Path path = Paths.get(pathName);
						Files.write(path, responseAckDataByte);
						
						System.out.println(new String(responseAckDataByte));

						XmlMapper mapper = new XmlMapper();
						XMLBillPaymentFile ackFile = mapper.readValue(responseAckDataByte, XMLBillPaymentFile.class);

						
						
						authData.setDecryptedRek(rek);
						authService.saveAuthentication(authData);
						
						
						IFMSIntegrationTrack transactionDetails;
 						for(Beneficiary beneficiary : beneficiaries) {
							 transactionDetails = new IFMSIntegrationTrack();
							 transactionDetails.setAccNo(beneficiary.getAccountNo());
							 transactionDetails.setAckDate(new Date());
							 System.out.println(ackFile.getBillDetail().getBillStatus().getCodes().size() == 0);
							 if(ackFile.getBillDetail().getBillStatus().getCodes().size() != 0) {
								 transactionDetails.setAckErr(ackFile.getBillDetail().getBillStatus().getCodes().get(0).getCode());
							 }
							 if(ackFile.getBeneficiary().size() != 0) {
								 List<Beneficiary> benfList = ackFile.getBeneficiary();
								 for(Beneficiary benf: benfList) {
									 if(benf.getBenfId() == beneficiary.getBenfId()) {
										 transactionDetails.setAckErr(benf.getCodes().get(0).getCode());
										 
									 }
								 }
								
							 }

							 transactionDetails.setAckStatus("Y");
							 transactionDetails.setApplNo(beneficiary.getApplNo());
							 transactionDetails.setIfsc(beneficiary.getIfsc());
							 transactionDetails.setName(beneficiary.getName());
							 transactionDetails.setBillNo(billNumber);
							 transactionDetails.setFileRefId(fileRefId);
							 if(ackFile.getBillDetail().getBillRefNo() != null) {
								 transactionDetails.setBillRefNo(ackFile.getBillDetail().getBillRefNo());
							 }
							
							 transactionDetails.setRegnNo(beneficiary.getBenfId());
							 transactionDetails.setSubmitDate(new Date());
							 transactionDetails.setSubmitStatus("Y");
							 transactionDetails.setCreatedDate(new Date());
							 
							
							 try {
								 IFMSIntegrationTrack ifmsTrack = ifmsTransactionRepo.save(transactionDetails);
							 }
							 catch(Exception e) {
								 System.out.println(e);
								 logger.error(e.getMessage());
							 }
						}
						
						
						
//						BillPaymentAckResponse authData = new BillPaymentAckResponse();
//						authData.setAppKey(publicKey);
//						authData.setEncryptedAppKey(appkey);
//						authData.setAuthToken(billPaymentAckResponse.getData().getAuthToken());
//						authData.setClientId(clientId);
//						authData.setClientSecret(clientSecret);
//						authData.setOpDate(new Date());
//						authData.setSek(billPaymentAckResponse.getData().getSek());  
//						byte[] decryptedSek = getDecryptedSEK(authData.getSek(),authData.getAppKey());
//						authData.setDecryptedSek(decryptedSek);;
//						try {
//							saveAuthentication(authData); 
//						} catch (Exception e) {
//		
//						}
//						
					}
				} catch (Exception e) {
					dataBody.put("msg", e.getMessage());
					return dataBody;
					
				}
		
			return dataBody;
			}
			else {
				Map<String,String> dataBody = new HashMap<>();
				dataBody.put("msg", "0 Beneficies found");
				return dataBody;
			}

		} catch (Exception e) {
			System.out.println(e);
			return null;

		}

	}

	
	public String exportToXML(XMLBillPaymentFile xmlBankDetails) throws IOException {
		XmlMapper xmlMapper = new XmlMapper();
		String personXml = xmlMapper.writeValueAsString(xmlBankDetails);
		personXml = "<?xml  version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>" + personXml;
		String pathName = "D:\\EVCell\\INP\\"+xmlBankDetails.getBillDetail().getBillNumber()+".xml";
		Path path = Paths.get(pathName);
		Files.write(path, personXml.getBytes());
		

		return personXml;
	}


	public Map<String,String> billPaymetStatusCheck(String dataString) throws NoSuchAlgorithmException, InvalidKeyException, KeyManagementException, KeyStoreException {
		// TODO Auto-generated method stub
		
		AuthenticationTokenAndSEKTable authData = authRepo.findLatestSEK();
//		String xmlString = exportToXML(xmlBillPaymentDetails);
//		System.out.println(dataString);
		Map<String,String> dataBody = new HashMap<>();
		byte[] sek =  authData.getDecryptedSek();
		String dataByte = authService.encrypt(dataString, sek);
		
		byte[] hmacSha256 = null;
		 Mac mac = Mac.getInstance("HmacSHA256");
	      SecretKeySpec secretKeySpec = new SecretKeySpec(sek, "AES");
	      mac.init(secretKeySpec);
	      hmacSha256 = mac.doFinal(dataString.getBytes());
		  
		String data = dataByte;
		String hmac = Base64.getEncoder().encodeToString(hmacSha256);
		

		dataBody.put("data", dataString);
		dataBody.put("hmac", hmac);

		HttpHeaders headers = new HttpHeaders();
		headers.add("clientId", clientId);
		
//		System.out.println(authData.getAuthToken());
		headers.add("authToken", authData.getAuthToken());
		headers.add("Content-Type", "application/json");
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		TrustStrategy acceptingTrustStrategy = (x509Certificates, s) -> true;
		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
				.build();
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
//		HttpEntity<JsonObject> entity = new HttpEntity<JsonObject>(jsonObject, headers);
		HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String,String>>(dataBody, headers);
//		BillPaymentAckResponse billPaymentAckResponse = new BillPaymentAckResponse();
		try {
			ResponseEntity<String> res = restTemplate.exchange(
					"https://uat.odishatreasury.gov.in/bdbillreceivingws/0.1/paymentstatus", HttpMethod.POST, entity,
					String.class);
			System.out.println("result is" + res.getBody());
			dataBody.put("res", res.getBody());
			BillPaymentResponse billPaymentAckRes= new ObjectMapper().readValue(res.getBody(), BillPaymentResponse.class);
			String billPaymentResponse = billPaymentAckRes.getBillStatusString();

			byte[] responseAckDataByte = billPaymentResponse.getBytes();
			String result = new String(responseAckDataByte);
			result= result.substring(38, result.length());
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyHHmm");
			String dateStr = sdf.format(today);
			String billRefId = dataString.substring(3,dataString.length());
			String pathName = "D:\\EVCell\\RES\\"+billRefId+"_"+dateStr+".xml";
			Path path = Paths.get(pathName);
			Files.write(path, responseAckDataByte);
			
			XmlMapper mapper = new XmlMapper();
			XMLBillPaymentFile resFile = mapper.readValue(responseAckDataByte, XMLBillPaymentFile.class);
//			System.out.println(resFile.getBillDetail().getBillStatus().getCodes().get(0).getCode());
			
			
			IFMSIntegrationTrack transactionDetails;
			for(Beneficiary beneficiary : resFile.getBeneficiary()) {
				transactionDetails = ifmsTransactionRepo.findByBenfIdAndBillRefId(beneficiary.getBenefAcctNumber(),beneficiary.getBenefIFSC(),billRefId);
				if(transactionDetails != null) {
//					 transactionDetails = ifmsTransactionRepo.findByApplNo(beneficiary.getApplNo());
					 transactionDetails.setResFileName(pathName);
					 System.out.println(resFile.getBillDetails().getVoucherNo());
					 if(resFile.getBillDetails().getVoucherNo() != null) {
						 transactionDetails.setVoucherNo(resFile.getBillDetail().getVoucherNo());
						 transactionDetails.setVoucherDate(resFile.getBillDetail().getVoucherDate());
					 }
					 BillPaymentErrorMaster billStatus = billPaymentErrorMasterRepo.findByErrorMsg(resFile.getBillDetails().getBillStatus());
					 
					 transactionDetails.setBillStatusString(billStatus.getErrorCode());
					 transactionDetails.setCheckStatus("Y");
					 transactionDetails.setCheckStatusDate(new Date());
					 transactionDetails.setUtrDate(beneficiary.getUtrDate());
					 transactionDetails.setUtrNo(beneficiary.getUtrNo());
					 billStatus = billPaymentErrorMasterRepo.findByErrorMsg(beneficiary.getStatusDesc());
					 transactionDetails.setBenfPaymentStatus(beneficiary.getPayStatus());
					 transactionDetails.setBenefBillStatus(billStatus.getErrorCode());
					
					 try {
						 IFMSIntegrationTrack ifmsTrack = ifmsTransactionRepo.save(transactionDetails);
					 }
					 catch(Exception e) {
						 System.out.println(e);
						 logger.error(e.getMessage());
					 }
				}
				
			}
			
			
			
			
//			billPaymentAckResponse = new ObjectMapper().readValue(res.getBody(), BillPaymentAckResponse.class);
//				if ((billPaymentAckResponse.getData() != null) && (billPaymentAckResponse.getStatus() == true)) {
//					String encryptedRek = billPaymentAckResponse.getRek();
//					
//					byte[] rek = authService.decrypt(encryptedRek,sek);
//					byte[] responseAckDataByte = authService.decrypt(billPaymentAckResponse.getData(), rek);
//					String result = new String(responseAckDataByte);
//					result= result.substring(38, result.length());
//					
//					System.out.println(new String(responseAckDataByte));
//
//					XmlMapper mapper = new XmlMapper();
//					XMLBillPaymentFile ackFile = mapper.readValue(responseAckDataByte, XMLBillPaymentFile.class);
//					System.out.println(ackFile.getBillDetail().getBillStatus().getCodes().get(0).getCode());
//					
//					
//					authData.setDecryptedRek(rek);
//					authService.saveAuthentication(authData);
//					
//					
//					
////					BillPaymentAckResponse authData = new BillPaymentAckResponse();
////					authData.setAppKey(publicKey);
////					authData.setEncryptedAppKey(appkey);
////					authData.setAuthToken(billPaymentAckResponse.getData().getAuthToken());
////					authData.setClientId(clientId);
////					authData.setClientSecret(clientSecret);
////					authData.setOpDate(new Date());
////					authData.setSek(billPaymentAckResponse.getData().getSek());
////					byte[] decryptedSek = getDecryptedSEK(authData.getSek(),authData.getAppKey());
////					authData.setDecryptedSek(decryptedSek);;
////					try {
////						saveAuthentication(authData); 
////					} catch (Exception e) {
////	
////					}
////					
//				}
			return dataBody;
			} catch (Exception e) {
					System.out.println(e);
					dataBody.put("errorMsg", e.getMessage());
					return dataBody;
			}
	}


}

class AppKey implements Serializable {
	AppKey() {
	}

	AppKey(String appKey) {
		this.appKey = appKey;
	}

	private String appKey;

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

}
