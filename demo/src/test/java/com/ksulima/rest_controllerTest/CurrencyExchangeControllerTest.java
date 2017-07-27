package com.ksulima.rest_controllerTest;

import com.ksulima.bussiness_logic_implementation.CurrencyServiceImpl;
import com.ksulima.bussiness_logic_interface.model.CurrencyParams;
import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import com.ksulima.rest_client.ExchangeClient;
import com.ksulima.rest_controller.CurrencyExchangeController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Krzysztof Sulima on 28.05.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CurrencyExchangeControllerTest {

    @InjectMocks
    CurrencyExchangeController sut;


    @Mock(answer = Answers.RETURNS_MOCKS)
    private ExchangeClient exchangeClient;

    @Mock(answer = Answers.RETURNS_MOCKS)
    private CurrencyServiceImpl currencyService;


    @Test
    public void getLatestStandardExRatesShouldReturnExchange(){
        ExchangeModel model = sut.getLatestStandardExRates();
        Assert.assertNotNull(model);
    }

    @Test
    public void getSelectedExRatesShouldReturnExchange(){
        ExchangeModel model = sut.getSelectedExRates(any(), any(), any());
        Assert.assertNotNull(model);
        verify(currencyService, times(1)).getSelectedExRates(any(), any(), any());
    }

    @Test
    public void getSelectedExRatesFromDefinedPeriodTest(){

        Set<ExchangeModel> testSet = new HashSet<>();
        testSet.add(new ExchangeModel());
        when(currencyService.getSelectedExRatesFromDefinedPeriod(any(), any(), any(), any())).thenReturn(testSet);
        Set<ExchangeModel> model = sut.getSelectedExRatesFromDefinedPeriod(any(), any(), any(), any());

        verify(currencyService, times(1)).getSelectedExRatesFromDefinedPeriod(any(), any(), any(), any());
        assertThat(model, is(testSet));
    }

    @Test
    public void getLatestExRatesForCurrenciesTest(){
        ExchangeModel model = sut.getLatestExRatesForCurrencies(any(), any());
        Assert.assertNotNull(model);
        verify(exchangeClient, times(1)).getLatestExRatesForCurrencies(any(), any());
    }

    @Test
    public void getSelectedExRatesTest(){

        CurrencyParams currParam = new CurrencyParams();
        ExchangeModel item = new ExchangeModel();
        when(currencyService.getSelectedExRates(any(), any(), any())).thenReturn(item);
        ResponseEntity result = sut.getSelectedExRates(currParam);
        assertThat(HttpStatus.OK, is(result.getStatusCode()));
    }


}
