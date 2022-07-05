package com.example.springsec.services;

import com.example.springsec.entities.Privilege;
import com.example.springsec.repositories.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivelegeService implements BaseDataService<Privilege> {
    private final PrivilegeRepository privilegeRepository;

    @Autowired
    public PrivelegeService(PrivilegeRepository privilegeRepository) {
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
}
