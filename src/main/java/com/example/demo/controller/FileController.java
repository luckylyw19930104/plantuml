package com.example.demo.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.repository.FilePathRepository;
import com.example.demo.service.FilePathService;
 
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
			String filename = "/Users/luckylyw19930104/eclipse-workspace/plantuml/src/main/resources/static/png/test.png";
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
}
