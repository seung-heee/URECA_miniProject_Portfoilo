package com.ureca.myspring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ureca.myspring.entity.Activity;
import com.ureca.myspring.entity.Intro;
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
}
