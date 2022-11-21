package com.nic.ev.ifms.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nic.ev.ifms.iservice.IAuthenticationTokenAndSEKTableService;
import com.nic.ev.ifms.model.webservice.AuthenticationResponse;
import com.nic.ev.ifms.model.webservice.AuthenticationTokenAndSEKTable;
import com.nic.ev.ifms.repo.AuthenticationTokenAndSEKTableRepo;
import com.nic.ev.utils.FileUtils;
import com.nic.ev.utils.SignerUtil;

@Service
public class AuthenticationTokenAndSEKTableService implements IAuthenticationTokenAndSEKTableService{
	@Autowired
	AuthenticationTokenAndSEKTableRepo authRepo;
	
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
	private SignerUtil signerUtil;
	
	@Autowired
	private FileUtils fileUtils;
	
	 private static SecretKeySpec secretKey;
	  private static byte[] key;


	public byte[] publicKey;

	public AuthenticationResponse getAuthToken() throws Exception {
		String appkey = createAppkey();
		AppKey appKey = new AppKey(appkey);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(appKey);

		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("clientId", clientId);
		headers.add("clientSecret", clientSecret);
//		TrustStrategy acceptingTrustStrategy = (x509Certificates, s) -> true;
//		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
//				.build();
//		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
//		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
//		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//		requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate();
//		HttpEntity<JsonObject> entity = new HttpEntity<JsonObject>(jsonObject, headers);
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		AuthenticationResponse authResponse = new AuthenticationResponse();
		try {
			ResponseEntity<String> res = restTemplate.exchange(
					"https://uat.odishatreasury.gov.in/bdbillreceivingws/0.1/authenticate", HttpMethod.POST, entity,
					String.class);
			System.out.println("result is" + res.getBody());
			authResponse = new ObjectMapper().readValue(res.getBody(), AuthenticationResponse.class);
				if ((authResponse.getData().getAuthToken() != null) && (authResponse.data.getSek() != null)) {
					AuthenticationTokenAndSEKTable authData = new AuthenticationTokenAndSEKTable();
					authData.setAppKey(publicKey);
					authData.setEncryptedAppKey(appkey);
					authData.setAuthToken(authResponse.getData().getAuthToken());
					authData.setClientId(clientId);
					authData.setClientSecret(clientSecret);
					authData.setOpDate(new Date());
					authData.setSek(authResponse.getData().getSek());
					byte[] decryptedSek = getDecryptedSEK(authData.getSek(),authData.getAppKey());
					authData.setDecryptedSek(decryptedSek);;
					try {
						saveAuthentication(authData); 
					} catch (Exception e) {
	
					}
				}
			} catch (Exception e) {
				authResponse.setErrorMessage(e.getMessage());
				return authResponse;
			}
		return authResponse;
		}

	private static final String CIPHER = "AES";

	public String createAppkey() throws Exception {	   
		byte[] byteArray = appKeyGenerator();
		
		publicKey = byteArray;
//		publicKey = java.util.Base64.getEncoder().encodeToString(byteArray);
		System.out.println("public Key is " +publicKey);
		String f = new String(
				"D:\\NIC\\Applications\\EVCell\\IFMS_Integration\\ifms-integration\\STA_public_key\\STA\\publicKey");
		PublicKey publicKey = fileUtils.readPublicKey(f);
		byte[] secret = signerUtil.encrypt(publicKey, byteArray);
		String encodedString = java.util.Base64.getEncoder().encodeToString(secret);
		return encodedString;
	}

	public byte[] appKeyGenerator() throws NoSuchAlgorithmException {
		javax.crypto.KeyGenerator keyGenerator = javax.crypto.KeyGenerator.getInstance(CIPHER);
		keyGenerator.init(256);
		SecretKey keySpec = keyGenerator.generateKey();
		return keySpec.getEncoded();
	}


	

	  public static void setKey(final byte[] myKey) {
	    MessageDigest sha = null;
	    try {
//	      key = myKey.getBytes("UTF-8");
//	      sha = MessageDigest.getInstance("SHA-1");
//	      key = sha.digest(key);
//	      key = Arrays.copyOf(key, 16);
	      secretKey = new SecretKeySpec(myKey, "AES");
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	  
	    byte[] decrypt(final String strToDecrypt,  byte[] secret) {
		    try {
		      setKey(secret);
		      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
		      cipher.init(Cipher.DECRYPT_MODE, secretKey);
		      return cipher.doFinal(Base64.getDecoder()
		        .decode(strToDecrypt));
		    } catch (Exception e) {
		      System.out.println("Error while decrypting: " + e.toString());
		      return e.getMessage().getBytes();
		    }
		    
		  }
	    
	    String encrypt(String strToEncrypt, byte[] secret) {
	    	 SecretKeySpec  secretKey;
	    	 try {
//	   	      key = myKey.getBytes("UTF-8");
//	   	      sha = MessageDigest.getInstance("SHA-1");
//	   	      key = sha.digest(key);
//	   	      key = Arrays.copyOf(key, 16);
	    	  secretKey = new SecretKeySpec(secret, "AES");
	    	  Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		      return Base64.getEncoder().encodeToString( cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
	   	    } catch (Exception e) {
	   	      e.printStackTrace();
	   	      return e.getMessage();
	   	    }
	    	 
	    	 
	    }

	@Override
	public List<AuthenticationTokenAndSEKTable> getListOfAuthentication() {
		// TODO Auto-generated method stub
		return authRepo.findAll();
	}

	@Override
	public AuthenticationTokenAndSEKTable saveAuthentication(AuthenticationTokenAndSEKTable authData) {
		// TODO Auto-generated method stub
		return authRepo.save(authData);
	}

	public byte[] getDecryptedSEK(String sek, byte[] appKey) {
		// TODO Auto-generated method stub

		System.out.println(appKey);
//		String f = new String(
//				"D:\\NIC\\Applications\\EVCell\\IFMS_Integration\\ifms-integration\\STA_public_key\\STA\\publicKey");
//		PublicKey publicKey = fileUtils.readPublicKey(f);
//		String appKey = SignerUtil.decrypt(publicKey,encryptedAppKey.getBytes());
		byte[] decryptSek = decrypt(sek,appKey);
		
		return decryptSek;
	}
	

}
