package com.example.demo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;

public class PlantUmlUtil {
	
	//private final static String path = "/Users/luckylyw19930104/Documents/test.uml";
	public static void PlantUMLGenerate(String filePath) throws IOException {
		File source = new File(filePath);
		SourceFileReader reader = new SourceFileReader(source);
		List<GeneratedImage> list = reader.getGeneratedImages();
		// Generated files
		File png = list.get(0).getPngFile();
	}
	
	public static String Txt2String() throws IOException{
		StringBuffer buffer = new StringBuffer();
        BufferedReader bf= new BufferedReader(new FileReader("/Users/luckylyw19930104/Documents/test.txt"));
        String s = null;
        while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
            buffer.append(s.trim());
        }
        String xml = buffer.toString();
        return xml;
	}
}
