package com.luisguilherme.apireceitas.models.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.luisguilherme.apireceitas.models.embedded.Author;
import com.luisguilherme.apireceitas.models.entities.User;

public class UserDTO {

	private String id;
	private String name;
	private String email;
	
	private List<String> roles = new ArrayList();

	public UserDTO() {

	}

	public UserDTO(String id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public UserDTO(User entity) {
		id = entity.getId();
		name = entity.getName();
		email = entity.getEmail();
		for (GrantedAuthority role : entity.getRoles()) {
			roles.add(role.getAuthority());		
		}
	}
	
	public UserDTO(Author entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}
	
	public List<String> getRoles() {
		return roles;
	}
}
