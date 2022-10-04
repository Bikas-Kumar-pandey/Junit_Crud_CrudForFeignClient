package com.test.service;

import com.test.dto.CategoryRequest;
import com.test.exception.ResourceNotFound;
import com.test.model.Category;
import com.test.repository.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }


    public List<Category> getData() {
        System.out.println("This is service layer");
        return categoryRepo.findAll();

    }

    public Category setData(CategoryRequest categoryRequest) {
        System.out.println("This set is from service");
        Category input = new Category();
        input.setCategoryName(categoryRequest.getCategoryName());
        input.setDescription(categoryRequest.getDescription());
        return categoryRepo.save(input);
    }

    public Category getById(int id) {
        Category category = categoryRepo.findById(id).get();
        return category;
    }

    public String deleteDataById(int id) throws Exception {
        if (!categoryRepo.existsById(id)) {
            throw new Exception("No category by given id " + id + " Exists");
        }
        categoryRepo.deleteById(id);
        return "Deleted";

    }


    public Category updateData(Category category) throws Exception {
        Optional<Category> byId = categoryRepo.findById(category.getId());
        if (byId.isEmpty()) {
            throw new ResourceNotFound("No category by given id " + category.getId());
        }
        Category categories = byId.get();
        categories.setCategoryName("Software");
        categories.setDescription("changed from hardware to software");
        categoryRepo.save(categories);
        return categories;

    }

    public Category updateOnlyCategory(Category category) throws Exception {
        if (!categoryRepo.existsById(category.getId())) {
            throw new Exception("NO category by given id :" + category.getId());
        }
        category.setCategoryName("Software");
        return categoryRepo.save(category);
    }

    public boolean emailValidation(String email) {

//        String emailRegex = "^\\\\w+([\\\\.-]?\\\\w+)*@\\\\w+([\\\\.-]?\\\\w+)*(\\\\.\\\\w{2,3})+$";
        String emailRegex=  "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher matcher = emailPattern.matcher(email);
        System.out.println(matcher.matches());
        return matcher.matches();

    }
}
