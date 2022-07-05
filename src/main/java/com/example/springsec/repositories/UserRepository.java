package com.example.springsec.repositories;

import com.example.springsec.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	@Query("select u from User u " +
	  "where lower(u.username) like lower(concat('%', :searchTerm, '%')) " +
	  "or lower(u.email) like lower(concat('%', :searchTerm, '%')) "
	)
	Optional<User> search(String searchTerm);
}
