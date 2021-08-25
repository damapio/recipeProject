package recipes.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import recipes.domain.Recipe;
import recipes.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	public Set<Recipe> getRecipes() {
		Set<Recipe> myRecipes = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(myRecipes::add);
		return myRecipes;
	}
}
