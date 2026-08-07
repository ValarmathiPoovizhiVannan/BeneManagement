package com.example.demo.controller;

import com.example.demo.dto.BeneficiarySubmitRequest;
import com.example.demo.dto.BeneficiarySubmitResponse;
import com.example.demo.service.BeneficiaryService;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1/beneficiaries")
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    public BeneficiaryController(BeneficiaryService beneficiaryService) {
        this.beneficiaryService = beneficiaryService;
    }


    @PostMapping("/submit")
    public ResponseEntity<BeneficiarySubmitResponse> submitBeneficiary(
            @Valid @RequestBody BeneficiarySubmitRequest request) throws SQLException {

        BeneficiarySubmitResponse response =
                beneficiaryService.submitBeneficiary(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}