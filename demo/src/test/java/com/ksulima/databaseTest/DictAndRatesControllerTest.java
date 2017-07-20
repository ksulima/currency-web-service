package com.ksulima.databaseTest;

import com.ksulima.database.DictAndRatesController;
import com.ksulima.database.entity.CurrencyDict;
import com.ksulima.database.entity.CurrencyRates;
import com.ksulima.database.service.DictAndRatesService;
import com.ksulima.util.NotSavedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Krzysztof Sulima on 20.07.2017.
 */


@RunWith(MockitoJUnitRunner.class)
public class DictAndRatesControllerTest {

    @InjectMocks
    DictAndRatesController sut;

    @Mock
    DictAndRatesService dictAndRatesService;

    @Test
    public void testAddDictWithRequestBody(){
        CurrencyDict currencyDict = new CurrencyDict();
        sut.addDict(currencyDict);
        verify(dictAndRatesService, times(1)).addDictRecord(currencyDict);
    }

    @Test
    public void testAddDictWithPathVariable(){
        String baseCode = "PLN";
        String name = "zloty";
        sut.addDict(baseCode, name);
        verify(dictAndRatesService, times(1)).addDictRecord(baseCode, name);
    }

    @Test
    public void testAddRates() throws NotSavedException{
        CurrencyRates currencyRates = new CurrencyRates();
        String baseCode = "PLN";
        sut.addRates(currencyRates, baseCode);
        verify(dictAndRatesService).addRatesRecord(currencyRates, baseCode);
    }

    @Test
    public void testFindAllDict(){
        List<CurrencyDict> dictList = new ArrayList<>();
        CurrencyDict item1 = new CurrencyDict(); item1.setName("item1"); dictList.add(item1);
        CurrencyDict item2 = new CurrencyDict(); item2.setName("item2"); dictList.add(item2);
        CurrencyDict item3 = new CurrencyDict(); item3.setName("item3"); dictList.add(item3);
        CurrencyDict item4 = new CurrencyDict(); item4.setName("item4"); dictList.add(item4);
        CurrencyDict item5 = new CurrencyDict(); item5.setName("item5"); dictList.add(item5);

        when(dictAndRatesService.findDictAll()).thenReturn(dictList);
        List<CurrencyDict> result = sut.findDictAll();
        assertThat(dictList, is(result));
        assertThat(dictList, hasSize(5));
    }

    @Test
    public void testFindAllCurrencyRates(){
        List<CurrencyRates> ratesList = new ArrayList<>();
        CurrencyRates item1 = new CurrencyRates(); item1.setRate("rate1"); ratesList.add(item1);
        CurrencyRates item2 = new CurrencyRates(); item2.setRate("rate2"); ratesList.add(item2);
        CurrencyRates item3 = new CurrencyRates(); item3.setRate("rate3"); ratesList.add(item3);
        CurrencyRates item4 = new CurrencyRates(); item4.setRate("rate4"); ratesList.add(item4);
        CurrencyRates item5 = new CurrencyRates(); item5.setRate("rate5"); ratesList.add(item5);

        when(dictAndRatesService.findRatesAll()).thenReturn(ratesList);
        List<CurrencyRates> result = sut.findRatesAll();
        assertThat(ratesList, is(result));
        assertThat(result, hasSize(5));
        assertThat(result, hasItems(item1));
    }

    @Test
    public void testFindDictByProperty(){
        CurrencyDict dict1 = new CurrencyDict();
        CurrencyDict dict2 = new CurrencyDict();
        dict1.setName("dict1");
        dict2.setName("dict2");


        when(dictAndRatesService.findDictById(1L)).thenReturn(dict1);
        when(dictAndRatesService.findDictById(2L)).thenReturn(dict2);
        when(dictAndRatesService.findDictByCode("PLN")).thenReturn(dict1);
        when(dictAndRatesService.findDictByCode("EUR")).thenReturn(dict2);

        CurrencyDict resultID_1 = sut.findDictById(1L);
        CurrencyDict resultID_2 = sut.findDictById(2L);
        CurrencyDict resultCode_1 = sut.findDictByCode("PLN");
        CurrencyDict resultCode_2 = sut.findDictByCode("EUR");

        assertThat(dict1, is(resultID_1));
        assertThat(dict2, is(resultID_2));
        assertThat(dict1, not(is(resultID_2)));

        assertThat(dict1, is(resultCode_1));
        assertThat(dict2, is(resultCode_2));

    }

    @Test
    public void testFindRatesByProperty(){
        CurrencyRates rate1 = new CurrencyRates();
        CurrencyRates rate2 = new CurrencyRates();

        List<CurrencyRates> rateList = new ArrayList<>();
        rateList.add(rate1);
        rateList.add(rate2);

        when(dictAndRatesService.findRatesById(1L)).thenReturn(rate1);
        when(dictAndRatesService.findRatesById(2L)).thenReturn(rate2);
        when(dictAndRatesService.findRatesByDictCode("PLN")).thenReturn(rateList);

        CurrencyRates result1 = sut.findRatesById(1L);
        CurrencyRates result2 = sut.findRatesById(2L);

        assertThat(rate1, is(result1));
        assertThat(rate2, is(result2));
        assertThat(rate1, not(is(result2)));


    }



}
