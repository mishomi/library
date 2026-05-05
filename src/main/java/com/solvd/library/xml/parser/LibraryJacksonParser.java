package com.solvd.library.xml.parser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solvd.library.xml.model.LibraryXml;

import java.io.File;

public class LibraryJacksonParser implements Parser<LibraryXml> {

    @Override
    public LibraryXml parse(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.readValue(new File(path), LibraryXml.class);
    }
}