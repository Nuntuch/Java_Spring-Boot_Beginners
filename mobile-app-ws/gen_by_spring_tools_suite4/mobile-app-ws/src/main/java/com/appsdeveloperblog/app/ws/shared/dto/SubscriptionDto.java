package com.appsdeveloperblog.app.ws.shared.dto;

import lombok.Value;

import java.util.List;
import org.apache.log4j.Logger;
//@Value
public class SubscriptionDto {

	final static Logger logger = Logger.getLogger(SubscriptionDto.class);


    private String name;
    private String email;
    private List<String> hobbies;

    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
    
    
}