package recipes.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import recipes.domain.Category;
import recipes.domain.UnitOfMeasure;
import recipes.repositories.CategoryRepository;
import recipes.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {
	
	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;
	
	

	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}



	@RequestMapping({"", "/", "/index", "/index.html"})
	public String getIndexPage() {
		Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
		Optional<UnitOfMeasure> unitOptional = unitOfMeasureRepository.findByDescription("Cup");
		
		System.out.println("CatId=" + categoryOptional.get().getId() + ":" + categoryRepository.findById(categoryOptional.get().getId()).get().getDescription());
		System.out.println("UnitId=" + unitOptional.get().getId());
		
		return "index";
	}
}
