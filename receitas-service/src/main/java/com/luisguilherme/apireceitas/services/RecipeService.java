package com.luisguilherme.apireceitas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luisguilherme.apireceitas.config.RabbitMQConfig;
import com.luisguilherme.apireceitas.models.dto.CommentDTO;
import com.luisguilherme.apireceitas.models.dto.EmailDTO;
import com.luisguilherme.apireceitas.models.dto.RecipeDTO;
import com.luisguilherme.apireceitas.models.embedded.Author;
import com.luisguilherme.apireceitas.models.embedded.Comment;
import com.luisguilherme.apireceitas.models.entities.Recipe;
import com.luisguilherme.apireceitas.models.entities.User;
import com.luisguilherme.apireceitas.repositories.RecipeRepository;
import com.luisguilherme.apireceitas.repositories.UserRepository;
import com.luisguilherme.apireceitas.services.exceptions.ResourceNotFoundException;

@Service
public class RecipeService {

	@Autowired
	RecipeRepository repository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RabbitTemplate rabbitTemplate;

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
		
		User user = userService.getMe();
		
		copyDtoToEntity(dto, recipe);
		recipe.setAuthor(new Author(user));

		recipe = repository.insert(recipe);
		
		user.getRecipes().add(recipe);
		
		userRepository.save(user);
		
		return new RecipeDTO(recipe);
	}
	
	public CommentDTO addComment(CommentDTO commentDto, String id) {
		
		Recipe recipe = getEntityById(id);
		
		Comment comment = new Comment();
		
		comment.setText(commentDto.getText());
		comment.setMoment(commentDto.getMoment());
		comment.setAuthor(new Author(userService.getMe()));
		
		recipe.getComments().add(comment);
		repository.save(recipe);		

		sendEmail(recipe, comment);

		return commentDto;
		
	}	
	
	private Recipe getEntityById(String id) {
		Optional<Recipe> result = repository.findById(id);
		return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
	}
	
	private void copyDtoToEntity(RecipeDTO dto, Recipe recipe) {
		recipe.setTitle(dto.getTitle());
		recipe.setDescription(dto.getDescription());
		recipe.setMoment(dto.getMoment());
		recipe.getIngredients().addAll(dto.getIngredients());
		recipe.getSteps().addAll(dto.getSteps());
	}
	
	private void sendEmail(Recipe recipe, Comment comment) {
		
		EmailDTO emailDTO = new EmailDTO();
		
		String to = userService.findById(recipe.getAuthor().getId()).getEmail();
		
		emailDTO.setTo(to);
		emailDTO.setSubject(comment.getAuthor().getName() + " comentou na sua receita " + recipe.getTitle());
		emailDTO.setBody("Olá " + recipe.getAuthor().getName() + ". " + emailDTO.getSubject() + "\n\n" + comment.getText());
		
		rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, emailDTO);
	}

}
