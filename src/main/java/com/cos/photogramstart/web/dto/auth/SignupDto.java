package com.cos.photogramstart.web.dto.auth;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data // Getter, Setter
// 통신할때 필요한 데이터를 담아주는 DTO
public class SignupDto {
	// https://bamdule.tistory.com/35 (@Valid 어노테이션 종류)
	@Size(min = 2, max = 20)
	@NotBlank // Null, 빈 문자열, 스페이스만 있는 문자열 불가
	private String username;
	
	@NotBlank
	private String password;
	
	@NotBlank 
	private String email;
	
	@NotBlank 
	private String name;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
	
}
