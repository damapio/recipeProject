package recipes.services;

import java.util.Set;

import recipes.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();
}
