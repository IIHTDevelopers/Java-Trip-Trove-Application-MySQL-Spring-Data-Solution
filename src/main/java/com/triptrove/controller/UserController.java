package com.triptrove.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triptrove.dto.UserDTO;
import com.triptrove.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid UserDTO userDTO) {
		UserDTO createdUser = userService.registerUser(userDTO);
		return ResponseEntity.ok(createdUser);
	}

	@GetMapping("/profile")
	public ResponseEntity<UserDTO> getUserProfile(@RequestParam Long userId) {
		UserDTO user = userService.getUserProfile(userId);
		return ResponseEntity.ok(user);
	}

	@PutMapping("/profile")
	public ResponseEntity<UserDTO> updateUserProfile(@RequestParam Long userId, @RequestBody @Valid UserDTO userDTO) {
		UserDTO updatedUser = userService.updateUserProfile(userId, userDTO);
		return ResponseEntity.ok(updatedUser);
	}
}
