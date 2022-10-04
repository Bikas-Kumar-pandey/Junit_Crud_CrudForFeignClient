package com.test.repository;

import com.test.model.Category;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@DataJpaTest   //This will test only both entity and Repository Layer
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class CategoryRepoTest {
    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveCategoryTest() {
        Category category = new Category();
        category.setCategoryName("Phones");
        category.setDescription("All Phones are in this category");
        categoryRepo.save(category);
        Assertions.assertThat(category.getId()).isEqualTo(1);
    }

//    @Test
//    @Order(2)
//    public void GetCategoryByIdTest() {
//        Optional<Category> byId = categoryRepo.findById(1);
//        Category category = byId.get();
//        System.out.println(category.getId());
//        Assertions.assertThat(category.getId()).isEqualTo(1);
//    }

    @Test
    @Order(3)
    public void GetCategoryTest() {
        List<Category> all = categoryRepo.findAll();
        Assertions.assertThat(all.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateCategoryTest() {
        Category category = categoryRepo.findById(1).get();
        category.setCategoryName("Mobiles");
        Category save = categoryRepo.save(category);
        Assertions.assertThat(save.getCategoryName()).isEqualTo("Mobiles");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteById() {
//        Category category = new Category();
//        categoryRepo.deleteById(1);
        Category category = categoryRepo.findById(1).get();
        System.out.println(category);
        categoryRepo.delete(category);

        Assertions.assertThat(category).isNull();

//        Category category1= null;
//        Optional<Category> mobiles = categoryRepo.findByCategory("Mobiles");
//        if(mobiles.isPresent()){
//            category1=mobiles.get();
//        }
//        Assertions.assertThat(category1).isNull();
    }

}
