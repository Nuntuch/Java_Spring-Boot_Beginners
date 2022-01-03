package com.appsdeveloperblog.app.ws.temp;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;

//import com.appsdeveloperblog.app.ws.temp.JsonToObjDto.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import java.nio.file.Files;
import java.nio.file.Paths;



public class JsonToObjMain {

	public static void main(String[] args) throws Exception {
		
//       String file = "src/test/resources/myFile.json";        
//		 String file = "/mobile-app-ws/src/main/java/com/appsdeveloperblog/app/ws/temp/JsonToObj.json";
		 String file = "src/main/java/com/appsdeveloperblog/app/ws/temp/JsonToObj.json";
	     String json = readFileAsString(file);
        System.out.println(json);
	    
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSourceNameTokenizer(NameTokenizers.UNDERSCORE);
		
		JsonNode orderNode = new ObjectMapper().readTree(json);
		Order order = modelMapper.map(orderNode, Order.class);
		
		
//		assertEquals(order.getId(), 456);
//		assertEquals(order.getCustomer().getId(), 789);
//		assertEquals(order.getCustomer().getAddress().getStreet(), "123 Main Street");
//		assertEquals(order.getCustomer().getAddress().getCity(), "SF");		
//		
		System.out.println(order.getId());
		System.out.println(order.getCustomer().getId());
		System.out.println(order.getCustomer().getAddress().getStreet());
		System.out.println(order.getCustomer().getAddress().getCity());
	}
	
	
    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
