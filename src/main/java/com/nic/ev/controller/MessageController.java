package com.nic.ev.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.repo.UserStatusRepo;


@RestController
@CrossOrigin
@RequestMapping("/")
public class MessageController {
	
	@Autowired UserStatusRepo userRepo;
	
	URLConnection myURLConnection=null;
    URL myURL=null;
    BufferedReader reader=null;
	
	@PostMapping("/otp")
	private String getOtp(@RequestBody Map<String, String> ot){
		
		
		 String numbers = "0123456789";
		 String regnNo= ot.get("regn_no");
		 String mob = ot.get("mobile_no");
		 String mobileNo="7894966399";
		 char[] otp = new char[4];
		 
		 System.out.println(regnNo+" "+mob);		 
		 Random rndm_method = new Random();
		 
		  
	        
	  
	        for (int i = 0; i < 4; i++)
	        {
	            otp[i] =
	             numbers.charAt(rndm_method.nextInt(numbers.length()));
	        }
		 
		 String mainUrl = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=odipms.sms&pin=G6%25hK0%24gS1&message=Hi%20your%20e-Vehicle%20no%20"+regnNo+"%20registration%20OTP%20is%20"+String.valueOf(otp)+".%20&mnumber="+mob+"&signature=ODISTA&dlt_entity_id=1401613730000030174&dlt_template_id=1407164569630839439";
		 
	        
	        try {
	        	SSLContext ssl_ctx = SSLContext.getInstance("TLS");
                TrustManager[] trust_mgr = get_trust_mgr();
                ssl_ctx.init(null, trust_mgr, new SecureRandom()); 
                HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
//	        	StringBuilder sb = new StringBuilder(mainUrl);
//	        	mainUrl = sb.toString();
	        	myURL = new URL(mainUrl);
	        	myURLConnection = myURL.openConnection();
	        	myURLConnection.connect();
	        	HttpURLConnection httpConn = (HttpURLConnection) myURLConnection;
	        	int respcd = httpConn.getResponseCode();
	        	String resString="";
	        	if(respcd==200) {
	        		BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        resString = resString + line;
                    }
                    br.close();
	        	}
	        	System.out.println("resString :"+resString);
	        	

	        	
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
		return String.valueOf(otp);
	}
	
	@PostMapping("/sendSuccessRegistrationMsg")
	private Map<String,String> sendSuccessRegistrationMsg(@RequestBody Map<String, String> ot){
		
		
		 String numbers = "0123456789";
		 String regnNo= ot.get("regn");
		 String mob = ot.get("mob");
		 String applNo = ot.get("applNo");
		 String rto = ot.get("rto");
		 String mobileNo="7894966399";
		 Map<String, String> mp = new HashMap<>();
	
		 String mainUrl = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=odipms.sms&pin=G6%25hK0%24gS1&message=You%20have%20successfully%20registered%20for%20Electric%20Vehicle%20subsidy%20with%20Application%20No%20"+applNo+"%20for%20Vehicle%20No%20"+regnNo+"%20in%20RTO%20"+rto+".Please%20note%20down%20the%20Application%20No%20for%20future%20reference.&mnumber="+mob+"&signature=ODISTA&dlt_entity_id=1401613730000030174&dlt_template_id=1407164862462769724";
		 		
		 	
	        try {
	        	SSLContext ssl_ctx = SSLContext.getInstance("TLS");
                TrustManager[] trust_mgr = get_trust_mgr();
                ssl_ctx.init(null, trust_mgr, new SecureRandom()); 
                HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
//	        	StringBuilder sb = new StringBuilder(mainUrl);
//	        	mainUrl = sb.toString();
	        	myURL = new URL(mainUrl);
	        	myURLConnection = myURL.openConnection();
	        	myURLConnection.connect();
	        	HttpURLConnection httpConn = (HttpURLConnection) myURLConnection;
	        	int respcd = httpConn.getResponseCode();
	        	String resString="";
	        	if(respcd==200) {
	        		mp.put("msg", "sent");
	        		BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        resString = resString + line;
                        
                    }
                    br.close();
	        	}
	        	System.out.println("resString :"+resString);
	        	
	        	
	        	

	        	
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	        
	        
		return mp;
	}
	
	
	@PostMapping("/sendApprovedMsg")
	private Map<String,String> sendApprovedMsg(@RequestBody Map<String, String> ot){
		
		
		 String numbers = "0123456789";
//		 String regnNo= ot.get("regn");
		 String mob = ot.get("mob");
		 String applNo = ot.get("applNo");
		 String rto = ot.get("rto");
		 String mobileNo="7894966399";
		 Map<String, String> mp = new HashMap<>();
	
		 String mainUrl = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=odipms.sms&pin=G6%25hK0%24gS1&message=Your%20Application%20No%20"+applNo+"%20for%20Electric%20Vehicle%20subsidy%20is%20approved%20in%20RTO%20"+rto+".&mnumber="+mob+"&signature=ODISTA&dlt_entity_id=1401613730000030174&dlt_template_id=1407164862500018175";
		 		
		 	
	        try {
	        	SSLContext ssl_ctx = SSLContext.getInstance("TLS");
                TrustManager[] trust_mgr = get_trust_mgr();
                ssl_ctx.init(null, trust_mgr, new SecureRandom()); 
                HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
//	        	StringBuilder sb = new StringBuilder(mainUrl);
//	        	mainUrl = sb.toString();
	        	myURL = new URL(mainUrl);
	        	myURLConnection = myURL.openConnection();
	        	myURLConnection.connect();
	        	HttpURLConnection httpConn = (HttpURLConnection) myURLConnection;
	        	int respcd = httpConn.getResponseCode();
	        	String resString="";
	        	if(respcd==200) {
	        		mp.put("msg", "sent");
	        		BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        resString = resString + line;
                        
                    }
                    br.close();
	        	}
	        	System.out.println("resString :"+resString);
	        	
	        	
	        	

	        	
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	        
	        
		return mp;
	}
	
