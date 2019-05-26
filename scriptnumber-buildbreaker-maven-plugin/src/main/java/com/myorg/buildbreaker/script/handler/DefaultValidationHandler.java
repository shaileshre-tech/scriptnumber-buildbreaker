package com.myorg.buildbreaker.script.handler;

import java.util.Map;

import com.myorg.buildbreaker.script.factory.PostValidationProcessorFactory;
import com.myorg.buildbreaker.script.pojo.ValidationConfiguration;
import com.myorg.buildbreaker.script.processor.PostValidationProcessor;
import com.myorg.buildbreaker.script.validator.DuplicateScriptNumberValidator;
import com.myorg.buildbreaker.script.validator.NewScriptAvailabilityValidator;
import com.myorg.buildbreaker.script.validator.ScriptLogFileValidator;
import com.myorg.buildbreaker.script.validator.Validator;
import com.myorg.buildbreaker.script.validator.constant.ValidationStatusCode;
import com.myorg.buildbreaker.script.validator.result.ValidationResult;
import com.myorg.buildbreaker.script.validator.result.ValidationResultList;

import static com.google.common.collect.Maps.newLinkedHashMap;

/**
 * Created by Shailesh on 7/13/16.
 */
public class DefaultValidationHandler implements ValidationHandler {

    private final Map<Validator, PostValidationProcessor> validatorProcessorMap = newLinkedHashMap();
    private final ValidationConfiguration configuration;

    public DefaultValidationHandler(ValidationConfiguration configuration) {
        this.configuration = configuration;

        validatorProcessorMap.put(new ScriptLogFileValidator(), PostValidationProcessorFactory.getScriptLogFilePostValidationProcessorInstance());
        validatorProcessorMap.put(new NewScriptAvailabilityValidator(), PostValidationProcessorFactory.getScriptLogFilePostValidationProcessorInstance());
        validatorProcessorMap.put(new DuplicateScriptNumberValidator(), PostValidationProcessorFactory.getScriptLogFilePostValidationProcessorInstance());
    }

    @Override
    public void handle() {
        ValidationResultList validationResultList = new ValidationResultList();

        for(Validator validator : validatorProcessorMap.keySet()) {
            ValidationResult validationResult = validator.validate(configuration, validationResultList);

            if (validationResult.getStatusCode() == ValidationStatusCode.FAILURE) {
                throw new RuntimeException("\n\nScript number in use\n");
            } else if(validationResult.getStatusCode() == ValidationStatusCode.SUCCESS) {
                validatorProcessorMap.get(validator).process(validationResult);
            } else if(validationResult.getStatusCode() == ValidationStatusCode.SKIP) {
                validatorProcessorMap.get(validator).process(validationResult);
                return;
            }

            validationResultList.addValidationResult(validationResult);
        }
    }
}
