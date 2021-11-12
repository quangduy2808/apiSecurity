package com.news.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.NewDTO;
import com.news.service.INewService;

@RestController
public class NewAPI {
	
	@Autowired
	private INewService newService;
	
	@GetMapping("/api/new")
	public List<NewDTO> findAll(){
		return newService.findAll();
	}
	@PostMapping("/api/new")
	public NewDTO createNew(@RequestBody NewDTO newDTO) {
		return newService.save(newDTO);
	}
	
	@PutMapping("/api/new")
	public NewDTO updateNew(@RequestBody NewDTO updateNew) {
		return newService.save(updateNew);
	}
	
	@DeleteMapping("/api/new")
	public void deleteNew(@RequestBody long[] ids) {
		newService.delete(ids);
	}
}
