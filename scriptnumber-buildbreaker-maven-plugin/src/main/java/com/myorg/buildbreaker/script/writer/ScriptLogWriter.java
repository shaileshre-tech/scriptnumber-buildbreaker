package com.myorg.buildbreaker.script.writer;

import com.myorg.buildbreaker.script.factory.BuildBreakerJaxbUtilityFactory;
import com.myorg.buildbreaker.script.schema.BuildBreakerJaxbUtility;
import com.myorg.plugin.script.entity.PluginReference;

/**
 * Created by Shailesh on 7/13/16.
 */
public class ScriptLogWriter implements Writer<PluginReference, String> {

    private final BuildBreakerJaxbUtility<PluginReference> jaxbUtility = BuildBreakerJaxbUtilityFactory.getBuildBreakerJaxbUtilityInstance();

    @Override
    public void write(String xmlFile, PluginReference pluginReference) {

        jaxbUtility.marshal(pluginReference, xmlFile);
    }
}
