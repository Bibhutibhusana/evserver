package com.nic.ev.ifms.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.nic.ev.ifms.model.XMLACKFileFormat;
import com.nic.ev.utils.SignerUtil;

@RestController
@CrossOrigin
public class ConnectSystemAndFileUploadController {
	FTPClient ftpClient = new FTPClient();

	@Value("${ftpserver.name}")
	String serverName;
	@Value("${ftpserver.port}")
	int port;
	@Value("${ftpserver.username}")
	String username;
	@Value("${ftpserver.password}")
	String password;

	@Value("${file.inpFileStorePath}")
	String inpFileStorePath;

	@Value("${file.ackFileStorePath}")
	String ackFileStorePath;

	// @GetMapping("/connectSystem")
	String uploadFileToIFMS(File uploadFile) throws IOException {
		String res = getConnection();
		// Collection<String> listOfFile = listFiles("/icservice");
//		for (String file : listOfFile) {
//			System.out.println("files : " + file);
//		}
		File f = new File(uploadFile.getAbsolutePath());
		boolean output = putFileToPath(f, "/icservice/AV/INP");
		System.out.println("Put file : " + output);
		// downloadFile(uploadFile.getName(), zipFileStorePath +uploadFile.getName());

		return res;
	}

	@SuppressWarnings("resource")
	public
	String downloadACK(List<Map<String,String>> ifmsIntegrationList) throws IOException {
		try {
		String res = getConnection();
		int count=0;
		for(Map<String,String> file : ifmsIntegrationList) {

			String fileName = SignerUtil.removeExtn(file.get("file_name"));
			FileOutputStream out = new FileOutputStream(ackFileStorePath+fileName+".zip");
			try {	
//			ftpClient.retrieveFile("/icserver/AV/ACK/"+file.getFileName(), out);
			////*****************************  File Read from sftp server **********************////////////
			
			InputStream b = ftpClient.retrieveFileStream("/icservice/AV/ACK/"+fileName+".zip");
			System.out.println(b);
			if(b != null) {
				count += 1;
			ObjectInputStream ois = new ObjectInputStream(b);
			XMLACKFileFormat object = (XMLACKFileFormat) ois.readObject();
			System.out.println(object.getBeneficiaries().get(0).benfAccountNo);
			System.out.println(object.getFileDetails().getAckStatus());
			}
			}
			catch(Exception e){
				continue;
			}
			
		}
		return "successfully downloaded "+count+" files";
		}
		catch(Exception e) {
			return "Downloading File failed";
		}finally {
			closeConnection();
		}
		
	}

	String getConnection() throws IOException {
///////////////////////////// FTP credentials/////////////////////////////////////////////////////////////////////////

		try {
			ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

			ftpClient.connect(serverName, port);

			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftpClient.disconnect();
				throw new IOException("Exception in connecting to FTP Server");
			}

			ftpClient.login(username, password);
			// ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

			return "Connected Successfully";
		} catch (Exception e) {
			System.out.println("for other failures : " + e);
			return "error";
//		       e.printStackTrace();
		}

	}

	void closeConnection() throws IOException {
		ftpClient.disconnect();

	}

	Collection<String> listFiles(String path) throws IOException {
		FTPFile[] files = ftpClient.listFiles(path);
		return Arrays.stream(files).map(FTPFile::getName).collect(Collectors.toList());
	}

	/* For Uploading file into Server */
	boolean putFileToPath(File file, String path) throws IOException {
		String firstRemoteFile = "/icservice/AV/INP/"+file.getName();
		InputStream inputStream = new FileInputStream(file);

		System.out.println("Start uploading first file");
		boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
		inputStream.close();
		if (done) {
			System.out.println("The first file is uploaded successfully.");
		}
		// boolean res = ftpClient.storeFile(path, new FileInputStream(file));
		return done;
	}

	/* For Downloading */
	void downloadFile(String source, String destination) throws IOException {
		// ftpClient.setFileTransferMode(FTPClient.BLOCK_TRANSFER_MODE);
		// ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		FileOutputStream out = new FileOutputStream(destination);
		ftpClient.retrieveFile(source, out);

	}
}
