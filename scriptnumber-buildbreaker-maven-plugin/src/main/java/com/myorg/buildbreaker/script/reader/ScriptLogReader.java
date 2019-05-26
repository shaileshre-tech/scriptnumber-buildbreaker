package com.myorg.buildbreaker.script.reader;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.myorg.buildbreaker.script.exception.BuildBreakerJaxbException;
import com.myorg.buildbreaker.script.factory.BuildBreakerJaxbUtilityFactory;
import com.myorg.buildbreaker.script.schema.BuildBreakerJaxbUtility;
import com.myorg.plugin.script.entity.PluginReference;

/**
 * Created by Shailesh on 7/13/16.
 */
public class ScriptLogReader implements Reader<PluginReference, String> {

    private final BuildBreakerJaxbUtility<PluginReference> jaxbUtility = BuildBreakerJaxbUtilityFactory.getBuildBreakerJaxbUtilityInstance();

    private final Map<String, PluginReference> xmlFileToReferenceMap = new ConcurrentHashMap<>();

    @Override
    public PluginReference read(String xmlFile) {
        if(!xmlFileToReferenceMap.containsKey(xmlFile)){
            try {
                PluginReference pluginReference = jaxbUtility.unMarshal(xmlFile);

                xmlFileToReferenceMap.put(xmlFile, pluginReference);
            } catch (BuildBreakerJaxbException bbje){
                return null;
            }
        }

        return xmlFileToReferenceMap.get(xmlFile);
    }
}
