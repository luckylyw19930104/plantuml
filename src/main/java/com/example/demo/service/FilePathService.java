package com.example.demo.service;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.FileContent;
import com.example.demo.pojo.FilePath;
import com.example.demo.repository.FilePathRepository;
import com.example.demo.util.FileUtil;
import com.example.demo.util.PlantUmlUtil;
import com.example.demo.util.decodeUtil;

import net.sourceforge.plantuml.asciiart.UmlCharArea;
 
@Service
public class FilePathService {
	
	
	@Autowired
	private FilePathRepository filePathRepository;
	
	public String Upload(@RequestParam("file") MultipartFile file) {
		if(!file.isEmpty()) {	
			String fileName = file.getOriginalFilename();		
			String path = "./src/main/resources/static/png";
			String GroupId = "MTC";
			String ProjectId = "test";
			try {			
				FileUtil.fileupload(file.getBytes(), path, fileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String umlFile = "";
			FilePath biaopath = new FilePath();
			biaopath.setPath("http://localhost:8080/png/"+fileName);
			biaopath.setUml(umlFile);
			biaopath.setProjectId(ProjectId);
			biaopath.setGroupId(GroupId);
			filePathRepository.save(biaopath);			
		}
		return "success";	
	}
	
	public void Generate(@RequestParam("encode") String encode) throws IOException {
		String uml = decodeUtil.getUmlSource(encode);
		try {
			PlantUmlUtil.PlantUMLGenerate(uml, encode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//FileUtil.copyImage();
	}
	
	public String UploadDatabase(@RequestParam("encode") String encode) throws IOException {
			String imageName = encode + ".png";
			//String path = "/Users/luckylyw19930104/eclipse-workspace/plantuml/src/main/resources/static/png";
			String GroupId = "MTC";
			String ProjectId = "test";
			String uml = decodeUtil.getUmlSource(encode);
			
			FilePath biaopath = new FilePath();
			biaopath.setPath("http://localhost:8080/"+imageName);
			biaopath.setUml(uml);
			biaopath.setProjectId(ProjectId);
			biaopath.setGroupId(GroupId);
			filePathRepository.save(biaopath);			
		
		return "success";	
	}
	
	public List<FilePath> SelectByGroup(@RequestParam("groupId") String groupId) {
		return filePathRepository.findUserByGroupName(groupId);
	}
}
