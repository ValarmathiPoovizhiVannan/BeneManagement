package com.example.demo.service;

import com.example.demo.validate.BeneficiaryValidator;
import com.example.demo.dto.AmendBeneficiaryRequest;
import com.example.demo.dto.AmendBeneficiaryResponse;
import com.example.demo.dto.BeneficiarySubmitRequest;
import com.example.demo.dto.BeneficiarySubmitResponse;
import com.example.demo.repo.BeneRepo;
import com.example.demo.util.EmailUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

@Service
public class BeneficiaryService {
    private final BeneficiaryValidator validator;
    private final BeneRepo beneRepo;
    private EmailUtil emailUtil;
    private final EmailService emailService;

    public BeneficiaryService(BeneficiaryValidator validator, BeneRepo beneRepo, EmailService emailService) {
        this.validator = validator;
        this.beneRepo = beneRepo;
        this.emailService = emailService;
    }

    private static final Logger log = LoggerFactory.getLogger(BeneficiaryService.class);

    public BeneficiarySubmitResponse submitBeneficiary(@Valid BeneficiarySubmitRequest request) throws SQLException {
        validator.submitValidator(request);
        String status = beneRepo.save(request);

        BeneficiarySubmitResponse response = new BeneficiarySubmitResponse();
        if (status.equalsIgnoreCase("SUCCESS")) {
            if (request.getEmail() != null && !request.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,}$")){
            String subject = "Beneficiary was successfully added";
            String body = EmailUtil.createTemplate(request);
            try {
                CompletableFuture.runAsync(() -> {
                    emailService.sendMail(request.getEmail(), subject, body);
                });
            } catch (Exception e) {
                log.info("Mail sending failed: {}", e.getMessage());

            }
        }}
        response.setResponseCode("00");
        response.setBeneficiaryName(request.getBeneficiaryName());
        response.setBeneficiaryId(String.valueOf(request.getBeneId()));
        response.setStatus(status);
        response.setCreatedDate(System.currentTimeMillis());
        response.setResponseMessage("Beneficiary created successfully");
        return response;
    }

    public AmendBeneficiaryResponse amendBeneficiary(@Valid AmendBeneficiaryRequest request) throws SQLException {
        validator.amendValidator(request);
        String status = beneRepo.amend(request);
        AmendBeneficiaryResponse response = new AmendBeneficiaryResponse();
        response.setBeneNickName(request.getBeneficiaryName());
        response.setBeneficiaryId(String.valueOf(request.getBeneId()));
        response.setBeneNickName(request.getNickName());
        response.setStatus(status);
        response.setResponseMessage("Amend created successfully");

        return response;
    }
}
