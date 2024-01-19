package com.luisguilherme.apireceitas.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luisguilherme.apireceitas.models.dto.RecipeDTO;
import com.luisguilherme.apireceitas.services.RecipeService;

@RestController
@RequestMapping(value = "/recipes")
public class RecipeController {
	
	@Autowired
	RecipeService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<RecipeDTO> findById(@PathVariable String id) {
		RecipeDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}	

	@GetMapping
	public ResponseEntity<List<RecipeDTO>> findAll() {
		List<RecipeDTO> result = service.findAll();
		return ResponseEntity.ok().body(result);
	}
	
	@GetMapping(value="/user/{userId}")
 	public ResponseEntity<List<RecipeDTO>> findByAuthor(@PathVariable String userId) {
		List<RecipeDTO> list = service.findByAuthor(userId);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value="/search")
 	public ResponseEntity<List<RecipeDTO>> findByText(@RequestParam(value="text") String text) {
		List<RecipeDTO> list = service.findByText(text);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<RecipeDTO> insert(@RequestBody RecipeDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping(value="/{id}")
 	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
