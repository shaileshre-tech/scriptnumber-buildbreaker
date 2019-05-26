package com.myorg.buildbreaker.script.shell;

import java.io.IOException;

/**
 * Created by Shailesh on 7/12/16.
 */
public class DefaultShellExecutor implements ShellExecutor<Void, String> {

    @Override
    public Void execute(String shellScriptLocation) {
        System.out.println("shellScriptLocation = "+shellScriptLocation);

        try {
            Process process = Runtime.getRuntime().exec(shellScriptLocation);

            process.waitFor();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        return null;
    }
}
