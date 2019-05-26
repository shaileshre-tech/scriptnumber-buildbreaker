package com.myorg.buildbreaker.script.service;


import com.myorg.plugin.script.entity.BranchScriptList;
import com.myorg.plugin.script.entity.PluginReference;

/**
 * Created by Shailesh on 7/15/16.
 */
public interface ScriptLogFileService {

    PluginReference getPluginReference(String logFile);
    BranchScriptList getScriptListByBranch(String logFile, String branchName);
    void saveorUpdateScriptListByBranch(String logFile, String branchName, String scriptListString);
}
