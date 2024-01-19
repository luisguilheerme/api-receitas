package com.luisguilherme.apireceitas.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.luisguilherme.apireceitas.models.embedded.Author;
import com.luisguilherme.apireceitas.models.embedded.Comment;
import com.luisguilherme.apireceitas.models.entities.Recipe;
import com.luisguilherme.apireceitas.models.entities.User;
import com.luisguilherme.apireceitas.repositories.RecipeRepository;
import com.luisguilherme.apireceitas.repositories.UserRepository;

import jakarta.annotation.PostConstruct;

@Configuration
@Profile("test")
public class TestConfig {
/*
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RecipeRepository recipeRepository;

	@PostConstruct
	public void init() {

		userRepository.deleteAll();
		recipeRepository.deleteAll();
		
		User maria = new User(null, "Maria dos Santos", "maria@gmail.com");
		User alex = new User(null, "Alex da Silva", "alex@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex));			
       
        Recipe lasagna = new Recipe(null, "Lasanha à Bolonhesa", "Uma deliciosa lasanha com carne moída e molho bechamel.", Instant.parse("2023-02-14T12:34:26Z"), new Author(maria));
        lasagna.getIngredients().add("500g de carne moída");
        lasagna.getIngredients().add("1 cebola picada");
        lasagna.getIngredients().add("2 dentes de alho picados");
        lasagna.getIngredients().add("1 lata de molho de tomate");
        lasagna.getIngredients().add("250g de massa de lasanha");
        lasagna.getIngredients().add("200g de queijo muçarela");
        lasagna.getIngredients().add("50g de queijo parmesão ralado");
        lasagna.getSteps().add("Refogue a carne, cebola e alho até dourar.");
        lasagna.getSteps().add("Adicione o molho de tomate e deixe cozinhar por 10 minutos.");
        lasagna.getSteps().add("Intercale camadas de massa, molho e queijo em um refratário.");
        lasagna.getSteps().add("Asse no forno a 180°C por 30 minutos."); 
        
        Recipe chickenCurry = new Recipe(null, "Frango ao Curry", "Um prato indiano clássico com frango, curry e leite de coco.", Instant.parse("2023-02-14T12:45:00Z"), new Author(maria));
        chickenCurry.getIngredients().add("500g de peito de frango cortado em cubos");
        chickenCurry.getIngredients().add("2 colheres de sopa de curry em pó");
        chickenCurry.getIngredients().add("1 cebola picada");
        chickenCurry.getIngredients().add("2 colheres de sopa de óleo de coco");
        chickenCurry.getIngredients().add("400ml de leite de coco");
        chickenCurry.getIngredients().add("Sal e pimenta a gosto");
        chickenCurry.getSteps().add("Tempere o frango com curry, sal e pimenta.");
        chickenCurry.getSteps().add("Em uma panela, aqueça o óleo de coco e refogue a cebola.");
        chickenCurry.getSteps().add("Adicione o frango e cozinhe até dourar.");
        chickenCurry.getSteps().add("Despeje o leite de coco e deixe cozinhar por 20 minutos.");
        
        Comment comment1 = new Comment("Receita maravilhosa!", Instant.parse("2021-03-14T20:33:11Z"), new Author(alex));
		
        lasagna.getComments().add(comment1);
        
        recipeRepository.saveAll(Arrays.asList(lasagna, chickenCurry));
		
		maria.getRecipes().addAll(Arrays.asList(lasagna, chickenCurry));
		
		userRepository.save(maria);

	}
*/
}
