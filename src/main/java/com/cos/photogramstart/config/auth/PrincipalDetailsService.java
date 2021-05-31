package com.cos.photogramstart.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

// 누군가가 POST방식으로 /auth/signin을 요청하면 http body에 (username,password)를 가지고와서
// IoC컨테이너에 있는 UserDetailsService한테 던진다.
@RequiredArgsConstructor
@Service // IoC
public class PrincipalDetailsService implements UserDetailsService {
	// PrincipalDetailsService : 로그인 요청이 진행된다. IoC
	
	private final UserRepository userRepository;
	
	
	// 1. 패스워드는 알아서 체킹하니깐 신경쓸 필요 없다.
	// 2. 리턴이 잘되면 자동으로 UserDetails타입을 세션으로 만든다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User userEntity = userRepository.findByUsername(username);
		
		if(userEntity == null) {
			return null;
		} else {
			return new PrincipalDetails(userEntity);
		}
		
	}
	
}
