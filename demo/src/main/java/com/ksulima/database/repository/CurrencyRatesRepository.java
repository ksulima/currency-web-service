package com.ksulima.database.repository;

import com.ksulima.database.entity.CurrencyRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Krzysztof Sulima on 15.06.2017.
 */
@Repository
public interface CurrencyRatesRepository extends JpaRepository<CurrencyRates, Long> {

    CurrencyRates findByRatesPkCurrencyId(Long id);

    List<CurrencyRates> findByCurrencyDictDictPkBaseCode(String code);

    List<CurrencyRates> findByCurrencyDictDictPkBaseCodeAndRatesPkDate(String code, String date);
}
