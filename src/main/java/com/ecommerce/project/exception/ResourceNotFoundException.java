package com.ecommerce.project.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException  {
    String resourceName;
    String field;
    String fieldName;
    Long fieldId;


    public ResourceNotFoundException(String resourceName, String field, String fieldValue) {
        super(String.format("%s not found with %s: %s", resourceName, field, fieldValue));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String field, Long fieldId) {
        super(String.format("%s not found with %s: %d", resourceName, field, fieldId));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }

}
