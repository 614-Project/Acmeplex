package com.example.Acmeplex.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.Acmeplex.config.JWTService;
import com.example.Acmeplex.controllers.AuthResponse;
import com.example.Acmeplex.convertors.UserConvertor;
import com.example.Acmeplex.entities.User;
import com.example.Acmeplex.repositories.UserRepository;
import com.example.Acmeplex.request.UserRequest;
import com.example.Acmeplex.response.UserResponse;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// method to register new user
	public AuthResponse addUser(UserRequest userRequest) throws Exception {
		Optional<User> users = userRepository.findByEmail(userRequest.getEmail());

		if (users.isPresent()) {
			throw new Exception();
		}
		User user = UserConvertor.userDtoToUser(userRequest, passwordEncoder.encode(userRequest.getPassword()));

		userRepository.save(user);

		String token = jwtService.generateToken(user.getEmail());

		UserResponse userResponse = UserConvertor.userToUserDto(user);
		// AuthResponse authResponse = new AuthResponse(token, userResponse);

		return new AuthResponse(token, userResponse);
	}

// method to get user by their id
	public UserResponse getUserById(Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));
		return UserConvertor.userToUserDto(user);
	}

	// Method to delete user by ID
	public void deleteUser(Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));
		userRepository.delete(user);

	}

	// Method to update user details by ID
	public UserResponse updateUser(Integer id, UserRequest userRequest) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setAddress(userRequest.getAddress());
		user.setPhone(userRequest.getPhone());
		user.setPassword(userRequest.getPassword());
		userRepository.save(user);
		return UserConvertor.userToUserDto(user);
	}
}