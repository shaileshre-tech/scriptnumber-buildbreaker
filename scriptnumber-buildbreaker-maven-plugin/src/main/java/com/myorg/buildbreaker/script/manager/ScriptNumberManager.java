package com.myorg.buildbreaker.script.manager;

import com.myorg.buildbreaker.script.handler.DefaultValidationHandler;
import com.myorg.buildbreaker.script.handler.ValidationHandler;
import com.myorg.buildbreaker.script.pojo.PluginConfiguration;
import com.myorg.buildbreaker.script.pojo.ValidationConfiguration;
import com.myorg.buildbreaker.script.shell.DefaultShellExecutor;
import com.myorg.buildbreaker.script.shell.ShellExecutor;
import com.myorg.buildbreaker.script.shell.SingleOperationShellExecutor;

/**
 * Created by Shailesh on 7/12/16.
 */
public class ScriptNumberManager {

    private final ShellExecutor<Void, String> defaultShellOperation = new DefaultShellExecutor();

    private final PluginConfiguration configuration;
    private final ValidationHandler validationHandler;

    public ScriptNumberManager(PluginConfiguration configuration) {
        this.configuration = configuration;

        String branchName = new SingleOperationShellExecutor().execute(configuration.getBranchNameScriptLocation());
        System.out.println("branchName script location = "+configuration.getBranchNameScriptLocation());
        System.out.println("branchName = "+branchName);

        validationHandler = new DefaultValidationHandler(
                new ValidationConfiguration(
                        branchName,
                        configuration.getScriptNumberLogLocation(),
                        configuration.getMigrationScriptDirectory(),
                        configuration.isSkipLogFileWrite()
                ));
    }

    public void manage() {

        defaultShellOperation.execute(configuration.getPreValidationScriptLocation());

        validationHandler.handle();

        if(!configuration.isSkipLogFileWrite()) {
            //defaultShellOperation.execute(configuration.getPostValidationScriptLocation());
        }
    }
}
