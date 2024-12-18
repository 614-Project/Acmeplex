package com.example.Acmeplex.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.Acmeplex.entities.Credit;
import com.example.Acmeplex.entities.Movie;
import com.example.Acmeplex.entities.Show;
import com.example.Acmeplex.entities.Ticket;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    //send email to user when their payment is confirmed 
    public void sendPaymentSuccessEmail(String email, String paymentIntentId) {
        String paymentBody = String.format(
            "Dear Customer,%n" +
            "Your payment has been successfully processed. Thank you for your purchase!%n%n" +
            "Your confirmation number is: %s%n",
            paymentIntentId
        );
        sendEmail(email, "Payment Confirmation", paymentBody);
    }


        //send ticket details email to user when their payment is confirmed 
    public void sendTicketDetailsEmail(String email, Ticket ticket, String customerName) {
        Show show = ticket.getShow();
        Movie movie = show.getMovie();
        String Movie = movie.getTitle();
        String ticketBody = String.format(
            "Dear %s,%n" +
            "Your ticket has been confirmed. The details are as follows:%n%n" +
            "Show Date: %s%n" +
            "Show Time: %s%n" +
            "Ticket ID: %s%n" +
            "Movie Name: %s%n" +
            "Seat Number: %s%n",
            customerName,
            show.getDate(),
            show.getTime(),
            ticket.getId(),
            Movie,
            ticket.getBookedSeats()
        );
        sendEmail(email, "Ticket Confirmation", ticketBody);
    }


    // send promotional email to user when a movie is added to the database
    public void sendMovieAddedEmail(String email, String movieName) {
        String paymentBody = String.format(
            "Dear Customer,%n" +
            "New movie has been available to watch on the theater. " +
            "The newest Movie is: %s %n%n",
            movieName
        );
        sendEmail(email, "Movie Promotion", paymentBody);
    }


    //Notify user when they cancel the ticket through email with credit they got
    public void sendTicketCancelEmail(String email, Credit credit, String customerName) {

        String ticketBody = String.format(
            "Dear %s,%n" +
            "Your ticket has been canceled. You have received store credit which must be used within a year.%n" +
            "Credit: %s  points%n" +
            "Expiration Date: %s",
            customerName,
            credit.getCredit(),
            credit.getExpireDate()
        );
    
        sendEmail(email, "Ticket Cancellation Confirmation", ticketBody);
    }


    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject); 
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
