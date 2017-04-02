package com.ksulima.rest_controller;

import com.ksulima.bussiness_logic_interface.model.CurrencyParams;
import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import com.ksulima.bussiness_logic_interface.service.CurrencyService;
import com.ksulima.bussiness_logic_interface.service.CurrencyTotService;
import com.ksulima.rest_client.ExchangeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/{number}")
    public Long multiplyByFive(@PathVariable Long number){
        return number*5;
    }


    @RequestMapping("/multiplier/{value}/{exchangeRatio}")
    public String currencyMultiplier(@RequestParam("currFrom") String currFrom,
                                     @RequestParam("currTo") String currTo,
                                     @PathVariable Long value,
                                     @PathVariable Long exchangeRatio){

        try{
            Currency currencyFrom = Currency.getInstance(currFrom);
        }catch (IllegalArgumentException e){
            throw new RuntimeException("invalid currency code");
        }
        try{
            Currency currencyTo = Currency.getInstance(currTo);
        }catch(IllegalArgumentException e){
            throw new RuntimeException("invalid currency code");
        }
        return value + " " + currFrom + " equals " + value*exchangeRatio + " " + currTo;
    }



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
        return new ResponseEntity<List<String>>(result, HttpStatus.ALREADY_REPORTED);
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

    @RequestMapping("/currency/fixer")
    public ExchangeModel getExchange(){
        return exchangeClient.getExchange();
    }

    @RequestMapping("/currency/fixer/{inCurrency}/{outCurrency}/{date}")
    public ExchangeModel getExchange(@PathVariable String inCurrency,
                                     @PathVariable String outCurrency,
                                     @PathVariable String date){
        return exchangeClient.getExchangeInOut(inCurrency, outCurrency, date);
    }

    @RequestMapping("/currency/fixer/multi/{inCurrency}/{outCurrencies}")
    public ExchangeModel getMultiExchange(@PathVariable String inCurrency,
                                     @PathVariable String outCurrencies){
        return exchangeClient.getMultiExchangeInOut(inCurrency, outCurrencies);
    }

    @Autowired
    private CurrencyTotService currencyTotService;

    @RequestMapping("/currency/select/{curr}")
    public String getSelectedCurrency(@PathVariable String curr){
        return currencyTotService.getSelectedCurrency(curr);
    }


    @RequestMapping(value="/currency/fixer/inout/post", method = RequestMethod.POST)
    ResponseEntity<ExchangeModel> getExchangeInOut(@RequestBody CurrencyParams param){
       return new ResponseEntity(exchangeClient.getExchangeInOut(param.getInCurrency(), param.getOutCurrency(), param.getDate()), HttpStatus.OK);
    }

    @RequestMapping("/currency/fixer/convert/{inCurrency}/{outCurrency}/{amount}/{date}")
    public ResponseEntity<ExchangeModel> calculateConversion(@PathVariable String inCurrency,
                                             @PathVariable String outCurrency,
                                             @PathVariable Integer amount,
                                             @PathVariable String date){
        ExchangeModel fixerdata = exchangeClient.getExchangeInOut(inCurrency, outCurrency, date);

        if(amount>0 && amount <=1000){
            ExchangeModel result = currencyTotService.calculateConversion(fixerdata, amount);
            return new ResponseEntity(result, HttpStatus.OK);
        }else{
            return new ResponseEntity(fixerdata, HttpStatus.BAD_REQUEST);
        }
    }

}
