package com.myorg.buildbreaker.script.pojo;

/**
 * Created by Shailesh on 7/12/16.
 */
public class ValidationConfiguration {

    private final String branchName;
    private final String scriptNumberLogLocation;
    private final String migrationScriptDirectory;
    private final boolean skipLogFileWrite;

    public ValidationConfiguration(
            String branchName,
            String scriptNumberLogLocation,
            String migrationScriptDirectory,
            boolean skipLogFileWrite) {

        this.branchName = branchName;
        this.scriptNumberLogLocation = scriptNumberLogLocation;
        this.migrationScriptDirectory = migrationScriptDirectory;
        this.skipLogFileWrite = skipLogFileWrite;
    }

    public String getBranchName() {
        return branchName;
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
