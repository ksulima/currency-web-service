package com.ksulima.database.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/**
 * Created by Krzysztof Sulima on 03.04.2017.
 */
@Getter
@Setter
@Data
@Entity
@Table(name = "currencyarchive")
public class MyCurrency {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String date;

    @Column
    private String base;

    @Column
    private String currency;

    @Column
    private String rate;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
