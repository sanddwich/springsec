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
    private final AccessRoleService accessRoleService;
    private final PrivilegeService privilegeService;

    private List<Privilege> privilegeList;
    private List<AccessRole> accessRoleList;

    public DefaultDataSetter(UserService userService, AccessRoleService accessRoleService, PrivilegeService privilegeService) {
        this.userService = userService;
        this.accessRoleService = accessRoleService;
        this.privilegeService = privilegeService;
        System.out.println("DefaultDataSetter Begin");

        for (int i = 0; i < 10; i++) {
            createRolesAndPrivileges();
        }

//		createUser();
    }

    public void createRolesAndPrivileges() {
        createPrivileges();
        createAccessRoles();
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

        this.privilegeList = Stream.of(
                adminPrivilegeRead, adminPrivilegeWrite
        ).collect(Collectors.toList());
//
//        this.privilegeList.forEach(this.privilegeService::save);
    }

    public void createAccessRoles() {
        AccessRole adminAccessRole = new AccessRole();

        adminAccessRole.setName("ADMIN");
        adminAccessRole.setCode("ADMIN");
        adminAccessRole.setDescription("FULL SUCCESS ROLE");
        adminAccessRole.setPrivileges(this.privilegeList);

        accessRoleList = Stream.of(
                adminAccessRole
        ).collect(Collectors.toList());

        accessRoleList.forEach(accessRoleService::save);
    }

    public void consoleUserPrint(User user) {
        System.out.println(user.getClass() + ": " + user.getUsername());
    }

    public void createUser() {
        User user = new User();
        user.setUsername("admin");
        user.setEmail("bck-dkiselev@yandex.ru");
        user.setActive(true);
        user.setPassword(passwordEncoder().encode("admin"));
        user.setAccessRoles(this.accessRoleList);

        System.out.println("############### Admin user Create...");
        this.userService.save(user);

        if (this.userService.findByUsernameOREmail(user))
            System.out.println("Пользователь: " + user.getUsername() + " создан!");
    }

    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
