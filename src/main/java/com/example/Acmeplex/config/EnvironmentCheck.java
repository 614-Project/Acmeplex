package com.example.Acmeplex.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class EnvironmentCheck {

    @Value("${MYSQL_DB_USERNAME}")
    private String dbUsername;

    @Value("${MYSQL_DB_PASSWORD}")
    private String dbPassword;

    // @Value("${STRIPE_SECRET_KEY}")
    // private String stripkey;


    @PostConstruct
    public void checkEnv() {
        System.out.println("DB Username: " + dbUsername);
        System.out.println("DB Password: " + dbPassword);
       // System.out.println("Stripe stripkey: " + stripkey);
    }
}
