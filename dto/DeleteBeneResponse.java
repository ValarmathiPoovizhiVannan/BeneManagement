package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DeleteBeneResponse {
    @JsonProperty("beneId")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private Integer beneId;

    @JsonProperty("status")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private String status;

    @JsonProperty("beneStatus")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private String beneStatus;

    @JsonProperty("beneNickName")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    private String beneNickName;
}
