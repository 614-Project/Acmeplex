package com.example.Acmeplex.config;

import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;

@Configuration
public class DotenvConfig {

   @PostConstruct
   public void init() {
       Dotenv dotenv = Dotenv.configure().load();
       System.setProperty("STRIPE-WEBHOOK-SECRET", dotenv.get("STRIPE-WEBHOOK-SECRET"));
       System.setProperty("STRIPE_PUBLIC_KEY", dotenv.get("STRIPE_PUBLIC_KEY"));
       System.setProperty("STRIPE_SECRET_KEY", dotenv.get("STRIPE_SECRET_KEY"));
       System.setProperty("MYSQL_DB_USERNAME", dotenv.get("MYSQL_DB_USERNAME"));
       System.setProperty("MYSQL_DB_PASSWORD", dotenv.get("MYSQL_DB_PASSWORD"));
       System.setProperty("MYSQL_DB_URL", dotenv.get("MYSQL_DB_URL"));
       System.setProperty("EMAIL_USERNAME", dotenv.get("EMAIL_USERNAME"));
       System.setProperty("EMAIL_PASSWORD", dotenv.get("EMAIL_PASSWORD"));

   }
}
