package recipes.repositories;

import org.springframework.data.repository.CrudRepository;

import recipes.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
