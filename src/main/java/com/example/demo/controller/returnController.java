package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/image")
public class returnController {
	
	    @RequestMapping(value = "/get",produces = MediaType.IMAGE_JPEG_VALUE)
	    @ResponseBody
	    public byte[] getImage() throws IOException {
	        File file = new File("D:/test.jpg");
	        FileInputStream inputStream = new FileInputStream(file);
	        byte[] bytes = new byte[inputStream.available()];
	        inputStream.read(bytes, 0, inputStream.available());
	        return bytes;
	    }
}
