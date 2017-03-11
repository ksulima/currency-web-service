package com.ksulima.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Currency;

/**
 * Created by Krzysztof Sulima on 08.03.2017.
 */



@RestController


public class CurrencyExchangeController {


    @RequestMapping("/multiplier/{value}/{exchangeRatio}")
    public String currencyMultiplier(@PathVariable Long value,
                                     @PathVariable Long exchangeRatio,
                                     @RequestParam("currFrom") String currFrom,
                                     @RequestParam("currTo") String currTo){

        try{
            Currency currencyFrom = Currency.getInstance(currFrom);
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Something is no yes with @param 'currFrom'");
        }
        try{
            Currency currencyTo = Currency.getInstance(currTo);
        }catch(IllegalArgumentException e){
            throw new RuntimeException("Something is no yes with @param 'currTo'");
        }
        return value + " " + currFrom + " equals " + value*exchangeRatio + " " + currTo;
    }


}
