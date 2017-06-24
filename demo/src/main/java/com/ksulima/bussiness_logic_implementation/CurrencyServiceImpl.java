package com.ksulima.bussiness_logic_implementation;

import com.ksulima.bussiness_logic_interface.model.ExchangeModel;
import com.ksulima.bussiness_logic_interface.service.CurrencyService;
import com.ksulima.database.entity.CurrencyDict;
import com.ksulima.database.entity.CurrencyRates;
import com.ksulima.database.entity.MyCurrency;
import com.ksulima.database.repository.MyCurrencyRepository;
import com.ksulima.database.service.DictAndRatesService;
import com.ksulima.rest_client.ExchangeClient;
import com.ksulima.util.CurrencyCodeValidation;
import com.ksulima.util.NotSavedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Krzysztof Sulima on 22.06.2017.
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private ExchangeClient exchangeClient;
    @Autowired
    private MyCurrencyRepository myCurrencyRepository;
    @Autowired
    private DictAndRatesService dictAndRatesService;


    @Override
    public ExchangeModel getSelectedExRates(String base, String currency, String date) {

        CurrencyCodeValidation.isValid(base);
        CurrencyCodeValidation.isValid(currency);
        return exchangeClient.getSelectedExRates(base, currency, date);
    }

    @Override
    public Set<ExchangeModel> getSelectedExRatesFromDefinedPeriod(String base, String currency, String startDate, String endDate) {

        CurrencyCodeValidation.isValid(base);
        CurrencyCodeValidation.isValid(currency);

        Set<ExchangeModel> result = new HashSet<ExchangeModel>();
        for (LocalDate date = LocalDate.parse(startDate); date.isBefore(LocalDate.parse(endDate)); date = date.plusDays(1))
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd");
            String formattedString = date.format(formatter);

            result.add(exchangeClient.getSelectedExRates(base, currency, formattedString));

        }
        return result;
    }

    @Override
    public void saveSelectedExRatesFromDefinedPeriod(String base, String currency, String startDate, String endDate) {

        CurrencyCodeValidation.isValid(base);
        CurrencyCodeValidation.isValid(currency);

        Set<ExchangeModel> response = getSelectedExRatesFromDefinedPeriod(base, currency, startDate, endDate);

        for(ExchangeModel responseItem: response){
            List<MyCurrency> databaseItems = myCurrencyRepository.findByDateAndBaseAndCurrency(responseItem.getDate(), responseItem.getBase(), responseItem.getRates().get(currency));
            if(databaseItems.size() == 0){
                MyCurrency myCurrency = new MyCurrency();
                myCurrency.setDate(responseItem.getDate());
                myCurrency.setBase(responseItem.getBase());
                myCurrency.setCurrency(currency);
                myCurrency.setRate(responseItem.getRates().get(currency));
                myCurrencyRepository.save(myCurrency);
            }
        }

    }


    @Override
    public void saveSelectedExRates(String base, String currency, String date) {

        CurrencyCodeValidation.isValid(base);
        CurrencyCodeValidation.isValid(currency);

        try{
            ExchangeModel item = exchangeClient.getSelectedExRates(base, currency, date);
            CurrencyDict currencyDict = dictAndRatesService.findDictByCode(item.getBase());

            if(currencyDict == null){
                dictAndRatesService.addDictRecord(item.getBase(), item.getBase());
            }

            CurrencyRates currencyRates = new CurrencyRates();
            currencyRates.getRatesPk().setDate(item.getDate());
            if(item.getRates().containsKey(currency)){
                currencyRates.getRatesPk().setCurrency(currency);
            }
            currencyRates.setRate(item.getRates().get(currency));
            dictAndRatesService.addRatesRecord(currencyRates, item.getBase());

        }catch (NotSavedException e){
            e.printStackTrace();
        }

    }


}
