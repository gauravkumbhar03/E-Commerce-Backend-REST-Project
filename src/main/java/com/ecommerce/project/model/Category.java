package com.ecommerce.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "Category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryName;

    @OneToMany(mappedBy = "productCategory" ,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Product>productList;

}
