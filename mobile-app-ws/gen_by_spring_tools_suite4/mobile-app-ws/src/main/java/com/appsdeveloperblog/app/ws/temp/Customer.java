package com.appsdeveloperblog.app.ws.temp;

import lombok.Data;

//@Data
public class Customer {

	private int id;
	private Address address;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	
	
	}