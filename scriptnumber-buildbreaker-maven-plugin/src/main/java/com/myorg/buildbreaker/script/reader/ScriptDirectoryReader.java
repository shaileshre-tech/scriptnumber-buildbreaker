package com.myorg.buildbreaker.script.reader;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.base.Function;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.transform;
import static com.google.common.collect.Sets.newHashSet;
import static java.lang.Integer.valueOf;
import static java.util.Arrays.asList;
import static java.util.Collections.sort;
import static java.util.Collections.unmodifiableList;

/**
 * Created by Shailesh on 7/13/16.
 */
public class ScriptDirectoryReader implements Reader<List<Integer>, String>{

    private static final String SQL_SCRIPT_EXTENSION = ".sql";

    private static final Function<File, Integer> SCRIPT_NUMBER_FUNCTION = new Function<File, Integer>() {
        @Override
        public Integer apply(File input) {
            return valueOf(input.getName().split("_")[0]);
        }
    };

    private final Map<String, List<Integer>> directoryToSetMap = new ConcurrentHashMap<>();

    @Override
    public List<Integer> read(String dirPath) {
        if(!directoryToSetMap.containsKey(dirPath)) {
            File dir = new File(dirPath);

            File[] fileList = dir.listFiles(new FilenameFilter() {

                public boolean accept(File dir, String name) {
                    return name.endsWith(SQL_SCRIPT_EXTENSION);
                }
            });

            List<Integer> transformedScriptNumberList = transform(asList(fileList), SCRIPT_NUMBER_FUNCTION);
            List<Integer> scriptNumberList = newArrayList(newHashSet(transformedScriptNumberList));

            sort(scriptNumberList);

            directoryToSetMap.put(dirPath, unmodifiableList(scriptNumberList));
        }

        return directoryToSetMap.get(dirPath);
    }
}
