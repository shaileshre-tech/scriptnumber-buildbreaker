package com.myorg.buildbreaker.script.service;

import java.util.List;

/**
 * Created by Shailesh on 7/14/16.
 */
public interface ScriptLogService {

    List<Integer> getAllMergedScriptNumbersAcrossBranches(String logFile);
    List<Integer> getAllScriptNumbersFromBranch(String logFile, String branchName);
    Integer getLatestScriptNumberAcrossBranches(String logFile);
    Integer getLatestScriptNumberFromBranch(String logFile, String branchName);

}
