package com.ksulima.database;

import com.ksulima.database.entity.CurrencyDict;
import com.ksulima.database.entity.CurrencyRates;
import com.ksulima.database.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Krzysztof Sulima on 17.06.2017.
 */

@RestController
public class DatabaseController {

    @Autowired
    DatabaseService databaseService;

    @RequestMapping(value = "/dict/add", method = RequestMethod.PUT)
    public void addDict(@RequestBody CurrencyDict currencyDict){
        databaseService.addDictRecord(currencyDict);
    }

    @RequestMapping(value = "/dict/add/{baseCode}/{name}", method = RequestMethod.PUT)
    public void addDict(@PathVariable String baseCode,
                        @PathVariable String name){
        databaseService.addDictRecord(baseCode, name);
    }

    @RequestMapping(value = "/rates/add/{baseCode}", method = RequestMethod.PUT)
    public void addRates(@RequestBody CurrencyRates currencyRates, @PathVariable String baseCode){
        databaseService.addRatesRecord(currencyRates, baseCode);
    }

    @RequestMapping(value = "/dict/all", method = RequestMethod.GET)
    public List<CurrencyDict> findDictAll(){
        return databaseService.findDictAll();
    }

    @RequestMapping(value = "/rates/all", method = RequestMethod.GET)
    public List<CurrencyRates> findRatesAll(){
        return databaseService.findRatesAll();
    }

    @RequestMapping(value = "/dict/id/{id}", method = RequestMethod.GET)
    public CurrencyDict findDictById(@PathVariable Long id){
        return databaseService.findDictById(id);
    }

    @RequestMapping(value = "/dict/code/{code}", method = RequestMethod.GET)
    public CurrencyDict findDictByCode(@PathVariable String code){
        return databaseService.findDictByCode(code);
    }

    @RequestMapping(value = "/rates/id/{id}", method = RequestMethod.GET)
    public CurrencyRates findRatesById(@PathVariable Long id){
        return databaseService.findRatesById(id);
    }

    @RequestMapping(value = "/rates/code/{code}", method = RequestMethod.GET)
    public List<CurrencyRates> findRatesByDictCode(@PathVariable String code){
        return databaseService.findRatesByDictCode(code);
    }

    @RequestMapping(value = "/rates/code/{code}/date/{date}", method = RequestMethod.GET)
    public List<CurrencyRates> findRatesByDictCodeAndDate(@PathVariable String code,
                                                          @PathVariable String date){
        return databaseService.findRatesByBaseCodeAndDate(code, date);
    }
}
