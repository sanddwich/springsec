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
	private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	private User admin = new User(
	  "admin", "bck-dkiselev@yandex.ru", this.bCryptPasswordEncoder.encode("Gjlvfcnthmt1!"),
	  true, Collections.emptyList()
	);

	private User user = new User(
	  "user", "sanddwich51@gmail.com", this.bCryptPasswordEncoder.encode("user"),
	  true, Collections.emptyList()
	);

	private List<Privilege> privilegeList = Stream.of(
	  new Privilege("ADMIN", "ADMIN", "ADMIN ACCESS"),
	  new Privilege("USER", "USER", "USER ACCESS"),
	  new Privilege("SECURE_PAGE", "SECURE_PAGE", "SECURE PAGE"),
	  new Privilege("REST_API_GET", "REST_API_GET", "REST API GET"),
	  new Privilege("REST_API_POST", "REST_API_POST", "REST API POST"),
	  new Privilege("REST_API_UPDATE", "REST_API_UPDATE", "REST API UPDATE"),
	  new Privilege("REST_API_DELETE", "REST_API_DELETE", "REST API DELETE")
	).collect(Collectors.toList());

	private List<AccessRole> accessRoleList = Stream.of(
	  new AccessRole("ADMIN", "ADMIN", "ADMIN ROLE", Collections.emptyList()),
	  new AccessRole("USER", "USER", "USER ROLE", Collections.emptyList()),
	  new AccessRole("API_FULL", "API_FULL", "API FULL ACCESS ROLE", Collections.emptyList()),
	  new AccessRole("API_GET", "API_GET", "API READ ACCESS ROLE", Collections.emptyList())
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

		System.out.println("############### Default Data Created...");
	}

	public void createUsers() {
		User admin = new User(
		  "admin", "bck-dkiselev@yandex.ru", this.bCryptPasswordEncoder.encode("Gjlvfcnthmt1!"),
		  true, Collections.emptyList()
		);

		User user = new User(
		  "user", "sanddwich51@gmail.com", this.bCryptPasswordEncoder.encode("user"),
		  true, Collections.emptyList()
		);

		AccessRole adminAccessRole =
		  new AccessRole("ADMIN", "ADMIN", "ADMIN ROLE", Stream.of(
			new Privilege("ADMIN", "ADMIN", "ADMIN ACCESS"),
			new Privilege("USER", "USER", "USER ACCESS"),
			new Privilege("SECURE_PAGE", "SECURE_PAGE", "SECURE PAGE")
		  ).collect(Collectors.toList()));

		AccessRole adminAPIAccessRole =
		  new AccessRole("API_FULL", "API_FULL", "API FULL ACCESS ROLE", Stream.of(
			new Privilege("REST_API_GET", "REST_API_GET", "REST API GET"),
			new Privilege("REST_API_POST", "REST_API_POST", "REST API POST"),
			new Privilege("REST_API_UPDATE", "REST_API_UPDATE", "REST API UPDATE"),
			new Privilege("REST_API_DELETE", "REST_API_DELETE", "REST API DELETE")
		  ).collect(Collectors.toList()));

		AccessRole userAccessRole =
		  new AccessRole("USER", "USER", "USER ROLE", Stream.of(
			new Privilege("USER", "USER", "USER ACCESS")
		  ).collect(Collectors.toList()));

		AccessRole userApiAccessRole =
		  new AccessRole("API_GET", "API_GET", "API READ ACCESS ROLE", Stream.of(
			new Privilege("REST_API_GET", "REST_API_GET", "REST API GET")
		  ).collect(Collectors.toList()));

		admin.setAccessRoles(Stream.of(
		  adminAccessRole, adminAPIAccessRole
		).collect(Collectors.toList()));

		user.setAccessRoles(Stream.of(
		  userAccessRole, userApiAccessRole
		).collect(Collectors.toList()));

		admin = this.createUser(admin);
		user = this.createUser(user);
	}

	public User createUser(User user) {
		user.setAccessRoles(
		  user.getAccessRoles().stream()
			.map(accessRole -> {
				accessRole.setPrivileges(
				  accessRole.getPrivileges().stream()
				  .peek(this.privilegeService::save)
				  .filter(this.privilegeService::findPrivilegeByNameOrCode)
				  .map(this::getDBPrivilegeByCode)
				  .collect(Collectors.toList())
				);
				return accessRole;
			})
		    .peek(this.accessRoleService::save)
			.filter(this.accessRoleService::findAccessRoleByNameOrCode)
		    .map(this::getDBAccessRoleByCode)
			.collect(Collectors.toList())
		);

		return this.userService.save(user);
	}

	public Privilege getDBPrivilegeByCode(Privilege privilege) {
		return this.privilegeService.findByCode(privilege.getCode()).stream().findFirst().get();
	}

	public AccessRole getDBAccessRoleByCode(AccessRole accessRole) {
		return this.accessRoleService.findByCode(accessRole.getCode()).stream().findFirst().get();
	}

