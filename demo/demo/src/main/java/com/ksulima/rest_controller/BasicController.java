package com.ksulima.rest_controller;

import com.ksulima.bussiness_logic_interface.service.BetaService;
import com.ksulima.util.CurrencyCodeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Krzysztof Sulima on 21.06.2017.
 */
@RestController
@RequestMapping("/beta")
public class BasicController {

    @Autowired
    private Collection<BetaService> betaServices;

    @RequestMapping("/multiply/{number}")
    public Long multiplyByFive(@PathVariable Long number){
        return number * 5;
    }

    @RequestMapping("/currency/multiply/{value}/{exchangeRatio}")
    public String currencyMultiplier(@RequestParam("base") String base,
                                     @RequestParam("currency") String currency,
                                     @PathVariable Double value,
                                     @PathVariable Double exchangeRatio) {


        boolean baseValidated = CurrencyCodeValidation.isValid(base);
        boolean currencyValidated = CurrencyCodeValidation.isValid(currency);

        if(baseValidated && currencyValidated){
            Double result = value*exchangeRatio;
            BigDecimal bd = new BigDecimal(result);
            bd = bd.setScale(3, RoundingMode.HALF_UP);
            return value + " " + base + " equals " + bd.toString() + " " + currency;
        }

        return "Multiplying not successful";
    }


    @RequestMapping("/currency/all")
    public List<String> allCurrencies() {
        List<String> result = new ArrayList<String>();
        betaServices.forEach((x) -> result.add(x.getCurrency()));
        return result;
    }

    @RequestMapping("/currency/all/status")
    public ResponseEntity<List<String>> allCurrencyStatus() {
        List<String> result = new ArrayList<String>();
        betaServices.forEach((x) -> result.add(x.getCurrency()));
        return new ResponseEntity<List<String>>(result, HttpStatus.ALREADY_REPORTED);
    }

    @RequestMapping("/currency/all/status/dec")
    public List<String> allCurrencyStatusDecorator(HttpServletResponse response) {
        List<String> result = new ArrayList<String>();
        betaServices.forEach((x) -> result.add(x.getCurrency()));
        response.setStatus(200);
        return result;
    }





}
