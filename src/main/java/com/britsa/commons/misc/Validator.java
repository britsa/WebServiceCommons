package com.britsa.commons.misc;

import com.britsa.commons.codes.ApplicationCodes;
import com.britsa.commons.codes.HTTPCodes;
import com.britsa.commons.exceptions.WebExceptionType;
import com.britsa.commons.exceptions.WebServiceException;
import com.britsa.commons.loggers.CommonLogger;

/**
 * @author Maria Irudaya Regilan J
 */

public class Validator {

    private Validator() {
    }

    public static String ignoreNullByString(String any) {
        // This methods will have string argument and returns the same if not null else will return an empty string
        if (any == null) {
            CommonLogger.warning(Validator.class, "[ignoreNullByString()] Given input is null. Returning Empty string...");
            return "";
        }
        CommonLogger.info(Validator.class, "[ignoreNullByString()] Given input is [".concat(any).concat("] is not null. Returning the same string..."));
        return any;
    }

    public static void notNull(Object value) throws WebServiceException {
        if (value == null)
            throw new WebServiceException(ApplicationCodes.VALIDATION_NULL_FOUND, HTTPCodes.BAD_REQUEST, WebExceptionType.VALIDATION);
    }

    public static boolean isNull(Object value) {
        return value == null;
    }

    public static boolean isNotNull(Object value) {
        return value != null;
    }

}
