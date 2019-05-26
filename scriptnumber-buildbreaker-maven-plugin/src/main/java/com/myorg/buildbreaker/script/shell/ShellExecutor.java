package com.myorg.buildbreaker.script.shell;

/**
 * Created by Shailesh on 7/11/16.
 */
public interface ShellExecutor<T, S> {

    T execute(S s);
}
