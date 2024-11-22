package com.example.Acmeplex.exceptions;

public class ShowDoesNotExist extends RuntimeException {

    private static final long serialVersionUID = -4436119261176031165L;

    public ShowDoesNotExist() {
        super("Show does not exists");
    }
}
