package com.solvd.library.xml.parser;

public interface Parser<T> {
    T parse(String path) throws Exception;
}