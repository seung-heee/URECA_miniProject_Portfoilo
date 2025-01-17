package com.ureca.myspring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Project {
	@Id
	private int no;
	private String title;
	private String content;
    private String image; // 파일 경로 저장
	private String date;
}
