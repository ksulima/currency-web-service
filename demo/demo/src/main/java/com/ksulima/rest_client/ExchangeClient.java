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

    private String url = "http://api.fixer.io/latest?symbols=USD,GBP,NOK&base=EUR";

    @Autowired
    private RestTemplate rest;

    public ExchangeModel getExchange(){
        return rest.getForObject(url, ExchangeModel.class);
    }

    public ExchangeModel getExchangeInOut(String inCurrency, String outCurrency, String date){
        return rest.getForObject(urlModif(inCurrency, outCurrency, date), ExchangeModel.class);
    }

    private String urlModif(String inCurrency, String outCurrency, String date){
        return "http://api.fixer.io/"+date+"?symbols="+outCurrency+"&base=" + inCurrency;
    }

    public ExchangeModel getMultiExchangeInOut(String inCurrency, String outCurrencies){
        return rest.getForObject(urlMultiModif(inCurrency, outCurrencies), ExchangeModel.class);
    }

    private String urlMultiModif(String inCurrency, String outCurrencies){
        List<String> items = Arrays.asList(outCurrencies.split("-"));
        String url = "http://api.fixer.io/latest?symbols=";
        for(String item : items){
            url += item;
            url += ",";
        }
        url = url.substring(0, url.length()-1);
        url += "&base=" + inCurrency;
        return url;
    }




}
