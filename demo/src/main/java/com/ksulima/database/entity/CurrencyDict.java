package com.ksulima.database.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Krzysztof Sulima on 15.06.2017.
 */
@Getter
@Setter
@Entity
@Table(name = "CURRENCY_DICT")
public class CurrencyDict implements Serializable {

    @EmbeddedId
    private DictPk dictPk;

    private String name;


    @OneToMany(mappedBy = "currencyDict", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<CurrencyRates> currencyRates;


    public CurrencyDict() {
        createDictPk();
    }

    public void createDictPk(){
        DictPk dictPk = new DictPk();
        this.setDictPk(dictPk);
    }

}
