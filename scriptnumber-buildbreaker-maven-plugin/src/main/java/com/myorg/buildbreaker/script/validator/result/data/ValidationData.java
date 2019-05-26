package com.myorg.buildbreaker.script.validator.result.data;

import java.util.Map;

import com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey;

import static com.google.common.collect.Maps.newHashMap;

/**
 * Created by Shailesh on 7/15/16.
 */
public class ValidationData {

    private final Map<ValidationDataParameterKey, Object> validationDataMap = newHashMap();

    public <T> void addParameter(ValidationDataParameterKey key, T value){
        validationDataMap.put(key, value);
    }

    public <T> T getParameter(ValidationDataParameterKey key){
        return (T)validationDataMap.get(key);
    }
}
