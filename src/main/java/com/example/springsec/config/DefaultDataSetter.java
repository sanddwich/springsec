package com.example.springsec.config;

import com.example.springsec.entities.AccessRole;
import com.example.springsec.entities.Privilege;
import com.example.springsec.entities.User;
import com.example.springsec.services.AccessRoleService;
import com.example.springsec.services.PrivilegeService;
import com.example.springsec.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DefaultDataSetter {
    private final UserService userService;

    private List<Privilege> privilegeList;
    private List<AccessRole> accessRoleList;

    public DefaultDataSetter(UserService userService) {
        this.userService = userService;
        System.out.println("DefaultDataSetter Begin");

        addDefaultAdmin();

        System.out.println("############### Admin user Created...");
    }

    public void addDefaultAdmin() {
        createPrivileges();
        createAccessRoles();
        createUser();
    }

    public void createPrivileges() {
        this.privilegeList = Stream.of(
                new Privilege("ADMIN_READ", "ADMIN_READ", "FULL READ"),
                new Privilege("ADMIN_WRITE", "ADMIN_WRITE", "FULL WRITE")
        ).collect(Collectors.toList());
    }

    public void createAccessRoles() {
        this.accessRoleList = Stream.of(
                new AccessRole("ADMIN", "ADMIN", "FULL SUCCESS ROLE", this.privilegeList)
        ).collect(Collectors.toList());
    }

    public void createUser() {
        this.userService.save(
                new User(
                        "admin", "bck-dkiselev@yandex.ru", passwordEncoder().encode("admin"),
                        true, this.accessRoleList
                )
        );
    }

    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
