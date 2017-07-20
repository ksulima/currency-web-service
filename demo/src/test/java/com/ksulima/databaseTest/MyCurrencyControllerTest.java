package com.ksulima.databaseTest;

import com.ksulima.database.MyCurrencyController;
import com.ksulima.database.entity.MyCurrency;
import com.ksulima.database.repository.MyCurrencyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.*;
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
    public void shouldReturnFirst1OrderById(){
        MyCurrency myCurrency = new MyCurrency();
        myCurrency.setBase("EUR");
        myCurrency.setCurrency("PLN");
        myCurrency.setRate("4.2424");
        when(myCurrencyRepository.findFirst1ByOrderByIdDesc()).thenReturn(myCurrency);

        String result = sut.findTop1ById().getRate();
        assertThat("4.2424", equalToIgnoringCase(result));
        verify(myCurrencyRepository, times(1)).findFirst1ByOrderByIdDesc();
    }

    @Test
    public void testFindById(){
        MyCurrency item1 = new MyCurrency();
        item1.setBase("PLN");
        item1.setCurrency("EUR");
        item1.setRate("4.00");

        MyCurrency item2 = new MyCurrency();
        item2.setBase("PLN");
        item2.setCurrency("NOK");
        item2.setRate("0.40");

        when(myCurrencyRepository.findById(1L)).thenReturn(item1);
        when(myCurrencyRepository.findById(2L)).thenReturn(item2);

        MyCurrency result1 = sut.findById(1L);
        MyCurrency result2 = sut.findById(2L);

        assertThat(item1, is(result1));
        assertThat(item2, is(result2));

        assertThat(item1, not(is(result2)));
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

    @Test
    public void testFindAll(){
        sut.findAll();
        verify(myCurrencyRepository, times(1)).findAll();
    }


}
