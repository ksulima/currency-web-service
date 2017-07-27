package com.ksulima.bussiness_logic_implementation;

import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import com.ksulima.bussiness_logic_interface.service.ConversionService;
import com.ksulima.rest_client.ExchangeClient;
import com.ksulima.util.CurrencyCodeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Krzysztof Sulima on 22.06.2017.
 */
@Service
public class ConversionServiceImpl implements ConversionService {

    @Autowired
    private ExchangeClient exchangeClient;


    @Override
    public ExchangeModel calculateConversion(String base, String currency, Integer amount, String date) {

        if(amount < 0 || amount > 1000000){
            return null;
        }

         CurrencyCodeValidation.isValid(base);
         CurrencyCodeValidation.isValid(currency);


        ExchangeModel response = exchangeClient.getSelectedExRates(base, currency, date);

        Map<String, String> rates = response.getRates();
        for(String key: rates.keySet()){
            BigDecimal rate = new BigDecimal(rates.get(key));
            BigDecimal value = rate.multiply(new BigDecimal(amount));

            rates.put(key, value.toString());
        }
        return response;
    }
}
