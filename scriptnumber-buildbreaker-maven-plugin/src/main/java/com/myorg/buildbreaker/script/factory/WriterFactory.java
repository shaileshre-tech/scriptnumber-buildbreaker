package com.myorg.buildbreaker.script.factory;


import com.myorg.buildbreaker.script.writer.ScriptLogWriter;
import com.myorg.buildbreaker.script.writer.Writer;
import com.myorg.plugin.script.entity.PluginReference;

/**
 * Created by Shailesh on 7/14/16.
 */
public class WriterFactory {

    private static final Writer<PluginReference, String> SCRIPT_LOG_WRITER = new ScriptLogWriter();

    public static Writer<PluginReference, String> getScriptLogWriterInstance() {
        return SCRIPT_LOG_WRITER;
    }
}
