package com.ksulima.bussiness_logic_interface.service;

import com.ksulima.bussiness_logic_interface.model.ExchangeModel;

/**
 * Created by Krzysztof Sulima on 22.06.2017.
 */
public interface ConversionService {

     ExchangeModel calculateConversion(String base, String currency, Integer amount, String date);
}
