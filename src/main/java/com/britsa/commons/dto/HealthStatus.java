package com.britsa.commons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Maria Irudaya Regilan J
 */

public class HealthStatus {

    @JsonProperty("Status")
    protected String status;

    @JsonProperty("ProductOwner")
    protected String productOwner;

    @JsonProperty("DeveloperName")
    protected String[] developerName;

    public HealthStatus(String companyName, String[] developerNames) {
        this.status = "UP";
        this.productOwner = companyName;
        this.developerName = developerNames;
    }
}
