package recipes.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import recipes.controllers.IndexController;
import recipes.domain.Recipe;
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
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }
    
    @Test
    public void getIndexPage() throws Exception {
    	
    	//given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        Recipe recipe = new Recipe();
        recipe.setId(1L); // para que los dos objetos sean diferentes y no los pise
        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipes);

        @SuppressWarnings("unchecked")
		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
    	final String index = "index";
    	String viewName = indexController.getIndexPage(model);
    	
    	//then
    	assertEquals(index, viewName);
    	verify(recipeService, times(1)).getRecipes(); //comprobar el método se llama 1 vez y sólo una
    	verify(model, times(1)).addAttribute(ArgumentMatchers.eq("recipes"), argumentCaptor.capture());
        Set<Recipe> recipesSetInController = argumentCaptor.getValue();
        assertEquals(2, recipesSetInController.size()); 
    }
}
