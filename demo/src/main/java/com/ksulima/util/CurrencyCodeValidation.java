package com.ksulima.util;

import java.util.Currency;

/**
 * Created by Krzysztof Sulima on 24.06.2017.
 */
public class CurrencyCodeValidation {

    public static boolean isValid(String code){

        try {
            Currency validated = Currency.getInstance(code);
            return true;
        }catch(IllegalArgumentException e ){
            throw new RuntimeException("Currency code not supported by ISO standard");
        }
    }

}
