package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void SetUp(){
        //initialize this property
        category = new Category();//new object
    }

    @Test
    public void getId() {
        Long idValue = 4L;
        category.setId(4L);
        assertEquals(idValue,category.getId());
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getRecipes() {
    }
}