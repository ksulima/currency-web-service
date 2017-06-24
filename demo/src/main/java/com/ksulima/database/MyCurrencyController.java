package com.ksulima.database;

import com.ksulima.database.entity.MyCurrency;
import com.ksulima.database.repository.MyCurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Krzysztof Sulima on 04.04.2017.
 */


@RestController
@RequestMapping("/db")
public class MyCurrencyController {


    @Autowired
    MyCurrencyRepository myCurrencyRepository;

    @RequestMapping("/find/{id}")
    public MyCurrency findById(@PathVariable Long id){

        return myCurrencyRepository.findById(id);
    }

    @RequestMapping("/find/last")
    public MyCurrency findTop1ById(){

        return myCurrencyRepository.findFirst1ByOrderByIdDesc();
    }

    @RequestMapping("/find/all")
    public List<MyCurrency> findAll(){
        return myCurrencyRepository.findAll();
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public void removeRecord(@PathVariable Long id){
        myCurrencyRepository.delete(id);
    }

    @RequestMapping(value = "/add/record", method = RequestMethod.PUT)
    public String addRecord(@RequestBody MyCurrency currency){
        myCurrencyRepository.save(currency);
        return "Now currency archive contains "+ myCurrencyRepository.count() + " records.";
    }


}
