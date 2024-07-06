package com.triptrove.service;

import com.triptrove.dto.UserDTO;

public interface UserService {
	UserDTO registerUser(UserDTO userDTO);

	UserDTO getUserProfile(Long userId);

	UserDTO updateUserProfile(Long userId, UserDTO userDTO);
}
