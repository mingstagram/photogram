package com.cos.photogramstart.web.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T> { // message와 에러내용을 함께 보기위해 만든 DTO
	private int code; // 1(성공), -1(실패)
	private String message;
	private T errorMap;
}
