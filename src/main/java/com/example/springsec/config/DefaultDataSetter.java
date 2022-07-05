package com.example.springsec.config;

import com.example.springsec.entities.Privilege;
import com.example.springsec.services.PrivelegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DefaultDataSetter {
    private final PrivelegeService privelegeService;

    @Autowired
    public DefaultDataSetter(PrivelegeService privelegeService) {
        this.privelegeService = privelegeService;
        createPrivileges();
        System.out.println("123213321321332213");
    }

    public void createPrivileges() {
        Privilege adminPrivilegeRead = new Privilege();
        Privilege adminPrivilegeWrite = new Privilege();

        adminPrivilegeRead.setName("ADMIN_READ");
        adminPrivilegeWrite.setName("ADMIN_WRITE");

        adminPrivilegeRead.setCode("ADMIN_READ");
        adminPrivilegeWrite.setCode("ADMIN_WRITE");

        adminPrivilegeRead.setDescription("FULL READ");
        adminPrivilegeWrite.setDescription("FULL WRITE");

        this.privelegeService.save(adminPrivilegeRead);
    }

//    private Set<AccessRole> createAccessRoles() {
//        AccessRole adminAccessRole = new AccessRole();
//
//        adminAccessRole.setName("ADMIN");
//        adminAccessRole.setCode("ADMIN");
//        adminAccessRole.setDescription("FULL SUCCESS ROLE");
//        adminAccessRole.setPrivileges(createPrivileges());
//
//        return Stream.of(
//                adminAccessRole
//        ).collect(Collectors.toSet());
//    }
//
//    public void createUser() {
//        User user = new User();
//        user.setUsername("admin");
//        user.setEmail("bck-dkiselev@yandex.ru");
//        user.setActive(true);
//        user.setPassword(passwordEncoder().encode("admin"));
//        user.setAccessRoles(createAccessRoles());
//
//        this.userRepository.save(user);
//    }

    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
