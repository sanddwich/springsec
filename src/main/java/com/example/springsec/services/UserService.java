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
		return null;
	}

	@Override
	public void save(User entity) {

	}

	@Override
	public void delete(User entity) throws DataIntegrityViolationException {

	}
}
