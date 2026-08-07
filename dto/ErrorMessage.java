package com.example.demo.dto;

import com.fasterxml.jackson.annotation.*;

public class ErrorMessage {

    @JsonProperty("errorCode")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    @JsonSetter(nulls = Nulls.FAIL)
    private String errorCode;

    @JsonProperty("severity")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    @JsonSetter(nulls = Nulls.FAIL)
    private String severity;

    @JsonProperty("errorMessage")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    @JsonSetter(nulls = Nulls.FAIL)
    private String errorMessage;

    @JsonProperty("identifier")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    @JsonSetter(nulls = Nulls.FAIL)
    private String identifier;

    @JsonCreator
    public ErrorMessage(@JsonProperty("severity") String severity, @JsonProperty("errorCode") String errorCode, @JsonProperty("errorMessage") String errorMessage, @JsonProperty("identifier") String identifier) {
        this.severity = severity;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.identifier = identifier;
    }

    public ErrorMessage(@JsonProperty("severity") String severity, @JsonProperty("errorCode") String errorCode, @JsonProperty("errorMessage") String errorMessage) {
        this.severity = severity;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
