package com.luisguilherme.apireceitas.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(value = "/recipes")
public class RecipeController {
	
	@Autowired
	RecipeService service;

	@SecurityRequirement(name= "bearerAuth")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<RecipeDTO> findById(@PathVariable String id) {
		RecipeDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}	

	@SecurityRequirement(name= "bearerAuth")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<RecipeDTO>> findAll() {
		List<RecipeDTO> result = service.findAll();
		return ResponseEntity.ok().body(result);
	}
	
	@SecurityRequirement(name= "bearerAuth")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value="/user/{userId}", produces = "application/json")
 	public ResponseEntity<List<RecipeDTO>> findByAuthor(@PathVariable String userId) {
		List<RecipeDTO> list = service.findByAuthor(userId);
		return ResponseEntity.ok().body(list);
	}

	@SecurityRequirement(name= "bearerAuth")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@GetMapping(value="/search", produces = "application/json")
 	public ResponseEntity<List<RecipeDTO>> findByText(@RequestParam(value="text") String text) {
		List<RecipeDTO> list = service.findByText(text);
		return ResponseEntity.ok().body(list);
	}
	
	@SecurityRequirement(name= "bearerAuth")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	@PostMapping(produces = "application/json")
	public ResponseEntity<RecipeDTO> insert(@RequestBody RecipeDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
}
