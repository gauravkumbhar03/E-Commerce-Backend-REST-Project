package com.ecommerce.project.exception;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiException extends RuntimeException {
    public ApiException(String s) {
    super(s);
    }
}
