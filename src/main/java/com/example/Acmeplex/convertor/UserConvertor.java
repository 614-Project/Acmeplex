package com.example.Acmeplex.convertor;

import com.example.Acmeplex.entities.User;
import com.example.Acmeplex.request.UserRequest;
import com.example.Acmeplex.response.UserResponse;

public class UserConvertor {
     public static User userDtoToUser(UserRequest userRequest, String password) {
        User user = User.builder()
                .name(userRequest.getName())
                .address(userRequest.getAddress())
                .phone(userRequest.getPhone())
                .email(userRequest.getEmail())
                .password(password)
                .build();

        return user;
    }

    public static UserResponse userToUserDto(User user) {
        UserResponse userDto = UserResponse.builder()
                .name(user.getName())
                .address(user.getAddress())
                .phone(user.getPhone())
                .email(user.getEmail())
                .build();
        return userDto;
    }
}
