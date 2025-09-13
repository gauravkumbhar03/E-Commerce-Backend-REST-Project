package com.ecommerce.project.service;

import com.ecommerce.project.exception.ApiException;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumb ,Integer pageSize,String sortBy,String sortOrder) {

        Sort sortByAndOrderBy = sortOrder.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumb,pageSize,sortByAndOrderBy);

        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);

        List<Category> categories = categoryPage.getContent();

        if (categories.isEmpty()) {
            throw new ApiException("No category Create till now");
        }

        List<CategoryDTO> categoryDTOS = categories.stream().map(
                category -> modelMapper.map(category, CategoryDTO.class)
        ).toList();

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        categoryResponse.setPageSize(categoryPage.getSize());
        categoryResponse.setPageNumber(categoryPage.getNumber());
        categoryResponse.setTotalElements(categoryPage.getTotalElements());
        categoryResponse.setTotalPages(categoryPage.getTotalPages());
        categoryResponse.setSetLastPage(categoryPage.isLast());
 //       categoryResponse.setSortBy(categoryPage.get;
        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category1 = modelMapper.map(categoryDTO, Category.class);

        Category savedCategoryFromDB = categoryRepository.findByCategoryName(category1.getCategoryName());

        if (savedCategoryFromDB != null) {
            throw new ApiException("Category with name " + savedCategoryFromDB.getCategoryName() + " already exists");
        }


        Category savedCategory = categoryRepository.save(category1);

        CategoryDTO categoryDTO1 = modelMapper.map(savedCategory, CategoryDTO.class);
        return categoryDTO1;
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));
          categoryRepository.delete(category);

        return modelMapper.map(category,CategoryDTO.class);
    }

    @Override
        public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryid) {

            Category savedCategoryFromDD = categoryRepository.findById(categoryid)
                    .orElseThrow(() -> new ResourceNotFoundException("Category","CategoryId",categoryid)); ;

            Category savedCategory = modelMapper.map(categoryDTO,Category.class);

            savedCategory.setCategoryName(categoryDTO.getCategoryName());
            savedCategory.setCategoryId(categoryid);
            Category updatedCategory = categoryRepository.save(savedCategory);

           return modelMapper.map(updatedCategory, CategoryDTO.class);

        }


    }
