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
import com.ureca.myspring.entity.Project;
import com.ureca.myspring.repository.ActivityRepository;

@Controller
public class ActivityController {
	@Autowired
	private ActivityRepository actRepo;
	
	@GetMapping("/activity/list")
	@ResponseBody
	public Map<String, Object> list() {
		Map<String, Object> result = new HashMap<>();
		List<Activity> list = actRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		result.put("code", "ok");
		result.put("data", list);
		return result;
	}
	
//	개별 조회
	@GetMapping("/activity/list/{id}")
	@ResponseBody
	public Map<String, Object> list_(@PathVariable("id") Long id) {
		Map<String, Object> result = new HashMap<>();
		Optional<Activity> ac = actRepo.findById(id);
		
	    if (ac.isPresent()) {
	        result.put("code", "ok");
	        result.put("data", ac.get()); // Optional에서 값을 꺼냄
	    } else {
	        result.put("code", "error");
	        result.put("message", "활동을 찾을 수 없습니다."); // 에러 메시지 추가
	    }
	    
		return result;
	}
	
//	활동 등록
	@PostMapping("/activity/regist")
	@ResponseBody
	public Map<String,Object> regist(Activity act) {
		Map<String, Object> result = new HashMap<>();
		actRepo.save(act);
		result.put("code", "ok");
		return result;
	}
	
// 	개별 활동 수정
	@PostMapping("/activity/update/{id}")
	@ResponseBody
	public Map<String,Object> update(Activity act) {
		Map<String, Object> result = new HashMap<>();
		Optional<Activity> ac = actRepo.findById(act.getId());
		
		if(ac.isPresent()) {
			Activity activity = ac.get();
			activity.setYear(act.getYear());
			activity.setDescription(act.getDescription());
			activity.setRole(act.getRole());
			actRepo.save(activity);
			result.put("code", "ok");
		} else {
			result.put("code", "error");
			result.put("message","없거나 삭제된 정보입니다." );
		}

		return result ;
	}
		
//	개별 활동 삭제
	@GetMapping("/activity/delete/{id}")
	@ResponseBody
	public Map<String,Object> delete(@PathVariable("id") Long id) {
		Map<String, Object> result = new HashMap<>();
		Optional<Activity> act = actRepo.findById(id);
		if(act.isPresent()) {
			actRepo.deleteById(id);
			result.put("code", "ok");
		} else {
			result.put("code", "error");
			result.put("message", "없거나 삭제된 번호입니다.");
		}
		return result;
	}
}
