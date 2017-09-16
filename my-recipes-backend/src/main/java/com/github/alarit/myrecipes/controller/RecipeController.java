package com.github.alarit.myrecipes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.alarit.myrecipes.model.Recipe;
import com.github.alarit.myrecipes.service.RecipeService;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

	final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RecipeService recipeService;
	
	@PostMapping
	public void save(@RequestBody Recipe recipe) {
		log.info("Saving: " + recipe.getName());
		recipeService.save(recipe);
	}
	
	@GetMapping(value = "/{id}")
	public Recipe findById(@PathVariable final String id) {
		return recipeService.findById(id);
	}
	
	@GetMapping(value = "")
	public Iterable<Recipe> findAll() {
		return recipeService.findAll();
	}
	
	@GetMapping(value = "/autocomplete")
	public Iterable<Recipe> findByNameLike(@RequestParam final String word, @RequestParam final Long filter){
		return recipeService.find(word,filter);
	}
	
	@PutMapping(value = "/{recipe}")
	public Recipe update(final Recipe recipe) {
		recipeService.save(recipe);
		return recipe;
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable final String id) {
		recipeService.delete(id);
	}
}