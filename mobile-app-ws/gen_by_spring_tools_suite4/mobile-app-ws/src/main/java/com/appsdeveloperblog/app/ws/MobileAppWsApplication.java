package com.appsdeveloperblog.app.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.appsdeveloperblog.app.ws.security.AppProperties;

import java.sql.*;

import org.apache.log4j.Logger;

@SpringBootApplication
public class MobileAppWsApplication extends SpringBootServletInitializer{

	final static Logger logger = Logger.getLogger(MobileAppWsApplication.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MobileAppWsApplication.class);
	}
	
	public static void main(String[] args) {	
		
		
		logger.trace("MobileAppWs Application Start...");
		logger.debug("MobileAppWs Application Start...");
		logger.info("MobileAppWs Application Start...");
		logger.warn("MobileAppWs Application Start...");
		logger.error("MobileAppWs Application Start...");
		logger.fatal("MobileAppWs Application Start...");
		
		
		
		SpringApplication.run(MobileAppWsApplication.class, args);
	}

	@Bean 
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Bean 
	public SpringApplicationContext springApplicationContext(){
		return new SpringApplicationContext();
	}
	
	
	@Bean(name = "AppProperties")
	public AppProperties getAppProperties() {
		return new AppProperties();
	}
	
	
}
