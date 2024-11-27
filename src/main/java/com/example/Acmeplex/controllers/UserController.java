package com.example.Acmeplex.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Acmeplex.config.JWTService;
import com.example.Acmeplex.config.UserInfoUserDetails;
import com.example.Acmeplex.convertor.UserConvertor;
import com.example.Acmeplex.entities.User;
import com.example.Acmeplex.request.UserRequest;
import com.example.Acmeplex.response.UserResponse;
import com.example.Acmeplex.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTService jwtService;


	//Register a user
	@PostMapping("/addNew")
	public ResponseEntity<AuthResponse> addNewUser(@RequestBody UserRequest userEntryDto) {
		try {
			AuthResponse result = userService.addUser(userEntryDto); // Call the updated service method
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	//Login 
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserInfoUserDetails userDetails = (UserInfoUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        String token = jwtService.generateToken(user.getEmail());

        UserResponse userResponse = UserConvertor.userToUserDto(user);
        AuthResponse authResponse = new AuthResponse(token, userResponse);

        return ResponseEntity.ok(authResponse);
    }

    // View Profile by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> viewUserProfile(@PathVariable Integer id) {
        try {
            UserResponse userResponse = userService.getUserById(id);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

	//Update user
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUserProfile(@PathVariable Integer id, @RequestBody UserRequest userRequest) {
        try {
            UserResponse updatedUser = userService.updateUser(id, userRequest);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

	//Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUserProfile(@PathVariable Integer id) {
        try {
			userService.deleteUser(id); 
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
	
	@PostMapping("/getToken")
	public ResponseEntity<AuthResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

    if (authentication.isAuthenticated()) {
        UserInfoUserDetails userDetails = (UserInfoUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser(); // Extract the user from UserInfoUserDetails
        String token = jwtService.generateToken(user.getEmail());

        UserResponse userResponse = UserConvertor.userToUserDto(user);
        AuthResponse authResponse = new AuthResponse(token, userResponse);

        return ResponseEntity.ok(authResponse);
    }

    throw new UsernameNotFoundException("Invalid user details.");
}

}