package com.example.springsec.repositories;

import com.example.springsec.entities.AccessRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessRoleRepository extends JpaRepository<AccessRole, Long> {

}
