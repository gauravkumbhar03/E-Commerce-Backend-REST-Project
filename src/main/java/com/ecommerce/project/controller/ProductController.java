package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.model.User;
import com.ecommerce.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping("/api/public/product")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return new ResponseEntity<>("Product Created Successfully", HttpStatus.OK);
    }

    @PutMapping("/api/admin/products/{id}")
    public ResponseEntity<String> updateProduct(@RequestBody Product product,@PathVariable Long id){
        productService.updateProduct(id,product);
        return new ResponseEntity<>("Product Updated Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/api/admin/products/{id}")
    public  ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return  new ResponseEntity<>("Product Deleted Successfullyy" , HttpStatus.OK);
    }

    @GetMapping("/api/public/products")
    public ResponseEntity<List<Product>> getAllProducts()
    {
        List<Product>productsList = productService.getAllProducts();
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }




}
