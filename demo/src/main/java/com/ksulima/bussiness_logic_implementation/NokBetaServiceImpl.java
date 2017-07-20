package com.ksulima.bussiness_logic_implementation;

import com.ksulima.bussiness_logic_interface.service.BetaService;
import org.springframework.stereotype.Service;

/**
 * Created by Krzysztof Sulima on 19.07.2017.
 */
@Service
public class NokBetaServiceImpl implements BetaService {

    @Override
    public String getCurrency() {
        return "0,45 NOK/PLN";
    }
}
