package com.example.springsec.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Data
@Table(indexes = {
  @Index(name = "nameIndex", columnList = "name"),
  @Index(name = "codeIndex", columnList = "code"),
  @Index(name = "descriptionIndex", columnList = "description")
})
public class AccessRole extends AbstractEntity {

	@NotEmpty
	private String name;

	@NotEmpty
	private String code;

	@NotEmpty
	private String description;

	@ManyToMany(mappedBy = "accessRoles", cascade = CascadeType.ALL)
	private Set<User> users;

	@ManyToMany(fetch = FetchType.EAGER, cascade = {
	  CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
	})
	@JoinTable(
	  name = "acess_role_privilege_lnk",
	  joinColumns = {@JoinColumn(name = "access_role_id", referencedColumnName = "id")},
	  inverseJoinColumns = {@JoinColumn(name = "privilege_id", referencedColumnName = "id")}
	)
	private Set<Privilege> privileges;

	@Override
	public String toString() {
		return "\nAccessRole{" +
		  "name='" + name + '\'' +
		  ", code='" + code + '\'' +
		  ", description='" + description + '\'' +
		  ", users=" + users +
		  ", privileges=" + privileges +
		  '}';
	}
}
