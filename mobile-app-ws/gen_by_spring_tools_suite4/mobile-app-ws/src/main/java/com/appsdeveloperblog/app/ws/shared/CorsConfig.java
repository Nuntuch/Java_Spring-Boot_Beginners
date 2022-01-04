//package com.appsdeveloperblog.app.ws.shared;
//
//import org.apache.log4j.Logger;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import com.appsdeveloperblog.app.ws.MobileAppWsApplication;
//
////package com.appsdeveloperblog.app.ws.shared;
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.web.servlet.config.annotation.CorsRegistry;
////import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
////
////public class Application {
////
////	  @Bean
////	    public WebMvcConfigurer corsConfigurer() {
////	        return new WebMvcConfigurer() {
////	            @Override
////	            public void addCorsMappings(CorsRegistry registry) {
//////	                registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:9000");
////	                registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:8080");
////	            }
////	        };
////	    }
////	
////}
//
//@Configuration
//public class CorsConfig{
//	final static Logger logger = Logger.getLogger(CorsConfig.class);
//	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
////				registry.addMapping("/**")
//				registry.addMapping("/password-reset")
//				.allowedMethods("GET","POST","PUT","DELETE")
//				.allowedHeaders("*")
//				.allowedOrigins("http://localhost:8046/users/mobile-app-ws/");
//				logger.trace("allowedOrigins(\"http://localhost:8046/mobile-app-ws/\") "
//						+ "\n"
//						+ "Methods(\"GET\",\"POST\",\"PUT\",\"DELETE\")"
//						+ "\n"
//						+ "Headers(\"*\")");
//			}
//		};
//	}
//}