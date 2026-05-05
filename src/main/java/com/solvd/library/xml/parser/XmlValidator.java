package com.solvd.library.xml.parser;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public final class XmlValidator {

    private XmlValidator() {
    }

    public static void validate(String xmlPath, String xsdPath) throws Exception {
        var schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        var schema = schemaFactory.newSchema(new File(xsdPath));
        var validator = schema.newValidator();
        validator.validate(new StreamSource(new File(xmlPath)));
    }
}