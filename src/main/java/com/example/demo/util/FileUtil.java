package com.example.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 
public class FileUtil {
	
	public static void fileupload(byte[] file,String filePath,String fileName) throws IOException {
			
		File targetfile = new File(filePath);
		if(targetfile.exists()) {
			targetfile.mkdirs();
		}
		
		
		FileOutputStream out = new FileOutputStream(filePath+fileName);
	    out.write(file);
	    out.flush();
	    out.close();
	}
	
	public static void copyImage() throws IOException {
        FileInputStream fis = new FileInputStream("/Users/luckylyw19930104/Documents/123.png");
        FileOutputStream fos = new FileOutputStream("/Users/luckylyw19930104/eclipse-workspace/plantuml/src/main/resources/static/png/123.png");
        int len = 0;
        while ((len = fis.read()) != -1) {
            fos.write(len);
        }
        
        fos.close(); 
        fis.close(); 
        
	}
	
}
