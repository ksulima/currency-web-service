package com.ksulima.controller;

import com.ksulima.model.CurrencyService;
import com.ksulima.model.ExchangeModel;
import com.ksulima.rest.client.ExchangeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;

/**
 * Created by Krzysztof Sulima on 08.03.2017.
 */

@RestController
@RequestMapping("/api")
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

    @Autowired
    @Qualifier("usd")
    private CurrencyService currencyService;

    @Autowired
    private Collection<CurrencyService> currencyServices;

    @RequestMapping("/currency")
    public List<String> allCurrencies(){
        List<String> result = new ArrayList<String>();
        currencyServices.forEach((x)-> result.add(x.getCurrency()));
        return result;
    }

    @RequestMapping("/currency/status")
    public ResponseEntity<List<String>> allCurrencyStatus(){
        List<String> result = new ArrayList<String>();
        currencyServices.forEach((x)-> result.add(x.getCurrency()));
        return new ResponseEntity<List<String>>(result, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/currency/status/dec")
    public List<String> allCurrencyStatusDecorator(
            HttpServletRequest request,
            HttpServletResponse response
    ){
      List<String> result = new ArrayList<String>();
      currencyServices.forEach((x)-> result.add(x.getCurrency()));
      response.setStatus(401);
      return result;
    }


    @Autowired
    private ExchangeClient exchangeClient;

    @RequestMapping("/currency/external")
    public ExchangeModel getExchange(){
        return exchangeClient.getExchange();
    }


}
