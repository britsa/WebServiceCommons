package com.britsa.commons.misc;

import com.britsa.commons.codes.ApplicationCodes;
import com.britsa.commons.codes.HTTPCodes;
import com.britsa.commons.dto.StatusIndicator;
import com.britsa.commons.exceptions.WebExceptionType;
import com.britsa.commons.exceptions.WebServiceException;
import com.britsa.commons.loggers.CommonLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.Timestamp;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * @author Maria Irudaya Regilan J
 */

public class Utility {

    private Utility() {
    }

    private static ObjectMapper objectMapper = null;

    private static DateFormat simpleDateFormat;

    private static DateFormat getSimpleDateFormat() {
        if (Utility.simpleDateFormat == null) {
            CommonLogger.info(Utility.class, "creating SimpleDateFormat object...");
            Utility.simpleDateFormat = new SimpleDateFormat(CommonConstants.DATE_TIME_FORMAT);
            Utility.simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        CommonLogger.info(Utility.class, "returning SimpleDateFormat object...");
        return Utility.simpleDateFormat;
    }

    public static ObjectMapper jsonConverter() {
        if (Utility.objectMapper == null) {
            CommonLogger.info(Utility.class, "Creating Jackson Json Converter object...");
            Utility.objectMapper = new ObjectMapper();
        }
        return Utility.objectMapper;
    }

    public static String objectToJsonString(Object any) throws WebServiceException {
        try {
            return Utility.jsonConverter().writeValueAsString(any);
        } catch (IOException ex) {
            CommonLogger.error(Utility.class, ex.getMessage());
            throw new WebServiceException(ApplicationCodes.ERROR_JACKSON_CONVERSION, HTTPCodes.INTERNAL_ERROR, WebExceptionType.INTERNAL_ERROR);
        }
    }

    public static Object jsonStringToObject(String anyJsonString, Class<?> type) throws WebServiceException {
        try {
            return Utility.jsonConverter().readValue(anyJsonString, type);

        } catch (IOException ex) {
            CommonLogger.error(Utility.class, ex.getMessage());
            throw new WebServiceException(ApplicationCodes.ERROR_JACKSON_CONVERSION, HTTPCodes.INTERNAL_ERROR, WebExceptionType.INTERNAL_ERROR);
        }
    }

    public static StatusIndicator defaultSuccessBody() {
        CommonLogger.info(Utility.class, "Wrapping up the response...");

        StatusIndicator statusIndicator = new StatusIndicator();
        statusIndicator.completed();

        return statusIndicator;
    }

    public static String convertTimeStampToString(Timestamp timestamp) {
        CommonLogger.info(Utility.class, "converting Timestamp [".concat(timestamp.toString()).concat("] to string..."));

        String formattedOutput = Utility.getSimpleDateFormat().format(timestamp.toDate());

        CommonLogger.info(Utility.class, "Successfully converted [".concat(timestamp.toString()).concat("] to [").concat(formattedOutput).concat("]"));
        return formattedOutput;
    }

    public static Timestamp convertStringDateToTimestamp(String date) throws WebServiceException {
        CommonLogger.info(Utility.class, "converting String Date [".concat(date).concat("] to Timestamp..."));

        Timestamp formattedOutput;
        String correctFormat;

        if (date.matches(CommonConstants.INPUT_DATE_FORMAT_1)) { // [0-3][0-9][0-1][0-9][2-9][0-9]{3} -> ddMMyyyy
            correctFormat = date.substring(4).concat("-").concat(date.substring(2, 4)).concat("-").concat(date.substring(0, 2)).concat(CommonConstants.INPUT_TIME_FORMAT); // yyyy-MM-ddT00:00:00

        } else if (date.matches(CommonConstants.INPUT_DATE_FORMAT_2)) { // [0-3][0-9]/[0-1][0-9]/[2-9][0-9]{3} -> dd/MM/yyyy
            String[] dateValues = date.split("/");
            correctFormat = dateValues[2].concat("-").concat(dateValues[1]).concat("-").concat(dateValues[0]).concat(CommonConstants.INPUT_TIME_FORMAT); // yyyy-MM-ddT00:00:00

        } else {
            throw new WebServiceException(ApplicationCodes.INVALID_DATE_FORMAT_PARSE_TIMESTAMP, HTTPCodes.BAD_REQUEST, WebExceptionType.VALIDATION);
        }

        formattedOutput = Timestamp.parseTimestamp(correctFormat);

        CommonLogger.info(Utility.class, "Successfully converted [".concat(date).concat("] to [").concat(formattedOutput.toString()).concat("]"));
        return formattedOutput;
    }

    public static Timestamp convertStringDateTimeToTimestamp(String dateTime) throws WebServiceException {
        CommonLogger.info(Utility.class, "converting Timestamp DateTime [".concat(dateTime).concat("] to Timestamp..."));

        Timestamp formattedOutput;
        String correctFormat;

        if (dateTime.matches(CommonConstants.INPUT_DATE_FORMAT_1)) { // [0-3][0-9][0-1][0-9][2-9][0-9]{3} [0-2][0-9][0-5][0-9][0-5][0-9] -> ddMMyyyy
            correctFormat = dateTime.substring(4, 8).concat("-").concat(dateTime.substring(2, 4)).concat("-").concat(dateTime.substring(0, 2)).concat("T").concat(dateTime.substring(9, 11)).concat(":").concat(dateTime.substring(11, 13)).concat(":").concat(dateTime.substring(13)); // yyyy-MM-ddTHH:mm:ss

        } else if (dateTime.matches(CommonConstants.INPUT_DATE_TIME_FORMAT_2)) { // [0-3][0-9]/[0-1][0-9]/[2-9][0-9]{3} [0-2][0-9]:[0-5][0-9]:[0-5][0-9] -> dd/MM/yyyy
            String[] dateValues = dateTime.substring(0, 8).split("/");
            correctFormat = dateValues[2].concat("-").concat(dateValues[1]).concat("-").concat(dateValues[0]).concat(dateTime.substring(8)); // yyyy-MM-ddT00:00:00

        } else {
            throw new WebServiceException(ApplicationCodes.INVALID_DATE_TIME_FORMAT_PARSE_TIMESTAMP, HTTPCodes.BAD_REQUEST, WebExceptionType.VALIDATION);
        }

        formattedOutput = Timestamp.parseTimestamp(correctFormat);

        CommonLogger.info(Utility.class, "Successfully converted [".concat(dateTime).concat("] to [").concat(formattedOutput.toString()).concat("]"));
        return formattedOutput;
    }


}
