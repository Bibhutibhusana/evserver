package com.nic.ev.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.exception.BusinessException;
import com.nic.ev.repo.UserStatusRepo;

@RestController
@CrossOrigin
@RequestMapping("/")
public class MessageController {

	@Autowired
	UserStatusRepo userRepo;
	@PostMapping("/otp")
	private String getOtp(@RequestBody Map<String, String> ot) throws BusinessException {

		String numbers = "0123456789";
		String regnNo = ot.get("regn_no");
		String mob = ot.get("mobile_no");
		char[] otp = new char[4];
		mob="9658621918"; 
		SecureRandom rndm_method = new SecureRandom();
		URLConnection myURLConnection = null;
		URL myURL = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;

		for (int i = 0; i < 4; i++) {
			otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
		}

		String mainUrl = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=odipms.sms&pin=G6%25hK0%24gS1&message=Hi%20your%20e-Vehicle%20no%20"
				+ regnNo + "%20registration%20OTP%20is%20" + String.valueOf(otp) + ".%20&mnumber=" + mob
				+ "&signature=ODISTA&dlt_entity_id=1401613730000030174&dlt_template_id=1407164569630839439";
		try {
			SSLContext ssl_ctx = SSLContext.getInstance("TLS");
			TrustManager[] trust_mgr = get_trust_mgr();
			ssl_ctx.init(null, trust_mgr, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
//	        	StringBuilder sb = new StringBuilder(mainUrl);
//	        	mainUrl = sb.toString();
			myURL = new URL(mainUrl);
			if (!myURL.getHost().endsWith(".sms.gov.in") ||
			        !myURL.getProtocol().equals("http") &&
			        !myURL.getProtocol().equals("https")) {
				throw new BusinessException("Remote Access Denied");
			    }
			myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			HttpURLConnection httpConn = (HttpURLConnection) myURLConnection;
			int respcd = httpConn.getResponseCode();
			String resString = "";
			if (respcd == 200) {
				inputStream = httpConn.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(inputStreamReader);
				String line = "";
				while ((line = br.readLine()) != null) {
					resString = resString + line;
				}
				br.close();
			}
		} catch (Exception e) {
			throw new BusinessException("Input Stream Issue in Service Layer"+e.getMessage());
		} finally {
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException ioex) {
					throw new BusinessException("Input Stream close Issue in Service Layer"+ioex.getMessage());
				}
			}
		}

