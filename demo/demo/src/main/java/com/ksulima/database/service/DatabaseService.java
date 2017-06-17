package com.ksulima.database.service;

import com.ksulima.database.entity.CurrencyDict;
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

    public CurrencyDict findById(Long id){
        return currDictRepo.findByDictPkDictId(id);
    }

}
