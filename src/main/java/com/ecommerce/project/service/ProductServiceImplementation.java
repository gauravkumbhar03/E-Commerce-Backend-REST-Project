    package com.ecommerce.project.service;

    import com.ecommerce.project.model.Product;
    import com.ecommerce.project.repositories.ProductRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Service;
    import org.springframework.web.server.ResponseStatusException;

    import java.util.List;
    @Service
    public class ProductServiceImplementation implements ProductService{
       @Autowired
       private ProductRepository productRepository;


        @Override
        public String createProduct(Product product) {
            productRepository.save(product);
            return "Product created Successfully";
        }

        @Override
        public String deleteProduct(Long id) {
           Product product = productRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
            productRepository.delete(product);
            return "Product Deleted Successfully";
        }

        @Override
        public String updateProduct(Long id, Product product) {
            Product newProduct = productRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
            newProduct.setProductCategory(product.getProductCategory());
            newProduct.setProductName(product.getProductName());
            newProduct.setProductPrice(product.getProductPrice());
            newProduct.setProductCategory(product.getProductCategory());
            productRepository.save(newProduct);

            return "Product Updated Successfully";
        }

        @Override
        public List<Product> getAllProducts() {
            return productRepository.findAll();
        }
    }
