package com.example.demo.Exception;


import com.example.demo.dto.ErrorMessage;

import java.util.List;

public record ExceptionResponse(String status, List<ErrorMessage> errors) {

}
