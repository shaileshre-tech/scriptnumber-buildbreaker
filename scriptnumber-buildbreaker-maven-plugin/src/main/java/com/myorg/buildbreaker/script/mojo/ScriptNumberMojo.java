package com.myorg.buildbreaker.script.mojo;

import com.myorg.buildbreaker.script.manager.ScriptNumberManager;
import com.myorg.buildbreaker.script.pojo.PluginConfiguration;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Created by Shailesh on 6/15/16.
 */
@Mojo(name="validate")
public class ScriptNumberMojo extends AbstractMojo {

    @Parameter(required = true)
    private String preValidationScriptLocation;

    @Parameter(required = true)
    private String postValidationScriptLocation;

    @Parameter(required = true)
    private String branchNameScriptLocation;

    @Parameter(required = true)
    private String scriptNumberLogLocation;

    @Parameter(required = true)
    private String migrationScriptDirectory;

    @Parameter
    private boolean skipLogFileWrite = true;

    @Parameter
    private boolean skipValidation;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("----------------Hello, world----------------");
        System.out.println("preValidationScriptLocation = "+preValidationScriptLocation);
        System.out.println("postValidationScriptLocation = "+postValidationScriptLocation);
        System.out.println("branchNameScriptLocation = "+branchNameScriptLocation);
        System.out.println("scriptNumberLogLocation = "+ scriptNumberLogLocation);
        System.out.println("migrationScriptDirectory = "+ migrationScriptDirectory);
        System.out.println("skipLogFileWrite = "+ skipLogFileWrite);
        System.out.println("skipValidation = "+ skipValidation);
        System.out.println("----------------Hello, world----------------");

        if(skipValidation) {
            return;
        }

        ScriptNumberManager manager = new ScriptNumberManager(new PluginConfiguration(
                preValidationScriptLocation,
                postValidationScriptLocation,
                branchNameScriptLocation,
                scriptNumberLogLocation,
                migrationScriptDirectory,
                skipLogFileWrite));

        manager.manage();
    }

    public void setPreValidationScriptLocation(String preValidationScriptLocation) {
        this.preValidationScriptLocation = preValidationScriptLocation;
    }

    public void setPostValidationScriptLocation(String postValidationScriptLocation) {
        this.postValidationScriptLocation = postValidationScriptLocation;
    }

    public void setBranchNameScriptLocation(String branchNameScriptLocation) {
        this.branchNameScriptLocation = branchNameScriptLocation;
    }

    public void setScriptNumberLogLocation(String scriptNumberLogLocation) {
        this.scriptNumberLogLocation = scriptNumberLogLocation;
    }

    public void setMigrationScriptDirectory(String migrationScriptDirectory) {
        this.migrationScriptDirectory = migrationScriptDirectory;
    }

    public void setSkipLogFileWrite(boolean skipLogFileWrite) {
        this.skipLogFileWrite = skipLogFileWrite;
    }

    public void setSkipValidation(boolean skipValidation) {
        this.skipValidation = skipValidation;
    }
}
