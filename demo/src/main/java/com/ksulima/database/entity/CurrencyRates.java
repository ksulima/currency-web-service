package com.ksulima.database.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Krzysztof Sulima on 15.06.2017.
 */
@Getter
@Setter
@Entity
@Table(name = "CURRENCY_RATES")
public class CurrencyRates implements Serializable {

    @EmbeddedId
    private RatesPk ratesPk;

    private String rate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns(
            {@JoinColumn(name = "DICT_ID_FK", referencedColumnName = "DICT_ID"),
            @JoinColumn(name = "BASE_CODE_FK", referencedColumnName = "BASE_CODE")})
    private CurrencyDict currencyDict;


    public CurrencyRates() {
        RatesPk ratesPk = new RatesPk();
        this.setRatesPk(ratesPk);
    }


}
