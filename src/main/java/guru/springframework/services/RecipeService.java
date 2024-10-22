package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import javassist.NotFoundException;

import java.util.Set;



public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l) throws NotFoundException;

   RecipeCommand findCommandById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long idToDelete);
}
