package com.triptrove.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triptrove.dto.UserDTO;
import com.triptrove.entity.User;
import com.triptrove.exception.NotFoundException;
import com.triptrove.repo.UserRepository;
import com.triptrove.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		user.setFullName(userDTO.getFullName());

		user = userRepository.save(user);

		return convertToDTO(user);
	}

	@Override
	public UserDTO getUserProfile(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

		return convertToDTO(user);
	}

	@Override
	public UserDTO updateUserProfile(Long userId, UserDTO userDTO) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

		user.setUsername(userDTO.getUsername());
		user.setPassword(userDTO.getPassword());
		user.setEmail(userDTO.getEmail());
		user.setFullName(userDTO.getFullName());

		user = userRepository.save(user);

		return convertToDTO(user);
	}

	private UserDTO convertToDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setEmail(user.getEmail());
		userDTO.setFullName(user.getFullName());

		return userDTO;
	}
}
