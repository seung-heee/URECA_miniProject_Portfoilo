package com.ureca.myspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ureca.myspring.entity.Intro;

@Repository
public interface IntroRepository extends JpaRepository<Intro, Long>{

}
