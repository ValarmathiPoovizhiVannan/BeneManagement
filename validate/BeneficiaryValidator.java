package com.example.demo.validate;

import com.example.demo.exception.BeneficiaryException;
import com.example.demo.dto.AmendBeneficiaryRequest;
import com.example.demo.dto.BeneficiarySubmitRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
