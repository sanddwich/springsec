package com.example.springsec.config;

import com.example.springsec.entities.AccessRole;
import com.example.springsec.entities.Privilege;
import com.example.springsec.entities.User;
import com.example.springsec.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultDataSetter {
    private final UserRepository userRepository;

    @Autowired
    public DefaultDataSetter(UserRepository userRepository) {
        this.userRepository = userRepository;
        createUser();
    }

    private Set<Privilege> createPrivileges() {
        Privilege adminPrivilegeRead = new Privilege();
        Privilege adminPrivilegeWrite = new Privilege();

        adminPrivilegeRead.setName("ADMIN_READ");
        adminPrivilegeWrite.setName("ADMIN_WRITE");

        adminPrivilegeRead.setCode("ADMIN_READ");
        adminPrivilegeWrite.setCode("ADMIN_WRITE");

        adminPrivilegeRead.setDescription("FULL READ");
        adminPrivilegeWrite.setDescription("FULL WRITE");

        return Stream.of(
                adminPrivilegeRead, adminPrivilegeWrite
        ).collect(Collectors.toSet());
    }

    private Set<AccessRole> createAccessRoles() {
        AccessRole adminAccessRole = new AccessRole();

        adminAccessRole.setName("ADMIN");
        adminAccessRole.setCode("ADMIN");
        adminAccessRole.setDescription("FULL SUCCESS ROLE");
        adminAccessRole.setPrivileges(createPrivileges());

        return Stream.of(
                adminAccessRole
        ).collect(Collectors.toSet());
    }

    public void createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setEmail("bck-dkiselev@yandex.ru");
        user.setActive(true);
        user.setPassword(passwordEncoder().encode("admin"));
        user.setAccessRoles(createAccessRoles());

        this.userRepository.save(user);
    }

    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
