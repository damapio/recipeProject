package recipes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import recipes.domain.Recipe;
import recipes.repositories.RecipeRepository;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceImplTest {

	@InjectMocks //clase que los usa
	RecipeServiceImpl recipeService;

    @Mock //clase a fingir
    RecipeRepository recipeRepository;
    
    @BeforeEach
    public void setUp() throws Exception {
        recipeService = new RecipeServiceImpl(recipeRepository);
    }
    
    @Test
    public void getEmptyRecipes() throws Exception {
        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 0);
    }
    
    @Test
    public void getRecipes() throws Exception {

        Recipe recipe = new Recipe();
        HashSet<Recipe> receipesData = new HashSet<Recipe>();
        receipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(receipesData); //devolver los datos falsos al llamar al mock

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll(); //comprobar el método se llama 1 vez y sólo una
    }
    
}
