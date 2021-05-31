package com.cos.photogramstart.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;

@Controller
public class UserController {

	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id) {
		return "user/profile";
	}
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		// 1. 추천
		System.out.println("세션 정보 : " + principalDetails.getUser());
		
		// 2. 비추천
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		PrincipalDetails mPrincipalDetails = (PrincipalDetails) auth.getPrincipal();
		System.out.println("직접 찾은 세션 정보 : " + mPrincipalDetails.getUser());
		
		// 헤더에 시큐리티 라이브러리를 사용해서 PrincipalDetails에 접근할 수 있으므로 모델 넘길 필요 없다.
		// <sec:authorize access="isAuthenticated()">
		//     <sec:authentication property="principal" var="principal"/>
		// </sec:authorize>
//		model.addAttribute("principal",principalDetails.getUser());
		return "user/update";
	}
	
}
