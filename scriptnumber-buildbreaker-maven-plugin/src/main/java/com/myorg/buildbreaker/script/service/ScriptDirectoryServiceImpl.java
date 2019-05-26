package com.myorg.buildbreaker.script.service;

import java.util.List;

import com.myorg.buildbreaker.script.factory.ReaderFactory;

/**
 * Created by Shailesh on 7/14/16.
 */
public class ScriptDirectoryServiceImpl implements ScriptDirectoryService {

    @Override
    public String getAllScriptNumbersAsString(String dirPath) {
        List<Integer> scriptNumberList = getAllScriptNumbers(dirPath);

        String scriptNumberListString = scriptNumberList.toString();

        return scriptNumberListString!=null ? scriptNumberListString.substring(1, scriptNumberListString.length() - 1) : "";
    }

    @Override
    public List<Integer> getAllScriptNumbers(String dirPath) {
        return ReaderFactory.getScriptDirectoryReaderInstance().read(dirPath);
    }

    @Override
    public Integer getLatestScriptNumber(String dirPath) {
        List<Integer> allScriptNumberList = ReaderFactory.getScriptDirectoryReaderInstance().read(dirPath);

        return allScriptNumberList != null ? allScriptNumberList.get(allScriptNumberList.size() - 1) : null;
    }
}
