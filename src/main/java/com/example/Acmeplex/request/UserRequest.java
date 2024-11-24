package com.example.Acmeplex.request;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
    private String password;
}
