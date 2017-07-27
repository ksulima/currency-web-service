package com.ksulima.rest_controllerTest;

import com.ksulima.bussiness_logic_interface.service.CurrencyService;
import com.ksulima.rest_controller.CurrencyExDatabaseController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Krzysztof Sulima on 26.07.2017.
 */


@RunWith(MockitoJUnitRunner.class)
public class CurrencyExDatabaseControllerTest {

    @InjectMocks
    CurrencyExDatabaseController sut;

    @Mock
    CurrencyService currencyServiceMOCK;

    @Test
    public void saveSelectedExRatesFromDefinedPeriodTest(){

        sut.saveSelectedExRatesFromDefinedPeriod(any(), any(), any(), any());
        verify(currencyServiceMOCK, times(1)).saveSelectedExRatesFromDefinedPeriod(any(), any(), any(), any());
    }

    @Test
    public void saveSelectedExRatesTest() {
        sut.saveSelectedExRates(any(), any(), any());
        verify(currencyServiceMOCK, times(1)).saveSelectedExRates(any(), any(), any());
    }


}
