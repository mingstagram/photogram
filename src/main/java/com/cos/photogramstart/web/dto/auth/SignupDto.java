package com.cos.photogramstart.web.dto.auth;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data // Getter, Setter
// 통신할때 필요한 데이터를 담아주는 DTO
public class SignupDto {

	private String username;
	private String password;
	private String email;
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
