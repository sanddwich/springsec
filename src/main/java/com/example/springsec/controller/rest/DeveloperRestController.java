package com.example.springsec.controller.rest;

import com.example.springsec.entities.AccessRole;
import com.example.springsec.entities.User;
import com.example.springsec.model.Developer;
import com.example.springsec.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class DeveloperRestController {
	private final UserService userService;

	public DeveloperRestController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String index() {
		return "REST API Service";
	}

	@GetMapping("/fetch")
	@PreAuthorize("hasAuthority('REST_API_GET')")
	public List<AccessRole> getAdmin() {
//		User user = new User(
//		  "aaaaaaaaaaaaaaaa",
//		  "sdsaadsa@dsfsdfdsf.com",
//		  "bbbbbbbbbbbbbbbb",
//		  true,
//		  Collections.emptyList()
//		);

		User user = this.userService.findByUsername("admin").stream().findFirst().get();
		return user.getAccessRoles();
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
