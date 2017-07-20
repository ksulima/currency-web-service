package com.ksulima.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

/**
 * Created by Krzysztof Sulima on 16.06.2017.
 */

@Getter
@Setter
@Embeddable
public class RatesPk implements Serializable {


    @GeneratedValue
    @Column(name = "CURRENCY_ID")
    protected Long currencyId;

    protected String date;

    protected String currency;

    public RatesPk() {
    }

    public RatesPk(String currency) {
        this.currency = currency;
    }

    public RatesPk(String date, String currency) {
        this.date = date;
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatesPk)) return false;

        RatesPk that = (RatesPk) o;

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

}
