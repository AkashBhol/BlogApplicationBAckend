package com.example.BlogApplicationBAckend.ServiceImpl;

import com.example.BlogApplicationBAckend.Service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServoceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public String sendEmail(String email) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("akashbhol9900@outlook.com");
            helper.setTo(email);
            helper.setSubject("Password Reset Request");
            Context context = new Context();
            context.setVariable("resetLink", "http://localhost:8080/reset-password?token=" + "resetToken");
            String htmlContent = templateEngine.process("Email", context);

            helper.setText(htmlContent, true);
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Email Sent Successfully";
    }
}
