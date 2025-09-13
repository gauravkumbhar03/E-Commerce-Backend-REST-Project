package com.ecommerce.project.exception;

import com.ecommerce.project.payload.ApiResponse;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalException {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> myMethodArgumentsNotValid(MethodArgumentNotValidException e) {
        Map<String, String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(
                err ->
                {
                    String fieldName = ((FieldError) err).getField();
                    String message = err.getDefaultMessage();
                    response.put(fieldName, message);
                }
        );

        return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public  ResponseEntity<ApiResponse> myResourceNotFoundException(ResourceNotFoundException rnf){

        String message = rnf.getMessage();

        ApiResponse apiResponse = new ApiResponse(message,false);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiException.class)
    public  ResponseEntity<ApiResponse> ApiException(ApiException api){

        String message = api.getMessage();

        ApiResponse apiResponse = new ApiResponse(message,false);

        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

}
