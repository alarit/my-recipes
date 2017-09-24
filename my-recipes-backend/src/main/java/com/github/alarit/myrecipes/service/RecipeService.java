package com.github.alarit.myrecipes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.github.alarit.myrecipes.model.Recipe;
import com.github.alarit.myrecipes.repository.RecipeRepository;

@Service
public class RecipeService {

	final Logger log = LoggerFactory.getLogger(getClass());
	
	private RecipeRepository recipeRepository;
	
	@Autowired
	public void setRecipeRepository(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	public Recipe findById(final String id) {
		return recipeRepository.findOneById(id);
	}
	
	public Iterable<Recipe> findAll() {
		return recipeRepository.findAll();
	}
	
	public Iterable<Recipe> find(final String word, final Long filter) {
		log.info("Word: " + word + " filter: " + filter);
		return (filter == 0L?findByName(word):findByDescription(word));
	}
	
	public Recipe save(final Recipe r) {
		Assert.notNull(r, "Recipe cannot be null");
		
		if(r.getId().isEmpty()) {
			r.setId(null);
		}
		
		return recipeRepository.save(r);
	}
	
	public void delete(final String id) {
		Assert.notNull(id, "Id cannot be null");
		Recipe r = findById(id);
		
		Assert.notNull(r, "Recipe cannot be null");
		recipeRepository.delete(r);
	}
	
	private Iterable<Recipe> findByName(final String name) {
		return recipeRepository.findByNameContainingIgnoreCase(name);
	}
	
	private Iterable<Recipe> findByDescription(final String description) {
		return recipeRepository.findByDescriptionContainingIgnoreCase(description);
	}
}
