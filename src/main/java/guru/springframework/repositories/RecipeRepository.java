package guru.springframework.repositories;

import guru.springframework.domain.Recipe;
import org.springframework.data.repository.CrudRepository;
                                                         //entity type, id type of the entity
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
