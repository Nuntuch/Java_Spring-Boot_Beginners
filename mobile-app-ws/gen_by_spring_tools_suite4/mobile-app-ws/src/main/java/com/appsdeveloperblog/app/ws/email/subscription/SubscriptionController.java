package com.appsdeveloperblog.app.ws.email.subscription;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
