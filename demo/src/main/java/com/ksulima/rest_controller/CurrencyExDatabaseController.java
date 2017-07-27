package com.ksulima.rest_controller;

import com.ksulima.bussiness_logic_interface.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Krzysztof Sulima on 21.07.2017.
 */
@RestController
@RequestMapping("/api")
public class CurrencyExDatabaseController {


    @Autowired
    private CurrencyService currencyService;


    // fixer --> currencyarchive

    @RequestMapping(value = "/save/{base}/{currency}/period/{startDate}/{endDate}", method = RequestMethod.POST)
    public void saveSelectedExRatesFromDefinedPeriod(@PathVariable String base,
                                                     @PathVariable String currency,
                                                     @PathVariable String startDate,
                                                     @PathVariable String endDate){

        currencyService.saveSelectedExRatesFromDefinedPeriod(base, currency, startDate, endDate);
    }

    // fixer --> currency_dict, currency_rates

    @RequestMapping(value = "/save/{base}/{currency}/{date}", method = RequestMethod.POST)
    public void saveSelectedExRates(@PathVariable String base,
                                    @PathVariable String currency,
                                    @PathVariable String date){

        currencyService.saveSelectedExRates(base, currency, date);

    }


}
