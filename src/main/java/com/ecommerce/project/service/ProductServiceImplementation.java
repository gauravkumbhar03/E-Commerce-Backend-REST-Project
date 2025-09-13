package com.ecommerce.project.service;


import com.ecommerce.project.exception.ApiException;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.model.Product;
import com.ecommerce.project.payload.ProductDTO;
import com.ecommerce.project.payload.ProductResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import com.ecommerce.project.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;


@Service
public class ProductServiceImplementation implements ProductService {
 @Autowired
 private ProductRepository productRepository;

 @Autowired
 private CategoryRepository categoryRepository;

 @Autowired
 private ModelMapper modelMapper;

 @Autowired
 private FileService fileService;

 @Value("${project.image}")
private String path;

    @Override
    public ProductDTO addProduct(Long categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));

        product.setCategory(category);
        product.setImage("default.png");
        double specialPrice =product.getPrice() -( (product.getDiscount()*0.01) * product.getPrice()) ;
        Product savedImage  = productRepository.save(product);
            product.setSpecialPrice(specialPrice);
        return modelMapper.map(savedImage, ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts() {
        List<Product> allProducts = productRepository.findAll();

        if(allProducts.isEmpty()){
            throw new ApiException("NO PRODUCTS PRESENT");
        }
        List<ProductDTO> productDTOS = allProducts.stream().
                map(product -> modelMapper.map(product, ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);

        return productResponse;

    }

    @Override
    public ProductResponse searchProductsByCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));

        List<Product>products = productRepository.findByCategoryOrderByPriceAsc(category);

        List<ProductDTO>productDTOList = products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOList);

        return productResponse;
    }

    @Override
    public ProductResponse searchProductsByKeyword(String keyword) {
        List<Product>productsList = productRepository.findByProductNameLikeIgnoreCase("%"+keyword+"%");
            List<ProductDTO>productDTOList = productsList.stream().map(product->modelMapper.map(product,ProductDTO.class)).toList();

            ProductResponse productResponse = new ProductResponse();
            productResponse.setContent(productDTOList);
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(Long productId, Product product) {
        Product productFromDB = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFoundException()
        );

            productFromDB.setProductName(product.getProductName());
            productFromDB.setDescription(product.getDescription());
            productFromDB.setQuantity(product.getQuantity());
            productFromDB.setDiscount(product.getDiscount());
            productFromDB.setPrice(product.getPrice());
            productFromDB.setSpecialPrice(product.getSpecialPrice());
            Product savedProduct =  productRepository.save(productFromDB);

            return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Product productFromDb = productRepository.findById(productId).orElseThrow(
                ()-> new ResourceNotFoundException());

        productRepository.delete(productFromDb);
        return modelMapper.map(productFromDb,ProductDTO.class);
    }

    @Override
    public ProductDTO updateImage(Long productId, MultipartFile image) throws IOException {
        Product productFromDb = productRepository.findById(productId).orElseThrow(
                ()->new ResourceNotFoundException()
        );
        // String path ="images/";       ;
        String fileName = fileService.uploadImage(path,image);

        productFromDb.setImage(fileName);
        return modelMapper.map(productFromDb,ProductDTO.class);
    }



}
