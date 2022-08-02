package com.example.springsec.services;

import com.example.springsec.entities.AccessRole;
import com.example.springsec.repositories.AccessRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccessRoleService implements BaseDataService<AccessRole> {
	private final AccessRoleRepository accessRoleRepository;

	@Autowired
	public AccessRoleService(AccessRoleRepository accessRoleRepository) {
		this.accessRoleRepository = accessRoleRepository;
	}

	@Override
	public List<AccessRole> findAll() {
		return this.accessRoleRepository.findAll();
	}

	@Override
	public List<AccessRole> search(String searchTerm) {
		return this.accessRoleRepository.search(searchTerm);
	}

	@Override
	public void save(AccessRole accessRole) {
		if (!this.findAccessRoleByNameOrCode(accessRole))
			this.accessRoleRepository.save(accessRole);
	}

	@Override
	public void delete(AccessRole entity) throws DataIntegrityViolationException {
		this.accessRoleRepository.delete(entity);
	}

	public boolean findAccessRoleByNameOrCode(AccessRole accessRole) {
		if (
		  !this.accessRoleRepository.findByName(accessRole.getName()).isEmpty() ||
		    !this.accessRoleRepository.findByCode(accessRole.getCode()).isEmpty()
		) return true;

		return false;
	}

	public List<AccessRole> findByName(String name) {
		return this.accessRoleRepository.findByName(name);
	}

	public List<AccessRole> findByCode(String code) {
		return this.accessRoleRepository.findByCode(code);
	}

	public List<AccessRole> findByDescription(String description) {
		return this.accessRoleRepository.findByDescription(description);
	}

	public void saveAll(List<AccessRole> accessRoleList) {

	}

	public Optional<AccessRole> findById(Integer id) {
		return this.accessRoleRepository.findById(id);
	}
}
