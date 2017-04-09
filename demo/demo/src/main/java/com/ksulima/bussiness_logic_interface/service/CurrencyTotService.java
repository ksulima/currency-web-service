package com.ksulima.bussiness_logic_interface.service;

import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by Krzysztof Sulima on 01.04.2017.
 */

@Service
public class CurrencyTotService {

    public ExchangeModel calculateConversion(ExchangeModel fixerData, int amount){
        Map<String, String> rates = fixerData.getRates();
        for(String key: rates.keySet()){
            BigDecimal rate = new BigDecimal(rates.get(key));
            BigDecimal value = rate.multiply(new BigDecimal(amount));

            rates.put(key, value.toString());
        }
        return fixerData;
    }


}



