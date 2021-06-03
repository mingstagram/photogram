package com.cos.photogramstart.domain.image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image, Integer> {
	//  cos(2) 로그인 : userId = 1, 3 왜냐하면 로그인 본인의 사진은 뉴스피드에 보이면 안되기 때문
	@Query(value = "SELECT * FROM image WHERE userId IN (SELECT toUserId FROM subscribe WHERE fromUserId = :principalId)", nativeQuery = true)
	List<Image> mStory(int principalId);
	
}
