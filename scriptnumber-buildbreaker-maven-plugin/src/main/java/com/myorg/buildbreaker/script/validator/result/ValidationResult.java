package com.myorg.buildbreaker.script.validator.result;

import com.myorg.buildbreaker.script.validator.constant.ValidationOperationCode;
import com.myorg.buildbreaker.script.validator.constant.ValidationStatusCode;
import com.myorg.buildbreaker.script.validator.result.data.ValidationData;

/**
 * Created by Shailesh on 7/13/16.
 */
public class ValidationResult {

    private final ValidationOperationCode operationCode;
    private final ValidationStatusCode statusCode;
    private final String message;
    private final ValidationData validationData = new ValidationData();


    public ValidationResult(ValidationOperationCode operationCode, ValidationStatusCode statusCode) {
        this.operationCode = operationCode;
        this.statusCode = statusCode;
        this.message = null;
    }

    public ValidationResult(ValidationOperationCode operationCode, ValidationStatusCode statusCode, String message) {
        this.operationCode = operationCode;
        this.statusCode = statusCode;
        this.message = message;
    }

    public ValidationOperationCode getOperationCode() {
        return operationCode;
    }

    public ValidationStatusCode getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public ValidationData getValidationData() {
        return validationData;
    }
}
