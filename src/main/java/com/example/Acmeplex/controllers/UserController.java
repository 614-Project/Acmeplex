package com.example.Acmeplex.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
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

	// @PostMapping("/addNew")
	// public ResponseEntity<String> addNewUser(@RequestBody UserRequest userEntryDto) {
	// 	try {
	// 		String result = userService.addUser(userEntryDto);
	// 		return new ResponseEntity<>(result, HttpStatus.CREATED);
	// 	} catch (Exception e) {
	// 		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	// 	}
	// }



	
	@PostMapping("/addNew")
	public ResponseEntity<AuthResponse> addNewUser(@RequestBody UserRequest userEntryDto) {
		try {
			AuthResponse result = userService.addUser(userEntryDto); // Call the updated service method
			return new ResponseEntity<>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

    // @PostMapping("/getToken")
	// public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
	// 	Authentication authentication = authenticationManager.authenticate(
	// 			new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

	// 	if (authentication.isAuthenticated()) {
	// 		return jwtService.generateToken(authRequest.getUsername());
	// 	}

	// 	throw new UsernameNotFoundException("invalid user details.");
	// }


// @PostMapping("/getToken")
// public ResponseEntity<AuthResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
//     Authentication authentication = authenticationManager.authenticate(
//             new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

//     if (authentication.isAuthenticated()) {
//         User user = (User) authentication.getPrincipal();  // Assuming `User` implements `UserDetails`
//         String token = jwtService.generateToken(authRequest.getUsername());

//         UserResponse userResponse = UserConvertor.userToUserDto(user);
//         AuthResponse authResponse = new AuthResponse(token, userResponse);

//         return ResponseEntity.ok(authResponse);
//     }

//     throw new UsernameNotFoundException("Invalid user details.");
// }

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