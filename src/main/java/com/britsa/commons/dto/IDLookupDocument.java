package com.britsa.commons.dto;

import com.britsa.commons.misc.CommonConstants;

import java.util.Map;

/**
 * @author Maria Irudaya Regilan J
 */

public class IDLookupDocument {

    private String idValue;

    public IDLookupDocument(Map<String, Object> databaseValue) {
        this.idValue = String.valueOf(databaseValue.getOrDefault(CommonConstants.LOOKUP_DOC_LATEST_ID, null));
    }

    public String getIdValue() {
        return idValue;
    }
}
