package com.appsdeveloperblog.app.ws.service;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
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
}