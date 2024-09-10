package guru.springframework.domain;

import javax.persistence.*;
import java.util.Set;

//Data is going include, getter, setter equals and hashcode on this class
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories") //this will be on the join table
    private Set<Recipe> recipes;

    public Category() {
    }

    public Long getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public Set<Recipe> getRecipes() {
        return this.recipes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
}
