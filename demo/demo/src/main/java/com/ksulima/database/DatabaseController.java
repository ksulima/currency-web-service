package com.ksulima.database;

import com.ksulima.database.entity.CurrencyDict;
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

    @RequestMapping(value = "/dict/{id}", method = RequestMethod.GET)
    public CurrencyDict findDictRecordById(@PathVariable Long id){
        return databaseService.findById(id);
    }


}
