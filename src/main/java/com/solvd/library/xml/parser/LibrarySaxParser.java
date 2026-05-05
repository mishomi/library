package com.solvd.library.xml.parser;

import com.solvd.library.xml.model.LibraryXml;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * SAX parser for the library XML.
 *
 * XPath examples:
 * /library/libraryName
 * /library/publisher/publisherName
 * /library/authors/author[1]/authorName
 * /library/customers/customer[@active='true']/customerName
 * /library/books/book[@id='1']/title
 */
public class LibrarySaxParser implements Parser<LibraryXml> {

    private static final Logger log = LogManager.getLogger(LibrarySaxParser.class);

    private final String xsdPath;

    public LibrarySaxParser(String xsdPath) {
        this.xsdPath = xsdPath;
    }

    @Override
    public LibraryXml parse(String xmlPath) throws Exception {
        XmlValidator.validate(xmlPath, xsdPath);

        var factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(false);

        var saxParser = factory.newSAXParser();
        var handler = new LibraryHandler();
        saxParser.parse(new File(xmlPath), handler);
        return handler.library;
    }

    private static final class LibraryHandler extends DefaultHandler {
        private final StringBuilder text = new StringBuilder();

        private final LibraryXml library = new LibraryXml();
        private LibraryXml.PublisherXml publisher;
        private LibraryXml.AuthorXml author;
        private LibraryXml.AuthorXml bookAuthor;
        private LibraryXml.CustomerXml customer;
        private LibraryXml.BookXml book;
        private LibraryXml.GenreXml genre;
        private boolean insideBook = false;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            text.setLength(0);

            switch (qName) {
                case "library" -> {
                    library.id = Integer.parseInt(attributes.getValue("id"));
                    library.open = Boolean.parseBoolean(attributes.getValue("open"));
                }
                case "publisher" -> {
                    publisher = new LibraryXml.PublisherXml();
                    publisher.country = attributes.getValue("country");
                }
                case "author" -> {
                    if (insideBook) {
                        bookAuthor = new LibraryXml.AuthorXml();
                        String id = attributes.getValue("id");
                        if (id != null) bookAuthor.id = Integer.parseInt(id);
                    } else {
                        author = new LibraryXml.AuthorXml();
                        String id = attributes.getValue("id");
                        if (id != null) author.id = Integer.parseInt(id);
                    }
                }
                case "customer" -> {
                    customer = new LibraryXml.CustomerXml();
                    customer.id = Integer.parseInt(attributes.getValue("id"));
                    customer.active = Boolean.parseBoolean(attributes.getValue("active"));
                }
                case "book" -> {
                    book = new LibraryXml.BookXml();
                    book.id = Integer.parseInt(attributes.getValue("id"));
                    insideBook = true;
                }
                case "genre" -> genre = new LibraryXml.GenreXml();
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            text.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            String value = text.toString().trim();

            switch (qName) {
                case "libraryName" -> library.libraryName = value;
                case "createdAt" -> library.createdAt = LocalDateTime.parse(value);
                case "publisherName" -> publisher.publisherName = value;
                case "foundedYear" -> publisher.foundedYear = Integer.parseInt(value);

                case "authorName" -> {
                    if (insideBook) bookAuthor.authorName = value;
                    else author.authorName = value;
                }
                case "nationality" -> {
                    if (insideBook) bookAuthor.nationality = value;
                    else author.nationality = value;
                }

                case "customerName" -> customer.customerName = value;
                case "age" -> customer.age = Integer.parseInt(value);
                case "registeredAt" -> customer.registeredAt = LocalDateTime.parse(value);

                case "title" -> book.title = value;
                case "pages" -> book.pages = Integer.parseInt(value);
                case "price" -> book.price = new BigDecimal(value);
                case "available" -> book.available = Boolean.parseBoolean(value);
                case "publishedAt" -> book.publishedAt = LocalDateTime.parse(value);

                case "genreName" -> genre.genreName = value;

                case "author" -> {
                    if (insideBook) {
                        book.author = bookAuthor;
                        bookAuthor = null;
                    } else {
                        library.authors.add(author);
                        author = null;
                    }
                }
                case "customer" -> {
                    library.customers.add(customer);
                    customer = null;
                }
                case "publisher" -> {
                    library.publisher = publisher;
                    publisher = null;
                }
                case "genre" -> book.genre = genre;
                case "book" -> {
                    library.books.add(book);
                    book = null;
                    genre = null;
                    insideBook = false;
                }
            }
        }
    }
}