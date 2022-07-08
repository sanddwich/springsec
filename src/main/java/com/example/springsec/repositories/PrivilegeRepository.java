package com.example.springsec.repositories;

import com.example.springsec.entities.Privilege;
import com.example.springsec.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    @Query("select p from Privilege p " +
            "where lower(p.name) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(p.code) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(p.description) like lower(concat('%', :searchTerm, '%')) "
    )
    List<Privilege> search(String searchTerm);

    Privilege findById(Integer id);

    List<Privilege> findByName(String name);

    List<Privilege> findByCode(String code);

    List<Privilege> findByDescription(String description);
}
