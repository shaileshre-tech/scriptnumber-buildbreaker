package com.myorg.buildbreaker.script.service;

import java.util.List;

/**
 * Created by Shailesh on 7/14/16.
 */
public interface ScriptDirectoryService {

    String getAllScriptNumbersAsString(String dirPath);
    List<Integer> getAllScriptNumbers(String dirPath);
    Integer getLatestScriptNumber(String dirPath);
}
