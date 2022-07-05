package com.example.springsec.repositories;

import com.example.springsec.entities.Privilege;
import com.example.springsec.services.BaseDataService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long>, BaseDataService<Privilege> {
	
}
