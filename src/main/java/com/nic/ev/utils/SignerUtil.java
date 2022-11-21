package com.nic.ev.utils;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableFile;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationStore;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class SignerUtil {
	
	
	private static final Logger log = LoggerFactory.getLogger(SignerUtil.class);

	 // private final static Logger LOGGER =  Logger.getLogger(Signer);  

	static String certificate;
	private static final int BUFFER_SIZE = 16384;

	public static final String signatureExtention = ".sig", ZipExtention = ".zip", TXTExtension = ".txt",
			XMLExtension = ".xml";

//	public void generateXMLDigitalSignature(String originalXmlFilePath,  
//		    String destnSignedXmlFilePath, String privateKeyFilePath, String publicKeyFilePath) {  
//		    //Get the XML Document object  
//		    Document doc = getXmlDocument(originalXmlFilePath);  
//		    //Create XML Signature Factory  
//		    XMLSignatureFactory xmlSigFactory = XMLSignatureFactory.getInstance("DOM");  
//		    PrivateKey privateKey = new KryptoUtil().getStoredPrivateKey(privateKeyFilePath);  
//		    DOMSignContext domSignCtx = new DOMSignContext(privateKey, doc.getDocumentElement());  
//		    Reference ref = null;  
//		    SignedInfo signedInfo = null;  
//		    try {  
//		        ref = xmlSigFactory.newReference("", xmlSigFactory.newDigestMethod(DigestMethod.SHA1, null),
//		         Collections.singletonList(xmlSigFactory.newTransform(Transform.ENVELOPED,  
//		                (TransformParameterSpec) null)), null, null);  
//		        signedInfo = xmlSigFactory.newSignedInfo(
//		            xmlSigFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE,  
//		                (C14NMethodParameterSpec) null),
//		            xmlSigFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null),  
//		            Collections.singletonList(ref));  
//		    } catch (NoSuchAlgorithmException ex) {  
//		        ex.printStackTrace();  
//		    } catch (InvalidAlgorithmParameterException ex) {  
//		        ex.printStackTrace();  
//		    }  
//		    //Pass the Public Key File Path  
//		    KeyInfo keyInfo = getKeyInfo(xmlSigFactory, publicKeyFilePath);  
//		    //Create a new XML Signature  
//		    XMLSignature xmlSignature = xmlSigFactory.newXMLSignature(signedInfo, keyInfo);  
//		    try {  
//		        //Sign the document  
//		        xmlSignature.sign(domSignCtx);  
//		    } catch (MarshalException ex) {  
//		        ex.printStackTrace();  
//		    } catch (XMLSignatureException ex) {  
//		        ex.printStackTrace();  
//		    }  
//		    //Store the digitally signed document inta a location  
//		    storeSignedDoc(doc, destnSignedXmlFilePath);  
//		}  

	////////////////////////////////////////////////////////// Sign and Zip the
	////////////////////////////////////////////////////////// File//////////////////////////////////////////////////////////

	public static File signAndZipFile(final File inputXmlFile, String certPath) throws Exception {
		certificate = certPath;
		//System.out.println(certificate);
		// log.debug("Sign and Zip " + inputXmlFile.getAbsolutePath());
		String zipFileName = null;
		String filePath = "";
		String fileName = "";

		byte[] signedBytes = null;
		final byte[] data = new byte[SignerUtil.BUFFER_SIZE];
		org.bouncycastle.cms.CMSTypedData content = null;
		File zipOutputFile = null;
		FileOutputStream fileOutputStream = null;
		FileInputStream fileInputStream = null;
		ZipOutputStream zipOutputStream = null;
		int nRead = -1;
		try {
			filePath = inputXmlFile.getAbsolutePath();
			fileName = SignerUtil.removeExtn(inputXmlFile.getName());
			final String path = filePath.substring(0, inputXmlFile.getAbsolutePath().lastIndexOf(File.separator) + 1);
			final X509Certificate signerCert = SignerUtil.getX509Certificate();
			final java.security.PrivateKey privateKey = SignerUtil.getPrivateKey();
			//System.out.println("Serial Number = " + signerCert.getSerialNumber());
			Security.addProvider(new BouncyCastleProvider());
			CMSSignedDataGenerator generator = new CMSSignedDataGenerator();
			/*************************************            ***************************************************************************///
			ContentSigner sha1Signer = new JcaContentSignerBuilder("SHA256WITHRSAENCRYPTION").build(privateKey);

			generator.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(
					new JcaDigestCalculatorProviderBuilder().setProvider("BC").build()).build(sha1Signer, signerCert));
			// generator.addSigners(privateKey, signerCert,
			// CMSSignedDataGenerator.DIGEST_SHA1);
			final ArrayList<X509Certificate> list = new ArrayList<>();
			list.add(signerCert);
//			final CertStore _certStore = CertStore.getInstance("Collection", new CollectionCertStoreParameters(list),
//					BouncyCastleProvider.PROVIDER_NAME);
			// generator.addCertificatesAndCRLs(_certStore);

			//// Store certs = new JcaCertStore(list);
			// generator.addCertificates(certs);
			content = new CMSProcessableFile(inputXmlFile);
			final CMSSignedData signedData = generator.generate(content, false);
			signedBytes = signedData.getEncoded();
			System.out.println("Encrypted hash" + signedBytes);
			zipOutputFile = new File(path + fileName + SignerUtil.ZipExtention);
			fileOutputStream = new FileOutputStream(zipOutputFile);
			zipOutputStream = new ZipOutputStream(fileOutputStream);
			final ZipEntry zipEntry = new ZipEntry(fileName + SignerUtil.signatureExtention);
			zipOutputStream.putNextEntry(zipEntry);
			zipOutputStream.write(signedBytes, 0, signedBytes.length);
			final ZipEntry zipTxtEntry = new ZipEntry(fileName + SignerUtil.XMLExtension);
			zipOutputStream.putNextEntry(zipTxtEntry);
			fileInputStream = new FileInputStream(inputXmlFile);
			while ((nRead = fileInputStream.read(data, 0, data.length)) != -1) {
				zipOutputStream.write(data, 0, nRead);
			}
			//return zipOutputFile;
			//zipFileName = zipOutputFile.getAbsolutePath();
			//// log.debug("Zip file created " + zipFileName);
		} catch (final SecurityException se) {
			throw se;
		} catch (Exception e) {
			// log.error("Error in signature generation", e);
			System.out.println(e);
		} finally {
			if (null != fileInputStream) {
				fileInputStream.close();
			}
			if (null != zipOutputStream) {
				zipOutputStream.close();
				zipOutputStream.flush();
			}
			if (null != fileOutputStream) {
				fileOutputStream.close();
			}
		}
		return zipOutputFile;
	}

