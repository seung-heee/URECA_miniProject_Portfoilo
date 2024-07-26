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

import com.ureca.myspring.entity.Project;
import com.ureca.myspring.repository.ProjectRepository;

@Controller
public class ProjectController {

	@Autowired
	private ProjectRepository proRepo;
	
//	프로젝트 전체 조회
	@GetMapping("/project/list")
	@ResponseBody
	public Map<String, Object> list() {
		Map<String, Object> result = new HashMap<>();
		List<Project> list = proRepo.findAll(Sort.by(Sort.Direction.DESC, "no"));
		result.put("code", "ok");
		result.put("data", list);
		return result;
	}
	
	
//	프로젝트 개별 조회
	@GetMapping("/project/list/{no}")
	@ResponseBody
	public Map<String, Object> list_(Project pro) {
		Map<String, Object> result = new HashMap<>();
		Optional<Project> pr = proRepo.findById(pro.getNo());
		result.put("code", "ok");
		result.put("data", pr);
		return result;
	}

//	프로젝트 등록
	@PostMapping("/project/regist")
	@ResponseBody
	public Map<String,Object> regist(Project pro) {
		Map<String, Object> result = new HashMap<>();
		proRepo.save(pro);
		result.put("code", "ok");
		return result;
	}
	
// 	개별 프로젝트 수정
	@PostMapping("/project/update/{no}")
	@ResponseBody
	public Map<String,Object> update(Project pro) {
		Map<String, Object> result = new HashMap<>();
		Optional<Project> pr = proRepo.findById(pro.getNo());
		
		if(pr.isPresent()) {
			Project project = pr.get();
			project.setTitle(pro.getTitle());
			project.setContent(pro.getContent());
			project.setImage(pro.getImage());
			project.setDate(pro.getDate());
			proRepo.save(project);
			result.put("code", "ok");
		} else {
			result.put("code", "error");
			result.put("message","없거나 삭제된 정보입니다." );
		}

		return result ;
	}
	
//	개별 프로젝트 삭제
	@GetMapping("/project/delete/{no}")
	@ResponseBody
	public Map<String,Object> delete(@PathVariable("no") int no) {
		Map<String, Object> result = new HashMap<>();
		Optional<Project> pr = proRepo.findById(no);
		if(pr.isPresent()) {
			proRepo.deleteById(no);
			result.put("code", "ok");
		} else {
			result.put("code", "error");
			result.put("message", "없거나 삭제된 번호입니다.");
		}
		return result;
	}
	
}