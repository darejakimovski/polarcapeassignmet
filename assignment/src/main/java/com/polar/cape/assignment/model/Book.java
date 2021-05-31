package com.polar.cape.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance
public abstract class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ISBN;
    private String title;


    private Author author;
    private Integer yearOfRelease;

    public Book() {

    }


    public Book(String title, Integer yearOfRelease, Author author) {
        this.yearOfRelease = yearOfRelease;
        this.title = title;
        this.author = author;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getISBN() {
        return ISBN;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "{" +
                "title='" + title + '\'' +
                ", ISBN=" + ISBN +
                ", author=" + author +
                ", yearOfRelease=" + yearOfRelease +
                '}';
    }
}
