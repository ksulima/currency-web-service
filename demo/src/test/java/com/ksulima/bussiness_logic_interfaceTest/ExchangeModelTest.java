package com.ksulima.bussiness_logic_interfaceTest;

import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * Created by Krzysztof Sulima on 31.05.2017.
 */
@RunWith(DataProviderRunner.class)
public class ExchangeModelTest {

    private ExchangeModel model;

    @Before
    public void setup(){
        model = new ExchangeModel();
        }

    @Test
    public void shouldReturnProperBase(){
        model.setBase("PLN");
        Assert.assertEquals("PLN", model.getBase());
    }

    @Test
    @DataProvider({"PLN,4.321","GBP,5.321","USD,3.999"})
    public void checkIfReturnsProperRates(String currency, String rate){
        Map<String, String> rates = new HashMap<String, String>();
        rates.put(currency, rate);
        model.setRates(rates);
        Assert.assertThat(rates, equalTo(model.getRates()));
    }

    @Test
    public void shouldReturnProperDate(){
        model.setDate("2017-01-01");
        Assert.assertEquals("2017-01-01", model.getDate());
    }

    @Test
    public void testAssertMapWithRates(){

        Map<String, String> ratesMap = new HashMap<String, String>();

        ratesMap.put("PLN", "1.00");
        ratesMap.put("NOK", "0.45");
        ratesMap.put("USD", "3.45");
        ratesMap.put("CHR", "2.45");
        ratesMap.put("CZK", "0.15");
        ratesMap.put("EUR", "4.15");

        model.setRates(ratesMap);

        Map<String, String> expectedMap = new HashMap<String, String>();

        expectedMap.put("PLN", "1.00");
        expectedMap.put("NOK", "0.45");
        expectedMap.put("USD", "3.45");
        expectedMap.put("CHR", "2.45");
        expectedMap.put("CZK", "0.15");
        expectedMap.put("EUR", "4.15");


        assertThat(model.getRates(), is(expectedMap));
        assertThat(model.getRates().size(), is(6));
        assertThat(model.getRates(), IsMapContaining.hasEntry("EUR", "4.15"));
        assertThat(model.getRates(), not(IsMapContaining.hasEntry("unknown", "4.15")));
        assertThat(model.getRates(), IsMapContaining.hasKey("CZK"));

    }

}