	@PostMapping("/sendRejectedMsg")
	private Map<String,String> sendRejectedMsg(@RequestBody Map<String, String> ot){
		
		
		 String numbers = "0123456789";
		 String regnNo= ot.get("regn");
		 String mob = ot.get("mob");
		 String applNo = ot.get("applNo");
		 String rto = ot.get("rto");
		 String reason = ot.get("reason");
		 String mobileNo="7894966399";
		 Map<String, String> mp = new HashMap<>();
	
		 String mainUrl = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=odipms.sms&pin=G6%25hK0%24gS1&message=Your%20Application%20No%20"+applNo+"%20of%20Vehicle%20No%20"+regnNo+"%20for%20Electric%20Vehicle%20subsidy%20is%20rejected%20by%20RTO-"+rto+",due%20to%20"+reason+".Please%20verify%20the%20criteria%20of%20Electric%20Vehicle%20Subsidy%20and%20apply%20again.&mnumber="+mob+"&signature=ODISTA&dlt_entity_id=1401613730000030174&dlt_template_id=1407164862500018175";
		 		
		 		
		 	
	        try {
	        	SSLContext ssl_ctx = SSLContext.getInstance("TLS");
                TrustManager[] trust_mgr = get_trust_mgr();
                ssl_ctx.init(null, trust_mgr, new SecureRandom()); 
                HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
//	        	StringBuilder sb = new StringBuilder(mainUrl);
//	        	mainUrl = sb.toString();
	        	myURL = new URL(mainUrl);
	        	myURLConnection = myURL.openConnection();
	        	myURLConnection.connect();
	        	HttpURLConnection httpConn = (HttpURLConnection) myURLConnection;
	        	int respcd = httpConn.getResponseCode();
	        	String resString="";
	        	if(respcd==200) {
	        		mp.put("msg", "sent");
	        		BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        resString = resString + line;
                        
                    }
                    br.close();
	        	}
	        	System.out.println("resString :"+resString);
	        	
	        	
	        	

	        	
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	        
	        
		return mp;
	}
	
	
	@PostMapping("/sendRevertedMsg")
	private Map<String,String> sendRevertedMsg(@RequestBody Map<String, String> ot){
		
		
		 String numbers = "0123456789";
		 String regnNo= ot.get("regn");
		 String mob = ot.get("mob");
		 String applNo = ot.get("applNo");
		 String rto = ot.get("rto");
		 String reason = ot.get("reason");
		 String mobileNo="7894966399";
		 Map<String, String> mp = new HashMap<>();
		 
		 String newReason="";
		 String temp = "";
		 
		 
		 
		 for(String val: reason.split(" ")) {
			 if(temp.equals("")) {
				 temp = temp+val;
			 }else {
				 temp = temp+"%20"+val;
			 }
			 
		 }
		 
		 newReason = temp;
	
		 String mainUrl = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=odipms.sms&pin=G6%25hK0%24gS1&message=Your%20Application%20No%20"+applNo+"%20of%20Vehicle%20No%20"+regnNo+"%20for%20Electric%20Vehicle%20subsidy%20is%20reverted%20by%20RTO-"+rto+",due%20to%20"+reason+".Please%20submit%20again%20with%20due%20modification.&mnumber="+mob+"&signature=ODISTA&dlt_entity_id=1401613730000030174&dlt_template_id=1407164862545133513";
		 		
		 System.out.println(newReason);
		 		System.out.println(mainUrl);
		 		
		 	
	        try {
	        	SSLContext ssl_ctx = SSLContext.getInstance("TLS");
                TrustManager[] trust_mgr = get_trust_mgr();
                ssl_ctx.init(null, trust_mgr, new SecureRandom()); 
                HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
//	        	StringBuilder sb = new StringBuilder(mainUrl);
//	        	mainUrl = sb.toString();
	        	myURL = new URL(mainUrl);
	        	myURLConnection = myURL.openConnection();
	        	myURLConnection.connect();
	        	HttpURLConnection httpConn = (HttpURLConnection) myURLConnection;
	        	int respcd = httpConn.getResponseCode();
	        	String resString="";
	        	if(respcd==200) {
	        		mp.put("msg", "sent");
	        		BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        resString = resString + line;
                        
                    }
                    br.close();
	        	}
	        	System.out.println("resString :"+resString);
	        	
	        	
	        	

	        	
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	        
	        
		return mp;
	}
	
