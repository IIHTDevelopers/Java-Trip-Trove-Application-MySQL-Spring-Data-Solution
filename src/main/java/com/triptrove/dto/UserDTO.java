package com.triptrove.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {
	private Long id;

	@NotBlank(message = "Username is mandatory")
	@Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
	private String username;

	@NotBlank(message = "Password is mandatory")
	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;

	@NotBlank(message = "Email is mandatory")
	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Full name is mandatory")
	private String fullName;

	public UserDTO() {
		super();
	}

	public UserDTO(Long id,
			@NotBlank(message = "Username is mandatory") @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String username,
			@NotBlank(message = "Password is mandatory") @Size(min = 6, message = "Password must be at least 6 characters") String password,
			@NotBlank(message = "Email is mandatory") @Email(message = "Email should be valid") String email,
			@NotBlank(message = "Full name is mandatory") String fullName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
