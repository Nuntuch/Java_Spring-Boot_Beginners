package com.appsdeveloperblog.app.ws.temp;

import org.aspectj.weaver.patterns.ParserException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.jackson.JsonNodeValueReader;

//import com.appsdeveloperblog.app.ws.temp.JsonToObjDto.Address;
//import com.appsdeveloperblog.app.ws.temp.JsonToObjDto.Customer;
//import com.appsdeveloperblog.app.ws.temp.JsonToObjDto.Order;
//import com.appsdeveloperblog.app.ws.temp.JsonToObjDto.Order;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

import java.nio.file.Files;
import java.nio.file.Paths;



public class JsonToObjMain {

	
	public static void main(String[] args) throws Exception {
		
		 String file = "src/main/java/com/appsdeveloperblog/app/ws/temp/JsonToObj.json";
	     String json = readFileAsString(file);

//       String  json = "{\"id\": 456, \"customer\": {\"id\": 789,\"street_address\": \"123 Main Street\",\"address_city\": \"SF\"}}";
        System.out.println(json);
        
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().addValueReader(new JsonNodeValueReader()).setSourceNameTokenizer(NameTokenizers.UNDERSCORE);
		
		JsonNode orderNode = new ObjectMapper().readTree(json);
		Order order = modelMapper.map(orderNode, Order.class);
 
	
//		System.out.println(order.getId());
//		System.out.println(order.getCustomer().getId());
//		System.out.println(order.getCustomer().getAddress().getStreet());
//		System.out.println(order.getCustomer().getAddress().getCity());
	}
	
	
    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }


    


}

