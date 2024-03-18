package com.example.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class BorrowingRecord {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("Borrower")
    private String borrower;
    @JsonProperty("Book")
    private String book;
    @JsonProperty("borrowed from")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate borrowedFrom;
    @JsonProperty("borrowed to")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate borrowedTo;

    public String getBorrower() {
        return borrower;
    }

    public String getBook() {
        return book;
    }

    public LocalDate getBorrowedFrom() {
        return borrowedFrom;
    }

    public LocalDate getBorrowedTo() {
        return borrowedTo;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setBorrowedFrom(LocalDate borrowedFrom) {
        this.borrowedFrom = borrowedFrom;
    }

    public void setBorrowedTo(LocalDate borrowedTo) {
        this.borrowedTo = borrowedTo;
    }

    @Override
    public String toString() {
        return "BorrowingRecord{" +
                "borrower='" + borrower + '\'' +
                ", book='" + book + '\'' +
                ", borrowedFrom='" + borrowedFrom + '\'' +
                ", borrowedTo='" + borrowedTo + '\'' +
                '}';
    }
}
