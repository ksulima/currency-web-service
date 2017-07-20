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

}
