package com.appsdeveloperblog.app.ws.security;

import org.springframework.boot.SpringApplication;

import com.appsdeveloperblog.app.ws.SpringApplicationContext;
import org.apache.log4j.Logger;

public class SecurityConstants {
	
	public static final long EXPIRATION_TIME = 864000000; //10 days
    public static final long PASSWORD_RESET_EXPIRATION_TIME = 3600000; // 1 hour
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";
	public static final String VIRIFICATION_EMAIL_URL = "/users/email-verification";
    public static final String PASSWORD_RESET_REQUEST_URL = "/users/password-reset-request";
    public static final String PASSWORD_RESET_URL = "/users/password-reset";
//	public static final String TOKEN_SECRET = "jf9i4jgu83nflojfu57ejf7";
	
	
	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
//		System.out.println("[DEBUGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG] appProperties.getTokenSecret() : " + appProperties.getTokenSecret());
		return appProperties.getTokenSecret();
	}
	
	
		

}
