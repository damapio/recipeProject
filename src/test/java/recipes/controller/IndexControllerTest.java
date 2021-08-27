package recipes.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import recipes.controllers.IndexController;
import recipes.services.RecipeService;

@ExtendWith(MockitoExtension.class)
public class IndexControllerTest {

	@InjectMocks //clase que los usa
	private IndexController indexController;
	
	@Mock private RecipeService recipeService;
	@Mock private Model model;
	
    @BeforeEach
    public void setUp() throws Exception {
    	indexController = new IndexController(recipeService);
    }
    
    @Test
    public void getIndexPage() throws Exception {
    	final String index = "index";
    	String page = indexController.getIndexPage(model);
    	
    	assertEquals(index, page);
    	verify(recipeService, times(1)).getRecipes(); //comprobar el método se llama 1 vez y sólo una
    	verify(model, times(1)).addAttribute("recipes", recipeService.getRecipes()); 
    }
}
