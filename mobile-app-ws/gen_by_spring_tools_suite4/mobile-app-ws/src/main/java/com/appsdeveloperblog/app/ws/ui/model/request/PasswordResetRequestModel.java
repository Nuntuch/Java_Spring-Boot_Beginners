package com.appsdeveloperblog.app.ws.ui.model.request;

import org.apache.log4j.Logger;

public class PasswordResetRequestModel {

	final static Logger logger = Logger.getLogger(PasswordResetRequestModel.class);


	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
