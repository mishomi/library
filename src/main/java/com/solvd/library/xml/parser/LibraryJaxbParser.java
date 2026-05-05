package com.solvd.library.xml.parser;

import com.solvd.library.xml.model.LibraryXml;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;

public class LibraryJaxbParser implements Parser<LibraryXml> {

    @Override
    public LibraryXml parse(String path) throws Exception {
        JAXBContext context = JAXBContext.newInstance(LibraryXml.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (LibraryXml) unmarshaller.unmarshal(new File(path));
    }
}