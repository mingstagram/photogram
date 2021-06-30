package com.cos.photogramstart.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
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
	@PostMapping("/auth/signup") // BindingResult : Validator를 상속받는 클래스에서 객체값을 검증하는 방식
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // form으로 데이터가 날아오면 key=value(x-www-form-urlencoded)
		
		// 만약 bindingResult에 에러값(valid걸었던것에 걸림)이 있다면
		// 오류가 발생하면 bindingResult에 모아줘서 List로 받는다
		// 유효성검사는 front단 뿐아니라 Back단에서도 해줘야된다.
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage()); 
			}
			// Exception 강제 발동
			// 여기서 터진 exception을 CustomValidationException에서 낚아채서 
			// 에러창을 좀더 깔끔하게 보여준다.
			throw new CustomValidationException("유효성 검사 실패함", errorMap);
		} else {
			log.info(signupDto.toString());
			// User오브젝트 <- SignupDto값을 넣기
			User user = signupDto.toEntity(); // 컨트롤러에서 세팅하지 않고, dto내부 메소드에서 세팅
			log.info(user.toString());
			User userEntity = authService.회원가입(user);
			System.out.println(userEntity);
			return "auth/signin";
		}
		
		
	}
	
}
