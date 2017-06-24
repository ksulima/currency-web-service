package com.ksulima.bussiness_logic_implementation;

import com.ksulima.bussiness_logic_interface.service.BetaService;
import org.springframework.stereotype.Service;

/**
 * Created by Krzysztof Sulima on 15.03.2017.
 */

@Service
public class EurBetaServiceImpl implements BetaService {

    @Override
    public String getCurrency() {
        return "4.35 EUR/PLN";
    }

}
