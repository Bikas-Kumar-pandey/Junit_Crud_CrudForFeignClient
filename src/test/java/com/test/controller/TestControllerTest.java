package com.test.controller;

import com.test.model.Category;
import com.test.service.CategoryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TestControllerTest {

    @InjectMocks //Tell that we gonna test this component
    private TestController testController;

    @Mock //Wanted to mock this component
    private CategoryService categoryService;


    @Test
    public void getData() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Phones", "all Redmi"));
        categoryList.add(new Category(1, "Laptops", "all Laptops"));
        when(categoryService.getData()).thenReturn(categoryList);

        List<Category> categoryList2 = testController.getData();
        assertEquals(2, categoryList2.size());
        verify(categoryService, times(1)).getData();
    }


//    @Test
//    void setData() {
//        Category category = new Category(1, "Phones", "All Redmi");
//        Category category1 = categoryService.setData(category);
//        when(categoryService.setData(category)).thenReturn(category1);
//        Assertions.assertThat(category.getId()).isEqualTo(1);
//        Assertions.assertThat(category.getCategoryName()).isEqualTo("Phones");
//        Assertions.assertThat(category.getDescription()).isEqualTo("All Redmi");
//        ;
//    }

}