//	public boolean checkUserExist(User user) {
//		return this.userService.findByUsernameOREmail(user);
//	}
//
//	public void createUsers() {
//		if (!checkUserExist(this.adminUser)) createAdmin();
//		this.adminUser = this.userService.findByUsername(this.adminUser.getUsername()).stream().findFirst().get();
//
//		if (!checkUserExist(this.simpleUser)) createUser();
//		this.simpleUser = this.userService.findByUsername(this.adminUser.getUsername()).stream().findFirst().get();
//	}
//
//	public void createAdmin() {
//		createAdminPrivileges();
//		createAdminAccessRoles();
//		createAdminUser();
//	}
//
//	public void createUser() {
//		createPrivileges();
//		createAccessRoles();
//		createSimpleUser();
//	}
//
//	public void createAdminPrivileges() {
//		this.privilegeList.stream()
//		  .peek(this.privilegeService::save)
//		  .filter(this.privilegeService::findPrivilegeByNameOrCode)
//		  .map(this::getDBPrivilegeByCode)
//		  .collect(Collectors.toList());
//	}
//
//	public void createPrivileges() {
//		String code = "REST_API_GET";
//		this.privilegeList = Stream.of(
//		  this.privilegeService.findByCode(code).stream().findFirst().get()
//		).collect(Collectors.toList());
//	}
//
//	public void createAdminAccessRoles() {
//		this.accessRoleList.stream()
//		  .peek(this.accessRoleService::save)
//		  .filter(this.accessRoleService::findAccessRoleByNameOrCode)
//		  .map(this::getDBAccessRoleByCode)
//		  .collect(Collectors.toList());
//	}
//
//	public void createAccessRoles() {
//		this.accessRoleList = Stream.of(
//		  new AccessRole("USER", "USER", "USER ACCESS", this.privilegeList)
//		)
//		  .peek(this.accessRoleService::save)
//		  .filter(this.accessRoleService::findAccessRoleByNameOrCode)
//		  .map(this::getDBAccessRoleByCode)
//		  .collect(Collectors.toList());
//	}
//
//	public void createAdminUser() {
//		this.adminUser.setAccessRoles(this.accessRoleList);
//		this.userService.save(this.adminUser);
//	}
//
//	public void createSimpleUser() {
//		this.simpleUser.setAccessRoles(this.accessRoleList);
//		this.userService.save(this.simpleUser);
//	}
//
//	public Privilege getDBPrivilegeByCode(Privilege privilege) {
//		return this.privilegeService.findByCode(privilege.getCode()).stream().findFirst().get();
//	}
//
//	public AccessRole getDBAccessRoleByCode(AccessRole accessRole) {
//		return this.accessRoleService.findByCode(accessRole.getCode()).stream().findFirst().get();
//	}
//

}
