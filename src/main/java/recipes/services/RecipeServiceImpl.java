package recipes.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import recipes.domain.Recipe;
import recipes.repositories.RecipeRepository;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;
	
	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	public Set<Recipe> getRecipes() {
		log.debug("Inside the service"); // log importado de Lombok con SLF4J
		Set<Recipe> myRecipes = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(myRecipes::add);
		return myRecipes;
	}
}
