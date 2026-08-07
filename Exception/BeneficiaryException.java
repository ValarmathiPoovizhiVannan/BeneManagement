package com.example.demo.Exception;

public class BeneficiaryException extends RuntimeException {

    ExceptionResponse errorDto;

    public BeneficiaryException(String message, ExceptionResponse errorDto, Throwable cause) {
        super(message, cause);
        this.errorDto = errorDto;
    }

    public ExceptionResponse getErrorDto() {
        return errorDto;
    }

}