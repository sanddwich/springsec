package com.example.springsec.services;

import com.example.springsec.entities.User;
import com.example.springsec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements BaseDataService<User> {
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	@Override
	public List<User> search(String searchTerm) {
		return this.userRepository.search(searchTerm);
	}

	@Override
	public void save(User user) {
		if (!this.findByUsernameOREmail(user))
			this.userRepository.save(user);
	}

	@Override
	public void delete(User user) throws DataIntegrityViolationException {
		this.userRepository.delete(user);
	}

	public boolean findByUsernameOREmail(User user) {
		if (
		  !this.findByUsername(user.getUsername()).isEmpty() || !this.findByEmail(user.getEmail()).isEmpty()
		) return true;

		return false;
	}

	public List<User> findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

	public List<User> findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
}
