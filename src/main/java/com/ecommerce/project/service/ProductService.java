package com.ecommerce.project.service;

import com.ecommerce.project.model.Product;

import java.util.List;

public interface ProductService {
    public String createProduct(Product product);
    public String deleteProduct(Long id);
    public String updateProduct(Long id,Product product);
    public List<Product> getAllProducts();

}
