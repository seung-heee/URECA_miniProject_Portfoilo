package com.ureca.myspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ureca.myspring.entity.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {
    // Custom query methods (if needed) can be defined here
}
