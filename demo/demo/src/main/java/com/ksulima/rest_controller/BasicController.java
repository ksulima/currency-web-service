package com.ksulima.rest_controller;

import com.ksulima.bussiness_logic_interface.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;

/**
 * Created by Krzysztof Sulima on 21.06.2017.
 */
@RestController
@RequestMapping("/beta")
public class BasicController {

    @Autowired
    private Collection<CurrencyService> currencyServices;


    @RequestMapping("/currency/{number}")
    public Long multiplyByFive(@PathVariable Long number) {
        return number * 5;
    }


    @RequestMapping("/currency/multiply/{value}/{exchangeRatio}")
    public String currencyMultiplier(@RequestParam("currFrom") String currFrom,
                                     @RequestParam("currTo") String currTo,
                                     @PathVariable Double value,
                                     @PathVariable Double exchangeRatio) {

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
        Double result = value*exchangeRatio;
        BigDecimal bd = new BigDecimal(result);
        bd = bd.setScale(3, RoundingMode.HALF_UP);
        return value + " " + currFrom + " equals " + bd.toString() + " " + currTo;
    }


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





}
