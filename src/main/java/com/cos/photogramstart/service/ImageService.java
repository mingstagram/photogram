package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {

	private final ImageRepository imageRepository;
	
	// 따로 정의해놓는 이유 : 직접 정의해두면 실수의 위험도 있을수 있기때문에
	@Value("${file.path}") // application.yml에 정의해놓은 값 가져오기
	private String uploadFolder;
	
	@Transactional 
	// @Transactional 거는 이유  
	// DB값을 변경해주는경우 오류가 났을때 DB에 바로 적용하지않고 rollback처리를 해준다.
	// 그래서 변경(update, insert, delete)이 일어날 때는 @Transactional을 걸어주는 습관을 들여야 한다.
	public void 사진업로드(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
		// UUID란? 네트워크 상에서 고유성이 보장되는 id를 만들기 위한 표준 규약.
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename(); // ex) 1.jpg
		System.out.println("이미지 파일 이름 : " + imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder + imageFileName);
		
		// 통신, I/O가 일어날 때 예외가 발생할 수 있기에 try/catch로 묶어준다.
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// image 테이블에 저장
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser()); 
		imageRepository.save(image);
		
//		System.out.println("imageEntity : "+imageEntity.toString());
		
		
	}
	
}
