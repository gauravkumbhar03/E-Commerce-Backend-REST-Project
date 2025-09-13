package com.ecommerce.project.payload;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponse {
    private List<ProductDTO> content;
    private  Integer pageNumber;
    private  Integer pageSize;
    private  Long totalElements;
    private  Integer totalPages;
    private  String sortOrder;
    private  String sortBy;
    private  boolean setLastPage;
}
