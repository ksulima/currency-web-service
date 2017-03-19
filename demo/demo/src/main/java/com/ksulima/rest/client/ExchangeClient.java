package com.ksulima.rest.client;

import com.ksulima.model.ExchangeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Krzysztof Sulima on 19.03.2017.
 */

@Service
public class ExchangeClient {

    private String url = "http://api.fixer.io/latest?symbols=USD,GBP&base=PLN";

    @Autowired
    private RestTemplate rest;

    public ExchangeModel getExchange(){
        return rest.getForObject(url, ExchangeModel.class);
    }


}
