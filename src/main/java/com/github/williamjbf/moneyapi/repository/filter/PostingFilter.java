package com.github.williamjbf.moneyapi.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PostingFilter {

    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDueDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate untilDueDate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getFromDueDate() {
        return fromDueDate;
    }

    public void setFromDueDate(LocalDate fromDueDate) {
        this.fromDueDate = fromDueDate;
    }

    public LocalDate getUntilDueDate() {
        return untilDueDate;
    }

    public void setUntilDueDate(LocalDate untilDueDate) {
        this.untilDueDate = untilDueDate;
    }
}
