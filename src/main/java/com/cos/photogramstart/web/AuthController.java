package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 필드 DI 할 때 사용
@Controller // 1. IoC, 2. 파일을 리턴하는 컨트롤러
public class AuthController {
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);


	// 의존성 주입 방법
	// 방법 1	
//	@Autowired
//	private AuthService authService;
	
	// 방법 2 생성자
//	public AuthController(AuthService authService) {
//		this.authService = authService;
//	}
	
	 // 방법 3
	private final AuthService authService; 

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
		// User오브젝트 <- SignupDto값을 넣기
		User user = signupDto.toEntity(); // 컨트롤러에서 세팅하지 않고, dto내부 메소드에서 세팅
		log.info(user.toString());
		User userEntity = authService.회원가입(user);
		System.out.println(userEntity);
		return "auth/signin";
	}
	
}
