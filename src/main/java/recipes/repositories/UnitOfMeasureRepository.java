package recipes.repositories;

import org.springframework.data.repository.CrudRepository;

import recipes.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

}
