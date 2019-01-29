package com.example.demo.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.hibernate.mapping.Array;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.FileContent;
import com.example.demo.pojo.FilePath;
import com.example.demo.pojo.SelectPojo;
import com.example.demo.repository.FilePathRepository;
import com.example.demo.service.FilePathService;
import com.fasterxml.jackson.databind.util.JSONPObject;



@Controller
public class FileController {
		
		@Autowired
		private FilePathService filePathService;	
		@RequestMapping("/upload")
		@ResponseBody
		public String upload(@RequestParam("file") MultipartFile file) {
			return filePathService.Upload(file);
		}
		
		@RequestMapping(value = "/png/{encode}", produces = MediaType.IMAGE_PNG_VALUE) 
		 @ResponseBody 
		public ResponseEntity<byte[]> generate(@PathVariable("encode") String encode) throws IOException {
			filePathService.Generate(encode);
			filePathService.UploadDatabase(encode);
			String filename = FileContent.imagePath + encode + ".png";
	        InputStream inputImage = new FileInputStream(filename);
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        byte[] buffer = new byte[512];
	        int l = inputImage.read(buffer);
	        while(l >= 0) {
	            outputStream.write(buffer, 0, l);
	            l = inputImage.read(buffer);
	        }
	        HttpHeaders headers = new HttpHeaders();
	        headers.set("Content-Type", "image/png");
	        return new ResponseEntity<byte[]>(outputStream.toByteArray(), headers, HttpStatus.OK);  
	    }
		
		@RequestMapping("/index")
		@ResponseBody
		public String index() {
			return "/index";
		}
		
		@CrossOrigin
		@RequestMapping("/select")
		@ResponseBody
		public List<Object> select(@RequestParam("GroupId") String GroupId) {
			List<FilePath> result = filePathService.SelectByGroup(GroupId);
			JSONArray jsonList = new JSONArray();
			
			for(FilePath r: result) {
				JSONObject json = new JSONObject();
				json.put("id",r.ProjectId);
				json.put("path", r.Path);
				
				jsonList.put(json);
			}
			return jsonList.toList();
		}
}
