package com.ksulima.service.impl;

import com.ksulima.model.CurrencyService;
import org.springframework.stereotype.Service;

/**
 * Created by Krzysztof Sulima on 15.03.2017.
 */
@Service("eur")
public class EurCurrencyServiceImpl implements CurrencyService {

    @Override
    public String getCurrency() {
        return "4.35 EUR/PLN";
    }
}
