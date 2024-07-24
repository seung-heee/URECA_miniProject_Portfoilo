package com.ureca.myspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ureca.myspring.entity.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

}
