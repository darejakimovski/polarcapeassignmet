package com.polar.cape.assignment.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Ebook extends Book {

    @Enumerated(value = EnumType.STRING)
    private EbookFormat ebookFormat;
    private Double memory;

    public Ebook(){

    }

    public Ebook(String title, Integer yearOfRelease, Author author, EbookFormat ebookFormat, Double memory) {
        super(title, yearOfRelease, author);
        this.ebookFormat = ebookFormat;
        this.memory = memory;
    }

    public EbookFormat getFormat() {
        return ebookFormat;
    }

    public void setFormat(EbookFormat ebookFormat) {
        this.ebookFormat = ebookFormat;
    }

    public Double getMemory() {
        return memory;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return super.toString() + "as Ebook{" +
                "format=" + ebookFormat +
                ", memory=" + memory + "MB" +
                '}';
    }
}
