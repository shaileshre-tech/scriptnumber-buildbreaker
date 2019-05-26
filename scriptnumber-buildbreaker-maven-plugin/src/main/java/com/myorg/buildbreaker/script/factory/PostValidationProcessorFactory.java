package com.myorg.buildbreaker.script.factory;

import com.myorg.buildbreaker.script.processor.NoOperationPostValidationProcessor;
import com.myorg.buildbreaker.script.processor.PostValidationProcessor;
import com.myorg.buildbreaker.script.processor.ScriptLogFilePostValidationProcessor;

/**
 * Created by Shailesh on 7/15/16.
 */
public class PostValidationProcessorFactory {

    private static final PostValidationProcessor NO_OPERATION_POST_VALIDATION_PROCESSOR = new NoOperationPostValidationProcessor();
    private static final PostValidationProcessor SCRIPT_LOG_FILE_POST_VALIDATION_PROCESSOR = new ScriptLogFilePostValidationProcessor();

    public static PostValidationProcessor getNoOperationPostValidationProcessorInstance() {
        return NO_OPERATION_POST_VALIDATION_PROCESSOR;
    }

    public static PostValidationProcessor getScriptLogFilePostValidationProcessorInstance() {
        return SCRIPT_LOG_FILE_POST_VALIDATION_PROCESSOR;
    }
}
