package com.myorg.buildbreaker.script.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Shailesh on 7/13/16.
 */
public class SingleOperationShellExecutor implements ShellExecutor<String, String> {

    @Override
    public String execute(String singleShellOperationScriptLocation) {
        BufferedReader reader = null;

        try {
            Process process = Runtime.getRuntime().exec(singleShellOperationScriptLocation);
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String output = reader.readLine();

            process.waitFor();

            return output;
        } catch (IOException ioe){
            ioe.printStackTrace();
        } catch (InterruptedException ie){
            ie.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ioe){
                ioe.printStackTrace();
            }
        }

        return null;
    }
}
