package com.ureca.myspring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ureca.myspring.entity.Activity;
import com.ureca.myspring.entity.Education;
import com.ureca.myspring.repository.EducationRepository;

@Controller
public class EducationController {
	@Autowired
	private EducationRepository eduRepo;
	
	@GetMapping("/education/list")
	@ResponseBody
	public Map<String, Object> list() {
		Map<String, Object> result = new HashMap<>();
		List<Education> list = eduRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		result.put("code", "ok");
		result.put("data", list);
		return result;
	}	
	
	
//	개별 조회
	@GetMapping("/education/list/{id}")
	@ResponseBody
	public Map<String, Object> list_(@PathVariable("id") Long id) {
		Map<String, Object> result = new HashMap<>();
		Optional<Education> edu = eduRepo.findById(id);
		
	    if (edu.isPresent()) {
	        result.put("code", "ok");
	        result.put("data", edu.get()); // Optional에서 값을 꺼냄
	    } else {
	        result.put("code", "error");
	        result.put("message", "학력을 찾을 수 없습니다."); // 에러 메시지 추가
	    }
	    
		return result;
	}
	
//	활동 등록
	@PostMapping("/education/regist")
	@ResponseBody
	public Map<String,Object> regist(Education edu) {
		Map<String, Object> result = new HashMap<>();
		eduRepo.save(edu);
		result.put("code", "ok");
		return result;
	}
// 	개별 활동 수정
	@PostMapping("/education/update/{id}")
	@ResponseBody
	public Map<String,Object> update(Education edu) {
		Map<String, Object> result = new HashMap<>();
		Optional<Education> ed = eduRepo.findById(edu.getId());
		
		if(ed.isPresent()) {
			Education education = ed.get();
			education.setYear(edu.getYear());
			education.setDescription(edu.getDescription());
			education.setRole(edu.getRole());
			eduRepo.save(education);
			result.put("code", "ok");
		} else {
			result.put("code", "error");
			result.put("message","없거나 삭제된 정보입니다." );
		}

		return result ;
	}
	
	
//	개별 학력 삭제
	@GetMapping("/education/delete/{id}")
	@ResponseBody
	public Map<String,Object> delete(@PathVariable("id") Long id) {
		Map<String, Object> result = new HashMap<>();
		Optional<Education> edu = eduRepo.findById(id);
		if(edu.isPresent()) {
			eduRepo.deleteById(id);
			result.put("code", "ok");
		} else {
			result.put("code", "error");
			result.put("message", "없거나 삭제된 번호입니다.");
		}
		return result;
	}
}
