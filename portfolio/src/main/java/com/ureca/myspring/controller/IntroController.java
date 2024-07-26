package com.ureca.myspring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public Map<String,Object> update(Intro itr) {
		Map<String, Object> result = new HashMap<>();
		Optional<Intro> it = introRepo.findById(itr.getId());
		
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
			introRepo.save(intro);
			result.put("code", "ok");
		}else {
			result.put("code", "error");
			result.put("message","없거나 삭제된 정보입니다." );
		}
		return result ;
	}
}
