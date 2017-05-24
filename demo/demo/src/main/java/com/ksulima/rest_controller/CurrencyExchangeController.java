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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @RequestMapping("get/period/{inCurrency}/{outCurrency}/{startDate}/{endDate}")
    public Set<ExchangeModel> getExchangeFromPeriod(@PathVariable String inCurrency,
                                                     @PathVariable String outCurrency,
                                                     @PathVariable String startDate,
                                                     @PathVariable String endDate){
        Set<ExchangeModel> result = new HashSet<ExchangeModel>();
        for (LocalDate date = LocalDate.parse(startDate); date.isBefore(LocalDate.parse(endDate)); date = date.plusDays(1))
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");
            String formattedString = date.format(formatter);
            try{
                result.add(exchangeClient.getExchangeInOut(inCurrency, outCurrency, formattedString));
            }catch(Exception e){
                throw new RuntimeException("Exchange rate not found for date "+formattedString);
            }
        }
        return result;
    }


    @Autowired
    private CurrencyRepository currencyRepository;

    @RequestMapping("/save/{inCurrency}/{outCurrency}/{date}")
    public String saveCurrencyToDatabase(@PathVariable String inCurrency,
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

    @RequestMapping("save/period/{inCurrency}/{outCurrency}/{startDate}/{endDate}")
    public String saveCurrencyFromPeriodToDatabase(@PathVariable String inCurrency,
                                                   @PathVariable String outCurrency,
                                                   @PathVariable String startDate,
                                                   @PathVariable String endDate){

        Set<ExchangeModel> data = getExchangeFromPeriod(inCurrency, outCurrency, startDate, endDate);
        for(ExchangeModel dataItem: data){
            List<MyCurrency> DatabaseItems = currencyRepository.findByDateAndBaseAndWaluta(dataItem.getDate(), inCurrency, outCurrency);
            if(DatabaseItems.size() == 0){
                MyCurrency myCurrency = new MyCurrency();
                myCurrency.setDate(dataItem.getDate());
                myCurrency.setBase(dataItem.getBase());
                myCurrency.setWaluta(outCurrency);
                myCurrency.setRate(dataItem.getRates().get(outCurrency));
                currencyRepository.save(myCurrency);
            }
        }
        return "Saving finished";
    }



    @RequestMapping("/multi/{inCurrency}/{outCurrencies}")
    public ExchangeModel getMultiExchange(@PathVariable String inCurrency,
                                          @PathVariable String outCurrencies) {
        return exchangeClient.getMultiExchangeInOut(inCurrency, outCurrencies);
    }

    @Autowired
    private CurrencyTotService currencyTotService;


    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity<ExchangeModel> getExchangeInOut(@RequestBody CurrencyParams param) {
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