	public List<Map<String, Object>> sendDisbursedMsg(String regn){
		
		
		List<Map<String, Object>> mp = userRepo.getDataForDisburseMsg(regn);
		
		Map<String, Object> m = new HashMap<>();
		
		 String numbers = "0123456789";
		 String regnNo= regn;
		 String mob = mp.get(0).get("mob_no").toString();
		 String applNo = mp.get(0).get("appl_no").toString();
		 String amount = mp.get(0).get("sub_amnt").toString();
//		 String reason = ot.get("reason");
		 String mobileNo="7894966399";
		 
		 System.out.println(mob);
	
		 String mainUrl = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=odipms.sms&pin=G6%25hK0%24gS1&message=The%20Electric%20Vehicle%20subsidy%20amount%20Rs."+amount+"%20for%20Vehicle%20No%20"+regnNo+"%20and%20Application%20No%20"+applNo+"%20has%20been%20disbursed%20to%20your%20bank%20account.&mnumber="+mob+"&signature=ODISTA&dlt_entity_id=1401613730000030174&dlt_template_id=1407164862521134790";

		 		
		 		
		 	
	        try {
	        	SSLContext ssl_ctx = SSLContext.getInstance("TLS");
                TrustManager[] trust_mgr = get_trust_mgr();
                ssl_ctx.init(null, trust_mgr, new SecureRandom()); 
                HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
//	        	StringBuilder sb = new StringBuilder(mainUrl);
//	        	mainUrl = sb.toString();
	        	myURL = new URL(mainUrl);
	        	myURLConnection = myURL.openConnection();
	        	myURLConnection.connect();
	        	HttpURLConnection httpConn = (HttpURLConnection) myURLConnection;
	        	int respcd = httpConn.getResponseCode();
	        	String resString="";
	        	if(respcd==200) {
	        		m.put("msg", "sent");
	        		BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        resString = resString + line;
                        
                    }
                    br.close();
	        	}
	        	System.out.println("resString :"+resString);
	        	
	        	
	        	

	        	
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }
	        
	       return mp; 
	        
	}
	
	private TrustManager[] get_trust_mgr() {
        TrustManager[] certs = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String t) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String t) {
            }
        }};
        return certs;
    }
	 private static class SavingTrustManager implements X509TrustManager {
	        private X509TrustManager tm = null;

			private X509Certificate[] chain;

			
	        SavingTrustManager(X509TrustManager tm) {
	            this.tm = tm;
	        }
	        
	        public X509Certificate[] getAcceptedIssuers() {
	            throw new UnsupportedOperationException();
	        }
	        public void checkClientTrusted(X509Certificate[] chain, String authType)
	                throws CertificateException {
	            throw new UnsupportedOperationException();
	        }
	        public void checkServerTrusted(X509Certificate[] chain, String authType)
	                throws CertificateException {
	            this.chain = chain;
	            tm.checkServerTrusted(chain, authType);
	        }
	        
	    }
}
