package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;
@Data
public class DeleteBeneRequest {
    @JsonProperty("beneId")
    private Integer beneId;

    @JsonProperty("productGroup")
    private String productGroup;

    @JsonProperty("productCode")
    private String productCode;

    @JsonProperty("subProductCode")
    private String subProductCode;

    @JsonProperty("beneNickName")
    private String beneNickName;

    @JsonProperty("corpCrn")
    private String corpCrn;

    @JsonProperty("remarks")
    private String remarks;

    @JsonProperty("accounts")
    private List<AccountRequest> accounts;
}
