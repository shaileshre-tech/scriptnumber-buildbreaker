package com.myorg.buildbreaker.script.pojo;

/**
 * Created by Shailesh on 7/12/16.
 */
public class PluginConfiguration {

    private final String preValidationScriptLocation;
    private final String postValidationScriptLocation;
    private final String branchNameScriptLocation;
    private final String scriptNumberLogLocation;
    private final String migrationScriptDirectory;
    private final boolean skipLogFileWrite;

    public PluginConfiguration(
            String preValidationScriptLocation,
            String postValidationScriptLocation,
            String branchNameScriptLocation,
            String scriptNumberLogLocation,
            String migrationScriptDirectory,
            boolean skipLogFileWrite) {

        this.preValidationScriptLocation = preValidationScriptLocation;
        this.postValidationScriptLocation = postValidationScriptLocation;
        this.branchNameScriptLocation = branchNameScriptLocation;
        this.scriptNumberLogLocation = scriptNumberLogLocation;
        this.migrationScriptDirectory = migrationScriptDirectory;
        this.skipLogFileWrite = skipLogFileWrite;
    }

    public String getPreValidationScriptLocation() {
        return preValidationScriptLocation;
    }

    public String getPostValidationScriptLocation() {
        return postValidationScriptLocation;
    }

    public String getBranchNameScriptLocation() {
        return branchNameScriptLocation;
    }

    public String getScriptNumberLogLocation() {
        return scriptNumberLogLocation;
    }

    public String getMigrationScriptDirectory() {
        return migrationScriptDirectory;
    }

    public boolean isSkipLogFileWrite() {
        return skipLogFileWrite;
    }
}
