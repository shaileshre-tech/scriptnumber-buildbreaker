package com.myorg.buildbreaker.script.factory;

import com.myorg.buildbreaker.script.service.ScriptDirectoryService;
import com.myorg.buildbreaker.script.service.ScriptDirectoryServiceImpl;
import com.myorg.buildbreaker.script.service.ScriptLogFileService;
import com.myorg.buildbreaker.script.service.ScriptLogFileServiceImpl;
import com.myorg.buildbreaker.script.service.ScriptLogService;
import com.myorg.buildbreaker.script.service.ScriptLogServiceImpl;

/**
 * Created by Shailesh on 7/14/16.
 */
public class ServiceFactory {

    private static final ScriptDirectoryService SCRIPT_DIRECTORY_SERVICE = new ScriptDirectoryServiceImpl();
    private static final ScriptLogService SCRIPT_LOG_SERVICE = new ScriptLogServiceImpl();
    private static final ScriptLogFileService SCRIPT_LOG_FILE_SERVICE = new ScriptLogFileServiceImpl();

    public static ScriptDirectoryService getScriptDirectoryServiceInstance() {
        return SCRIPT_DIRECTORY_SERVICE;
    }

    public static ScriptLogService getScriptLogServiceInstance() {
        return SCRIPT_LOG_SERVICE;
    }

    public static ScriptLogFileService getScriptLogFileServiceInstance() {
        return SCRIPT_LOG_FILE_SERVICE;
    }
}
