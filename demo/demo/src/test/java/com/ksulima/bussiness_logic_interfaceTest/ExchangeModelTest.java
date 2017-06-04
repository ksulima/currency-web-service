package com.ksulima.bussiness_logic_interfaceTest;

import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

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
}
