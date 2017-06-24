package com.ksulima.bussiness_logic_interface.service;

import com.ksulima.bussiness_logic_interface.model.ExchangeModel;

import java.util.Set;

/**
 * Created by Krzysztof Sulima on 22.06.2017.
 */
public interface CurrencyService {


    Set<ExchangeModel> getSelectedExRatesFromDefinedPeriod(String base, String currency, String startDate, String endDate);


    ExchangeModel getSelectedExRates(String base, String currency, String date);

    void saveSelectedExRatesFromDefinedPeriod(String base, String currency, String startDate, String endDate);

    void saveSelectedExRates(String base, String currency, String date);
}
