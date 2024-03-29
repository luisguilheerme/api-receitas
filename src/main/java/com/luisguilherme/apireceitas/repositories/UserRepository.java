package com.luisguilherme.apireceitas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.luisguilherme.apireceitas.models.entities.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	User findByEmail(String username);

}
