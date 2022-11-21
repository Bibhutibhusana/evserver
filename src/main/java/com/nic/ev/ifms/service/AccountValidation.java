//package com.nic.ev.ifms.service;
//
//import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Unmarshaller;
//import javax.xml.stream.XMLStreamException;
//
//import org.apache.commons.net.util.Base64;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//import com.nic.ev.ifms.model.FileSendTrackModel;
//import com.nic.ev.ifms.model.IFMSIntegrationTrack;
//import com.nic.ev.ifms.model.XMLACKFileFormat;
//import com.nic.ev.ifms.model.XMLAVFileFormat;
//import com.nic.ev.ifms.model.XMLBankDetailsModel;
//import com.nic.ev.ifms.model.XMLFileDetails;
//import com.nic.ev.ifms.model.beneficiary;
//import com.nic.ev.ifms.repo.FilesSendTranckRepo;
//import com.nic.ev.ifms.repo.IFMSIntegrationTrackRepo;
//import com.nic.ev.ifms.repo.XMLBankDetailsRepo;
//import com.nic.ev.utils.SignerUtil;
//
//@Service
//public class AccountValidation {
//	@Autowired
//	XMLBankDetailsRepo xmlBankDetailsRepo;
//	@Autowired
//	FilesSendTranckRepo fileTrackRepo;
//
//	@Autowired
//	ConnectSystemAndFileUploadController connectSystemAndFileUploadController;
//	@Autowired
//	ConnectSystemThroughSFTPController connectSystemThroughSFTPController;
//	@Autowired
//	IFMSIntegrationTrackRepo iFMSIntegrationTrackRepo;
//	
//	@Autowired
//	SignerUtil signerUtil;
//
//	
//	static int i = 0;
//	@Value("${certificate.path}")
//	String certificate;
//	@Value("${file.xmlFileStorePath}")
//	String xmlFileStorePath;
//	@Value("${file.inpFileStorePath}")
//	String zipFileStorePath;
//	@Value("${departmentCode}")
//	String deptCode;
//	@Value("${serviceCode}")
//	String serviceCode;
//	public XMLAVFileFormat getXml() throws IOException{
//		try {
//			//System.out.println(connectSystemThroughSFTPController.setupJsch());
////			List<?> result = new ArrayList<>();
//			long millis = System.currentTimeMillis();
//			java.sql.Date toDay = new java.sql.Date(millis);
////		
////		List<XMLBankDetailsModel> beneficiary = xmlBankDetailsRepo.findAll();
////
////		List<List<?>> listOfBankDetails = new ArrayList<>();
////		listOfBankDetails.add(beneficiary);
//
//			List<XMLBankDetailsModel> bankDetails = new ArrayList<XMLBankDetailsModel>();
////			bankDetails.addAll(xmlBankDetailsRepo.findByOpDt(toDay));
//			bankDetails.addAll(xmlBankDetailsRepo.find20DummyRecordsFromDisbursedList());  //	 ---  for 20 records from already disbursed list
//			long today = new java.util.Date().getTime();
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
//			DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//			LocalDateTime now = LocalDateTime.now();
//			String date = dtf.format(now);
//			XMLAVFileFormat xmlBankDetails = new XMLAVFileFormat();
//			if (bankDetails.size() != 0) {
//				int count = 1;
//				FileSendTrackModel fileRecord = fileTrackRepo.findByFileSendDate(new java.util.Date());
//				String countForFileName = "001";
//				if (fileRecord != null) {
//					count = fileRecord.getNoOfFilesSend() + 1;
//					int length = String.valueOf(count).length();
//					if (length == 1) {
//						countForFileName = "00" + String.valueOf(count);
//					} else if (length == 2) {
//						countForFileName = "0" + String.valueOf(count);
//					} else if (length == 3) {
//						countForFileName = String.valueOf(count);
//					} else {
//						countForFileName = "000";
//					}
//					FileSendTrackModel fileStat = new FileSendTrackModel();
//					fileStat.setFileNames(fileRecord.getFileNames() + "," + xmlFileStorePath + "AV"+ deptCode +serviceCode + date + "INP"
//							+ countForFileName + ".xml");
//					fileStat.setFileSendDate(toDay);
//					fileStat.setNoOfFilesSend(count);
//					fileStat.setId(fileRecord.getId());
//					fileTrackRepo.save(fileStat);
//				} else {
//					// count = fileRecord.getNoOfFilesSend() + 1;
//
//					int length = String.valueOf(count).length();
//					if (length == 1) {
//						countForFileName = "00" + String.valueOf(count);
//					} else if (length == 2) {
//						countForFileName = "0" + String.valueOf(count);
//					} else if (length == 3) {
//						countForFileName = String.valueOf(count);
//					} else {
//						countForFileName = "000";
//					}
//					FileSendTrackModel fileStat = new FileSendTrackModel();
//					fileStat.setFileNames(xmlFileStorePath + "AV"+ deptCode +serviceCode + date + "INP" + countForFileName + ".xml");
//					fileStat.setFileSendDate(toDay);
//					fileStat.setNoOfFilesSend(1);
//					fileTrackRepo.save(fileStat);
//				}
//
//				XMLFileDetails fd = new XMLFileDetails();
//
//				fd.setIntgCode("24");
//				fd.setServiceCode("01");
//				String todayDt = dtf2.format(now);
//				fd.setFileDate(todayDt);
//				fd.setFileName("AV"+ deptCode +serviceCode + date + "INP" + countForFileName);
//				fd.setFileSerialNumber("001");
//				fd.setNoOfBeneficiary(bankDetails.size());
//				fd.setDbtFlag('Y');
//				List<Object> bankDetails1 = new ArrayList<>(bankDetails);
//				bankDetails1.add(fd);
//
//				java.util.Date todayDate = new java.util.Date();
//				// if((todayDate.getHours() == 17) && (todayDate.getMinutes() == 11) &&
//				// (todayDate.getSeconds() == 10)) {
//				xmlBankDetails.setFileDetails(fd);
//				xmlBankDetails.setBeneficiary(bankDetails);
//
//				// System.out.println();
//				// For Downloading File
//				String xmlBankDetailsString = exportToXML(xmlBankDetails);
//
//				byte[] xmlBankDetailsBytes = xmlBankDetailsString.getBytes();
//
//				// String formattedDate = dtf2.format(now);
//
//				String pathName = xmlFileStorePath + "AV"+ deptCode +serviceCode + date + "INP" + countForFileName + ".xml";
//				Path path = Paths.get(pathName);
//				// File newFile = new File("");
//				String s = new String(xmlBankDetailsBytes);
//				System.out.println("XMLString"+ s);
//				Files.write(path, xmlBankDetailsBytes);
//				/*****************************************
//				 * Code to make Zip File
//				 **********************************************/////
//				File file = new File(path.toUri());
//				File fileName = SignerUtil.signAndZipFile(file, certificate);
//			try {
//				connectSystemThroughSFTPController.whenUploadFileUsingJsch_thenSuccess(fileName);
//			
//			
////				 connectSystemAndFileUploadController.uploadFileToIFMS(fileName);
//				for (XMLBankDetailsModel custDetails : bankDetails) {
//					IFMSIntegrationTrack ifmsIntegration = new IFMSIntegrationTrack();
//					ifmsIntegration.setFileName("AV"+ deptCode +serviceCode + date + "INP" + countForFileName + ".xml");
//					ifmsIntegration.setPushDate(todayDate);
//					ifmsIntegration.setPushStatus("Y");
//					ifmsIntegration.setAccNo(custDetails.getAccNo());
//					ifmsIntegration.setName(custDetails.getName());
//					ifmsIntegration.setIfsc(custDetails.getIfscCode());
//					ifmsIntegration.setApplNo(custDetails.getApplNo());
//					ifmsIntegration.setUniqueId(
//							custDetails.getApplNo() + "/" + "AV"+ deptCode +serviceCode + date + "INP" + countForFileName + ".xml");
//
//					iFMSIntegrationTrackRepo.save(ifmsIntegration);
//				}
//			}
//			catch(Exception e){
//				System.out.println(e);
//			}
//				// connectSystemThroughSFTPController.whenDownloadFileUsingJsch_thenSuccess(fileName);
//
//				/////////////////////////////////////////////////////////////// now.getHour()+"-"+now.getMinute()+"-"+now.getSecond()
////			String zipFilename = "D:\\NIC\\Applications\\EVCell\\IFMS_Integration\\zip-files\\" + "AV0101" + date
////					+ "INP" + count + ".zip";
////			File zipFile = new File(zipFilename);
////			FileOutputStream fos = new FileOutputStream(zipFile);
////			ZipOutputStream zos = new ZipOutputStream(fos);
////			FileUtils fu = new FileUtils(); 
////			fu.zipFile(path.toString(), zos);
////			// zipFile(fileName2, zos);
////			zos.close();
//				/******************************************************************************************************************/
//				// }
////          i = i+1;
//			}
//			return xmlBankDetails;
//
//		} catch (Exception e) {
//			System.out.println(e);
//			return null;
//
//		}
//
//	}
//	public Map<String, String> downloadAckFiles() throws IOException {
//		List<Map<String,String>> ifmsIntegrationList = iFMSIntegrationTrackRepo.findByAckStatus(null);
//		System.out.println(ifmsIntegrationList.size());
//		String message = connectSystemAndFileUploadController.downloadACK(ifmsIntegrationList);
//		Map<String, String> msg = new HashMap<>();
//		msg.put("message", message);
//		return msg;
//	}
//
//	
//	public Map<String, String> readAckFile() throws Exception {
////		String zipFilePath = "D:\\NIC\\Applications\\EVCell\\IFMS_Integration\\ACKAV010120220509INP001.zip";
//		//String zipFilePath = "D:\\NIC\\Applications\\EVCell\\IFMS_Integration\\zip-files\\AV010120220426INP003.zip";
//		String zipFilePath ="D:\\AV\\ACK\\ACKAV010120220630INP001.zip";
//		File file = new File(zipFilePath);
//		signerUtil.unzipFile(file);
//		InputStream inputStream = new FileInputStream(file);
//		Map<String, String> msg = new HashMap<>();
//		final int extensionIndex = file.getAbsolutePath().indexOf(".");
//		
//		String noExtPath = file.getAbsolutePath().substring(0, extensionIndex); 
//		String sigPath = noExtPath+ "\\ACKAV010120220630INP001.sig";
//		File xmlFile = new File(noExtPath+"\\ACKAV010120220630INP001" + SignerUtil.XMLExtension);
//		File signatureFile = new File(sigPath);
//		String certFilePath = "/certificates/ifms_public_key.cer" ;
//		final StringBuffer sb = new StringBuffer();
//		String str;
//		File sigFile;
//		String message;
//		byte[] xmlFileContent = {};
//		try {
//			
//			inputStream = new FileInputStream(signatureFile);
//			final InputStreamReader streamReader = new InputStreamReader(inputStream);
//			@SuppressWarnings("resource")
//			final BufferedReader r = new BufferedReader(streamReader);
//			while ((str = r.readLine()) != null) {
//				sb.append(str);
//			}
//			
//			sigFile = new File(sigPath);
//			System.out.println(sigFile.exists());
//			
//			if (sigFile.exists()) {
////				errorBean.setError_code("EX00010");
////				errorBean.setError_msg("Signature File not found");
////				errorBean.setStatusFlag("10");
////			} else {
////				log.debug("exist status 2 : " + sigFile.exists());
//				xmlFileContent = Files.readAllBytes(xmlFile.toPath());
//				byte[] sigFileContent = Files.readAllBytes(sigFile.toPath());
//				byte signature[] = Base64.decodeBase64(sigFileContent);
//			   InputStream targetStream = new FileInputStream(sigFile);
//	
//					try{
//						 message = signerUtil.verifySignatures(xmlFile, targetStream, certFilePath);
//						
////					if(signerUtil.verifySignature(xmlFileContent, signature, certFilePath)) {
////					 message = "Successfull";
////					}else {
////						message = "Cetrification error";
////					}
//					}catch(Exception e) {
//						message = "Certification Error";
//				}
//					 
//				
////				
//			msg.put("message", message);
////				log.debug("exist status 3 : " + sigFile.exists());
//		}
//			else {
//				msg.put("message", "Failed");
//			}
//			
////		
//			//Scanner s = new Scanner(inputStream).useDelimiter("\\A");
//			//String result = s.hasNext() ? s.next() : "";
//			//result = result.substring(40, result.length());
//			String result = new String(xmlFileContent);
//			
//			result= result.substring(38, result.length());
//			System.out.println(result);
//			XMLACKFileFormat que= xmlToObject(result,xmlFile);
////			System.out.println(que.getBeneficiaries());
////			XMLAVFileFormat que = convertFromXMLToJson(result);
//
////		        JAXBContext jaxbContext = JAXBContext.newInstance(XMLAVFileFormat.class);  
////		   
////		        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
////		        XMLAVFileFormat que= (XMLAVFileFormat) jaxbUnmarshaller.unmarshal(file);  
////		          
////		        System.out.println(que.getFileDetails());  
////		        System.out.println("Answers:");   
//			 message = "Successfull";
//			
//			msg.put("message", message);
//			
//			XMLFileDetails fileDetails = que.getFileDetails();
//			
//			try {
//				List<beneficiary> list = que.getBeneficiaries();
//			for (beneficiary ans : list)
//				System.out.println(ans.benfAccountNo + " " + ans.benfIfsc + "  " + ans.ackErrorCode);
//			}
//			catch (Exception e) {
//				
//			}
//			System.out.println(fileDetails.getIntgCode());
//			System.out.println(fileDetails.getAckStatus());
//			return msg;
//
//		} catch (Exception e) {
//			msg.put("message",e.getMessage());
//			System.out.println(e);
//			return msg;
//		}
//		
//		
//	}
//	public Map<String, String> readXmlFileDemo() throws IOException, XMLStreamException, JAXBException {
//		File xmlFile = new File("D:\\AV\\ACK\\ACKAV010120220509INP001.xml");
//		byte[] xmlFileContent = {};
//		xmlFileContent = Files.readAllBytes(xmlFile.toPath());
//		String result = new String(xmlFileContent);
//		Map<String, String> msg = new HashMap<>();
//		
//		result= result.substring(38, result.length());
//		System.out.println(result);
//		XMLACKFileFormat que= xmlToObject(result,xmlFile);
////		System.out.println(que.getBeneficiaries());
////		XMLAVFileFormat que = convertFromXMLToJson(result);
//		//System.out.println(que.getBeneficiaries().get(0).benfAccountNo);
//
////	        JAXBContext jaxbContext = JAXBContext.newInstance(XMLAVFileFormat.class);  
////	   
////	        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
////	        XMLAVFileFormat que= (XMLAVFileFormat) jaxbUnmarshaller.unmarshal(file);  
////	          
////	        System.out.println(que.getFileDetails());  
////	        System.out.println("Answers:");   
//		 String message = "Successfull";
////		
//		msg.put("message", message);
//		XMLFileDetails fileDetails = que.getFileDetails();
//		
//		try {
//			List<beneficiary> list = que.getBeneficiaries();
//		for (beneficiary ans : list)
//			System.out.println(ans.benfAccountNo + " " + ans.benfIfsc + "  " + ans.ackErrorCode);
//		}
//		catch (Exception e) {
//			
//		}
//		System.out.println(fileDetails.getIntgCode());
//		System.out.println(fileDetails.getAckStatus());
//		return msg;
//		
//	}
//	
//	public static XMLACKFileFormat xmlToObject(String xmlString, File xmlFile)
//			throws XMLStreamException, UnsupportedEncodingException, JAXBException {
//		try {
//		InputStream stream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
//		JAXBContext jaxbContext2 = JAXBContext.newInstance(XMLACKFileFormat.class);
//		Unmarshaller jaxbUnmarshaller2 = jaxbContext2.createUnmarshaller();
//		XMLACKFileFormat responseObject = (XMLACKFileFormat) jaxbUnmarshaller2.unmarshal(xmlFile);
//		// XMLInputFactory factory = XMLInputFactory.newInstance();
//		// XMLEventReader someSource = factory.createXMLEventReader(stream);
//		// JAXBElement<XMLAVFileFormat> userElement =
//		// jaxbUnmarshaller2.unmarshal(someSource, XMLAVFileFormat.class);
//		// XMLAVFileFormat responseObject = userElement.getValue();
//		return responseObject;
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	public String exportToXML(XMLAVFileFormat xmlBankDetails) throws JsonProcessingException {
//		XmlMapper xmlMapper = new XmlMapper();
//		String personXml = xmlMapper.writeValueAsString(xmlBankDetails);
//		personXml = "<?xml  version=\"1.0\"  encoding=\"UTF-8\"?>" + personXml;
//		return personXml;
//	}
//
//	public XMLAVFileFormat convertFromXMLToJson(String xmlString) throws IOException {
//		XmlMapper xmlMapper = new XmlMapper();
//		JsonNode node = xmlMapper.readTree(xmlString.getBytes());
//
//		String personXml = xmlMapper.writeValueAsString(node);
//		ObjectMapper mapper = new ObjectMapper();
//		XMLAVFileFormat data = mapper.readValue(personXml, XMLAVFileFormat.class);
//		return data;
//	}
//
//}
