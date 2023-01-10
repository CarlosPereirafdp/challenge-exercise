package com.xpand.challenge.dto;

import com.xpand.challenge.model.Actor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class MovieDTO {

    private String title;
    private LocalDate date;
    private Float rank;
    private BigDecimal revenue;
    private List<Actor> actors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Float getRank() {
        return rank;
    }

    public void setRank(Float rank) {
        this.rank = rank;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

}
