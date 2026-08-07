package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class BeneficiarySubmitRequest {


    private String customerId;
    private String corporateId;
    private String userId;


    private String beneficiaryName;
    private String nickName;
    private String beneficiaryType;
    private String beneficiaryCategory;
    private String relationship;

    private String mobileNumber;
    private String email;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String postalCode;


    private String status;
    private String favourite;
    private String active;


    private BigDecimal dailyLimit;
    private BigDecimal monthlyLimit;
    private BigDecimal transactionLimit;


    private List<AccountRequest> accounts;

    private String createdBy;
}
