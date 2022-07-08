package com.example.springsec.controller;

import com.example.springsec.entities.AccessRole;
import com.example.springsec.entities.Privilege;
import com.example.springsec.model.WelcomePost;
import com.example.springsec.services.AccessRoleService;
import com.example.springsec.services.PrivilegeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WelcomeController {
	private final PrivilegeService privilegeService;
	private final AccessRoleService accessRoleService;
	private WelcomePost welcomePost;
	private String outputParam;

	public WelcomeController(AccessRoleService accessRoleService, PrivilegeService privilegeService) {
		this.accessRoleService = accessRoleService;
		this.privilegeService = privilegeService;
	}

	@GetMapping("/get")
	public String getIndexGet() {
		System.out.println("Welcome Page GET");
		return "welcome";
	}

	@RequestMapping(
	  value = "/post",
	  produces = "application/json",
	  method = {RequestMethod.GET, RequestMethod.POST})
	public String getIndexPost(@ModelAttribute WelcomePost welcomePost, Model model) {
		this.welcomePost = welcomePost;
		System.out.println(
		  "Welcome Page Post params: " + welcomePost.getHidden() + " || "
			+ welcomePost.getDeleteRole() + " || " + welcomePost.getDeletePrivilege()
		);

		actionExecutor();
		model.addAttribute("attr", this.outputParam);

		return "welcome";
	}

	public void actionExecutor() {
		if (this.welcomePost.getDeletePrivilege() != null) {
			this.outputParam = "DeletePrivilege action!";
			Privilege privilege = privilegeService.findAll().stream().findFirst().get();
			privilegeService.delete(privilege);
			System.out.println("Delete Privilege with ID: " + privilege.getId());
		}

		if (this.welcomePost.getDeleteRole() != null) {
			this.outputParam = "DeleteRole action!";
			AccessRole accessRole = accessRoleService.findAll().stream().findFirst().get();
			accessRoleService.delete(accessRole);
			System.out.println("Delete AccessRole with ID: " + accessRole.getId());
		}
	}
}
