package com.ureca.myspring.controller;

import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ureca.myspring.entity.Board;
import com.ureca.myspring.repository.BoardRepository;

@Controller
public class BoardController {
	@Value("${file.upload-dir}")
	private String uploadDir;

	@Autowired
	private BoardRepository boardRepo;
	
	@GetMapping("/board/count")
	@ResponseBody
	public Map<String, Object> count() {
		Map<String, Object> result = new HashMap<>();
		long count = boardRepo.count();
		result.put("code", "ok");
		result.put("data", count);
		return result;
	}
	
	@GetMapping("/board/list")
	@ResponseBody
	public Map<String, Object> list() {
		Map<String, Object> result = new HashMap<>();
		List<Board> list = boardRepo.findAll(Sort.by(Sort.Direction.DESC, "no"));
		result.put("code", "ok");
		result.put("data", list);
		return result;
	}
	
	@PostMapping("/board/regist")
	@ResponseBody
	public Map<String, Object> regist(@ModelAttribute Board bd,
	                                  @RequestParam("image") MultipartFile image) {
	    Map<String, Object> result = new HashMap<>();
	    bd.setDate(new Date());

	    if (!image.isEmpty()) {
	        try {
	            // 파일 저장
	            byte[] bytes = image.getBytes();
	            String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename(); // 중복 파일 처리 예시
	            Path path = Paths.get(uploadDir + "/" + filename);
	            Files.write(path, bytes);
	            System.out.println(image.getOriginalFilename());
	            System.out.println(image.getResource().getFile().getAbsolutePath());

	            // 파일 경로 설정
	            bd.setImage("upload/" + filename); // 상대 경로로 설정 예시
	        } catch (IOException e) {
	        	System.out.println(image.getOriginalFilename());

	            e.printStackTrace();
	            result.put("code", "error");
	            result.put("message", "File upload failed");
	            return result;
	        }
	    }

	    try {
	        // 데이터베이스에 저장
	        boardRepo.save(bd);
	        result.put("code", "ok");
	    } catch (Exception e) {
	        e.printStackTrace();
	        result.put("code", "error");
	        result.put("message", "Failed to save board");
	    }

	    return result;
	}

}