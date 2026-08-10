package com.example.demo.util;

import com.example.demo.dto.BeneficiarySubmitRequest;
import jakarta.validation.Valid;

public class EmailUtil {
    public static String createTemplate(@Valid BeneficiarySubmitRequest request) {
        return "Hello " + request.getBeneficiaryName() + ",\n\n"
                + "Your beneficiary profile has been created successfully.\n\n"
                + "Beneficiary Details:\n"
                + "Name : " + request.getBeneficiaryName() + "\n"
                + "Mobile : " + request.getMobileNumber() + "\n"
                + "Email : " + request.getEmail() + "\n\n"
                + "Thank you.";
    }

}
