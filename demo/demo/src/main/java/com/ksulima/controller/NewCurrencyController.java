package com.ksulima.controller;

import com.ksulima.model.ExchangeModel;
import com.ksulima.rest.client.ExchangeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Krzysztof Sulima on 19.03.2017.
 */
public class NewCurrencyController {

    @Autowired
    private ExchangeClient exchangeClient;

    @RequestMapping("/currency")
    public ExchangeModel getExchange(){
        return exchangeClient.getExchange();
    }
}
