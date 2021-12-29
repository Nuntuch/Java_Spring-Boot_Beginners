package com.appsdeveloperblog.app.ws.ui.controller;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.app.ws.service.SubscriptionService;
import com.appsdeveloperblog.app.ws.shared.dto.SubscriptionDto;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public String subscript(@RequestBody SubscriptionDto dto) {
        subscriptionService.doSubscript(dto.getName(), dto.getEmail(), dto.getHobbies());
        return "Subscription successful";
    }
}
