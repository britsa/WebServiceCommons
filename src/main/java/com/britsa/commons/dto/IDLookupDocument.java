package com.britsa.commons.dto;

import com.britsa.commons.misc.CommonConstants;

import java.util.Map;

/**
 * @author Maria Irudaya Regilan J
 */

public class IDLookupDocument {

    private Integer idValue;

    public IDLookupDocument(Map<String, Object> databaseValue) {
        this.idValue = ((Long) databaseValue.getOrDefault(CommonConstants.LOOKUP_DOC_LATEST_ID, null)).intValue();
    }

    public Integer getIdValue() {
        return idValue;
    }
}
