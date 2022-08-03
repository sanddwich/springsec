package com.example.springsec.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
//    WelcomePost welcomePost;
//
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/success")
    @PreAuthorize("hasAuthority('REST_API_GET')")
    public String getSuccessPage() {
        return "success";
    }

	@GetMapping("/welcome")
	@PreAuthorize("hasAuthority('SECURE_PAGE')")
	public String getWelcomePage() {
		return "welcome";
	}
//
//    @RequestMapping(
//            value = "/userinfo",
//            produces = "application/json",
//            method = {RequestMethod.GET, RequestMethod.POST})
//	public String userInfo(@ModelAttribute WelcomePost welcomePost, Model model) {
//        this.welcomePost = welcomePost;
//        System.out.println("Button: " + this.welcomePost.getUserinfo());
//        return "success";
//    }

}
