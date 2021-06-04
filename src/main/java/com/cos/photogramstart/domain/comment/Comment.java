package com.cos.photogramstart.domain.comment;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity // DB에 테이블 생성
public class Comment {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
	private int id;
	
	@Column(length = 100, nullable = false)
	private String content; // 내용
	
	@JoinColumn(name = "userId")
	// 한명의 유저는 여러개의 댓글을 쓸 수 있다. 1:N
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@JoinColumn(name = "imageId")
	// 하나의 이미지는 여러개의 댓글을 쓸 수 있다. 1:N
	@ManyToOne(fetch = FetchType.EAGER)
	private Image image;
	
	private LocalDateTime createDate;
	
	@PrePersist // DB에 INSERT 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
}
