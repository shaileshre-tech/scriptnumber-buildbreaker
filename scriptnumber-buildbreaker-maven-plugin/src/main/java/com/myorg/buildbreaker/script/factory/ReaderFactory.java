package com.myorg.buildbreaker.script.factory;

import java.util.List;

import com.myorg.buildbreaker.script.reader.Reader;
import com.myorg.buildbreaker.script.reader.ScriptDirectoryReader;
import com.myorg.buildbreaker.script.reader.ScriptLogReader;
import com.myorg.plugin.script.entity.PluginReference;

/**
 * Created by Shailesh on 7/14/16.
 */
public class ReaderFactory {

    private static final Reader<List<Integer>, String> SCRIPT_DIRECTORY_READER = new ScriptDirectoryReader();
    private static final Reader<PluginReference, String> SCRIPT_LOG_READER = new ScriptLogReader();

    public static Reader<List<Integer>, String> getScriptDirectoryReaderInstance() {
        return SCRIPT_DIRECTORY_READER;
    }

    public static Reader<PluginReference, String> getScriptLogReaderInstance() {
        return SCRIPT_LOG_READER;
    }
}
