package com.ecommerce.project.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDTO {

    private Long productId;

    private String productName;

    private String productDescription;

    private  String image;

    private  Integer quantity;

    private  Double productPrice;

    private double discount;

    private double specialPrice;
}
