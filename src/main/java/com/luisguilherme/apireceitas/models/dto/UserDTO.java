package com.luisguilherme.apireceitas.models.dto;

import com.luisguilherme.apireceitas.models.entities.User;

public class UserDTO {

	private String id;
	private String name;
	private String email;

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
}
