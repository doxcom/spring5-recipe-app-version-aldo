package guru.springframework.domain;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

//Data is going include, getter, setter equals and hashcode on this class
@Data
@EqualsAndHashCode(exclude={"recipes"}) //to avoid bi-directional references
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories") //this will be on the join table
    private Set<Recipe> recipes;


}
