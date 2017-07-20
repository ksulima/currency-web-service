package com.ksulima.databaseTest.serviceTest;

import com.ksulima.database.entity.CurrencyDict;
import com.ksulima.database.entity.CurrencyRates;
import com.ksulima.database.repository.CurrencyDictRepository;
import com.ksulima.database.repository.CurrencyRatesRepository;
import com.ksulima.database.service.DictAndRatesService;
import com.ksulima.util.NotSavedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;



/**
 * Created by Krzysztof Sulima on 20.07.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class DictAndRatesServiceTest {

    @InjectMocks
    DictAndRatesService sut;

    @Mock
    CurrencyDictRepository currDictRepo;

    @Mock
    CurrencyRatesRepository currRatesRepo;

    @Test
    public void addDictRecordTest(){
        CurrencyDict item = new CurrencyDict();
        sut.addDictRecord(item);
        verify(currDictRepo, times(1)).save(item);
    }

    @Test
    public void addRatesRecordTest() throws NotSavedException{
        CurrencyRates item = new CurrencyRates();
        sut.addRatesRecord(item, "PLN");
        verify(currRatesRepo).save(item);
    }

    @Test
    public void findDictAllTest(){
        sut.findDictAll();
        verify(currDictRepo).findAll();

        List<CurrencyDict> dictList = new ArrayList<>();
        CurrencyDict item1 = new CurrencyDict(); item1.setName("item1"); dictList.add(item1);
        CurrencyDict item2 = new CurrencyDict(); item2.setName("item2"); dictList.add(item2);
        CurrencyDict item3 = new CurrencyDict(); item3.setName("item3"); dictList.add(item3);
        CurrencyDict item4 = new CurrencyDict(); item4.setName("item4"); dictList.add(item4);
        CurrencyDict item5 = new CurrencyDict(); item5.setName("item5"); dictList.add(item5);

        when(currDictRepo.findAll()).thenReturn(dictList);
        List<CurrencyDict> result = sut.findDictAll();
        assertThat(dictList, is(result));
    }

    @Test
    public void findRatesAllTest(){

        List<CurrencyRates> ratesList = new ArrayList<>();
        CurrencyRates item1 = new CurrencyRates(); item1.setRate("rate1"); ratesList.add(item1);
        CurrencyRates item2 = new CurrencyRates(); item2.setRate("rate2"); ratesList.add(item2);
        CurrencyRates item3 = new CurrencyRates(); item3.setRate("rate3"); ratesList.add(item3);
        CurrencyRates item4 = new CurrencyRates(); item4.setRate("rate4"); ratesList.add(item4);
        CurrencyRates item5 = new CurrencyRates(); item5.setRate("rate5"); ratesList.add(item5);

        when(currRatesRepo.findAll()).thenReturn(ratesList);
        List<CurrencyRates> result = sut.findRatesAll();
        assertThat(ratesList, is(result));
    }

}
