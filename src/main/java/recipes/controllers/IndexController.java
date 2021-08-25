package recipes.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import recipes.domain.Category;
import recipes.domain.UnitOfMeasure;
import recipes.repositories.CategoryRepository;
import recipes.repositories.RecipeRepository;
import recipes.repositories.UnitOfMeasureRepository;
import recipes.services.RecipeService;

@Controller
public class IndexController {
	
	private final RecipeService recipeService;
	
	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping({"", "/", "/index", "/index.html"})
	public String getIndexPage(Model model) {
		model.addAttribute("recipes", recipeService.getRecipes());
		
		return "index";
	}
	
	// old test version
//	private CategoryRepository categoryRepository;
//	private UnitOfMeasureRepository unitOfMeasureRepository;
//	
//	
//
//	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
//		super();
//		this.categoryRepository = categoryRepository;
//		this.unitOfMeasureRepository = unitOfMeasureRepository;
//	}
//
//
//
//	@RequestMapping({"", "/", "/index", "/index.html"})
//	public String getIndexPage() {
//		Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
//		Optional<UnitOfMeasure> unitOptional = unitOfMeasureRepository.findByDescription("Cup");
//		
//		System.out.println("CatId=" + categoryOptional.get().getId() + ":" + categoryRepository.findById(categoryOptional.get().getId()).get().getDescription());
//		System.out.println("UnitId=" + unitOptional.get().getId());
//		
//		return "index";
//	}
}
