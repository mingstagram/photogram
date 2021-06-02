package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity // DB에 테이블 생성
public class User {
	
	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
	private int id;
	
	@Column(length = 20, unique = true) // 중복값 금지
	private String username; // 로그인 아이디
	@Column(nullable = false)
	private String password; // 패스워드
	@Column(nullable = false)
	private String name; // 이름
	
	private String website; // 웹 사이트
	
	private String bio; // 자기소개
	@Column(nullable = false)
	private String email; // 이메일
	
	private String phone; // 핸드폰번호
	
	private String gender; // 성별
	
	private String profileImageUrl; // 유저 사진
	
	private String role; // 권한
	
    // mappedBy : 나는 연관관계의 주인이 아니다. 그러므로 테이블에 컬럼 만들지마
	// User를 Select할 때 해당 userId로 등록된 image들을 다 가져와.
	// LAZY : User를 Select할 때 해당 User id로 등록된 image들을 가져오지마. - 대신 getImages() 함수가 호출될 때 가져와.
	// EAGER : User를 Select할 때 해당 User id로 등록된 image들을 전부 Join해서 가져와.
	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"user"})
	private List<Image> images; // 양방향 매핑
	
	private LocalDateTime createDate; // 회원가입 일자
	
	@PrePersist // DB에 INSERT 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
	
}
