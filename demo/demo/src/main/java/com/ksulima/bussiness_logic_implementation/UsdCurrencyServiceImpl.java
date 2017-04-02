package com.ksulima.bussiness_logic_implementation;

import com.ksulima.bussiness_logic_interface.service.CurrencyService;
import org.springframework.stereotype.Service;

/**
 * Created by Krzysztof Sulima on 15.03.2017.
 */

@Service
public class UsdCurrencyServiceImpl implements CurrencyService {

    @Override
    public String getCurrency() {
        return "4.10 USD/PLN";
    }
}
