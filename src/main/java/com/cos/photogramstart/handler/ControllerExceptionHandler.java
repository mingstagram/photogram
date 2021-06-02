package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController
@ControllerAdvice // exception을 다 낚아챈다
public class ControllerExceptionHandler {

	// 에러창에서 머무르지않고 script 함수를 통해서 처리해주는 메소드
	@ExceptionHandler(CustomValidationException.class) 
	public String validationException(CustomValidationException e) {
		if(e.getErrorMap() == null) {
			return Script.back(e.getMessage());
		} else {
			return Script.back(e.getErrorMap().toString());
		}
		
	} // 자바스크립트 리턴

	// ================================
	// CMRespDto, Script 비교
	// 1. 클라이언트에게 응답할 때는 Script가 좋음.
	// 2. Ajax통신 - CMRespDto가 좋음
	// 3. Android통신 - CMRespDto가 좋음
	// ================================
	
   @ExceptionHandler(CustomValidationApiException.class) 
	// CustomValidationException이 발생하면 이 메소드에서 다 낚아챈다
	// CustomValidationException << 개발자가 임의로 만든 Exception
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
		// <?> : return타입이 String이던 Integer던 Map이던 알아서 찾아준다.
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
	} // 데이터 리턴
   
   @ExceptionHandler(CustomApiException.class) 
  	public ResponseEntity<?> ㅁpiException(CustomApiException e) {
  		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), null), HttpStatus.BAD_REQUEST);
  	}
   
}
