package com.myorg.buildbreaker.script.validator.result;

import java.util.Map;

import com.myorg.buildbreaker.script.validator.constant.ValidationOperationCode;

import static com.google.common.collect.Maps.newHashMap;

/**
 * Created by Shailesh on 7/13/16.
 */
public class ValidationResultList {

    private final Map<ValidationOperationCode, ValidationResult> validationResultMap = newHashMap();

    public void addValidationResult(ValidationResult validationResult){
        validationResultMap.put(validationResult.getOperationCode(), validationResult);
    }

    public ValidationResult getValidationResult(ValidationOperationCode operationCode){
        return validationResultMap.get(operationCode);
    }
}
