package com.luisguilherme.apireceitas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisguilherme.apireceitas.models.dto.UserDTO;
import com.luisguilherme.apireceitas.models.entities.User;
import com.luisguilherme.apireceitas.repositories.UserRepository;
import com.luisguilherme.apireceitas.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;	
	
	public UserDTO findById(String id) {		
		User user = getEntityById(id);
		return new UserDTO(user);
	}
	
	private User getEntityById(String id) {
		Optional<User> result = repository.findById(id);
		return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
	}
	
	public UserDTO insert(UserDTO dto) {
		User user = new User();
		copyDtoToEntity(dto, user);
		user = repository.insert(user);
		return new UserDTO(user);
	}
	
	public void delete(String id) {
		User user = getEntityById(id);		
		repository.deleteById(id);
	}
	
	public UserDTO update(String id, UserDTO dto) {
		User user = getEntityById(id);
		copyDtoToEntity(dto, user);
		user = repository.save(user);
		return new UserDTO(user);
	}
	
	private void copyDtoToEntity(UserDTO dto, User user) {
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
	}
}
