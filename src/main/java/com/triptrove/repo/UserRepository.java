package com.triptrove.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.triptrove.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	// Custom query method
	@Query("SELECT u FROM User u WHERE u.username = ?1")
	User findByUsername(String username);

	// Dynamic query method
	User findByEmail(String email);
}
