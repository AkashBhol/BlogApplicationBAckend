package com.example.BlogApplicationBAckend.controller;

import com.example.BlogApplicationBAckend.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v2")
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/email")
    public ResponseEntity<String> getEmail(@RequestParam String email) {
        String s = emailService.sendEmail(email);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
