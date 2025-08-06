package com.sandeepkonda.portfolio.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import com.sandeepkonda.portfolio.model.Contact;

@RestController
public class HomeController {

    @PostMapping("/contact")
    public ResponseEntity<String> submitContact(@Valid @RequestBody Contact contact) {
        // Since EmailService is removed, just acknowledge receipt here.
        return ResponseEntity.ok("Contact received. Email sending disabled.");
    }
}
