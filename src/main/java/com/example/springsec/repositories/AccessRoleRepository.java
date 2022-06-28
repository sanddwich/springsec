package com.example.springsec.repositories;

import com.example.springsec.entities.AccessRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessRoleRepository extends JpaRepository<AccessRole, Integer> {
    @Override
    List<AccessRole> findAll();

    @Override
    AccessRole getById(Integer integer);

    @Override
    <S extends AccessRole> S save(S entity);

    @Override
    long count();

    @Override
    void delete(AccessRole entity);
}
