package com.example.springsec.model;

import lombok.Data;

@Data
public class WelcomePost {
	private String hidden;
	private String DeleteRole;
	private String DeletePrivilege;

	@Override
	public String toString() {
		return "\nWelcomePost{" +
		  "hidden='" + hidden + '\'' +
		  ", DeleteRole='" + DeleteRole + '\'' +
		  ", DeletePrivilege='" + DeletePrivilege + '\'' +
		  '}';
	}
}
