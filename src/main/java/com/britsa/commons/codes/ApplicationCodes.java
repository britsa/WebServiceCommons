package com.britsa.commons.codes;

import java.text.MessageFormat;

/**
 * @author Maria Irudaya Regilan J
 */

public enum ApplicationCodes {

    VALIDATION_NULL_FOUND(50001, "Null object found in a non-null place"),
    ERROR_JACKSON_CONVERSION(50002, "Some internal error occurred when parsing the argument with jackson methods"),
    DOCUMENT_LOOKUP_LATEST_ID_NOT_FOUND(50003, "Latest ID parameter missing the Lookup document"),
    FIREBASE_GOOGLE_CREDENTIALS_STREAM_ERROR(50004, "Error occurred during parsing Google Credentials strings"),
    FIREBASE_DOCUMENT_EXCEPTION(50005, "Interruption or Execution exception occurred while getting a document from ApiFuture"),
    FIREBASE_DOCUMENT_NOT_EXIST(50006, "Requested Firebase document not available in Firestore"),
    FIREBASE_DOCUMENT_NOT_RETRIEVED(50007, "Available Firestore document is not retrieved"),
    INVALID_DATE_FORMAT_PARSE_TIMESTAMP(50008, "Invalid String Date Format found when parsing to Timestamp"),
    INVALID_DATE_TIME_FORMAT_PARSE_TIMESTAMP(50009, "Invalid String DateTime Format found when parsing to Timestamp"),
    FIREBASE_DOCUMENT_ALREADY_EXISTS(50010, "Requested Firebase document already available in Firestore"),
    ;

    private int appCode;
    private String appCodeDescription;

    ApplicationCodes(int appCode, String appCodeDescription) {
        this.appCode = appCode;
        this.appCodeDescription = appCodeDescription;
    }

    public int getAppCode() {
        return appCode;
    }

    public String getAppCodeDescription() {
        return appCodeDescription;
    }

    public String logMessage() {
        final String messageFormat = "{0} - {1}";
        return MessageFormat.format(messageFormat, this.getAppCode(), this.getAppCodeDescription());
    }
}
