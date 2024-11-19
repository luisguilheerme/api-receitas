package com.luisguilherme.apireceitas.models.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.luisguilherme.apireceitas.models.embedded.Author;
import com.luisguilherme.apireceitas.models.embedded.Comment;
import com.luisguilherme.apireceitas.models.entities.Recipe;

public class RecipeDTO {

	private String id;
	private String title;
	private String description;
	private Instant moment;
	
	private Author author;

	private List<String> ingredients = new ArrayList<>();
	private List<String> steps = new ArrayList<>();
	private List<Comment> comments = new ArrayList<>();

	public RecipeDTO() {

	}

	public RecipeDTO(String id, String title, String description, Instant moment, Author author,
			List<String> ingredients, List<String> steps, List<Comment> comments) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.moment = moment;
		this.author = author;
		this.ingredients = ingredients;
		this.steps = steps;
		this.comments = comments;
	}

	public RecipeDTO(Recipe entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.description = entity.getDescription();
		this.moment = entity.getMoment();
		this.author = entity.getAuthor();
		this.ingredients.addAll(entity.getIngredients());
		this.steps.addAll(entity.getSteps());
		this.comments.addAll(entity.getComments());

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public List<String> getSteps() {
		return steps;
	}

	public List<Comment> getComments() {
		return comments;
	}
}
