package com.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.model.Category;
import com.test.service.CategoryService;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.bson.BSONObject;
import org.bson.BasicBSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(TestController.class)
public class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    Category category;
    Category categorys;
    List<Category> categoryList = new ArrayList<>();

    @MockBean
    private CategoryService categoryService;

    public static String getJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("unable to convert json string");
        }
    }

    @BeforeEach
    public void setup() {
        category = new Category(1, "Generator", "Red in colour and run on diesel");
        category = new Category(2, "Fan ", "Bajaj Fan ");
        categoryList.add(category);

        categorys = new Category(2, "Fans ", "Bajajs Fans ");

    }

    @Test
    public void getDataTest() throws Exception {

        when(categoryService.getData()).thenReturn(categoryList);
        mockMvc.perform(
                        get("/test").
                                content(getJsonString(categoryList)).
                                contentType(MediaType.APPLICATION_JSON).
                                accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk()).
                andExpect(content().json(getJsonString(categoryList)));
    }

    @Test
    void getById() throws Exception {
        when(categoryService.getById(anyInt())).thenReturn(category);
        mockMvc.perform(get("/test/" + anyInt()).
                        content(getJsonString(categoryList)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(content().json(getJsonString(category)));
    }

//    @Test
//    void setData() throws Exception {
//        when(categoryService.setData(category)).thenReturn(category);
//        mockMvc.perform(post("/test").content(getJsonString(category)).
//                        contentType(MediaType.APPLICATION_JSON).
//                        accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).
//                andExpect(content().json(getJsonString(category)));
//    }

    @Test
    public void deleteById() throws Exception {
        String response = "Deleted";
        when(categoryService.deleteDataById(anyInt())).thenReturn(response);
        mockMvc.perform(delete("/test/" + anyInt()
                )).andExpect(status().isOk())
                .andExpect(content().string("Deleted"));
    }

    @Test
    public void updateData() throws Exception {
        when(categoryService.updateData(category)).thenReturn(categorys);
        mockMvc.perform(put("/test").
                        content(getJsonString(category)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(content().
                        json(getJsonString(categorys)));
    }

    @Test
    public void updateOnlyCategory() throws Exception {
        when(categoryService.updateOnlyCategory(category)).thenReturn(categorys);
        mockMvc.perform(patch("/test").
                        content(getJsonString(category)).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect(content().
                        json(getJsonString(categorys)));
    }

}

