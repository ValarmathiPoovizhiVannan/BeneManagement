package com.example.demo.Validate;

import com.example.demo.Exception.BeneficiaryException;
import com.example.demo.Exception.ExceptionResponse;
import com.example.demo.dto.BeneficiarySubmitRequest;
import com.example.demo.dto.ErrorMessage;
import com.example.demo.util.BeneErrorCodeUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class BeneficiaryValidator {

    static final Logger LOG= LoggerFactory.getLogger(BeneficiaryValidator.class);


    public void submitValidator(@Valid BeneficiarySubmitRequest request) {
        List<ErrorMessage> errors = new ArrayList<>();
        if (request.getBeneficiaryName() == null || request.getBeneficiaryName().isEmpty()) {
            errors.add(BeneErrorCodeUtil.getErrorMessage("BeneficiaryName"));
            applyError(errors);
        }
    }

    public static void applyError(List<ErrorMessage> errors) {
        if (errors != null && !errors.isEmpty()) {
            LOG.info("error->" + errors);
            ExceptionResponse exceptionResponse = new ExceptionResponse("ERROR", errors);
            throw new BeneficiaryException("Error", exceptionResponse, null);
        }
    }
}
