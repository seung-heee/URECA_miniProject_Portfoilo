package com.ureca.myspring.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Board {
	@Id
	private int no;
	private String title;
	private String content;
    private String image; // 파일 경로 저장
	private Date date;
}
