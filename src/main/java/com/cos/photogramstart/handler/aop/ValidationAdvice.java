package com.cos.photogramstart.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;

@Component // 왜냐하면 RestController, Service 등 모든 것들이 Component를 상속해서 만들어져 있음.
@Aspect
public class ValidationAdvice {

	@Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		System.out.println("web api 컨트롤러 ===================================");
		Object[] args = proceedingJoinPoint.getArgs();
		for (Object arg : args) {
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<>();
					
					for(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage()); 
					}
					// Exception 강제 발동
					// 여기서 터진 exception을 CustomValidationException에서 낚아채서 
					// 에러창을 좀더 깔끔하게 보여준다.
					throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
				}
			}
		}
		// ProceedingJoinPoint : 함수의 내부까지 접근할 수 있음
		// proceedingJoinPoint : profile 함수의 모든 곳에 접근할 수 있는 변수
		// profile 함수보다 먼저 실행 
		return proceedingJoinPoint.proceed(); // proceed() : 그 함수로 다시 돌아가라
	}
	
	@Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
	public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		System.out.println("web 컨트롤러 ===================================");
		Object[] args = proceedingJoinPoint.getArgs();
		for (Object arg : args) {
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
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
				}
			}
		}
		
		return proceedingJoinPoint.proceed();
	}
	
}
