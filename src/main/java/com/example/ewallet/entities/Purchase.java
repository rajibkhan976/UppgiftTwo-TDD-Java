package com.example.ewallet.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private LocalDate startdate;
    private LocalDate enddate;
    private double amount;
    private String comment;
    private Integer categoryid;

    public Purchase () {}

    public Purchase (Integer id, LocalDate startdate, LocalDate enddate, double amount, String comment, Integer categoryid) {
        this.id = id;
        this.startdate = startdate;
        this.enddate = enddate;
        this.amount = amount;
        this.comment = comment;
        this.categoryid = categoryid;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartdate() {
        return this.startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getEnddate() {
        return this.enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCategoryid() {
        return this.categoryid;
    }

    public void setCategoryid(Integer category_id) {
        this.categoryid = categoryid;
    }
}