//	/// Extract Zip file and verify
//	public static String extractZipAndVerify(final File zipFile, String bankName, String fileType,
//			Map<String, String> sftpCredentials) throws IOException, SQLException {
//		//final ErrorPropertyBean errorBean = new ErrorPropertyBean();
//		String zipFilePath = "", fileName = "";
//		FileInputStream inputStream = null;
//		FileOutputStream outputStream = null;
//		SignerUtil signerUtil = null;
//		File txtFile = null, sigFile = null;
//		File xmlFile = null;
//		final DateFormat formatter = new SimpleDateFormat("ddMMyyyy");
//		final Date today = new Date();
//		final String todayDate = formatter.format(today);
//		final StringBuffer sb = new StringBuffer();
//		String str;
//
//		try {
//			//errorBean.setError_code("");
//			final String cerFilepath = Utils.getRbiCertPath(bankName);
//			if (null != cerFilepath) {
//				fileName = SignerUtil.removeExtn(zipFile.getName());
//				signerUtil = new SignerUtil();
//				signerUtil.unzipFile(zipFile, errorBean);
//				zipFilePath = zipFile.getAbsolutePath().substring(0,
//						zipFile.getAbsolutePath().lastIndexOf(File.separator) + 1) + fileName + File.separator;
//				log.debug("zipFilePath " + zipFilePath);
//				txtFile = new File(zipFilePath + fileName + SignerUtil.TXTExtension);
//				log.debug("txtFile " + txtFile.getAbsolutePath());
//				if (!txtFile.exists()) {
//					errorBean.setError_code("EX0009");
//					errorBean.setError_msg("Text File not found");
//					errorBean.setStatusFlag("9");
//				} else {
//					inputStream = new FileInputStream(txtFile);
//					final InputStreamReader streamReader = new InputStreamReader(inputStream);
//					@SuppressWarnings("resource")
//					final BufferedReader r = new BufferedReader(streamReader);
//					while ((str = r.readLine()) != null) {
//						sb.append(str);
//					}
//					log.info("encrypted text file content----" + sb.toString());
//					final String encryptionKeyFile = PropertyUtil.getValue("key.name");
//					final byte[] secretKey = AESUtility.readKeyFromFile(encryptionKeyFile, errorBean);
//					final byte[] decryptXmlContent = AESUtility.decrypt(sb.toString().replace("\r", ""), secretKey);
//					log.debug(new String(decryptXmlContent));
//					xmlFile = new File(zipFilePath + fileName + Utils.XML);
//					outputStream = new FileOutputStream(xmlFile);
//					outputStream.write(decryptXmlContent);
//
//					sigFile = new File(zipFilePath + fileName + SignerUtil.signatureExtention);
//					log.debug("sigFile " + sigFile);
//					log.debug("exist status 1 : " + sigFile.exists());
//					if (!sigFile.exists()) {
//						errorBean.setError_code("EX00010");
//						errorBean.setError_msg("Signature File not found");
//						errorBean.setStatusFlag("10");
//					} else {
//						log.debug("exist status 2 : " + sigFile.exists());
//						signerUtil.verifySignatures(xmlFile, new FileInputStream(sigFile), cerFilepath, errorBean);
//						log.debug("exist status 3 : " + sigFile.exists());
//					}
//				}
//			} else {
//				errorBean.setError_code("EX0017");
//				errorBean.setError_msg("Public key certificate not found");
//				errorBean.setStatusFlag("17");
//			}
//		} catch (final Exception e) {
//			log.error("Error in extract and verify", e);
//			errorBean.setError_code("EX0015");
//			errorBean.setError_msg("Error in extract and verify");
//			errorBean.setStatusFlag("15");
//		}
//		if ("".equals(errorBean.getError_code())) {
//			fileName = xmlFile.getCanonicalPath();
//		} else {
//			DatabaseUtils.saveFileErrors(errorBean.getError_code(), errorBean.getError_msg(), fileName);
//			DatabaseUtils.updateDbState(errorBean.getStatusFlag(), fileName, fileName, fileType, "",
//					errorBean.getError_msg());
//			generateRespAckFile(fileName, fileName, fileType, errorBean.getStatusFlag(), todayDate,
//					sftpCredentials.get("SFTP_URL"), sftpCredentials.get("SFTP_USER"), sftpCredentials.get("SFTP_PWD"),
//					Integer.parseInt(sftpCredentials.get("SFTP_PORT_UAT")), bankName);
//			fileName = null;
//		}
//		log.info("fileName---" + fileName);
//		return fileName;
//	}
//
	public static  String removeExtn(final String fileNameWithExtn) {
		final int extensionIndex = fileNameWithExtn.indexOf(".");
		if (extensionIndex == -1) {
			return fileNameWithExtn;
		}
		return fileNameWithExtn.substring(0, extensionIndex);
	}


	public String verifySignatures(final File contentFile, final InputStream signedDataStream, final String cerFilePath) throws OperatorCreationException {
//		File contentFile = contentFile.
		CMSSignedData cmsSignedData = null;
		X509Certificate publiceKeyCertificate = null;
		final CMSProcessable signedContent = new CMSProcessableFile(contentFile);
		try {
			cmsSignedData = new CMSSignedData(signedContent, signedDataStream);
			Security.addProvider(new BouncyCastleProvider());
		//	java.security.cert.Certificate[] myCert = new java.security.cert.Certificate[] { (java.security.cert.Certificate) cmsSignedData.getCertificates()};
//			final CertStore certs = (CertStore) cmsSignedData.getCertificates();
//					("Collection",
//					BouncyCastleProvider.PROVIDER_NAME);
			 final Store store = cmsSignedData.getCertificates();
			final SignerInformationStore signers = cmsSignedData.getSignerInfos();
			final Collection<?> c = signers.getSigners();
			final Iterator<?> it = c.iterator();
			while (it.hasNext()) {
				X509Certificate cert = null;
				final SignerInformation signer = (SignerInformation) it.next();
				log.debug("Alg :" + signer.getDigestAlgOID());
				final Collection<?> certCollection = store.getMatches(signer.getSID());
				// store.getMatches(signer.getSID());//
				if (!certCollection.isEmpty()) {
					 JcaX509CertificateConverter converter = new JcaX509CertificateConverter()
				                .setProvider(new BouncyCastleProvider());
					final Iterator<?> certIt = certCollection.iterator();
					if (certIt.hasNext()) {
						cert = (X509Certificate) converter.getCertificate((X509CertificateHolder) certIt.next());
						final CertificateFactory cf = CertificateFactory.getInstance("X.509");
						publiceKeyCertificate = (X509Certificate) cf
								.generateCertificate(new FileInputStream(cerFilePath));
						log.debug("Shared public Key Sl : " + publiceKeyCertificate.getSerialNumber());//
						log.debug("File  Public Key Sl : " + cert.getSerialNumber());
						if (publiceKeyCertificate.getSerialNumber().compareTo(cert.getSerialNumber()) == 0) {
							log.debug("Serial Verification Success");
						} else {
							log.error("Serial Verification failed");
//							errorBean.setError_code("EX0013");
//							errorBean.setError_msg("Serial Verification failed");
//							errorBean.setStatusFlag("13");
							return "Certificate Verification Failed";
						}
						log.debug("Shared public Key not before date : " + publiceKeyCertificate.getNotBefore());//
						log.debug("Shared public Key not after date : " + publiceKeyCertificate.getNotAfter());//
						final Date today = new Date();
						if (today.after(publiceKeyCertificate.getNotAfter())) {
							log.error("Certificate expired");
//							errorBean.setError_code("EX0012");
//							errorBean.setError_msg("Certificate expired");
//							errorBean.setStatusFlag("12");
							return "Certificate Verification Failed";
						}
						log.debug("File Public Key not before date : " + cert.getNotBefore());
						log.debug("File Public Key not after date : " + cert.getNotAfter());
						SignerInformationVerifier s = new JcaSimpleSignerInfoVerifierBuilder()
								.setProvider(BouncyCastleProvider.PROVIDER_NAME).build(cert.getPublicKey());
						if (signer.verify(s)) {
							
							log.debug("Verification Success");
							return "Successfully Verified";
						} else {
							log.error("Verification failed");
//							errorBean.setError_code("EX0014");
//							errorBean.setError_msg("Signature Verification failed");
//							errorBean.setStatusFlag("14");
							return "Certificate Verification Failed";
						}
					}return "Certificate Verification Failed";
				}return "Certificate Verification Failed";
			}
			return "Certificate Verification Failed";
		} catch (final CMSException e) {
			log.error(e.getMessage(), e);
//			errorBean.setError_code("EX0014");
//			errorBean.setError_msg("Unable to read file content");
//			errorBean.setStatusFlag("14");
			return "Certificate Verification Failed";
		} catch (final CertificateException e) {
			log.error("Certificate validation failed", e);
//			errorBean.setError_code("EX0014");
//			errorBean.setError_msg("Certificate validation failed");
//			errorBean.setStatusFlag("14");
			return "Certificate Verification Failed";
		} catch (final FileNotFoundException e) {
			log.error("Public key certificate not found", e);
//			errorBean.setError_code("EX0017");
//			errorBean.setError_msg("Public key certificate not found");
//			errorBean.setStatusFlag("17");
			log.error(e.getMessage(), e);
//			errorBean.setError_code("EX0034");
//			errorBean.setError_msg("Provider Exception");
//			e.printStackTrace();
//		} catch (final CertStoreException e) {
//			log.error(e.getMessage(), e);
////			errorBean.setError_code("EX0034");
////			errorBean.setError_msg("Certificate Store Exception");
////			e.printStackTrace();
			return "Certificate Verification Failed";
		}
		
		catch(Exception e) {
			System.out.println(e);
			return "Certificate Verification Failed";
		}

	}

	
	
	
	
	
	
	
	
	
	
	
	
	public boolean verifySignature(byte[] data, byte[] signature, String keyFile) throws Exception {
		try {
		Signature sig = Signature.getInstance("SHA1withRSA");
//		byte[] dataBytes = Base64.getDecoder().decode(data);
		String sign  = new String(signature);
		
		sig.initVerify(getPublic(keyFile));
		sig.update(data);
//        String jwsHeaderBase64 = sign.substring(0,sign.indexOf(".."));
//        String jwsSignBase64 = sign.substring(sign.indexOf("..") + 2);
//        String dateToSign = new StringBuilder().append(jwsHeaderBase64).append('.').append(Base64.encodeBase64String(data.toString().getBytes("UTF-8"))).toString();
//        byte[] dataset = dateToSign.getBytes("UTF-8");
//        sig.update(dataset);
//        System.out.println(Base64.decodeBase64(jwsSignBase64).length);
//        System.out.println(Base64.decodeBase64(jwsSignBase64));
//        System.out.println(Base64.decodeBase64(Base64.decodeBase64(jwsSignBase64)).length);
//       // return signature.verify(Base64.decodeBase64(Base64.decodeBase64(jwsSignBase64)));
//
////blog.csdn.net/tcctcszhanghao/article/details/105230316
//       // final byte[] bytes = Base64Decoder.decode( URLDecoder.decode( sign, PayboxUtil.CHARSET ) );
		return sig.verify(signature);
		}
		catch(SignatureException e){
			System.out.println(e);
			return false;
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
		
		
	}
	
	//Method to retrieve the Public Key from a file
	public PublicKey getPublic(String filename) throws Exception {
		FileInputStream fin = new FileInputStream(filename);
		CertificateFactory f = CertificateFactory.getInstance("X.509");
		X509Certificate certificate = (X509Certificate)f.generateCertificate(fin);
		PublicKey pk = certificate.getPublicKey();
		
		return pk;
//		byte[] keyBytes = Files.readAllBytes(Paths.get(filename));
//		// X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.decodeBase64(keyBytes));
//		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
//		try {
//			KeyFactory kf = KeyFactory.getInstance("RSA");
//			return kf.generatePublic(spec);
//		  } catch (InvalidKeySpecException ignore) {
//		    try {
//		    	KeyFactory kf = KeyFactory.getInstance("DSA");
//				return kf.generatePublic(spec);
//		    } catch (InvalidKeySpecException ignore2) {
//		      try {
//		    	  KeyFactory kf = KeyFactory.getInstance("EC");
//					return kf.generatePublic(spec);
//		      } catch (InvalidKeySpecException e) {
//		    	  e.printStackTrace();
//		        throw new InvalidKeySpecException("Neither RSA, DSA nor EC worked", e);
//		      }
//		      catch(Exception e) {
//		    	  System.out.println(e);
//		    	  throw new InvalidKeySpecException("Neither RSA, DSA nor EC worked", e);
//		      }
//		    }
//		  }
		
		
	}
	
	
	
	
	
//	@SuppressWarnings("deprecation")
//	private void verifySignatures(final File contentFile, final InputStream signedDataStream, final String cerFilePath) {
//		KeyStore keyStore = KeyStore.getInstance("PKCS12");
//		keyStore.load(new FileInputStream("receiver_keytore.p12"), "changeit");
//		Certificate certificate = keyStore.getCertificate("receiverKeyPair");
//		PublicKey publicKey = certificate.getPublicKey();
//	}
//		CMSSignedData cmsSignedData = null;
//		X509Certificate publiceKeyCertificate = null;
//		final CMSProcessable signedContent = new CMSProcessableFile(contentFile);
//		try {
//			cmsSignedData = new CMSSignedData(signedContent, signedDataStream);
//			Security.addProvider(new BouncyCastleProvider());
//			final CertStore certs = cmsSignedData.getCertificatesAndCRLs("Collection",
//					BouncyCastleProvider.PROVIDER_NAME);
//			// final Store store = cmsSignedData.getCertificates();
//			final SignerInformationStore signers = cmsSignedData.getSignerInfos();
//			final Collection<?> c = signers.getSigners();
//			final Iterator<?> it = c.iterator();
//			while (it.hasNext()) {
//				X509Certificate cert = null;
//				final SignerInformation signer = (SignerInformation) it.next();
//				//log.debug("Alg :" + signer.getDigestAlgOID());
//				final Collection<?> certCollection = certs.getCertificates(signer.getSID());// store.getMatches(signer.getSID());//
//				if (!certCollection.isEmpty()) {
//					final Iterator<?> certIt = certCollection.iterator();
//					if (certIt.hasNext()) {
//						cert = (X509Certificate) certIt.next();
//						final CertificateFactory cf = CertificateFactory.getInstance("X.509");
//						publiceKeyCertificate = (X509Certificate) cf
//								.generateCertificate(new FileInputStream(cerFilePath));
//						//log.debug("Shared public Key Sl : " + publiceKeyCertificate.getSerialNumber());//
//						//log.debug("File  Public Key Sl : " + cert.getSerialNumber());
//						if (publiceKeyCertificate.getSerialNumber().compareTo(cert.getSerialNumber()) == 0) {
//							log.debug("Serial Verification Success");
//						} else {
//							//log.error("Serial Verification failed");
//							//errorBean.setError_code("EX0013");
//							//errorBean.setError_msg("Serial Verification failed");
//							//errorBean.setStatusFlag("13");
//							return;
//						}
//						//log.debug("Shared public Key not before date : " + publiceKeyCertificate.getNotBefore());//
//						//log.debug("Shared public Key not after date : " + publiceKeyCertificate.getNotAfter());//
//						final Date today = new Date();
//						if (today.after(publiceKeyCertificate.getNotAfter())) {
//							//log.error("Certificate expired");
//							//errorBean.setError_code("EX0012");
//							//errorBean.setError_msg("Certificate expired");
//							//errorBean.setStatusFlag("12");
//							return;
//						}
//						//log.debug("File Public Key not before date : " + cert.getNotBefore());
//						//log.debug("File Public Key not after date : " + cert.getNotAfter());
////						if (signer.verify(new JcaSimpleSignerInfoVerifierBuilder()
////								.setProvider(BouncyCastleProvider.PROVIDER_NAME).build(cert.getPublicKey()))) {
//						if (signer.verify(cert.getPublicKey(), BouncyCastleProvider.PROVIDER_NAME)) {
//							log.debug("Verification Success");
//						} else {
//							//log.error("Verification failed");
//							//errorBean.setError_code("EX0014");
//							//errorBean.setError_msg("Signature Verification failed");
//							//errorBean.setStatusFlag("14");
//							return;
//						}
//					}
//				}
//			}
//		
//			catch{
//				
//			}
////		} catch (final CMSException e) {
////			log.error(e.getMessage(), e);
////			errorBean.setError_code("EX0014");
////			errorBean.setError_msg("Unable to read file content");
////			errorBean.setStatusFlag("14");
////		} catch (final CertificateException e) {
////			log.error("Certificate validation failed", e);
////			errorBean.setError_code("EX0014");
////			errorBean.setError_msg("Certificate validation failed");
////			errorBean.setStatusFlag("14");
////		} catch (final FileNotFoundException e) {
////			log.error("Public key certificate not found", e);
////			errorBean.setError_code("EX0017");
////			errorBean.setError_msg("Public key certificate not found");
////			errorBean.setStatusFlag("17");
////		} catch (final NoSuchAlgorithmException e) {
////			log.error(e.getMessage(), e);
////			errorBean.setError_code("EX0034");
////			errorBean.setError_msg("No such Algorithm");
////			e.printStackTrace();
////		} catch (final NoSuchProviderException e) {
////			log.error(e.getMessage(), e);
////			errorBean.setError_code("EX0034");
////			errorBean.setError_msg("Provider Exception");
////			e.printStackTrace();
////		} catch (final CertStoreException e) {
////			log.error(e.getMessage(), e);
////			errorBean.setError_code("EX0034");
////			errorBean.setError_msg("Certificate Store Exception");
////			e.printStackTrace();
////		}
//
//	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	/**
//	 * @param zipFile
//	 * @throws Exception
//	 */
	public  void unzipFile(final File zipFile) throws Exception {
		ZipInputStream zipIn = null;
		String zipFilePath = "", destDirectory = "";
		ZipEntry entry = null;
		try {
			zipFilePath = zipFile.getAbsolutePath();
			destDirectory = zipFilePath.substring(0, zipFilePath.lastIndexOf(File.separator) + 1)
					+ SignerUtil.removeExtn(zipFile.getName());
			zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
			entry = zipIn.getNextEntry();
			while (entry != null) {
				final File dir = new File(destDirectory);
				if (!dir.isDirectory()) {
					dir.mkdir();
				}
				final String filePath = destDirectory + File.separator + entry.getName().replace('n', ' ').trim();
				if (!entry.isDirectory()) {
					this.extractFile(zipIn, filePath);
				} else {
					final File zipdir = new File(filePath);
					zipdir.mkdir();
				}
				zipIn.closeEntry();
				entry = zipIn.getNextEntry();
			}
		} catch (final Exception e) {
//			errorBean.setError_code("EX0015");
//			errorBean.setError_msg("Error unziping file");
//			errorBean.setStatusFlag("15");
//			log.error("Error unziping file", e);
			throw e;
		} finally {
			zipIn.close();
		}
	}
//
	/**
	 * @param zipIn
	 * @param filePath
	 * @throws Exception
	 **/
	public  void extractFile(final ZipInputStream zipIn, final String filePath) throws Exception {
		BufferedOutputStream bos = null;
		final byte[] bytesIn = new byte[SignerUtil.BUFFER_SIZE];
		int read = 0;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(filePath));
			while ((read = zipIn.read(bytesIn)) != -1) {
				bos.write(bytesIn, 0, read);
			}
		} catch (final Exception e) {
			//log.error("Error extracting zip file", e);
			throw e;
		} finally {
			bos.close();
		}
	}

	public static String changeExtension(final String actualFile, final String ext) {
		final int dotIndex = actualFile.lastIndexOf('.');
		final String actualExt = actualFile.substring(dotIndex, actualFile.length());
		final String filePath = actualFile.replaceFirst(actualExt, ext);
		return filePath;
	}

	private static X509Certificate getX509Certificate() throws NoSuchAlgorithmException, CertificateException,
			FileNotFoundException, IOException, KeyStoreException, UnrecoverableKeyException {
		Security.addProvider(new BouncyCastleProvider());
		final KeyStore ks = KeyStore.getInstance("PKCS12");
		// ks.load(new FileInputStream(Utils.getCertPath()),
		// Utils.getCertPwd().toCharArray());
//		// Build CMS
//		final X509Certificate signerCert = (X509Certificate) ks.getCertificate(Utils.getCertAlias());

		ks.load(new FileInputStream(certificate), "12345678".toCharArray());
		// Build CMS
		Enumeration<String> aliases = ks.aliases();
		String alias = "";
		while (aliases.hasMoreElements()) {
			alias = aliases.nextElement();
			// System.out.println(alias);
		}

		final X509Certificate signerCert = (X509Certificate) ks.getCertificate(alias);

		return signerCert;
		// return null;
	}

	private static PrivateKey getPrivateKey() throws NoSuchAlgorithmException, CertificateException,
			FileNotFoundException, IOException, UnrecoverableKeyException, KeyStoreException {
		Security.addProvider(new BouncyCastleProvider());
		final KeyStore ks = KeyStore.getInstance("PKCS12");
		// ks.load(new FileInputStream(Utils.getCertPath()),
		// Utils.getCertPwd().toCharArray());
		// final Key key = ks.getKey(Utils.getCertAlias(),
		// Utils.getCertPwd().toCharArray());

		ks.load(new FileInputStream(certificate), "12345678".toCharArray());
		Enumeration<String> aliases = ks.aliases();
		String alias = "";
		while (aliases.hasMoreElements()) {
			alias = aliases.nextElement();
			// System.out.println(alias);
		}
		final Key key = ks.getKey(alias, "12345678".toCharArray());
		// Sign
		// Key key = null;
		return (PrivateKey) key;
	}

