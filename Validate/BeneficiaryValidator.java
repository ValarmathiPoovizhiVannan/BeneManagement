package com.example.demo.Validate;

import com.example.demo.Exception.BeneficiaryException;
import com.example.demo.dto.AmendBeneficiaryRequest;
import com.example.demo.dto.BeneficiarySubmitRequest;
import com.example.demo.dto.ErrorMessage;
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
        if (request.getBeneficiaryName() == null || request.getBeneficiaryName().isEmpty()) {
            applyError("BeneName is Mandatory");
        }
    }

    public  static BeneficiaryException applyError(String error){
        throw  new BeneficiaryException(error);
    }

        public void amendValidator(@Valid AmendBeneficiaryRequest request) {

        if (request.getBeneficiaryName() == null || request.getBeneficiaryName().isEmpty()) {
            applyError("BeneName is Mandatory");
        }
    }
}
