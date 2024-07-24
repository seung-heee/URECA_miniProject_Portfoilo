package com.ureca.myspring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/intro/list")
	@ResponseBody
	public Map<String, Object> list() {
		Map<String, Object> result = new HashMap<>();
		List<Intro> list = introRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		result.put("code", "ok");
		result.put("data", list);
		return result;
	}
}
