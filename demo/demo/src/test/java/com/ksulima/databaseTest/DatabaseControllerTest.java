package com.ksulima.databaseTest;

import com.ksulima.database.DatabaseController;
import com.ksulima.database.entity.MyCurrency;
import com.ksulima.database.repository.CurrencyRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by Krzysztof Sulima on 29.05.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class DatabaseControllerTest {


    @InjectMocks
    DatabaseController sut;

    @Mock
    CurrencyRepository currencyRepository;



    @Test
    public void shouldReturnFirst1ById(){
        String result = sut.findTop1ById().getRate();
        Assert.assertThat(result, equalToIgnoringCase("4.2424"));
        verify(currencyRepository, times(1)).findFirst1ByOrderByIdDesc();
    }

    @Before
    public void setup(){
        MyCurrency myCurrency = new MyCurrency();
        myCurrency.setBase("EUR");
        myCurrency.setWaluta("PLN");
        myCurrency.setRate("4.2424");
        when(currencyRepository.findFirst1ByOrderByIdDesc()).thenReturn(myCurrency);
    }





}
