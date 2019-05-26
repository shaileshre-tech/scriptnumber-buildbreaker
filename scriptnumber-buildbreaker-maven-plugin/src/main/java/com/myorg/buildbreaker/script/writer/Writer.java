package com.myorg.buildbreaker.script.writer;

/**
 * Created by Shailesh on 7/13/16.
 */
public interface Writer<T, S> {

    void write(S s, T t);
}
