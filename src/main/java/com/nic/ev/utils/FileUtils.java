package com.nic.ev.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Component;  

@Component
public class FileUtils {
	
	public void zipFile(String fileName, ZipOutputStream zos) throws IOException  
    {  
      final int BUFFER = 1024;  
      BufferedInputStream bis = null;  
      try  
      {  
        File file = new File(fileName);  
        FileInputStream fis = new FileInputStream(file);  
        bis = new BufferedInputStream(fis, BUFFER);            
    
        // ZipEntry --- Here file name can be created using the source file  
        ZipEntry zipEntry = new ZipEntry(file.getName());          
        zos.putNextEntry(zipEntry);  
        byte data[] = new byte[BUFFER];  
        int count;  
        while((count = bis.read(data, 0, BUFFER)) != -1)   
        {  
          zos.write(data, 0, count);  
        }    
        // close entry every time  
        zos.closeEntry();  
      }   
      finally  
      {  
        try   
        {  
          bis.close();  
        }   
      
        catch (IOException e)   
        {  
          e.printStackTrace();  
        }    
//System.out.println("successfully created...");  
      }  
    }  
	
	
	public byte[] readFileBytes(String filename) throws IOException
	{   
	    Path path = Paths.get(filename);
	    return Files.readAllBytes(path);        
	}

	public PublicKey readPublicKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
	{
	    X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(readFileBytes(filename));
	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	    return keyFactory.generatePublic(publicSpec);       
	}

	public PrivateKey readPrivateKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException
	{
	    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(readFileBytes(filename));
	    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	    return keyFactory.generatePrivate(keySpec);     
	}

}
