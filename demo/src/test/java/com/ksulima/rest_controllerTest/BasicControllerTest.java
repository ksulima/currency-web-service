package com.ksulima.rest_controllerTest;

import com.ksulima.rest_controller.BasicController;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by Krzysztof Sulima on 21.07.2017.
 */

@RunWith(DataProviderRunner.class)
public class BasicControllerTest {

    private BasicController controler;

    @Before
    public void setup(){
        controler = new BasicController();
    }

    @Test
    @DataProvider({"5,1", "10,2", "0,0"})
    public void shouldMultiplyGivenNumberByFive(Long result, Long input){
        Assert.assertEquals(result, controler.multiplyByFive(input));
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowInvalidCurrencyCode(){
        controler.currencyMultiplier("mistakenCode", "PLN", 1.0, 1.0);
    }

    @Test
    @DataProvider({"3.55,2.84,10.082", "9.35,6.24,58.344"})
    public void shouldProductProperString(Double value, Double ratio, String product){
        String result = controler.currencyMultiplier("EUR","PLN", value, ratio);
        List<Object> resultWords = Arrays.asList(result.split(" "));
        Assert.assertThat(resultWords.get(3), equalTo(product));
    }

    @Test
    public void shouldReturnNegativeResult(){
        Long result = controler.multiplyByFive(-4L);
        Assert.assertEquals(new Long(-20L), result);
    }


}
