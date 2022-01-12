package com.appsdeveloperblog.app.ws.temp;

import lombok.extern.log4j.*;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;




public class TestLombokLog {
	
	public static void main(String[] arg) {
		// TODO Auto-generated method stub
		
		TestLombokLog4j.main(arg);
		TestLombokLogjava.main(arg);
		TestLombokLogslf4j.main(arg);
	}

}


@Log4j
class TestLombokLog4j {
	
	public static void main(String[] arg) {
		// TODO Auto-generated method stub
		String log4j_message = "log4j_message"; 
		
		log.trace(log4j_message);
		log.debug(log4j_message);
		log.info(log4j_message);
		log.error(log4j_message);
		

	}

}


@Log
class TestLombokLogjava {
  
  public static void main(String... args) {
  
    String logjava_message = "logjava_message";
    
    log.severe(logjava_message);
    
  }
}

@Slf4j
class TestLombokLogslf4j {
  
  public static void main(String... args) {
	String logslf4j_message = "logslf4j_message"; 
    
	log.trace(logslf4j_message);
	log.debug(logslf4j_message);
	log.info(logslf4j_message);
	log.error(logslf4j_message);
	
	
	
	
  }
}