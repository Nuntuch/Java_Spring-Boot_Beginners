package com.appsdeveloperblog.app.ws;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware {

	final static Logger logger = Logger.getLogger(SpringApplicationContext.class);
	
	private static ApplicationContext CONTEXT;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		CONTEXT = context;
	}
	
	
	public static Object getBean(String beanName) {
		logger.trace("get bean : " + beanName);
		return CONTEXT.getBean(beanName);

	} 
		
		
}
