package com.example.springsec.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@Table(indexes = {
  @Index(name="nameIndex", columnList = "name"),
  @Index(name="codeIndex", columnList = "code"),
  @Index(name="descriptionIndex", columnList = "description")
})
public class Privilege extends AbstractEntity {
	@NotEmpty
	private String name;

	@NotEmpty
	private String code;

	@NotEmpty
	private String description;

	@ManyToMany(mappedBy = "privileges", cascade = CascadeType.ALL)
	private List<AccessRole> roles;

	@Override
	public String toString() {
		return "\nPrivilege{" +
		  "name='" + name + '\'' +
		  ", code='" + code + '\'' +
		  ", description='" + description + '\'' +
		  ", roles=" + roles +
		  '}';
	}
}
