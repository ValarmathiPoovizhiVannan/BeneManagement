package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequest {
    private Integer accountId;

    private String accountNumber;
    private String accountName;
    private String accountType;
    private String bankName;
    private String branchName;
    private String ifscCode;
    private String currency;
    private String defaultFlag;
    private BigDecimal beneficiaryId;
}
