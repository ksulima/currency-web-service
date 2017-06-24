package com.ksulima.bussiness_logic_interfaceTest;

import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Krzysztof Sulima on 31.05.2017.
 */
@RunWith(Parameterized.class)
public class ExchangeModelParametrized {

    private ExchangeModel model;
    private String currency;
    private String rate;


    @Before
    public void setup(){
        model = new ExchangeModel();
    }


    public ExchangeModelParametrized(String currency, String rate) {
        this.currency = currency;
        this.rate = rate;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testConditions(){
        return Arrays.asList(new Object[][]{
            {"PLN", "4.321"},
            {"GBP", "5.123"},
            {"USD", "3.999"},
        });
    }


    @Test
    public void checkIfReturnsProperRates(){
        Map<String, String> rates = new HashMap<String, String>();
        rates.put(currency, rate);
        model.setRates(rates);
        Assert.assertThat(rates, equalTo(model.getRates()));

    }
}
