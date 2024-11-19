package com.luisguilherme.apireceitas.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.luisguilherme.apireceitas.models.entities.Recipe;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

	@Query("{ $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'description': { $regex: ?0, $options: 'i' } } ] }")
	List<Recipe> findByText(String text);
	
}
