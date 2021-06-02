package com.cos.photogramstart.service;

import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public User 회원프로필(int userId) {
		// SELECT * FROM image WHERE userId = :userId;
		User userEntity = userRepository.findById(userId).orElseThrow(()->{
			throw new CustomException("해당 프로필 페이지는 존재하지 않습니다.");
		});
		return userEntity;
	}
	
	@Transactional
	public User 회원수정(int id, User user) {
		// 1. 영속화
//		User userEntity = userRepository.findById(id).get(); // 1. 무조건 찾았다. 걱정마 = get()함수
																										  // 2. 못찾았어 exception 발동시킬게 = orElseThrow()함수
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			return new CustomValidationApiException("찾을 수 없는 id 입니다.");
		});
		
		// 2. 영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료) - 자동으로 DB에 반영
		userEntity.setName(user.getName());
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		
		return userEntity;
	} // 더티체킹이 일어나서 업데이트가 완료됨.
	
}
