package com.ksulima.rest_client;

import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Krzysztof Sulima on 19.03.2017.
 */

@Service
public class ExchangeClient {

    private String url = "http://api.fixer.io/latest?symbols=USD,GBP,PLN&base=EUR";

    @Autowired
    private RestTemplate rest;

    public ExchangeModel getLatestStandardExRates(){
        return rest.getForObject(url, ExchangeModel.class);
    }

    public ExchangeModel getSelectedExRates(String base, String currency, String date){
        return rest.getForObject(urlParamForSelectedCurrency(base, currency, date), ExchangeModel.class);
    }

    private String urlParamForSelectedCurrency(String base, String currency, String date){
        return "http://api.fixer.io/"+date+"?symbols="+currency+"&base=" + base;
    }

    public ExchangeModel getLatestExRatesForCurrencies(String base, String currencies){
        return rest.getForObject(urlParamForCurrencies(base, currencies), ExchangeModel.class);
    }

    private String urlParamForCurrencies(String base, String currencies){
        List<String> items = Arrays.asList(currencies.split(","));
        String url = "http://api.fixer.io/latest?symbols=";
        for(String item : items){
            url += item;
            url += ",";
        }
        url = url.substring(0, url.length()-1);
        url += "&base=" + base;
        return url;
    }

}
