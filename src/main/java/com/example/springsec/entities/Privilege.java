package com.example.springsec.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

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
	private Set<AccessRole> roles;
}
