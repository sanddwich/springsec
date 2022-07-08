package com.example.springsec.services;

import com.example.springsec.entities.Privilege;
import com.example.springsec.repositories.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivilegeService implements BaseDataService<Privilege> {
	private final PrivilegeRepository privilegeRepository;

	@Autowired
	public PrivilegeService(PrivilegeRepository privilegeRepository) {
		this.privilegeRepository = privilegeRepository;
	}

	@Override
	public List<Privilege> findAll() {
		return this.privilegeRepository.findAll();
	}

	@Override
	public List<Privilege> search(String searchTerm) {
		return this.privilegeRepository.search(searchTerm);
	}

	@Override
	public void save(Privilege entity) {
		this.privilegeRepository.save(entity);
	}

	@Override
	public void delete(Privilege entity) throws DataIntegrityViolationException {
		this.privilegeRepository.delete(entity);
	}

	public List<Privilege> saveAll(List<Privilege> privileges) {
		return privileges.stream()
		  .filter(this::isPrivilegeByNameNotExist)
		  .peek(privilegeRepository::save)
		  .collect(Collectors.toList());
	}

	public boolean isPrivilegeByNameNotExist(Privilege privilege) {
		return privilegeRepository.findByName(privilege.getName()).isEmpty();
	}

	public List<Privilege> findByName(String name) {
		return this.privilegeRepository.findByName(name);
	}

	public List<Privilege> findByCode(String code) {
		return this.privilegeRepository.findByCode(code);
	}

	public List<Privilege> findByDescription(String description) {
		return this.privilegeRepository.findByDescription(description);
	}
}