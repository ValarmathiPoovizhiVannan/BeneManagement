package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.BeneficiaryService;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

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

    @PostMapping("/amend")
    public ResponseEntity<AmendBeneficiaryResponse> amendBeneficiary(
            @Valid @RequestBody AmendBeneficiaryRequest request) throws SQLException {

        AmendBeneficiaryResponse response =
                beneficiaryService.amendBeneficiary(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/delete")
    public ResponseEntity<DeleteBeneResponse> deleteBeneficiary(
            @Valid @RequestBody DeleteBeneRequest request) throws SQLException {

        DeleteBeneResponse response =
                beneficiaryService.deleteBeneficiary(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}