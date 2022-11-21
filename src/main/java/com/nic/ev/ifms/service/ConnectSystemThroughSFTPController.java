//package com.nic.ev.ifms.service;
//
//
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.jcraft.jsch.ChannelSftp;
//import com.jcraft.jsch.JSch;
//import com.jcraft.jsch.JSchException;
//import com.jcraft.jsch.Session;
//import com.jcraft.jsch.SftpException;
//
//@RestController
//@CrossOrigin
//public class ConnectSystemThroughSFTPController {
//	@Value("${ftpserver.name}")
//	String serverName;
//	@Value("${ftpserver.port}")
//	int port;
//	@Value("${ftpserver.username}") 
//	String username ;
//	@Value("${ftpserver.password}")
//	String password ;
//
//	@Value("${file.inpFileStorePath}")
//	String zipFileStorePath;
//	
//	///////////////////////////////////////////////////////////// File for SFTP for Treasury Transaction //////////////////////////////////////
//
//	ChannelSftp setupJsch() throws JSchException {
//	    JSch jsch = new JSch();
//	    
//	    //jsch.setKnownHosts("/Users/Dell/.ssh/known_hosts");
//	    System.out.println(password);
//	    Session jschSession = jsch.getSession(username, serverName, 22);
//	    jschSession.setPassword(password);
//	    java.util.Properties config = new java.util.Properties(); 
//	    config.put("StrictHostKeyChecking", "no");
//	    jschSession.setConfig(config);
//	    try {
//	    jschSession.connect();
//	    }
//	    catch(Exception e) {
//	    	System.out.println(e);
//	    }
//	    return (ChannelSftp) jschSession.openChannel("sftp");
//	}
//	
//	//////////////////////////// For Uploading File ////////////////////////////////////////
//	
//	
//	public void whenUploadFileUsingJsch_thenSuccess(File fileName) throws Exception {
//		ChannelSftp channelSftp = setupJsch();
//	    channelSftp.connect();
//	 
//	    String localFile = fileName.getAbsolutePath();
//	    String remoteDir = "AV/INP/";
//	    try {
//		 InputStream inputStream = new FileInputStream(fileName);
//	    //channelSftp.put(inputStream, remoteDir+fileName.getName());
//		 channelSftp.put(localFile,remoteDir+fileName.getName());
//	 }
//	 catch(Exception e) {
//		 System.out.println("During Upload: "+e);
//	 }
//	 finally {
//		 if(channelSftp != null) {
//	    channelSftp.exit();
//		 }
//	 }
//	}
//	
//	///////////////////////  For Downloading File ////////////////////////////////////////////
//	
//	public void whenDownloadFileUsingJsch_thenSuccess(File file) throws JSchException, SftpException {
//	    ChannelSftp channelSftp = setupJsch();
//	    channelSftp.connect();
//	 
//	    String remoteFile = file.getName();
//	    String remoteDir = "AV/INP/";
//	    String localDir = zipFileStorePath;
//	 try {
//		 System.out.println(localDir+"   "+remoteFile + "    "+ remoteDir);
//	    channelSftp.get(remoteDir+remoteFile, localDir + remoteFile);
//	 }
//	 catch(Exception e) {
//		 System.out.println("During Download: "+e);
//	 }
//	 
//	    channelSftp.exit();
//	}
//
//}
