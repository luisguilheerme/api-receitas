package com.luisguilherme.apireceitas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.luisguilherme.apireceitas.models.entities.Recipe;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

}
