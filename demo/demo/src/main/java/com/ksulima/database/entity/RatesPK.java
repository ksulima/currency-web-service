package com.ksulima.database.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Krzysztof Sulima on 16.06.2017.
 */
@Embeddable
public class RatesPK implements Serializable {


    @GeneratedValue
    @Column(name = "CURRENCY_ID")
    protected Long currencyId;

    @Temporal(TemporalType.DATE)
    protected Date date;

    protected String currency;

    public RatesPK() {
    }

    public RatesPK(String currency) {
        this.currency = currency;
    }

    public RatesPK(Date date, String currency) {
        this.date = date;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatesPK)) return false;

        RatesPK that = (RatesPK) o;

        if (currencyId != null ? !currencyId.equals(that.currencyId) : that.currencyId != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        return currency != null ? currency.equals(that.currency) : that.currency == null;
    }

    @Override
    public int hashCode() {
        int result = currencyId != null ? currencyId.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
