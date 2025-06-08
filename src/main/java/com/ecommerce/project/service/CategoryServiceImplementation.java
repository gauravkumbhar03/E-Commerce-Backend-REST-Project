package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

//    private Long nextId = 1L;

    //private List<Category> categories = new ArrayList<>();

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
     //   category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {

Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Not Found"));
categoryRepository.delete(category);

//        List<Category>categories = categoryRepository.findAll();
//
//
//        Category category = categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT FOUND"));
//        if (category == null) {
//            return "Category With With Category id :    " + categoryId + " do not exist";
//        }
//
//        categoryRepository.delete(category);
//
//
      return "Category With Category id : " + categoryId + " is removed";
    }

    @Override
    public Category updateCategory(Category category, Long categoryid) {

        Category savedCategory = categoryRepository.findById(categoryid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"Not Found") );

       // savedCategory.setCategoryName(category.getCategoryName());
        category.setCategoryId(categoryid);
        category.setCategoryName(category.getCategoryName());
        savedCategory = categoryRepository.save(category);
        return savedCategory;

//        List<Category>categories = categoryRepository.findAll();
//        Optional<Category> optionalCategory = categories.stream().filter(c -> c.getCategoryId().equals(categoryid)).findFirst();
//        if (optionalCategory.isPresent()) {
//
//            Category existingCategory = optionalCategory.get();
//
//            existingCategory.setCategoryName(category.getCategoryName());
//            Category updatedCategory = categoryRepository.save(existingCategory);
//            return existingCategory;
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found");
//        }

    }
}
