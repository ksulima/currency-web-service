package com.ksulima.database.entity;

import javax.persistence.*;


/**
 * Created by Krzysztof Sulima on 03.04.2017.
 */

@Entity
@Table(name = "currency_rates")
public class Currency {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String date;

    @Column
    private String base;

    @Column
    private String currencyOut;

    @Column
    private String rate;
}
