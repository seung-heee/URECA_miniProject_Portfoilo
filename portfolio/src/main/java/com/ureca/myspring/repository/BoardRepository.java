package com.ureca.myspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ureca.myspring.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
