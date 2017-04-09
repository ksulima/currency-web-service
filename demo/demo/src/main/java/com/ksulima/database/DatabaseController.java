package com.ksulima.database;

import com.ksulima.database.entity.MyCurrency;
import com.ksulima.database.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Krzysztof Sulima on 04.04.2017.
 */


@RestController
@RequestMapping("/db")
public class DatabaseController {


    @Autowired
    CurrencyRepository currencyRepository;

    @RequestMapping("/find/{id}")
    public MyCurrency findById(@PathVariable Long id){

        return currencyRepository.findById(id);
    }

    @RequestMapping("/find/last")
    public MyCurrency findTop1ById(){

        return currencyRepository.findFirst1ByOrderByIdDesc();
    }

    @RequestMapping("/find/all")
    public List<MyCurrency> findAll(){
        return currencyRepository.findAll();
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public void removeRecord(@PathVariable Long id){
        currencyRepository.delete(id);
    }

    @RequestMapping(value = "/add/record", method = RequestMethod.PUT)
    public String addRecord(@RequestBody MyCurrency currency){
        currencyRepository.save(currency);
        return "Now currency archive contains "+ currencyRepository.count() + " records.";
    }



}
