package recipes.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import recipes.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	//wrapper sobre un objeto que puede ser null
	Optional<Category> findByDescription(String description);

}
