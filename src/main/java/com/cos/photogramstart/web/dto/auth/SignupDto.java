package com.cos.photogramstart.web.dto.auth;

import lombok.Data;

@Data // Getter, Setter
// 통신할때 필요한 데이터를 담아주는 DTO
public class SignupDto {

	private String username;
	private String password;
	private String email;
	private String name;
	
}
