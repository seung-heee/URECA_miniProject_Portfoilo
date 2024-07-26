package com.ureca.myspring.entity;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@Table(name = "education")
public class Education {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	    
    private String year;
    private String description;
    private String role;

}
