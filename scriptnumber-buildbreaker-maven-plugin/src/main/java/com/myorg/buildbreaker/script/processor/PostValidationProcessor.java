package com.myorg.buildbreaker.script.processor;

import com.myorg.buildbreaker.script.validator.result.ValidationResult;

/**
 * Created by Shailesh on 7/14/16.
 */
public interface PostValidationProcessor {

    void process(ValidationResult validationResult);
}
