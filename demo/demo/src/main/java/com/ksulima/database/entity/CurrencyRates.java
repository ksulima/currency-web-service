package com.ksulima.database.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Krzysztof Sulima on 15.06.2017.
 */
@Entity
@Table(name = "CURRENCY_RATES")
public class CurrencyRates {

    @Id
    @GeneratedValue
    @Column(name = "CURRENCY_ID")
    private Long currencyId;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "DICT_ID_RATES")
    private Long dictId;

    @Column(name = "BASE_CODE_RATES")
    private String baseCode;

    private String currency;
    private String rate;

    @ManyToOne
    @JoinColumn(name = "DICT_ID")
    private CurrencyDict currencyDictId;


    @ManyToOne
    @JoinColumn(name = "BASE_CODE")
    private CurrencyDict currencyDictBaseCode;

}
