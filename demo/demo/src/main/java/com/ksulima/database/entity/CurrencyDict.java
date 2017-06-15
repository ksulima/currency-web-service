package com.ksulima.database.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Krzysztof Sulima on 15.06.2017.
 */

@Entity
@Table(name = "CURRENCY_DICT")
public class CurrencyDict {


    @Id
    @GeneratedValue
    @Column(name = "DICT_ID")
    private Long dictId;


    @Column(name = "BASE_CODE")
    private String baseCode;

    private String name;

    @OneToMany(mappedBy = "currencyDictId", cascade = CascadeType.ALL)
    private Set<CurrencyRates> currencyDictIdSet;

    @OneToMany(mappedBy = "currencyDictBaseCode", cascade = CascadeType.ALL)
    private Set<CurrencyRates> currencyDictBaseCodeSet;

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "currencyDictId")
    private Set<CurrencyRates> currencyDictId;

    @OneToMany(mappedBy = "currencyDictBaseCode")
    private Set<CurrencyRates> currencyDictBaseCode;

}
