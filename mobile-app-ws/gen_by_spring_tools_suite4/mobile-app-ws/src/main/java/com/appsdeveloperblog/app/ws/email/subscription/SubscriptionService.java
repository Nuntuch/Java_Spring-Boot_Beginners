package com.appsdeveloperblog.app.ws.email.subscription;


import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.email.EmailService;

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
}