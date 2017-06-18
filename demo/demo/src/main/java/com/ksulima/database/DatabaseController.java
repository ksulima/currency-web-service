package com.ksulima.database;

import com.ksulima.database.entity.CurrencyDict;
import com.ksulima.database.entity.CurrencyRates;
import com.ksulima.database.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Krzysztof Sulima on 17.06.2017.
 */

@RestController
public class DatabaseController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/dict/add", method = RequestMethod.PUT)
    public void addDictRecord(@RequestBody CurrencyDict currencyDict){
        databaseService.addDictRecord(currencyDict);
    }

    @RequestMapping(value = "/dict/add/{baseCode}/{name}", method = RequestMethod.PUT)
    public void addDictRecord(@PathVariable String baseCode,
                              @PathVariable String name){
        databaseService.addDictRecord(baseCode, name);
    }

    @RequestMapping(value = "/rates/add/{baseCode}", method = RequestMethod.PUT)
    public void addRatesRecord(@RequestBody CurrencyRates currencyRates, @PathVariable String baseCode){
        databaseService.addRatesRecord(currencyRates, baseCode);
    }

    @RequestMapping(value = "/dict/id/{id}", method = RequestMethod.GET)
    public CurrencyDict findDictRecordById(@PathVariable Long id){
        return databaseService.findDictById(id);
    }

    @RequestMapping(value = "/dict/code/{code}", method = RequestMethod.GET)
    public CurrencyDict findDictRecordByCode(@PathVariable String code){
        return databaseService.findDictByCode(code);
    }

    @RequestMapping(value = "/rates/id/{id}", method = RequestMethod.GET)
    public CurrencyRates findRatesRecordById(@PathVariable Long id){
        return databaseService.findRatesById(id);
    }


}
