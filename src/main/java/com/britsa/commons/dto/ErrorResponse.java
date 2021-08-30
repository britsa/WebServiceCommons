package com.britsa.commons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Maria Irudaya Regilan J
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    public int errorCode;
    public String errorMessage;

    @JsonProperty("Code")
    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("Message")
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
