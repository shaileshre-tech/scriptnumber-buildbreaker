package com.myorg.buildbreaker.script.validator;

import java.util.List;

import com.google.common.base.Predicate;
import com.myorg.buildbreaker.script.factory.ServiceFactory;
import com.myorg.buildbreaker.script.pojo.ValidationConfiguration;
import com.myorg.buildbreaker.script.validator.constant.ValidationOperationCode;
import com.myorg.buildbreaker.script.validator.constant.ValidationStatusCode;
import com.myorg.buildbreaker.script.validator.result.ValidationResult;
import com.myorg.buildbreaker.script.validator.result.ValidationResultList;
import com.myorg.buildbreaker.script.validator.result.data.ValidationData;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.BRANCH_NAME;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.NEW_SCRIPT_NUMBER_LIST;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.SCRIPT_DIRECTORY;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.SCRIPT_LOG_FILE_LOCATION;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.SKIP_LOG_FILE_WRITE;

/**
 * Created by Shailesh on 7/12/16.
 */
public class DuplicateScriptNumberValidator implements Validator {

    @Override
    public ValidationResult validate(ValidationConfiguration configuration, ValidationResultList validationResultList) {
        System.out.println("---------In ScriptNumberValidator-------");

        ValidationResult previousResult = validationResultList.getValidationResult(ValidationOperationCode.NEW_SCRIPT_AVAILABILITY_VALIDATION);
        List<Integer> newScriptNumberListInDirectory = previousResult.getValidationData().getParameter(NEW_SCRIPT_NUMBER_LIST);

        final List<Integer> allMergedScriptNumberListInLog = ServiceFactory.getScriptLogServiceInstance()
                .getAllMergedScriptNumbersAcrossBranches(
                        configuration.getScriptNumberLogLocation());

        List<Integer> duplicateScriptNumberListInDirectory = newArrayList(filter(newScriptNumberListInDirectory, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return allMergedScriptNumberListInLog.contains(input);
            }
        }));

        System.out.println("duplicateScriptNumberListInDirectory = "+duplicateScriptNumberListInDirectory);
        System.out.println("latestMergedScriptNumberInLog = "+allMergedScriptNumberListInLog.get(allMergedScriptNumberListInLog.size() - 1));

        ValidationStatusCode statusCode = duplicateScriptNumberListInDirectory.size() > 0 ? ValidationStatusCode.FAILURE : ValidationStatusCode.SUCCESS;

        ValidationResult result = new ValidationResult(ValidationOperationCode.NEW_SCRIPT_NUMBER_VALIDATION, statusCode);

        ValidationData data = result.getValidationData();

        data.addParameter(SKIP_LOG_FILE_WRITE, (!configuration.isSkipLogFileWrite() && statusCode == ValidationStatusCode.SUCCESS) ? false : true);
        data.addParameter(SCRIPT_LOG_FILE_LOCATION, configuration.getScriptNumberLogLocation());
        data.addParameter(SCRIPT_DIRECTORY, configuration.getMigrationScriptDirectory());
        data.addParameter(BRANCH_NAME, configuration.getBranchName());

        return result;
    }
}
