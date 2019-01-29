package com.example.demo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.security.spec.EncodedKeySpec;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.common.FileContent;
import com.example.demo.pojo.FilePath;

import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;
import net.sourceforge.plantuml.SourceStringReader;

public class PlantUmlUtil {
	//private final static String path = "/Users/luckylyw19930104/Documents/test.txt";
	public static void PlantUMLGenerate(String uml, String encode) throws IOException {
		String path = FileContent.imagePath + encode + ".png";
		File f= new File(path) ;
		OutputStream png = null;   // 准备好一个输出的对象
		png = new FileOutputStream(f)  ; 
		SourceStringReader reader = new SourceStringReader(uml);
		// Write the first image to "png"
		String desc = reader.outputImage(png).getDescription();
		// Return a null string if no generation
	}
	
	public static String Txt2String(String encode) throws IOException{
		String path = FileContent.filePath + encode + ".txt"; //TODO: add encode to upload
		StringBuffer buffer = new StringBuffer();
        BufferedReader bf= new BufferedReader(new FileReader(path));
        String s = null;
        while((s = bf.readLine())!=null){
            buffer.append(s.trim() + "\n");
        }
        String xml = buffer.toString();
        return xml;
	}
	
	public static boolean execShell(String cmd){
		boolean result = false;
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor(5, TimeUnit.SECONDS);
			int exitCode = process.exitValue();
			if(exitCode == 0) {
				result = true;
			}
		}catch(Exception e){
			//TODO: logger;
		}
		return result;
	}
}
