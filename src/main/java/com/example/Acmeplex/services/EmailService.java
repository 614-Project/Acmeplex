package com.example.Acmeplex.services;

// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.MimeMessageHelper;
// import org.springframework.stereotype.Service;

// import jakarta.mail.MessagingException;
// import jakarta.mail.internet.MimeMessage;

// import org.springframework.beans.factory.annotation.Autowired;

// @Service
// public class EmailService {

//     @Autowired
//     private JavaMailSender emailSender;

//     public boolean sendEmail(String to, String subject, String body) {
//         try {
//             MimeMessage message = emailSender.createMimeMessage();
//             MimeMessageHelper helper = new MimeMessageHelper(message, true);
//             helper.setTo(to);
//             helper.setSubject(subject);
//             helper.setText(body, true);

//             emailSender.send(message);
//             System.out.println("Email sent successfully to {}");
//             return true;
//         } catch (MessagingException e) {
//             System.out.println("Error sending email: {}"+ e.getMessage());
//             return false;
//         }
//     }
// }


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPaymentSuccessEmail(String toEmail, String emailBody) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Payment Confirmation");
        message.setText(emailBody);
        message.setFrom("your_email@gmail.com");

        try {
            mailSender.send(message);
            System.out.println("Email sent successfully to " + toEmail);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
