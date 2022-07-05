package com.example.springsec.entities;

import com.example.springsec.model.Role;
import com.example.springsec.model.Status;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Data
@Table(indexes = {
  @Index(name = "usernameIndex", columnList = "username"),
  @Index(name = "emailIndex", columnList = "email")
})
public class User extends AbstractEntity {

	@NotEmpty
	private String username;

	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	@NotEmpty
	private boolean active;

	@ManyToMany(fetch = FetchType.EAGER, cascade = {
	  CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
	})
	@JoinTable(
	  name = "user_access_role_lnk",
	  joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
	  inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
	)
	private Set<AccessRole> accessRoles;

	@Override
	public String toString() {
		return "\nUser{" +
		  "username='" + username + '\'' +
		  ", email='" + email + '\'' +
		  ", password='" + password + '\'' +
		  ", active=" + active +
		  ", accessRoles=" + accessRoles +
		  '}';
	}
}
