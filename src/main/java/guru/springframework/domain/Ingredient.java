package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {
//Recipe and ingredient is a bi-directional
// A recipe is gonna have many ingredients, but ingredients will have just one Recipe
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure uom;

    @ManyToOne    //cascade here is not used bc if we deleted an ingredient we dont want to cascade that and delete the recipe
    private Recipe recipe;//it belongs to a recipe at least

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount=amount;
        this.uom=uom;
    }


    public Ingredient() {

    }
}
