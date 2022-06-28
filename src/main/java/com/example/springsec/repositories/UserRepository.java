package com.example.springsec.repositories;

import com.example.springsec.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    List<User> findAll();

    @Override
    User getById(Integer integer);

    @Override
    <S extends User> S save(S entity);

    @Override
    long count();

    @Override
    void delete(User entity);
}
