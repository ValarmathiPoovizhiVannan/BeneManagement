package com.example.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class BeneficiarySubmitResponse {
    private String status;
    private String responseCode;
    private String responseMessage;

    private String beneficiaryId;

    private long createdDate;
}
