package com.example.springsec.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/secure")
public class SecureController {

	@GetMapping
	public String index() {
		return "secure/index";
	}
}
