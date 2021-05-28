package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.web.dto.auth.SignupDto;

@Controller // 1. IoC, 2. 파일을 리턴하는 컨트롤러
public class AuthController {
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);


	// 로그인 화면
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	// 회원가입 화면
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	// 회원가입 로직
	// 회원가입버튼 -> /auth/signup -> /auth/signin
	@PostMapping("/auth/signup")
	public String signup(SignupDto signupDto) { // form으로 데이터가 날아오면 key=value(x-www-form-urlencoded)
		
		log.info(signupDto.toString());
		
		return "auth/signin";
	}
	
}
