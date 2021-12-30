package com.appsdeveloperblog.app.ws.service;


import org.springframework.stereotype.Service;

import java.util.List;
import org.apache.log4j.Logger;

@Service
public class SubscriptionService {
	
	final static Logger logger = Logger.getLogger(SubscriptionService.class);

    private EmailService emailService;

    public SubscriptionService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void doSubscript(String name, String email, List<String> hobbies) {
        emailService.sent(name, email, hobbies);
    }
    
    public void doSubscript_new(String email, String token) {
        emailService.sent_subscription(email, token);
    }
    
    public void doResetPassword(String email, String token) {
        emailService.sent_reset_password(email, token);
    }    
    
}