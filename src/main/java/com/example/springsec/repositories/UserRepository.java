package com.example.springsec.repositories;

import com.example.springsec.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findByUsername(String username);

	List<User> findByEmail(String email);

	@Query("select u from User u " +
	  "where lower(u.username) like lower(concat('%', :searchTerm, '%')) " +
	  "or lower(u.email) like lower(concat('%', :searchTerm, '%')) "
	)
	List<User> search(String searchTerm);
}
