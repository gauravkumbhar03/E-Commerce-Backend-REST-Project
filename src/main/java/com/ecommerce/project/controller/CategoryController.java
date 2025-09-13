package com.ecommerce.project.controller;

import com.ecommerce.project.config.AppConfig;
import com.ecommerce.project.config.AppConstants;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CategoryController {


    @Autowired
    private CategoryService categoryService;


    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories(
          @RequestParam (name ="pageNumber" ,defaultValue = AppConstants.PAGE_NUMBER ,required = false) Integer pageNumber,
          @RequestParam(name ="pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
          @RequestParam(name ="sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
          @RequestParam(name="sortOrder" ,defaultValue =AppConstants.ORDER_BY, required = false)String sortOrder
    ) {
        CategoryResponse categories = categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDTO category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId) {

       CategoryDTO deleteCategoryDTO = categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(deleteCategoryDTO,HttpStatus.OK);

    }

    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId,
                                                 @RequestBody CategoryDTO categoryDTO) {

     CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO, categoryId);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);


    }
}