//	@SuppressWarnings("unused")
//	private static void generateRespAckFile(String fileName, String fileRefNo, String filetype, String statusCode,
//			String todayDate, String hostname, String username, String password, int port, String bankName) {
//		String dest_dir = null;
//		final List<AccountValidation> fileList1 = new ArrayList<>();
//		final Map<String, String> sftpCredentials = new HashMap<>();
//		final AccountValidation accountValidation = new AccountValidation();
//		String eachFileName = "";
//		try {
//			log.info("Start Generating Response ACK File");
//			AccountValidationAck requestPayload = null;
//			requestPayload = new AccountValidationAck();
//			eachFileName = fileName;
//			String statusRemarks = "";
//			if (statusCode == "7") {
//				statusRemarks = "Encryption Key not Found";
//			} else if (statusCode == "8") {
//				statusRemarks = "Invalid Encryption key found";
//			} else if (statusCode == "9") {
//				statusRemarks = "Text file not found";
//			} else if (statusCode == "10") {
//				statusRemarks = "Signature file not found";
//			} else if (statusCode == "11") {
//				statusRemarks = "Digital Certificate found to be revoked";
//			} else if (statusCode == "12") {
//				statusRemarks = "Digital Certificate found to be expired";
//			} else if (statusCode == "13") {
//				statusRemarks = "Certificate Serial Mismatch";
//			} else if (statusCode == "14") {
//				statusRemarks = "Signature Verification failed";
//			} else if (statusCode == "15") {
//				statusRemarks = "Error in extract and verify";
//			} else if (statusCode == "17") {
//				statusRemarks = "Public Key not found";
//			}
//			sftpCredentials.put("SFTP_URL", hostname);
//			sftpCredentials.put("SFTP_PORT_UAT", String.valueOf(port));
//			sftpCredentials.put("SFTP_USER", username);
//			sftpCredentials.put("SFTP_PWD", password);
//
//			if ("TXN".equalsIgnoreCase(filetype)) {
//				dest_dir = Utils.PREFIX_SFTP_PROD_PATH + bankName + PropertyUtil.getValue(Utils.SFTP_TXN_ACK_PATH);
//			} else if ("RES".equalsIgnoreCase(filetype)) {
//				dest_dir = Utils.PREFIX_SFTP_PROD_PATH + bankName + PropertyUtil.getValue(Utils.SFTP_RES_ACK_PATH);
//			} else if ("FD".equalsIgnoreCase(filetype)) {
//				dest_dir = Utils.PREFIX_SFTP_PROD_PATH + bankName + PropertyUtil.getValue(Utils.SFTP_FD_ACK_PATH);
//			}
//
//			accountValidation.setRecordAckDate(todayDate);
//			accountValidation.setRecordAckRemarks(statusRemarks);
//			accountValidation.setRecordAckStatusCode(statusCode);
//			accountValidation.setRecordReferenceNumber(fileRefNo);
//			fileList1.add(accountValidation);
//
//			requestPayload.setAccountInformation(fileList1);
//			try {
//				final JAXBContext jaxbContext = JAXBContext.newInstance(AccountValidationAck.class);
//				final Marshaller marshaller = jaxbContext.createMarshaller();
//				marshaller.setProperty("jaxb.encoding", "UTF-8");
//				marshaller.setProperty("jaxb.fragment", Boolean.TRUE);
//				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//				marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",
//						"<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//				final String orgFileName = fileName;
//				String changedFileName = orgFileName.substring(0, orgFileName.length() - 3);
//				changedFileName = changedFileName + "ACK.xml";
//
//				final FileOutputStream outputStream = new FileOutputStream(
//						PropertyUtil.getValue("localWorkingDir") + File.separator + changedFileName);
//				marshaller.marshal(requestPayload, outputStream);
//				marshaller.marshal(requestPayload, System.out);
//				final File file = new File(PropertyUtil.getValue("localWorkingDir") + File.separator + changedFileName);
//
//				final boolean result = Utils.uploadResAckFile(file, dest_dir, sftpCredentials);
//				log.info("Result after Response file ack uploaded: " + result);
//
//			} catch (final JAXBException e) {
//				log.error("Unable to create XML", e);
//				e.printStackTrace();
//			}
//
//		} catch (final Exception e) {
//			log.error("Unable to create XML", e);
//			e.printStackTrace();
//		}
//
//	}
//
//	public static void main(final String[] a) throws Exception {
//		PropertyUtil.loadLocalProperties();
//		PropertyUtil.loadLocalProperties();
//	}
	
	public byte[] encrypt(PublicKey key, byte[] plaintext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
	    Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");   
	    cipher.init(Cipher.ENCRYPT_MODE, key);  
	    return cipher.doFinal(plaintext);
	}

	public static byte[] decrypt(PrivateKey key, byte[] ciphertext) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
	{
	    Cipher cipher = Cipher.getInstance("RSA-2048/PKCS1_OAEP_PADDING");   
	    cipher.init(Cipher.DECRYPT_MODE, key);  
	    return cipher.doFinal(ciphertext);
	}
}
