package com.ecommerce.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private String productName;
    private Long productPrice;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonBackReference
    private Category productCategory;



}
