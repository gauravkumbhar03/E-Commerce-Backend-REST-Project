package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull(message = "Product name should not be null")
    @Size(min = 3 ,max = 25 ,message = "Name must be between 3 and 20 characters")
    private String productName;


    @NotNull(message = "Product description should not be null")
    private String productDescription;

    private  String image;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Please Enter Price")
    @Min(value = 100, message = "Price should not less than 100")
    private double productPrice;

    @Positive
    private double discount;

    private double specialPrice;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