		return String.valueOf(otp);
	}

	@PostMapping("/sendSuccessRegistrationMsg")
	private Map<String, String> sendSuccessRegistrationMsg(@RequestBody Map<String, String> ot)  throws BusinessException {

		String regnNo = ot.get("regn");
		String mob = ot.get("mob");
		String applNo = ot.get("applNo");
		String rto = ot.get("rto");
		mob="9658621918"; 
		Map<String, String> mp = new HashMap<>();
		URLConnection myURLConnection = null;
		URL myURL = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;

		String mainUrl = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=odipms.sms&pin=G6%25hK0%24gS1&message=You%20have%20successfully%20registered%20for%20Electric%20Vehicle%20subsidy%20with%20Application%20No%20"
				+ applNo + "%20for%20Vehicle%20No%20" + regnNo + "%20in%20RTO%20" + rto
				+ ".Please%20note%20down%20the%20Application%20No%20for%20future%20reference.&mnumber=" + mob
				+ "&signature=ODISTA&dlt_entity_id=1401613730000030174&dlt_template_id=1407164862462769724";
		try {
			SSLContext ssl_ctx = SSLContext.getInstance("TLS");
			TrustManager[] trust_mgr = get_trust_mgr();
			ssl_ctx.init(null, trust_mgr, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
//	        	StringBuilder sb = new StringBuilder(mainUrl);
//	        	mainUrl = sb.toString();
			myURL = new URL(mainUrl);
			if (!myURL.getHost().endsWith(".sms.gov.in") ||
			        !myURL.getProtocol().equals("http") &&
			        !myURL.getProtocol().equals("https")) {
				throw new BusinessException("Remote Access Denied");
			    }
			myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			HttpURLConnection httpConn = (HttpURLConnection) myURLConnection;
			int respcd = httpConn.getResponseCode();
			String resString = "";
			if (respcd == 200) {
				mp.put("msg", "sent");
				inputStream = httpConn.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(inputStreamReader);
				String line = "";
				while ((line = br.readLine()) != null) {
					resString = resString + line;

				}
				br.close();
			}

		} catch (Exception e) {
			throw new BusinessException("Input Stream Issue in Service Layer"+e.getMessage());
		} finally {
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException ioex) {
					throw new BusinessException("Input Stream close Issue in Service Layer"+ioex.getMessage());
				}
			}
		}
		return mp;
	}

	@PostMapping("/sendApprovedMsg")
	private Map<String, String> sendApprovedMsg(@RequestBody Map<String, String> ot)  throws BusinessException {

		String mob = ot.get("mob");
		String applNo = ot.get("applNo");
		String rto = ot.get("rto");
		mob="9658621918"; 
		Map<String, String> mp = new HashMap<>();
		URLConnection myURLConnection = null;
		URL myURL = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;

		String mainUrl = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=odipms.sms&pin=G6%25hK0%24gS1&message=Your%20Application%20No%20"
				+ applNo + "%20for%20Electric%20Vehicle%20subsidy%20is%20approved%20in%20RTO%20" + rto + ".&mnumber="
				+ mob + "&signature=ODISTA&dlt_entity_id=1401613730000030174&dlt_template_id=1407164862500018175";

		try {
			SSLContext ssl_ctx = SSLContext.getInstance("TLS");
			TrustManager[] trust_mgr = get_trust_mgr();
			ssl_ctx.init(null, trust_mgr, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
//	        	StringBuilder sb = new StringBuilder(mainUrl);
//	        	mainUrl = sb.toString();
			myURL = new URL(mainUrl);
			if (!myURL.getHost().endsWith(".sms.gov.in") ||
			        !myURL.getProtocol().equals("http") &&
			        !myURL.getProtocol().equals("https")) {
				throw new BusinessException("Remote Access Denied");
			    }
			myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			HttpURLConnection httpConn = (HttpURLConnection) myURLConnection;
			int respcd = httpConn.getResponseCode();
			String resString = "";
			if (respcd == 200) {
				mp.put("msg", "sent");
				inputStream = httpConn.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(inputStreamReader);
				String line = "";
				while ((line = br.readLine()) != null) {
					resString = resString + line;

				}
				br.close();
			}

		} catch (Exception e) {
			throw new BusinessException("Input Stream Issue in Service Layer"+e.getMessage());
		} finally {
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException ioex) {
					throw new BusinessException("Input Stream close Issue in Service Layer"+ioex.getMessage());
				}
			}
		}
		return mp;
	}

	@PostMapping("/sendRejectedMsg")
	private Map<String, String> sendRejectedMsg(@RequestBody Map<String, String> ot)  throws BusinessException {

		String regnNo = ot.get("regn");
		String mob = ot.get("mob");
		String applNo = ot.get("applNo");
		String rto = ot.get("rto");
		String reason = ot.get("reason");
		mob="9658621918"; 
		Map<String, String> mp = new HashMap<>();
		URLConnection myURLConnection = null;
		URL myURL = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;

		String mainUrl = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=odipms.sms&pin=G6%25hK0%24gS1&message=Your%20Application%20No%20"
				+ applNo + "%20of%20Vehicle%20No%20" + regnNo
				+ "%20for%20Electric%20Vehicle%20subsidy%20is%20rejected%20by%20RTO-" + rto + ",due%20to%20" + reason
				+ ".Please%20verify%20the%20criteria%20of%20Electric%20Vehicle%20Subsidy%20and%20apply%20again.&mnumber="
				+ mob + "&signature=ODISTA&dlt_entity_id=1401613730000030174&dlt_template_id=1407164862500018175";

		try {
			SSLContext ssl_ctx = SSLContext.getInstance("TLS");
			TrustManager[] trust_mgr = get_trust_mgr();
			ssl_ctx.init(null, trust_mgr, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
//	        	StringBuilder sb = new StringBuilder(mainUrl);
//	        	mainUrl = sb.toString();
			myURL = new URL(mainUrl);
			if (!myURL.getHost().endsWith(".sms.gov.in") ||
			        !myURL.getProtocol().equals("http") &&
			        !myURL.getProtocol().equals("https")) {
				throw new BusinessException("Remote Access Denied");
			    }
			myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			HttpURLConnection httpConn = (HttpURLConnection) myURLConnection;
			int respcd = httpConn.getResponseCode();
			String resString = "";
			if (respcd == 200) {
				mp.put("msg", "sent");
				inputStream = httpConn.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(inputStreamReader);
				String line = "";
				while ((line = br.readLine()) != null) {
					resString = resString + line;

				}
				br.close();
			}

		} catch (Exception e) {
			throw new BusinessException("Input Stream Issue in Service Layer"+e.getMessage());
		} finally {
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException ioex) {
					throw new BusinessException("Input Stream close Issue in Service Layer"+ioex.getMessage());
				}
			}
		}
		return mp;
	}

	@PostMapping("/sendRevertedMsg")
	private Map<String, String> sendRevertedMsg(@RequestBody Map<String, String> ot) throws BusinessException  {
		String regnNo = ot.get("regn");
		String mob = ot.get("mob");
		String applNo = ot.get("applNo");
		String rto = ot.get("rto");
		mob="9658621918"; 
		String reason = ot.get("reason");
		Map<String, String> mp = new HashMap<>();
		URLConnection myURLConnection = null;
		URL myURL = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		@SuppressWarnings("unused")
		String newReason = "";
		String temp = "";

		for (String val : reason.split(" ")) {
			if (temp.equals("")) {
				temp = temp + val;
			} else {
				temp = temp + "%20" + val;
			}

		}

		newReason = temp;

		String mainUrl = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=odipms.sms&pin=G6%25hK0%24gS1&message=Your%20Application%20No%20"
				+ applNo + "%20of%20Vehicle%20No%20" + regnNo
				+ "%20for%20Electric%20Vehicle%20subsidy%20is%20reverted%20by%20RTO-" + rto + ",due%20to%20" + reason
				+ ".Please%20submit%20again%20with%20due%20modification.&mnumber=" + mob
				+ "&signature=ODISTA&dlt_entity_id=1401613730000030174&dlt_template_id=1407164862545133513";

		try {
			SSLContext ssl_ctx = SSLContext.getInstance("TLS");
			TrustManager[] trust_mgr = get_trust_mgr();
			ssl_ctx.init(null, trust_mgr, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
//	        	StringBuilder sb = new StringBuilder(mainUrl);
//	        	mainUrl = sb.toString();
			myURL = new URL(mainUrl);
			if (!myURL.getHost().endsWith(".sms.gov.in") ||
			        !myURL.getProtocol().equals("http") &&
			        !myURL.getProtocol().equals("https")) {
				throw new BusinessException("Remote Access Denied");
			    }
			myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			HttpURLConnection httpConn = (HttpURLConnection) myURLConnection;
			int respcd = httpConn.getResponseCode();
			String resString = "";
			if (respcd == 200) {
				mp.put("msg", "sent");
				inputStream = httpConn.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(inputStreamReader);
				String line = "";
				while ((line = br.readLine()) != null) {
					resString = resString + line;

				}
				br.close();
			}

		} catch (Exception e) {
			throw new BusinessException("Input Stream Issue in Service Layer"+e.getMessage());
		} finally {
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException ioex) {
					throw new BusinessException("Input Stream close Issue in Service Layer"+ioex.getMessage());
				}
			}
		}
		return mp;
	}

	public List<Map<String, Object>> sendDisbursedMsg(String regn)  throws BusinessException {

		List<Map<String, Object>> mp = userRepo.getDataForDisburseMsg(regn);

		Map<String, Object> m = new HashMap<>();
		String regnNo = regn;
		String mob = mp.get(0).get("mob_no").toString();
		String applNo = mp.get(0).get("appl_no").toString();
		String amount = mp.get(0).get("sub_amnt").toString();
		mob="9658621918"; 
		URLConnection myURLConnection = null;
		URL myURL = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;

		String mainUrl = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=odipms.sms&pin=G6%25hK0%24gS1&message=The%20Electric%20Vehicle%20subsidy%20amount%20Rs."
				+ amount + "%20for%20Vehicle%20No%20" + regnNo + "%20and%20Application%20No%20" + applNo
				+ "%20has%20been%20disbursed%20to%20your%20bank%20account.&mnumber=" + mob
				+ "&signature=ODISTA&dlt_entity_id=1401613730000030174&dlt_template_id=1407164862521134790";

		try {
			SSLContext ssl_ctx = SSLContext.getInstance("TLS");
			TrustManager[] trust_mgr = get_trust_mgr();
			ssl_ctx.init(null, trust_mgr, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
//	        	StringBuilder sb = new StringBuilder(mainUrl);
//	        	mainUrl = sb.toString();
			myURL = new URL(mainUrl);
			if (!myURL.getHost().endsWith(".sms.gov.in") ||
			        !myURL.getProtocol().equals("http") &&
			        !myURL.getProtocol().equals("https")) {
				throw new BusinessException("Remote Access Denied");
			    }
			myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			HttpURLConnection httpConn = (HttpURLConnection) myURLConnection;
			int respcd = httpConn.getResponseCode();
			String resString = "";
			if (respcd == 200) {
				m.put("msg", "sent");
				inputStream = httpConn.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader br = new BufferedReader(inputStreamReader);
				String line = "";
				while ((line = br.readLine()) != null) {
					resString = resString + line;

				}
				br.close();
			}

		} catch (Exception e) {
			throw new BusinessException("Input Stream Issue in Service Layer"+e.getMessage());
		} finally {
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException ioex) {
					throw new BusinessException("Input Stream close Issue in Service Layer"+ioex.getMessage());
				}
			}
		}
		return mp;
	}

	private TrustManager[] get_trust_mgr() {
		TrustManager[] certs = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String t) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String t) {
			}
		} };
		return certs;
	}

	@SuppressWarnings("unused")
	private static class SavingTrustManager implements X509TrustManager {
		private X509TrustManager tm = null;

		private X509Certificate[] chain;

		@SuppressWarnings("unused")
		SavingTrustManager(X509TrustManager tm) {
			this.tm = tm;
		}

		public X509Certificate[] getAcceptedIssuers() {
			throw new UnsupportedOperationException();
		}

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			throw new UnsupportedOperationException();
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			this.chain = chain;
			tm.checkServerTrusted(chain, authType);
		}

	}
}
