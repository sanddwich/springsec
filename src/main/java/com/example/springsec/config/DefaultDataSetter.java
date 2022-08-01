package com.example.springsec.config;

import com.example.springsec.entities.AccessRole;
import com.example.springsec.entities.Privilege;
import com.example.springsec.entities.User;
import com.example.springsec.security.SecurityUser;
import com.example.springsec.services.AccessRoleService;
import com.example.springsec.services.PrivilegeService;
import com.example.springsec.services.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DefaultDataSetter {
	private final UserService userService;
	private final PrivilegeService privilegeService;
	private final AccessRoleService accessRoleService;

	private User adminUser = new User(
	  "admin", "admin@springsec.ru", passwordEncoder().encode("admin"),
	  true, Collections.emptyList()
	);

	private User simpleUser = new User(
	  "user", "user@springsec.ru", passwordEncoder().encode("user"),
	  true, Collections.emptyList()
	);

	private List<Privilege> privilegeList = Stream.of(
	  new Privilege("SECURE_PAGE", "SECURE_PAGE", "SECURE PAGE"),
	  new Privilege("REST_API_GET", "REST_API_GET", "REST API GET"),
	  new Privilege("REST_API_POST", "REST_API_POST", "REST API POST"),
	  new Privilege("REST_API_UPDATE", "REST_API_UPDATE", "REST API UPDATE"),
	  new Privilege("REST_API_DELETE", "REST_API_DELETE", "REST API DELETE")
	).collect(Collectors.toList());

	private List<AccessRole> accessRoleList = Stream.of(
	  new AccessRole("ADMIN", "ADMIN", "FULL SUCCESS ROLE", this.privilegeList)
	).collect(Collectors.toList());

	public DefaultDataSetter(
	  UserService userService,
	  PrivilegeService privilegeService,
	  AccessRoleService accessRoleService
	) {
		this.userService = userService;
		this.privilegeService = privilegeService;
		this.accessRoleService = accessRoleService;
		System.out.println("DefaultDataSetter Begin");

		this.createUsers();
//		this.authorization();

		System.out.println("############### Default Data Created...");
	}

	public void authorization() {

	}

	public boolean checkUserExist(User user) {
		return this.userService.findByUsernameOREmail(user);
	}

	public void createUsers() {
		if (!checkUserExist(this.adminUser)) createAdmin();
		this.adminUser = this.userService.findByUsername(this.adminUser.getUsername()).stream().findFirst().get();

		if (!checkUserExist(this.simpleUser)) createUser();
		this.simpleUser = this.userService.findByUsername(this.adminUser.getUsername()).stream().findFirst().get();
	}

	public void createAdmin() {
		createAdminPrivileges();
		createAdminAccessRoles();
		createAdminUser();
	}

	public void createUser() {
		createPrivileges();
		createAccessRoles();
		createSimpleUser();
	}

	public void createAdminPrivileges() {
		this.privilegeList.stream()
		  .peek(this.privilegeService::save)
		  .filter(this::privilegeIsExist)
		  .map(this::getDBPrivilegeByCode)
		  .collect(Collectors.toList());
	}

	public void createPrivileges() {
		String code = "REST_API_GET";
		this.privilegeList = Stream.of(
		  this.privilegeService.findByCode(code).stream().findFirst().get()
		).collect(Collectors.toList());
	}

	public void createAdminAccessRoles() {
		this.accessRoleList.stream()
		  .peek(this.accessRoleService::save)
		  .filter(this::accessRoleIsExist)
		  .map(this::getDBAccessRoleByCode)
		  .collect(Collectors.toList());
	}

	public void createAccessRoles() {
		this.accessRoleList = Stream.of(
		  new AccessRole("USER", "USER", "USER ACCESS", this.privilegeList)
		)
		  .peek(this.accessRoleService::save)
		  .filter(this::accessRoleIsExist)
		  .map(this::getDBAccessRoleByCode)
		  .collect(Collectors.toList());
	}

	public void createAdminUser() {
		this.adminUser.setAccessRoles(this.accessRoleList);
		this.userService.save(this.adminUser);
	}

	public void createSimpleUser() {
		this.simpleUser.setAccessRoles(this.accessRoleList);
		this.userService.save(this.simpleUser);
	}

	public Privilege getDBPrivilegeByCode(Privilege privilege) {
		return this.privilegeService.findByCode(privilege.getCode()).stream().findFirst().get();
	}

	public boolean privilegeIsExist(Privilege privilege) {
		if (!this.privilegeService.findByCode(privilege.getCode()).isEmpty()) {
			return true;
		}
		return false;
	}

	public AccessRole getDBAccessRoleByCode(AccessRole accessRole) {
		return this.accessRoleService.findByCode(accessRole.getCode()).stream().findFirst().get();
	}

	public boolean accessRoleIsExist(AccessRole accessRole) {
		if (!this.accessRoleService.findByCode(accessRole.getCode()).isEmpty()) {
			return true;
		}
		return false;
	}

	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
}
