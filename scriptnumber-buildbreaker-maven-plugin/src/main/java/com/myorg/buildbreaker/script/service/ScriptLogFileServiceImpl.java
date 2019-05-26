package com.myorg.buildbreaker.script.service;

import com.myorg.buildbreaker.script.factory.ReaderFactory;
import com.myorg.plugin.script.entity.AllBranchScriptList;
import com.myorg.plugin.script.entity.BranchScriptList;
import com.myorg.plugin.script.entity.PluginReference;

import static com.myorg.buildbreaker.script.factory.WriterFactory.getScriptLogWriterInstance;

/**
 * Created by Shailesh on 7/15/16.
 */
public class ScriptLogFileServiceImpl implements ScriptLogFileService {

    @Override
    public PluginReference getPluginReference(String logFile) {
        return ReaderFactory.getScriptLogReaderInstance().read(logFile);
    }

    @Override
    public BranchScriptList getScriptListByBranch(String logFile, String branchName) {
        PluginReference pluginReference = getPluginReference(logFile);

        for(BranchScriptList branchScriptList : pluginReference.getAllBranchScriptList().getBranchScriptList()){
            if(branchName.equalsIgnoreCase(branchScriptList.getBranch())){
                return branchScriptList;
            }
        }

        return null;
    }

    @Override
    public void saveorUpdateScriptListByBranch(String logFile, String branchName, String scriptListString) {

        PluginReference pluginReference = getPluginReference(logFile);

        BranchScriptList branchScriptList;

        if(pluginReference == null){
            pluginReference = new PluginReference();

            branchScriptList = new BranchScriptList();
            branchScriptList.setBranch(branchName);

            AllBranchScriptList allBranchScriptList = new AllBranchScriptList();
            allBranchScriptList.getBranchScriptList().add(branchScriptList);

            pluginReference.setAllBranchScriptList(allBranchScriptList);
        } else {
            branchScriptList = getScriptListByBranch(logFile, branchName);

            if(branchScriptList == null){
                branchScriptList = new BranchScriptList();
                branchScriptList.setBranch(branchName);

                pluginReference.getAllBranchScriptList().getBranchScriptList().add(branchScriptList);
            }
        }

        branchScriptList.setAllScriptNumberList(scriptListString);

        getScriptLogWriterInstance().write(logFile, pluginReference);
    }
}
