package com.ksulima.database.service;

import com.ksulima.database.entity.CurrencyDict;
import com.ksulima.database.entity.CurrencyRates;
import com.ksulima.database.repository.CurrencyDictRepository;
import com.ksulima.database.repository.CurrencyRatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Krzysztof Sulima on 17.06.2017.
 */
@Service
public class DatabaseService {

    @Autowired
    private CurrencyDictRepository currDictRepo;
    @Autowired
    private CurrencyRatesRepository currRatesRepo;


    public void addDictRecord(CurrencyDict item){
        currDictRepo.save(item);
    }

    public void addDictRecord(String baseCode, String name){
        CurrencyDict item = new CurrencyDict();
        item.getDictPk().setBaseCode(baseCode);
        item.setName(name);
        currDictRepo.save(item);
    }

    public void addRatesRecord(CurrencyRates item, String code){
        CurrencyDict currencyDict = findDictByCode(code);
        item.setCurrencyDict(currencyDict);
        currRatesRepo.save(item);
    }

    public CurrencyDict findDictById(Long id){
        return currDictRepo.findByDictPkDictId(id);
    }


    public CurrencyDict findDictByCode(String code){
        return currDictRepo.findByDictPkBaseCode(code);
    }

    public CurrencyRates findRatesById(Long id){
        return currRatesRepo.findByRatesPkCurrencyId(id);
    }


}
