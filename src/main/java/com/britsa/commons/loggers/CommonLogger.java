/*
 *  ==============================================================================
 *
 *  Copyright 2020 Persh Corporation
 *  All rights reserved.
 *
 *  This program may not be duplicated, disclosed or provided to any third parties
 *  without the prior written consent of Persh Corporation.
 *
 *  Disassembly or de-compilation of the software and/or reverse engineering of
 *  the object code are prohibited.
 *
 *  ==============================================================================
 */

package com.britsa.commons.loggers;

import com.britsa.commons.codes.ApplicationCodes;
import com.britsa.commons.misc.CommonConstants;

import java.util.logging.Logger;

/**
 * @author Maria Irudaya Regilan J
 */

public class CommonLogger {

    private CommonLogger() {
    }

    private static Logger getLoggerObject(Class<?> value) {
        return Logger.getLogger(value.getName());
    }

    private static String simplifyLogPrefix(String logPrefix) {
        return CommonConstants.BRACKET_SQUARE_LEFT.concat(logPrefix.trim().toUpperCase()).concat(CommonConstants.BRACKET_SQUARE_RIGHT).concat(CommonConstants.SPACE);
    }

    public static void info(Class<?> classType, String message) {
        CommonLogger.getLoggerObject(classType).info(message);
    }

    public static void warning(Class<?> classType, String message) {
        CommonLogger.getLoggerObject(classType).warning(message);
    }

    public static void error(Class<?> classType, String message) {
        CommonLogger.getLoggerObject(classType).severe(message);
    }

    public static void error(Class<?> classType, ApplicationCodes appResponse) {
        CommonLogger.getLoggerObject(classType).severe(appResponse.logMessage());
    }

    public static void info(Class<?> classType, String logPrefix, String message) {
        CommonLogger.info(classType, CommonLogger.simplifyLogPrefix(logPrefix).concat(message));
    }

    public static void warning(Class<?> classType, String logPrefix, String message) {
        CommonLogger.warning(classType, CommonLogger.simplifyLogPrefix(logPrefix).concat(message));
    }

    public static void error(Class<?> classType, String logPrefix, String message) {
        CommonLogger.error(classType, CommonLogger.simplifyLogPrefix(logPrefix).concat(message));
    }

    public static void error(Class<?> classType, String logPrefix, ApplicationCodes appResponse) {
        CommonLogger.getLoggerObject(classType).severe(CommonLogger.simplifyLogPrefix(logPrefix).concat(appResponse.logMessage()));
    }

}
