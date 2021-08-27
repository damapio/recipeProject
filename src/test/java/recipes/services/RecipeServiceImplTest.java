package recipes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import recipes.domain.Recipe;
import recipes.repositories.RecipeRepository;

public class RecipeServiceImplTest {

	RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;
    
    private AutoCloseable closeable;
      

    @BeforeEach
    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this); // deprecado. Cojonudo
    	closeable = MockitoAnnotations.openMocks(this); //debería ser un @BeforeAll, pero no lo sé configurar
        recipeService = new RecipeServiceImpl(recipeRepository);
    }
    
    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }
    
    
    @Test
    public void getRecipes() throws Exception {

        Recipe recipe = new Recipe();
        HashSet<Recipe> receipesData = new HashSet<Recipe>();
        receipesData.add(recipe);

        when(recipeService.getRecipes()).thenReturn(receipesData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    
}
