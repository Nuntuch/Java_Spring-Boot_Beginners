package com.appsdeveloperblog.app.ws.exceptions;
import org.apache.log4j.Logger;

public class UserServiceException extends RuntimeException {

	final static Logger logger = Logger.getLogger(UserServiceException.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 7159797720879793339L;
	
	public UserServiceException(String message) {
		super(message);
	}

}
