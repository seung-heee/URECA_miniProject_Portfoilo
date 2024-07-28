package com.ureca.myspring.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ureca.myspring.entity.Intro;
import com.ureca.myspring.repository.IntroRepository;

@Controller
public class IntroController {
	@Autowired
	private IntroRepository introRepo;
	
	@GetMapping("/intro/count")
	@ResponseBody
	public Map<String, Object> count() {
		Map<String, Object> result = new HashMap<>();
		long count = introRepo.count();
		result.put("code", "ok");
		result.put("data", count);
		return result;
	}
	
// 	정보 조회
	@GetMapping("/intro/list")
	@ResponseBody
	public Map<String, Object> list() {
		Map<String, Object> result = new HashMap<>();
		List<Intro> list = introRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		result.put("code", "ok");
		result.put("data", list);
		return result;
	}
	
// 	정보 수정
	@PostMapping("/intro/update/{id}")
	@ResponseBody
	public Map<String, Object> update(@PathVariable("id") Long id,
            
            @ModelAttribute Intro itr) {
		Map<String, Object> result = new HashMap<>();
		Optional<Intro> it = introRepo.findById(itr.getId());
		
		System.out.println(it);
		if(it.isPresent()) {
			Intro intro = it.get();
			intro.setName(itr.getName());
			intro.setRole(itr.getRole());
			intro.setPhoneNumber(itr.getPhoneNumber());
			intro.setEmail(itr.getEmail());
			intro.setAddress(itr.getAddress());
			intro.setGithub(itr.getGithub());
			intro.setTistory(itr.getTistory());
			intro.setInstagram(itr.getInstagram());
			intro.setIntroduction(itr.getIntroduction());
			intro.setImage(itr.getImage());
			
//			 이미지 파일이 제공되면 바이트 배열로 변환하여 저장
//            if (image != null && !image.isEmpty()) {
//                try {
//                    byte[] imageBytes = image.getBytes();
//                    intro.setImage(imageBytes); // Intro 엔티티에 이미지 바이트 설정
//                } catch (IOException e) {
//                    result.put("code", "error");
//                    result.put("message", "이미지 변환 중 오류가 발생했습니다.");
//                    return result;
//                }
//            }
            
			introRepo.save(intro);
			result.put("code", "ok");
		}else {
			result.put("code", "error");
			result.put("message","없거나 삭제된 정보입니다." );
		}
		return result ;
	}
}
