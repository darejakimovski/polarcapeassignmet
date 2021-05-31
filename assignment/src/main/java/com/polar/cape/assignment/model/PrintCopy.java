package com.polar.cape.assignment.model;

import javax.persistence.Entity;

@Entity
public class PrintCopy extends Book {

    private Integer numOfPages;
    private Double weightInKg;


    public PrintCopy(String title, Integer yearOfRelease, Author author, Integer numOfPages, Double weightInKg) {
        super(title, yearOfRelease, author);
        this.numOfPages = numOfPages;
        this.weightInKg = weightInKg;
    }

    public PrintCopy(){

    }

    public Integer getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(Integer numOfPages) {
        this.numOfPages = numOfPages;
    }

    public Double getWeightInKg() {
        return weightInKg;
    }

    public void setWeightInKg(Double weightInKg) {
        this.weightInKg = weightInKg;
    }

    @Override
    public String toString() {
        return super.toString() + "as PrintCopy{" +
                "numOfPages=" + numOfPages +
                ", weightInKg=" + weightInKg +
                '}';
    }
}
