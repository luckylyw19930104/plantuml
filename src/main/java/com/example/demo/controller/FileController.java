package com.example.demo.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
		public BufferedImage generate(@PathVariable("encode") String encode) throws IOException {
			filePathService.Generate(encode);
			return ImageIO.read(new FileInputStream(new File("/Users/luckylyw19930104/eclipse-workspace/plantuml/src/main/resources/static/png/123.png")));
		}
		
		@RequestMapping("/index")
		@ResponseBody
		public String index() {
			return "/index";
		}

		
}
