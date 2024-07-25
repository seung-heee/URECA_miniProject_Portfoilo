package com.ureca.myspring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Intro {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String name;
	private String role;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	private String email;
	private String address;
	@Lob
	private String introduction;
	private String github;
	private String tistory;
    private String instagram;
}
