package com.example.BlogApplicationBAckend.Exception;

import com.example.BlogApplicationBAckend.DTO.ApiResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(RescourceNotFoundException.class)
    public ResponseEntity<ApiResponce> rescourceNotFoundExceptioHandler(RescourceNotFoundException rescourceNotFoundException){
        String message = rescourceNotFoundException.getMessage();
        ApiResponce apiResponce=new ApiResponce(message);
       return new ResponseEntity<ApiResponce>(apiResponce, HttpStatus.NOT_FOUND);
    }
}
