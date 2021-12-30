package com.appsdeveloperblog.app.ws.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import org.apache.log4j.Logger;

@Component
public class AppProperties {

	final static Logger logger = Logger.getLogger(AppProperties.class);
	
	@Autowired
	private Environment env;
	
	public String getTokenSecret() {
		return env.getProperty("tokenSecret");
	}
	
}
