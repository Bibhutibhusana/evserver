package com.nic.ev.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
	
	
	

}
