package com.example.demo.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import net.sourceforge.plantuml.code.Transcoder;
import net.sourceforge.plantuml.code.TranscoderUtil;

public class decodeUtil {
	    
	
	static public String getUmlSource(String source) {

	        // build the UML source from the compressed part of the URL
	        String text;
	        try {
	            text = URLDecoder.decode(source, "UTF-8");
	        } catch (UnsupportedEncodingException uee) {
	            text = "' invalid encoded string";
	        }
	        Transcoder transcoder = TranscoderUtil.getDefaultTranscoder();
	        try {
	            text = transcoder.decode(text);
	        } catch (IOException ioe) {
	            text = "' unable to decode string";
	        }

	        // encapsulate the UML syntax if necessary
	        String uml;
	        if (text.startsWith("@start")) {
	            uml = text;
	        } else {
	            StringBuilder plantUmlSource = new StringBuilder();
	            plantUmlSource.append("@startuml\n");
	            plantUmlSource.append(text);
	            if (text.endsWith("\n") == false) {
	                plantUmlSource.append("\n");
	            }
	            plantUmlSource.append("@enduml");
	            uml = plantUmlSource.toString();
	        }
	        return uml;
	    }

	    protected void UmlExtractor() {
	        // prevents calls from subclass
	        throw new UnsupportedOperationException();
	    }

	}
