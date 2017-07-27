package com.ksulima.bussiness_logic_implementationTest;

import com.ksulima.bussiness_logic_implementation.CurrencyServiceImpl;
import com.ksulima.rest_client.ExchangeClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Krzysztof Sulima on 27.07.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class CurrencyServiceImplTest {

    @InjectMocks
    CurrencyServiceImpl sut;

    @Mock
    ExchangeClient exchangeClientMOCK;

    @Test
    public void getSelectedExRatesTest(){
        sut.getSelectedExRates("PLN", "EUR", "2017-01-01");
        verify(exchangeClientMOCK, times(1)).getSelectedExRates("PLN", "EUR", "2017-01-01");
    }

    // getSelectedExRatesFromDefinedPeriod = GSERFDP
    @Test
    public void GSERFDPInvokesProperNumberOfTimes(){
        sut.getSelectedExRatesFromDefinedPeriod("PLN", "EUR", "2017-01-01", "2017-01-10");
        verify(exchangeClientMOCK, times(10)).getSelectedExRates(any(), any(), any());
    }


//    @Test
//    public void GSERFDPChecksIfEndDateIsNotBeforeStartDate(){
//        sut.getSelectedExRatesFromDefinedPeriod("PLN", "EUR", "2017-01-01", "2017-01-10");
//    }
//    TODO IllegalException


}
