package com.ksulima.rest_controller;

import com.ksulima.bussiness_logic_interface.model.CurrencyParams;
import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import com.ksulima.bussiness_logic_interface.service.CurrencyService;
import com.ksulima.bussiness_logic_interface.service.CurrencyTotService;
import com.ksulima.database.entity.MyCurrency;
import com.ksulima.database.repository.CurrencyRepository;
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


    @RequestMapping("/currency/{number}")
    public Long multiplyByFive(@PathVariable Long number) {
        return number * 5;
    }


    @RequestMapping("/currency/multiply/{value}/{exchangeRatio}")
    public String currencyMultiplier(@RequestParam("currFrom") String currFrom,
                                     @RequestParam("currTo") String currTo,
                                     @PathVariable Long value,
                                     @PathVariable Long exchangeRatio) {

        try {
            Currency currencyFrom = Currency.getInstance(currFrom);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("invalid currency code");
        }
        try {
            Currency currencyTo = Currency.getInstance(currTo);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("invalid currency code");
        }
        return value + " " + currFrom + " equals " + value * exchangeRatio + " " + currTo;
    }


    @Autowired
    private Collection<CurrencyService> currencyServices;


    @RequestMapping("/currency/all")
    public List<String> allCurrencies() {
        List<String> result = new ArrayList<String>();
        currencyServices.forEach((x) -> result.add(x.getCurrency()));
        return result;
    }

    @RequestMapping("/currency/all/status")
    public ResponseEntity<List<String>> allCurrencyStatus() {
        List<String> result = new ArrayList<String>();
        currencyServices.forEach((x) -> result.add(x.getCurrency()));
        return new ResponseEntity<List<String>>(result, HttpStatus.ALREADY_REPORTED);
    }

    @RequestMapping("/currency/all/status/dec")
    public List<String> allCurrencyStatusDecorator(
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        List<String> result = new ArrayList<String>();
        currencyServices.forEach((x) -> result.add(x.getCurrency()));
        response.setStatus(200);
        return result;
    }


    @Autowired
    private ExchangeClient exchangeClient;

    @RequestMapping("/get/latest")
    public ExchangeModel getExchange() {
        return exchangeClient.getExchange();
    }

    @RequestMapping("/get/{inCurrency}/{outCurrency}/{date}")
    public ExchangeModel getExchange(@PathVariable String inCurrency,
                                     @PathVariable String outCurrency,
                                     @PathVariable String date) {
        return exchangeClient.getExchangeInOut(inCurrency, outCurrency, date);
    }




    @Autowired
    private CurrencyRepository currencyRepository;

    @RequestMapping("/db/save/{inCurrency}/{outCurrency}/{date}")
    public String saveCurrencyToDB(@PathVariable String inCurrency,
                                @PathVariable String outCurrency,
                                @PathVariable String date) {

        List<MyCurrency> itemList = currencyRepository.findByDateAndBaseAndWaluta(date, inCurrency, outCurrency);
        if(itemList.size() == 0){
            ExchangeModel fixerdata = exchangeClient.getExchangeInOut(inCurrency, outCurrency, date);
            MyCurrency myCurrency = new MyCurrency();
            myCurrency.setDate(fixerdata.getDate());
            myCurrency.setBase(fixerdata.getBase());
            myCurrency.setWaluta(outCurrency);
            myCurrency.setRate(fixerdata.getRates().get(outCurrency));
            currencyRepository.save(myCurrency);
            return "Added";
        }
        return "Not added";
    }



    @RequestMapping("/multi/{inCurrency}/{outCurrencies}")
    public ExchangeModel getMultiExchange(@PathVariable String inCurrency,
                                          @PathVariable String outCurrencies) {
        return exchangeClient.getMultiExchangeInOut(inCurrency, outCurrencies);
    }

    @Autowired
    private CurrencyTotService currencyTotService;


    @RequestMapping(value = "/post", method = RequestMethod.POST)
    ResponseEntity<ExchangeModel> getExchangeInOut(@RequestBody CurrencyParams param) {
        return new ResponseEntity(exchangeClient.getExchangeInOut(param.getInCurrency(), param.getOutCurrency(), param.getDate()), HttpStatus.OK);
    }

    @RequestMapping("/convert/{inCurrency}/{outCurrency}/{amount}/{date}")
    public ResponseEntity<ExchangeModel> calculateConversion(@PathVariable String inCurrency,
                                                             @PathVariable String outCurrency,
                                                             @PathVariable Integer amount,
                                                             @PathVariable String date) {
        ExchangeModel fixerdata = exchangeClient.getExchangeInOut(inCurrency, outCurrency, date);

        if (amount > 0 && amount <= 1000) {
            ExchangeModel result = currencyTotService.calculateConversion(fixerdata, amount);
            return new ResponseEntity(result, HttpStatus.OK);
        } else {
            return new ResponseEntity(fixerdata, HttpStatus.BAD_REQUEST);
        }
    }

}
