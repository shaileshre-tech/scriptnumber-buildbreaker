package com.myorg.buildbreaker.script.validator;

import java.util.List;

import com.google.common.base.Predicate;
import com.myorg.buildbreaker.script.pojo.ValidationConfiguration;
import com.myorg.buildbreaker.script.validator.constant.ValidationOperationCode;
import com.myorg.buildbreaker.script.validator.constant.ValidationStatusCode;
import com.myorg.buildbreaker.script.validator.result.ValidationResult;
import com.myorg.buildbreaker.script.validator.result.ValidationResultList;
import com.myorg.buildbreaker.script.validator.result.data.ValidationData;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;
import static com.myorg.buildbreaker.script.factory.ServiceFactory.getScriptDirectoryServiceInstance;
import static com.myorg.buildbreaker.script.factory.ServiceFactory.getScriptLogServiceInstance;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.BRANCH_NAME;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.NEW_SCRIPT_NUMBER_LIST;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.SCRIPT_DIRECTORY;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.SCRIPT_LOG_FILE_LOCATION;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.SKIP_LOG_FILE_WRITE;
import static com.myorg.buildbreaker.script.validator.constant.ValidationStatusCode.SKIP;
import static com.myorg.buildbreaker.script.validator.constant.ValidationStatusCode.SUCCESS;

/**
 * Created by Shailesh on 7/14/16.
 */
public class NewScriptAvailabilityValidator implements Validator {

    @Override
    public ValidationResult validate(ValidationConfiguration configuration, ValidationResultList validationResultList) {
        System.out.println("---------In NewScriptAvaliabilityValidator-------"+getScriptLogServiceInstance());

        List<Integer> newScriptNumberListInDirectory = null;

        final List<Integer> allScriptNumberListInLog = getScriptLogServiceInstance()
                .getAllScriptNumbersFromBranch(
                        configuration.getScriptNumberLogLocation(),
                        configuration.getBranchName());

        boolean listExistsForBranchInLog = (allScriptNumberListInLog.size() > 0);

        if(listExistsForBranchInLog) {
            List<Integer> allScriptNumberListInDirectory = getScriptDirectoryServiceInstance()
                    .getAllScriptNumbers(
                            configuration.getMigrationScriptDirectory());

            newScriptNumberListInDirectory = newArrayList(
                    filter(allScriptNumberListInDirectory, new Predicate<Integer>() {
                        @Override
                        public boolean apply(Integer input) {
                            return !allScriptNumberListInLog.contains(input);
                        }
                    }));
        } else {
            newScriptNumberListInDirectory = newArrayList();
        }

        System.out.println("newScriptNumberListInDirectory = "+newScriptNumberListInDirectory);

        ValidationStatusCode statusCode = newScriptNumberListInDirectory.size() > 0 ? SUCCESS : SKIP;

        ValidationResult result = new ValidationResult(ValidationOperationCode.NEW_SCRIPT_AVAILABILITY_VALIDATION, statusCode);

        ValidationData data = result.getValidationData();

        data.addParameter(NEW_SCRIPT_NUMBER_LIST, newScriptNumberListInDirectory);
        data.addParameter(SKIP_LOG_FILE_WRITE,
                (!configuration.isSkipLogFileWrite() && !listExistsForBranchInLog && statusCode == SKIP) ? false : true);
        data.addParameter(SCRIPT_LOG_FILE_LOCATION, configuration.getScriptNumberLogLocation());
        data.addParameter(SCRIPT_DIRECTORY, configuration.getMigrationScriptDirectory());
        data.addParameter(BRANCH_NAME, configuration.getBranchName());

        return result;
    }
}
