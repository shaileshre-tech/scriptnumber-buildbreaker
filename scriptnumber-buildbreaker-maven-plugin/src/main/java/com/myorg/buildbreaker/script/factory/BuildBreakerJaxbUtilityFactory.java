package com.myorg.buildbreaker.script.factory;


import com.myorg.buildbreaker.script.schema.BuildBreakerJaxbUtility;
import com.myorg.plugin.script.entity.PluginReference;

/**
 * Created by Shailesh on 7/14/16.
 */
public class BuildBreakerJaxbUtilityFactory {

    private static final String ENTITY_PACKAGE = "com.ga.endeavour.buildbreaker.script.entity";
    private static final String ENTITY_SCHEMA = "schema/scriptnumber-log-format.xsd";

    private static final BuildBreakerJaxbUtility<PluginReference> JAXB_UTILITY = new BuildBreakerJaxbUtility<>(ENTITY_PACKAGE, ENTITY_SCHEMA);

    public static BuildBreakerJaxbUtility<PluginReference> getBuildBreakerJaxbUtilityInstance(){
        return JAXB_UTILITY;
    }
}
