package com.britsa.commons.exceptions;

import com.britsa.commons.codes.ApplicationCodes;
import com.britsa.commons.codes.HTTPCodes;
import com.britsa.commons.dto.ErrorResponse;
import com.britsa.commons.dto.StatusIndicator;
import com.britsa.commons.loggers.CommonLogger;

/**
 * @author Maria Irudaya Regilan J
 */

public class WebServiceException extends Exception {

    private ApplicationCodes applicationCode;
    private HTTPCodes httpCode;
    private WebExceptionType exceptionType;

    public WebServiceException(ApplicationCodes appCode, HTTPCodes httpCode) {
        super(appCode.logMessage());
        this.applicationCode = appCode;
        this.httpCode = httpCode;
        this.exceptionType = WebExceptionType.INTERNAL_ERROR;
        CommonLogger.error(WebServiceException.class, this.applicationCode);
    }

    public WebServiceException(ApplicationCodes appCode, HTTPCodes httpCode, WebExceptionType exceptionType) {
        super(appCode.logMessage());
        this.applicationCode = appCode;
        this.httpCode = httpCode;
        this.exceptionType = exceptionType;
        CommonLogger.error(WebServiceException.class, this.applicationCode);
    }

    public WebExceptionType getExceptionType() {
        return exceptionType;
    }

    public StatusIndicator response() {
        StatusIndicator commonResponse = new StatusIndicator();
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setErrorCode(httpCode.getHttpCode());
        errorResponse.setErrorMessage(httpCode.getHttpCodeDescription());
        commonResponse.setError(errorResponse);

        return commonResponse;
    }

}
