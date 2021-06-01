package com.cos.photogramstart.handler.ex;

public class CustomApiException extends RuntimeException {
	
	// 객체를 구분할 때
	private static final long serialVersionUID = 1L;
 
	public CustomApiException(String message) {
		super(message); 
	}
}
