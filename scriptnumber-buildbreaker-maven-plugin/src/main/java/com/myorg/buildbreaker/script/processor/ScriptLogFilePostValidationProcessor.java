package com.myorg.buildbreaker.script.processor;

import com.myorg.buildbreaker.script.validator.result.ValidationResult;

import static com.myorg.buildbreaker.script.factory.ServiceFactory.getScriptDirectoryServiceInstance;
import static com.myorg.buildbreaker.script.factory.ServiceFactory.getScriptLogFileServiceInstance;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.BRANCH_NAME;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.SCRIPT_DIRECTORY;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.SCRIPT_LOG_FILE_LOCATION;
import static com.myorg.buildbreaker.script.validator.constant.ValidationDataParameterKey.SKIP_LOG_FILE_WRITE;

/**
 * Created by Shailesh on 7/14/16.
 */
public class ScriptLogFilePostValidationProcessor implements PostValidationProcessor {

    @Override
    public void process(ValidationResult validationResult) {
        System.out.println("-----------in ScriptLogFilePostValidationProcessor");

        boolean skipLogFileWrite = validationResult.getValidationData().getParameter(SKIP_LOG_FILE_WRITE);

        if(skipLogFileWrite){
            return;
        }

        String logFile = validationResult.getValidationData().getParameter(SCRIPT_LOG_FILE_LOCATION);
        String dirPath = validationResult.getValidationData().getParameter(SCRIPT_DIRECTORY);
        String branchName = validationResult.getValidationData().getParameter(BRANCH_NAME);

        getScriptLogFileServiceInstance().saveorUpdateScriptListByBranch(
                logFile,
                branchName,
                getScriptDirectoryServiceInstance().getAllScriptNumbersAsString(dirPath));
    }
}
