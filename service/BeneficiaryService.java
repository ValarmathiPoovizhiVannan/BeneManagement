package com.example.demo.service;

import com.example.demo.Validate.BeneficiaryValidator;
import com.example.demo.dto.BeneficiarySubmitRequest;
import com.example.demo.dto.BeneficiarySubmitResponse;
import com.example.demo.repo.BeneRepo;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.sql.SQLException;

@Service
public class BeneficiaryService {
    private final BeneficiaryValidator validator;
    private final BeneRepo beneRepo;

    public BeneficiaryService(BeneficiaryValidator validator, BeneRepo beneRepo) {
        this.validator = validator;
        this.beneRepo = beneRepo;
    }


    public BeneficiarySubmitResponse submitBeneficiary(@Valid BeneficiarySubmitRequest request) throws SQLException {
        validator.submitValidator(request);
        int beneId = beneRepo.save(request);

        BeneficiarySubmitResponse response =
                new BeneficiarySubmitResponse();


        response.setBeneficiaryId(String.valueOf(beneId));
        response.setResponseCode("00");
        response.setStatus("SUCCESS");
        response.setResponseMessage(
                "Beneficiary created successfully"
        );
return response;
    }
}
