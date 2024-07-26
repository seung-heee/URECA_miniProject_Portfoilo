package com.ureca.myspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ureca.myspring.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
