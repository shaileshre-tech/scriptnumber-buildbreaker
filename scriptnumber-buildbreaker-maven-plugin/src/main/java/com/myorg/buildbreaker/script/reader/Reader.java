package com.myorg.buildbreaker.script.reader;

/**
 * Created by Shailesh on 7/13/16.
 */
public interface Reader<T, S> {

    T read(S s);
}
