package com.myorg.buildbreaker.script.service;

import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.myorg.buildbreaker.script.factory.ServiceFactory;
import com.myorg.plugin.script.entity.BranchScriptList;
import com.myorg.plugin.script.entity.PluginReference;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;
import static com.google.common.collect.Sets.newHashSet;
import static java.lang.Integer.valueOf;
import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static java.util.Collections.unmodifiableList;

/**
 * Created by Shailesh on 7/14/16.
 */
public class ScriptLogServiceImpl implements ScriptLogService {

    private static final Function<String, Integer> SCRIPT_NUMBER_LIST_FUNCTION = new Function<String, Integer>() {
        @Override
        public Integer apply(String input) {
            return (!input.trim().equalsIgnoreCase("") ? valueOf(input.trim()) : -1);
        }
    };

    @Override
    public List<Integer> getAllMergedScriptNumbersAcrossBranches(String logFile) {
        Set<Integer> allMergedScriptNumberSet = newHashSet();

        PluginReference pluginReference = ServiceFactory.getScriptLogFileServiceInstance().getPluginReference(logFile);

        for(BranchScriptList branchScriptList : pluginReference.getAllBranchScriptList().getBranchScriptList()){
            if(branchScriptList != null) {
                allMergedScriptNumberSet.addAll(
                        transform(
                                asList(
                                        branchScriptList.getAllScriptNumberList().split(",")),
                                SCRIPT_NUMBER_LIST_FUNCTION));
            }
        }

        List<Integer> allMergedScriptNumberList = newArrayList(allMergedScriptNumberSet);

        sort(allMergedScriptNumberList);

        return unmodifiableList(allMergedScriptNumberList);
    }

    @Override
    public List<Integer> getAllScriptNumbersFromBranch(String logFile, String branchName) {
        BranchScriptList branchScriptList =
                ServiceFactory.getScriptLogFileServiceInstance()
                        .getScriptListByBranch(logFile, branchName);

        if(branchScriptList != null) {
            return transform(
                    asList(
                            branchScriptList.getAllScriptNumberList().split(",")),
                    SCRIPT_NUMBER_LIST_FUNCTION);
        }

        return unmodifiableList(Lists.<Integer>newArrayList());
    }

    @Override
    public Integer getLatestScriptNumberAcrossBranches(String logFile) {
        List<Integer> allMergedScriptNumberList = getAllMergedScriptNumbersAcrossBranches(logFile);

        return allMergedScriptNumberList.size() > 0 ? allMergedScriptNumberList.get(allMergedScriptNumberList.size() - 1) : -1;
    }

    @Override
    public Integer getLatestScriptNumberFromBranch(String logFile, String branchName) {
        List<Integer> allScriptNumberList = getAllScriptNumbersFromBranch(logFile, branchName);

        return allScriptNumberList.size() > 0 ? allScriptNumberList.get(allScriptNumberList.size() - 1) : -1;
    }
}