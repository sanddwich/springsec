package com.example.springsec.services;

import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface BaseDataService<T> {
	List<T> findAll();
	List<T> search(String searchTerm);
	void save(T entity) ;
	void delete(T entity) throws DataIntegrityViolationException;
}
