package com.luisguilherme.apireceitas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisguilherme.apireceitas.models.dto.RecipeDTO;
import com.luisguilherme.apireceitas.models.entities.Recipe;
import com.luisguilherme.apireceitas.repositories.RecipeRepository;
import com.luisguilherme.apireceitas.services.exceptions.ResourceNotFoundException;

@Service
public class RecipeService {

	@Autowired
	RecipeRepository repository;
	
	@Autowired
	UserService userService;

	public RecipeDTO findById(String id) {
		Recipe recipe = getEntityById(id);
		return new RecipeDTO(recipe);
	}
	
	public List<RecipeDTO> findAll() {
		List<Recipe> result = repository.findAll();
		return result.stream().map(x -> new RecipeDTO(x)).toList();
	}	
	
	public List<RecipeDTO> findByAuthor(String id) {
		return userService.getUserRecipes(id);
	}	
	
	public List<RecipeDTO> findByText(String text) {
		List<Recipe> result = repository.findByText(text);
		return result.stream().map(x -> new RecipeDTO(x)).toList();
	}	

	public RecipeDTO insert(RecipeDTO dto) {

		Recipe recipe = new Recipe();

		recipe.setTitle(dto.getTitle());
		recipe.setDescription(dto.getDescription());
		recipe.setMoment(dto.getMoment());
		recipe.setAuthor(dto.getAuthor());
		recipe.getIngredients().addAll(dto.getIngredients());
		recipe.getSteps().addAll(dto.getSteps());
		recipe.getComments().addAll(dto.getComments());

		recipe = repository.insert(recipe);
		return new RecipeDTO(recipe);
	}

	public void delete(String id) {
		getEntityById(id);
		repository.deleteById(id);
	}
	
	private Recipe getEntityById(String id) {
		Optional<Recipe> result = repository.findById(id);
		return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
	}

}
