package com.appsdeveloperblog.app.ws.shared;

import java.security.SecureRandom;
import java.util.Random;

import javax.persistence.GeneratedValue;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	public String generateUserId(int length) {
		return generatRandomString(length);
		}

	private String generatRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		
		
		return new String(returnValue);
	}
	
}
