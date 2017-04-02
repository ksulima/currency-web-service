package com.ksulima.bussiness_logic_interface.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Krzysztof Sulima on 01.04.2017.
 */
@Getter
@Setter
public class CurrencyParams {


    private String inCurrency;
    private String outCurrency;
    private String date;

    public String getInCurrency() {
        return inCurrency;
    }
    public String getOutCurrency() {
        return outCurrency;
    }
    public String getDate() {
        return date;
    }
}
