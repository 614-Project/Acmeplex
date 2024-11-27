package com.example.Acmeplex.exceptions;

public class TheaterAlreadyExists extends RuntimeException {
    private static final long serialVersionUID = 6386810783666583528L;

    public TheaterAlreadyExists() {
        super("Theater is already Present on this Address");
    }
}