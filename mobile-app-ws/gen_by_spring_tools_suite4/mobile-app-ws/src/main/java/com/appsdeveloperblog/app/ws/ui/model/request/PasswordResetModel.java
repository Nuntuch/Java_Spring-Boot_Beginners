package com.appsdeveloperblog.app.ws.ui.model.request;

import org.apache.log4j.Logger;

public class PasswordResetModel {
	private String token;
	private String password;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
