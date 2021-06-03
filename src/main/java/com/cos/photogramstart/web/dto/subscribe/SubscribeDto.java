package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {

	private int id;
	private String username;
	private String profileImageUrl;
	// Integer인 이유는 true/false 값을 못받는다.
	private Integer subscribeState;
	private Integer equalUserState;
	
}
