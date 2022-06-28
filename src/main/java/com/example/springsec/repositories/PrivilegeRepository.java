package com.example.springsec.repositories;

import com.example.springsec.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
    @Override
    List<Privilege> findAll();

    @Override
    Privilege getById(Integer integer);

    @Override
    <S extends Privilege> S save(S entity);

    @Override
    long count();

    @Override
    void delete(Privilege entity);
}
