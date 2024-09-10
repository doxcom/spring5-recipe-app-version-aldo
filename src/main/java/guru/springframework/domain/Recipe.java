package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

//RECIPE is going to be Primary Object

@Data
@Entity
public class Recipe {
//Recipe and ingredient is a bi-directional
// A recipe is gonna have many ingredients, but ingredients will have just one Recipe

    //own id value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions; //modified to accept more characters


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe") //relationship from recipe to ingredient
    private Set<Ingredient> ingredients = new HashSet<>(); //Set is an unordered collection of objects in which duplicate values cannot be stored


    @Lob //to store image or big objects into database
    private Byte[] image;

    @Enumerated(EnumType.STRING)//we use type string will overwrite behaviour of the enum
    private Difficulty difficulty; //will going to store the enum value into database


    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes; //one to one property

    @ManyToMany
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Recipe() {
    }


    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

}
