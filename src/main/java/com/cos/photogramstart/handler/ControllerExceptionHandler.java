package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController
@ControllerAdvice // exception을 다 낚아챈다
public class ControllerExceptionHandler {

	// 에러창에서 머무르지않고 script 함수를 통해서 처리해주는 메소드
	@ExceptionHandler(CustomValidationException.class) 
	public String validationException(CustomValidationException e) {
		return Script.back(e.getErrorMap().toString());
	}
	
/*	
   @ExceptionHandler(CustomValidationException.class) 
 
	// CustomValidationException이 발생하면 이 메소드에서 다 낚아챈다
	// CustomValidationException << 개발자가 임의로 만든 Exception
	public CMRespDto<?> validationException(CustomValidationException e) {
		// <?> : return타입이 String이던 Integer던 Map이던 알아서 찾아준다.
		
		
		return new CMRespDto<>(-1, e.getMessage(), e.getErrorMap());
	}
*/
	
}
