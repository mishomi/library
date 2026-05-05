package com.solvd.library.xml.model;

import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "library")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryXml {

    @XmlAttribute
    public int id;

    @XmlAttribute
    public boolean open;

    @XmlElement
    public String libraryName;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlElement
    public LocalDateTime createdAt;

    @XmlElement
    public PublisherXml publisher;

    @XmlElementWrapper(name = "authors")
    @XmlElement(name = "author")
    public List<AuthorXml> authors = new ArrayList<>();

    @XmlElementWrapper(name = "customers")
    @XmlElement(name = "customer")
    public List<CustomerXml> customers = new ArrayList<>();

    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    public List<BookXml> books = new ArrayList<>();

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class PublisherXml {
        @XmlAttribute
        public String country;

        @XmlElement
        public String publisherName;

        @XmlElement
        public int foundedYear;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class AuthorXml {
        @XmlAttribute
        public int id;

        @XmlElement
        public String authorName;

        @XmlElement
        public String nationality;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CustomerXml {
        @XmlAttribute
        public int id;

        @XmlAttribute
        public boolean active;

        @XmlElement
        public String customerName;

        @XmlElement
        public int age;

        @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
        @XmlElement
        public LocalDateTime registeredAt;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class GenreXml {
        @XmlElement
        public String genreName;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class BookXml {
        @XmlAttribute
        public int id;

        @XmlElement
        public String title;

        @XmlElement
        public int pages;

        @XmlElement
        public BigDecimal price;

        @XmlElement
        public boolean available;

        @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
        @XmlElement
        public LocalDateTime publishedAt;

        @XmlElement
        public AuthorXml author;

        @XmlElement
        public GenreXml genre;
    }
}