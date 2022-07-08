package com.example.springsec.entities;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity {

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
}
