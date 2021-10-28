package com.britsa.commons.dto;

import com.britsa.commons.loggers.CommonLogger;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.cloud.firestore.annotation.Exclude;

/**
 * @author Maria Irudaya Regilan J
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusIndicator {

    private Boolean success;
    private ErrorResponse error;

    @JsonProperty("Success")
    @Exclude
    public Boolean isSuccess() {
        return success;
    }

    public void completed() {
        this.success = true;
        CommonLogger.info(StatusIndicator.class, "API Response object set success to 'True'");
    }

    @JsonProperty("Error")
    @Exclude
    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.success = false;
        this.error = error;
        CommonLogger.info(StatusIndicator.class, "API Response object set success to 'False'. ErrorResponse noted!");
    }

}
