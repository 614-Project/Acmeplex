package com.example.Acmeplex.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.Acmeplex.entities.Movie;
import com.example.Acmeplex.entities.Show;
import com.example.Acmeplex.entities.Ticket;

// @Service
// public class EmailService {

//     @Autowired
//     private JavaMailSender mailSender;

//     public void sendPaymentSuccessEmail(String toEmail, String emailBody) {
//         SimpleMailMessage message = new SimpleMailMessage();
//         message.setTo(toEmail);
//         message.setSubject("Payment Confirmation");
//         message.setText(emailBody);
//         message.setFrom("meluxmeme@gmail.com");

//         try {
//             mailSender.send(message);
//             System.out.println("Email sent successfully to " + toEmail);
//         } catch (Exception e) {
//             System.err.println("Error sending email: " + e.getMessage());
//         }
//     }

//     //Send email for confirmation of ticket and details
//     public void sendTicketEmail(String toEmail, String emailBody) {
//         SimpleMailMessage message = new SimpleMailMessage();
//         message.setTo(toEmail);
//         message.setSubject("Ticket Information");
//         message.setText(emailBody);
//         message.setFrom("meluxmeme@gmail.com");

//         try {
//             mailSender.send(message);
//             System.out.println("Email sent successfully to " + toEmail);
//         } catch (Exception e) {
//             System.err.println("Error sending email: " + e.getMessage());
//         }
//     }

// }
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendPaymentSuccessEmail(String email, String paymentIntentId) {
        String paymentBody = String.format(
            "Dear Customer,%n" +
            "Your payment has been successfully processed. Thank you for your purchase!%n%n" +
            "Your confirmation number is: %s%n",
            paymentIntentId
        );
        sendEmail(email, "Payment Confirmation", paymentBody);
    }

    public void sendTicketDetailsEmail(String email, Ticket ticket, String customerName) {
        Show show = ticket.getShow();
        Movie movie = show.getMovie();
        String Movie = movie.getTitle();
        String ticketBody = String.format(
            "Dear %s,%n" +
            "Your ticket has been confirmed. The details are as follows:%n%n" +
            "Show Time: %s%n" +
            "Ticket ID: %s%n" +
            "Movie Name: %s%n" +
            "Seat Number: %s%n",
            customerName,
            ticket.getShowTime(),
            ticket.getId(),
            Movie,
            ticket.getBookedSeats()
        );
        sendEmail(email, "Ticket Confirmation", ticketBody);
    }

    public void sendMovieAddedEmail(String email, String movieName) {
        String paymentBody = String.format(
            "Dear Customer,%n" +
            "New movie has been available to watch on the theater. " +
            "The newest Movie is: %s %n%n",
            movieName
        );
        sendEmail(email, "Movie Promotion", paymentBody);
    }


    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject); // Use the subject provided to the method
        message.setText(body);
        message.setFrom("meluxmeme@gmail.com");

        try {
            mailSender.send(message);
            System.out.println("Email sent successfully to " + to + " with subject: " + subject);
        } catch (Exception e) {
            System.err.println("Error sending email to " + to + ": " + e.getMessage());
        }
    }
}
