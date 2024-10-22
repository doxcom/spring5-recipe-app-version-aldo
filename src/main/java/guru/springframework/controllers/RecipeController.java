package guru.springframework.controllers;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.build.BuildLogger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class RecipeController {


    private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model) throws NotFoundException {

        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        return "recipe/show";

    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";

    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute ('recipe') RecipeCommand command, BindingResult bindingResult){

    if(bindingResult.hasErrors()){

        bindingResult.getAllErrors().forEach(objectError ->{
            BuildLogger.Adapter log = null;
            log.debug(objectError.toString());
        });
        return RECIPE_RECIPEFORM_URL;
    }

       RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId()+"/show";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id){

        recipeService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

       // log.error("Handling not found exception");
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

    /*  Deleted to relocate in a main controller advice: ControllerExceptionHandler.java
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception){

        // log.error("Handling not found exception");
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("400error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

     */


}
