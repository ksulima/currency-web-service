package com.ksulima.bussiness_logic_interface.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Krzysztof Sulima on 01.04.2017.
 */
@Getter
@Setter
public class CurrencyParams {


    private String base;
    private String currency;
    private String date;

    public String getBase() {
        return base;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDate() {
        return date;
    }
}
