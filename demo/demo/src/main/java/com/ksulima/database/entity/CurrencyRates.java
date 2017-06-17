package com.ksulima.database.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Krzysztof Sulima on 15.06.2017.
 */
@Entity
@Table(name = "CURRENCY_RATES")
public class CurrencyRates implements Serializable {

    @EmbeddedId
    private RatesPK ratesPK;

    private String rate;

    @ManyToOne
    @JoinColumns(
            {@JoinColumn(name = "DICT_ID_FK", referencedColumnName = "DICT_ID"),
            @JoinColumn(name = "BASE_CODE_FK", referencedColumnName = "BASE_CODE")})
    private CurrencyDict currencyDict;


    public RatesPK getRatesPK() {
        return ratesPK;
    }

    public void setRatesPK(RatesPK ratesPK) {
        this.ratesPK = ratesPK;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public CurrencyDict getCurrencyDict() {
        return currencyDict;
    }

    public void setCurrencyDict(CurrencyDict currencyDict) {
        this.currencyDict = currencyDict;
    }



}
