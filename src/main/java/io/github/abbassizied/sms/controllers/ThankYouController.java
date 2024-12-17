package io.github.abbassizied.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThankYouController {

    @GetMapping("/thank-you")
    public String showThankYouPage() {
        return "frontoffice/thank-you";  // Return the Thymeleaf template for the Thank You page
    }
}

