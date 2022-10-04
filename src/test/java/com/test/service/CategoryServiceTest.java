package com.test.service;

import com.test.model.Category;
import com.test.repository.CategoryRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class CategoryServiceTest {

    Category category;

    Category categorys;
    @InjectMocks
    private CategoryService categoryService;
    @Mock
    private CategoryRepo categoryRepo;


//    @BeforeEach
//    public void setup() {
//        categoryService = new CategoryService(categoryRepo);
//        category = new Category(1, "Generator", "Red in colour and run on diesel");
//        category = new Category(2, "Fan ", "Bajaj Fan ");
//        categoryList.add(category);
//        categorys = new Category(2, "Fans ", "Bajajs Fans ");
//
//    }

    @Test
    void getData() {
        List<Category> categoryList = new ArrayList<>();
        category = new Category(1, "Generator", "Red in colour and run on diesel");
        category = new Category(2, "Fan ", "Bajaj Fan ");
        categoryList.add(category);
        when(categoryRepo.findAll()).thenReturn(categoryList);

    }

//    @Test
//    void setData() {
//        category = new Category(2, "Fan ", "Bajaj Fan ");
//        when(categoryRepo.save(any(Category.class))).thenReturn(category);
//        Category category1 = categoryService.setData(category);
//        Assertions.assertThat(category).isEqualTo(category1);
//    }

    @Test
    void getById() {
        category = new Category(2, "Fan ", "Bajaj Fan ");
        when(categoryRepo.findById(anyInt())).thenReturn(Optional.ofNullable(category));
        Category byId = categoryService.getById(anyInt());
        Assertions.assertThat(byId).isEqualTo(category);

    }

    @Test
    void deleteById() throws Exception {

        String deletecdMsg = "Deleted";
        when(categoryRepo.existsById(anyInt())).thenReturn(true);
        String s = categoryService.deleteDataById(anyInt());
        doNothing().when(categoryRepo).deleteById(anyInt());
        assertEquals(s, deletecdMsg);
    }

    @Test
    void updateData() throws Exception {
        category = new Category(2, "Fan ", "Bajaj Fan ");
        categorys = new Category(2, "Fans ", "Bajajs Fans ");
        when(categoryRepo.findById(anyInt())).thenReturn(Optional.ofNullable(category));
        Category category1 = categoryService.updateData(categorys);
        Assertions.assertThat(category1).isEqualTo(category);
    }

    @Test
    void updateOnlyCategory() throws Exception {
        category = new Category(2, "Fan ", "Bajaj Fan ");
        categorys = new Category(2, "Fans ", "Bajajs Fans ");
        when(categoryRepo.save(any(Category.class))).thenReturn(category);
        when(categoryRepo.existsById(anyInt())).thenReturn(true);
        Category category1 = categoryService.updateOnlyCategory(categorys);
        Assertions.assertThat(category1.getCategoryName()).isEqualTo(category.getCategoryName());
    }
}