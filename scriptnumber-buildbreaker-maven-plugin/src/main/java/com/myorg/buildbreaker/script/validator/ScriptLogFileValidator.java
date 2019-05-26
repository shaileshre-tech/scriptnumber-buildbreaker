package com.myorg.buildbreaker.script.validator;

import java.io.File;

import com.myorg.buildbreaker.script.pojo.ValidationConfiguration;
import com.myorg.buildbreaker.script.validator.constant.ValidationOperationCode;
import com.myorg.buildbreaker.script.validator.constant.ValidationStatusCode;
import com.myorg.buildbreaker.script.validator.result.ValidationResult;
import com.myorg.buildbreaker.script.validator.result.ValidationResultList;
import com.myorg.buildbreaker.script.validator.result.data.ValidationData;

import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.BRANCH_NAME;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.SCRIPT_DIRECTORY;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.SCRIPT_LOG_FILE_LOCATION;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.SKIP_LOG_FILE_WRITE;
import static com.myorg.buildbreaker.script.validator.constant.ValidationStatusCode.SKIP;
import static com.myorg.buildbreaker.script.validator.constant.ValidationStatusCode.SUCCESS;

/**
 * Created by Shailesh on 7/13/16.
 */
public class ScriptLogFileValidator implements Validator {

    @Override
    public ValidationResult validate(ValidationConfiguration configuration, ValidationResultList validationResultList) {
        System.out.println("---------In ScriptLogFileValidator-------");

        File file = new File(configuration.getScriptNumberLogLocation());

        ValidationStatusCode statusCode = file.exists() ? SUCCESS : SKIP;

        ValidationResult result = new ValidationResult(ValidationOperationCode.SCRIPT_LOG_FILE_VALIDATION, statusCode);

        ValidationData data = result.getValidationData();

        data.addParameter(SKIP_LOG_FILE_WRITE, (!configuration.isSkipLogFileWrite() && statusCode == SKIP) ? false : true);
        data.addParameter(SCRIPT_LOG_FILE_LOCATION, configuration.getScriptNumberLogLocation());
        data.addParameter(SCRIPT_DIRECTORY, configuration.getMigrationScriptDirectory());
        data.addParameter(BRANCH_NAME, configuration.getBranchName());

        return result;
    }
}
