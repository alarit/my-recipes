package com.github.alarit.myrecipes.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.github.alarit.myrecipes.model.Recipe;

@Repository
public interface RecipeRepository extends PagingAndSortingRepository<Recipe, Long>  {

	public Recipe findOneById(String id);
	
	public Iterable<Recipe> findByNameContainingIgnoreCase(String name);
	
	public Iterable<Recipe> findByDescriptionContainingIgnoreCase(String description);
}
