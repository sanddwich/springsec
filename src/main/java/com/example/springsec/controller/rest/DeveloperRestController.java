package com.example.springsec.controller.rest;

import com.example.springsec.entities.AccessRole;
import com.example.springsec.entities.Privilege;
import com.example.springsec.entities.User;
import com.example.springsec.services.AccessRoleService;
import com.example.springsec.services.PrivilegeService;
import com.example.springsec.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DeveloperRestController {
	private final PrivilegeService privilegeService;
	private final AccessRoleService accessRoleService;
	private final UserService userService;

	public DeveloperRestController(
	  PrivilegeService privilegeService,
	  AccessRoleService accessRoleService,
	  UserService userService
	) {
		this.privilegeService = privilegeService;
		this.accessRoleService = accessRoleService;
		this.userService = userService;
	}

	@GetMapping
	public String index() {
		return "REST API Service";
	}

	@GetMapping("/users")
//	@PreAuthorize("hasAuthority('REST_API_GET')")
	@PreAuthorize("hasAuthority('SECURE_PAGE')")
	public ResponseEntity userList() {
		try {
			return ResponseEntity.ok(this.userService.findAll());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Get userList ERROR: " + e.getMessage());
		}
	}

	@GetMapping("/users/{id}")
	@PreAuthorize("hasAuthority('REST_API_GET')")
	public ResponseEntity getUserRole(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(this.userService.findById(id).get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Get user ERROR: " + e.getMessage());
		}
	}

	@GetMapping("/roles")
	@PreAuthorize("hasAuthority('REST_API_GET')")
	public ResponseEntity accessRoleList() {
		try {
			return ResponseEntity.ok(this.accessRoleService.findAll());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Get accessRoleList ERROR: " + e.getMessage());
		}
	}

	@GetMapping("/roles/{id}")
	@PreAuthorize("hasAuthority('REST_API_GET')")
	public ResponseEntity getAccessRole(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(this.accessRoleService.findById(id).get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Get accessRole ERROR: " + e.getMessage());
		}
	}

	@GetMapping("/privileges")
	@PreAuthorize("hasAuthority('REST_API_GET')")
	public ResponseEntity privilegeList() {
		try {
			return ResponseEntity.ok(this.privilegeService.findAll());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Get privilegeList ERROR: " + e.getMessage());
		}
	}

	@GetMapping("/privileges/{id}")
	@PreAuthorize("hasAuthority('REST_API_GET')")
	public ResponseEntity getPrivilege(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(this.privilegeService.findById(id).get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Get privilege ERROR: " + e.getMessage());
		}
	}

//	@GetMapping()
//	@PreAuthorize("hasAuthority('developers:read')")
//	public List<Developer> getAll() {
//		return DEVELOPERS;
//	}
//
//	@GetMapping("/{id}")
//	@PreAuthorize("hasAuthority('developers:read')")
//	public Developer getById(@PathVariable Long id) {
//		return DEVELOPERS.stream().filter(developer -> developer.getId().equals(id))
//		  .findFirst()
//		  .orElse(null);
//	}
//
//	@PostMapping
//	@PreAuthorize("hasAuthority('developers:write')")
//	public Developer create(@RequestBody Developer developer) {
//		this.DEVELOPERS.add(developer);
//		return developer;
//	}
//
//	@DeleteMapping("/{id}")
//	@PreAuthorize("hasAuthority('developers:write')")
//	public void deleteById(@PathVariable Long id) {
//		this.DEVELOPERS.removeIf(developer -> developer.getId().equals(id));
//	}
}
