package com.ksulima.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Krzysztof Sulima on 15.06.2017.
 */

@Entity(name = "CurrencyDict")
@Table(name = "CURRENCY_DICT")
public class CurrencyDict implements Serializable {

    @EmbeddedId
    private DictPk dictPk;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "currencyDict", cascade = CascadeType.ALL)
    private Set<CurrencyRates> currencyRates;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DictPk getDictPk() {
        return dictPk;
    }

    public void setDictPk(DictPk dictPk) {
        this.dictPk = dictPk;
    }

    public Set<CurrencyRates> getCurrencyRates() {
        return currencyRates;
    }

    public void setCurrencyRates(Set<CurrencyRates> currencyRates) {
        this.currencyRates = currencyRates;
    }

}
