package com.example.emailservice.controller;

import com.example.emailservice.dto.EmailRequest;
import com.example.emailservice.response.ApiResponse;
import com.example.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<ApiResponse<Void>> sendEmail(@Valid @RequestBody EmailRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            Object userIdObj = auth.getDetails();
            if (userIdObj instanceof Long) {
                Long userId = (Long) userIdObj;
                System.out.println("User ID from JWT token: " + userId);
            } else {
                System.out.println("User ID not found in authentication details");
            }
        } else {
            System.out.println("No authenticated user found");
        }

        emailService.sendEmail(request);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Email sent successfully.")
                        .data(null)
                        .timestamp(Instant.now())
                        .build()
        );
    }
}