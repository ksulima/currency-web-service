package com.ksulima.rest_controller;

import com.ksulima.bussiness_logic_interface.model.CurrencyParams;
import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import com.ksulima.bussiness_logic_interface.service.CurrencyService;
import com.ksulima.database.repository.MyCurrencyRepository;
import com.ksulima.database.service.DictAndRatesService;
import com.ksulima.rest_client.ExchangeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Krzysztof Sulima on 08.03.2017.
 */

@RestController
@RequestMapping("/api")
public class CurrencyExchangeController {


    @Autowired
    private ExchangeClient exchangeClient;
    @Autowired
    private MyCurrencyRepository myCurrencyRepository;

    @Autowired
    DictAndRatesService dictAndRatesService;

    @Autowired
    private CurrencyService currencyService;

    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    public ExchangeModel getLatestStandardExRates() {
        return exchangeClient.getLatestStandardExRates();
    }

    @RequestMapping(value = "/{base}/{currency}/{date}", method = RequestMethod.GET)
    public ExchangeModel getSelectedExRates(@PathVariable String base,
                                            @PathVariable String currency,
                                            @PathVariable String date) {


        return currencyService.getSelectedExRates(base, currency, date);
    }

    @RequestMapping(value = "/{base}/{currency}/period/{startDate}/{endDate}", method = RequestMethod.GET)
    public Set<ExchangeModel> getSelectedExRatesFromDefinedPeriod(@PathVariable String base,
                                                                  @PathVariable String currency,
                                                                  @PathVariable String startDate,
                                                                  @PathVariable String endDate){

        return currencyService.getSelectedExRatesFromDefinedPeriod(base, currency, startDate, endDate);

    }

    @RequestMapping("/latest/base/{base}/currencies/{currencies}")
    public ExchangeModel getLatestExRatesForCurrencies(@PathVariable String base,
                                                       @PathVariable String currencies) {
        return exchangeClient.getLatestExRatesForCurrencies(base, currencies);
    }


    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<ExchangeModel> getSelectedExRates(@RequestBody CurrencyParams currencyParams) {
        return new ResponseEntity(exchangeClient.getSelectedExRates(currencyParams.getBase(), currencyParams.getCurrency(), currencyParams.getDate()), HttpStatus.OK);
    }



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
