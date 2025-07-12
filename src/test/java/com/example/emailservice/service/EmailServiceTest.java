package com.example.emailservice.service;

import com.example.emailservice.dto.EmailRequest;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.TestPropertySource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmailServiceTest {

    @Test
    void sendEmail_minimumCoverage() {
        JavaMailSender mailSender = mock(JavaMailSender.class);
        EmailService service = new EmailService(mailSender); // real service
        EmailRequest req = new EmailRequest();

        req.setTo("someone@example.com");
        req.setSubject("Subject");
        req.setBody("Body");

        // Just run the method. Don’t care about result.
        service.sendEmail(req);

        verify(mailSender).send(any(SimpleMailMessage.class));
    }
}