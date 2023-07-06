package com.minibackend.minibackend.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.minibackend.minibackend.error.exception.ProductNotFoundException;
import com.minibackend.minibackend.error.exception.ProductOutOfStockException;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductErrorResponse> handleProducNotFoundException(ProductNotFoundException exception) {
        ProductErrorResponse productErrorResponse = ProductErrorResponse.builder()
                .msg(exception.getMessage())
                .timeStamp(System.currentTimeMillis())
                .status(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<>(productErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductOutOfStockException.class)
    public ResponseEntity<ProductErrorResponse> handleProducNotInStoackException(ProductOutOfStockException exception) {
        ProductErrorResponse productErrorResponse = ProductErrorResponse.builder()
                .msg(exception.getMessage())
                .timeStamp(System.currentTimeMillis())
                .status(HttpStatus.NOT_ACCEPTABLE.value())
                .build();
        return new ResponseEntity<>(productErrorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> GlobalExceptionHandler(Exception exception) {
        ProductErrorResponse productErrorResponse = ProductErrorResponse.builder()
                .msg(exception.getMessage())
                .timeStamp(System.currentTimeMillis())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(productErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
