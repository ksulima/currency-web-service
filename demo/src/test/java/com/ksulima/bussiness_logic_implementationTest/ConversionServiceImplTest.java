package com.ksulima.bussiness_logic_implementationTest;

import com.ksulima.bussiness_logic_implementation.ConversionServiceImpl;
import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import com.ksulima.rest_client.ExchangeClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Krzysztof Sulima on 26.07.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ConversionServiceImplTest {


    @InjectMocks
    private ConversionServiceImpl sut;

    @Mock(answer = Answers.RETURNS_MOCKS)
    ExchangeClient exchangeClientMOCK;




    @Test
    public void shouldReturnNullWhenAmountMoreMillion() {
        Assert.assertNull(sut.calculateConversion("PLN", "EUR", 1000020, "2017-01-01"));
    }

    @Test
    public void shouldReturnNullWhenAmountLessZero() {
        Assert.assertNull(sut.calculateConversion("PLN", "EUR", -10, "2017-01-01"));
    }

    @Test
    public void shouldReturnNotNullWhenAmountBetweenZeroAndMillion() {
        Assert.assertNotNull(sut.calculateConversion("PLN", "EUR", 9000, "2017-01-01"));
        Assert.assertNotNull(sut.calculateConversion("PLN", "EUR", 250000, "2017-01-01"));
        Assert.assertNotNull(sut.calculateConversion("PLN", "EUR", 2, "2017-01-01"));
    }

    @Test
    public void shouldInvokeOnlyOnce() {
        sut.calculateConversion("PLN", "EUR", 1000, "2017-01-01");
        verify(exchangeClientMOCK, times(1)).getSelectedExRates(any(), any(), any());
    }

    @Test
    public void shouldMapRightCalculation(){
        ExchangeModel item = new ExchangeModel();
        Map<String, String> ratesMap = new HashMap<>();
        ratesMap.put("EUR", "4.00");
        item.setRates(ratesMap);
        when(exchangeClientMOCK.getSelectedExRates(any(), any(), any())).thenReturn(item);
        ExchangeModel result = sut.calculateConversion("PLN", "EUR", 100, "2017-01-01");
        Assert.assertEquals("400.00", result.getRates().get("EUR"));
    }






}
