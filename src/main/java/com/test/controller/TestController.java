package com.test.controller;


import com.test.dto.CategoryRequest;
import com.test.model.Category;
import com.test.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    public List<Category> getData() {
        System.out.println("This is controller  layer");
        return categoryService.getData();//i will mock this one (dummy data provide)
    }

    @PostMapping("/save")
    public Category setData(@RequestBody CategoryRequest categoryRequest) {
        System.out.println("this set Data is from Controller");
        return categoryService.setData(categoryRequest);
    }

    @GetMapping("{id}")
    public Category getById(@PathVariable int id) {
        return categoryService.getById(id);
    }

    @DeleteMapping("{id}")
    public String deleteDataById(@PathVariable int id) throws Exception {
        return categoryService.deleteDataById(id);
    }

    @PutMapping
    public Category updateData(@RequestBody Category category) throws Exception {
        return categoryService.updateData(category);
    }

    @PatchMapping
    public Category updateOnlyCategory(@RequestBody Category category) throws Exception {
       return categoryService.updateOnlyCategory(category);
    }
    @PostMapping("/{email}")
    public boolean emailValidation(@PathVariable String email){
       return categoryService.emailValidation(email);
    }
}
