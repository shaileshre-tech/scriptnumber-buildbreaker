package com.myorg.buildbreaker.script.validator;

import com.myorg.buildbreaker.script.pojo.ValidationConfiguration;
import com.myorg.buildbreaker.script.validator.result.ValidationResult;
import com.myorg.buildbreaker.script.validator.result.ValidationResultList;

/**
 * Created by Shailesh on 7/12/16.
 */
public interface Validator {

    ValidationResult validate(ValidationConfiguration configuration, ValidationResultList validationResultList);
}
