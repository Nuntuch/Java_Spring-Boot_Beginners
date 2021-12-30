package com.appsdeveloperblog.app.ws.ui.model.response;

import java.util.Date;
import org.apache.log4j.Logger;

public class ErrorMessage {

	final static Logger logger = Logger.getLogger(ErrorMessage.class);


		private Date timestamp;
		private String message;
		

		public ErrorMessage() {
			
		}

		public ErrorMessage(Date timestamp, String message) {
			super();
			this.timestamp = timestamp;
			this.message = message;
		}

		public Date getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(Date timestamp) {
			this.timestamp = timestamp;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
		
		
		
}