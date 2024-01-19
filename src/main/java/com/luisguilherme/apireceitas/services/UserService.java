package com.luisguilherme.apireceitas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.luisguilherme.apireceitas.config.util.CustomUserUtil;
import com.luisguilherme.apireceitas.models.dto.RecipeDTO;
import com.luisguilherme.apireceitas.models.dto.UserDTO;
import com.luisguilherme.apireceitas.models.embedded.Author;
import com.luisguilherme.apireceitas.models.entities.User;
import com.luisguilherme.apireceitas.repositories.UserRepository;
import com.luisguilherme.apireceitas.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService  {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	CustomUserUtil customUserUtil;
	
	public UserDTO findById(String id) {		
		User user = getEntityById(id);
		return new UserDTO(user);
	}
	
	public List<RecipeDTO> getUserRecipes(String id) {
		User user = getEntityById(id);
		return user.getRecipes().stream().map(x -> new RecipeDTO(x)).toList();
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
	
	private User getEntityById(String id) {
		Optional<User> result = repository.findById(id);
		return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if(user == null) {
			throw new UsernameNotFoundException("Username Not Found");
		}	
		for(GrantedAuthority role : user.getAuthorities()) {
			System.out.println(role);
		}
		
		return user;
	}
	
	protected User authenticated() {
		try {			
			String username = customUserUtil.getLoggedUsername();
			return repository.findByEmail(username);			
		}
		catch (Exception e) {
			throw new UsernameNotFoundException("Email not found");
		}
		
	}
	
	public User getMe() {
		User user = authenticated();
		return user;
	}
}
