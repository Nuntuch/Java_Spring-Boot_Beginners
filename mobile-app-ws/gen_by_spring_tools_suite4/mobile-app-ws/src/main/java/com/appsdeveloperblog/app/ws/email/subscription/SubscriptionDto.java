package com.appsdeveloperblog.app.ws.email.subscription;

import lombok.Value;

import java.util.List;

@Value
public class SubscriptionDto {
     String name;
     String email;
     List<String> hobbies;

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