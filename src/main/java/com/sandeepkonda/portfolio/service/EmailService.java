package com.sandeepkonda.portfolio.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendEmail(String to, String subject, String body) {
        // placeholder logic
        System.out.println("Sending email to " + to);
    }
}
