package com.ksulima.rest_controller;

import com.ksulima.bussiness_logic_interface.model.CurrencyParams;
import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import com.ksulima.bussiness_logic_interface.service.CurrencyTotService;
import com.ksulima.database.entity.MyCurrency;
import com.ksulima.database.repository.MyCurrencyRepository;
import com.ksulima.rest_client.ExchangeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
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
    private CurrencyTotService currencyTotService;

    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    public ExchangeModel getLatestStandardExRates() {
        return exchangeClient.getLatestStandardExRates();
    }

    @RequestMapping(value = "/{base}/{currency}/{date}", method = RequestMethod.GET)
    public ExchangeModel getSelectedExRates(@PathVariable String base,
                                            @PathVariable String currency,
                                            @PathVariable String date) {
        return exchangeClient.getSelectedExRates(base, currency, date);
    }

    @RequestMapping(value = "/{base}/{currency}/{startDate}/{endDate}", method = RequestMethod.GET)
    public Set<ExchangeModel> getSelectedExRatesFromDefinedPeriod(@PathVariable String base,
                                                                  @PathVariable String currency,
                                                                  @PathVariable String startDate,
                                                                  @PathVariable String endDate){
        Set<ExchangeModel> result = new HashSet<ExchangeModel>();
        for (LocalDate date = LocalDate.parse(startDate); date.isBefore(LocalDate.parse(endDate)); date = date.plusDays(1))
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");
            String formattedString = date.format(formatter);
            try{
                result.add(exchangeClient.getSelectedExRates(base, currency, formattedString));
            }catch(Exception e){
                throw new RuntimeException("Exchange rate not found for date "+formattedString);
            }
        }
        return result;
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

    @RequestMapping("/convert/{base}/{currency}/{amount}/{date}")
    public ResponseEntity<ExchangeModel> calculateConversion(@PathVariable String base,
                                                             @PathVariable String currency,
                                                             @PathVariable Integer amount,
                                                             @PathVariable String date) {
        ExchangeModel fixerdata = exchangeClient.getSelectedExRates(base, currency, date);

        if (amount > 0 && amount <= 1000) {
            ExchangeModel result = currencyTotService.calculateConversion(fixerdata, amount);
            return new ResponseEntity(result, HttpStatus.OK);
        } else {
            return new ResponseEntity(fixerdata, HttpStatus.BAD_REQUEST);
        }
    }

    // fixer --> Database

    @RequestMapping(value = "/save/{base}/{currency}/{startDate}/{endDate}", method = RequestMethod.PUT)
    public String saveSelectedExRatesFromDefinedPeriod(@PathVariable String base,
                                                   @PathVariable String currency,
                                                   @PathVariable String startDate,
                                                   @PathVariable String endDate){

        Set<ExchangeModel> data = getSelectedExRatesFromDefinedPeriod(base, currency, startDate, endDate);
        for(ExchangeModel dataItem: data){
            List<MyCurrency> DatabaseItems = myCurrencyRepository.findByDateAndBaseAndCurrency(dataItem.getDate(), base, currency);
            if(DatabaseItems.size() == 0){
                MyCurrency myCurrency = new MyCurrency();
                myCurrency.setDate(dataItem.getDate());
                myCurrency.setBase(dataItem.getBase());
                myCurrency.setCurrency(currency);
                myCurrency.setRate(dataItem.getRates().get(currency));
                myCurrencyRepository.save(myCurrency);
            }
        }
        return "Saving finished";
    }





}
