package com.ksulima.databaseTest;

import com.ksulima.database.MyCurrencyController;
import com.ksulima.database.entity.MyCurrency;
import com.ksulima.database.repository.MyCurrencyRepository;
import org.junit.Assert;
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
public class MyCurrencyControllerTest {


    @InjectMocks
    MyCurrencyController sut;

    @Mock
    MyCurrencyRepository myCurrencyRepository;



    @Test
    public void shouldReturnFirst1ById(){
        MyCurrency myCurrency = new MyCurrency();
        myCurrency.setBase("EUR");
        myCurrency.setWaluta("PLN");
        myCurrency.setRate("4.2424");
        when(myCurrencyRepository.findFirst1ByOrderByIdDesc()).thenReturn(myCurrency);

        String result = sut.findTop1ById().getRate();
        Assert.assertThat("4.2424", equalToIgnoringCase(result));
        verify(myCurrencyRepository, times(1)).findFirst1ByOrderByIdDesc();
    }



    @Test
    public void shouldRemoveRecordFromDataBase(){
        long id = 1;
        sut.removeRecord(id);
        verify(myCurrencyRepository).delete(id);
    }

    @Test
    public void shouldAddDefinedRecord(){
        MyCurrency myCurrency = new MyCurrency();
        sut.addRecord(myCurrency);
        verify(myCurrencyRepository, times(1)).save(myCurrency);
    }



}
