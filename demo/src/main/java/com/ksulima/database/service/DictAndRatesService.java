package com.ksulima.database.service;

import com.ksulima.util.NotSavedException;
import com.ksulima.database.entity.CurrencyDict;
import com.ksulima.database.entity.CurrencyRates;
import com.ksulima.database.repository.CurrencyDictRepository;
import com.ksulima.database.repository.CurrencyRatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Krzysztof Sulima on 17.06.2017.
 */
@Service
public class DictAndRatesService {

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

    public void addRatesRecord(CurrencyRates item, String code) throws NotSavedException{
        CurrencyDict currencyDict = findDictByCode(code);
        item.setCurrencyDict(currencyDict);

        try{
            currRatesRepo.save(item);
        }catch (Exception e){
            throw new NotSavedException("Item not saved");
        }
    }

    public List<CurrencyDict> findDictAll(){
        return currDictRepo.findAll();
    }

    public List<CurrencyRates> findRatesAll(){
        return currRatesRepo.findAll();
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

    public List<CurrencyRates> findRatesByDictCode(String code){
        return currRatesRepo.findByCurrencyDictDictPkBaseCode(code);
    }

    public List<CurrencyRates> findRatesByBaseCodeAndDate(String code, String date){
            return currRatesRepo.findByCurrencyDictDictPkBaseCodeAndRatesPkDate(code, date);

    }


}
