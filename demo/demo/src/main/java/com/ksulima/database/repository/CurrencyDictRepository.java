package com.ksulima.database.repository;

import com.ksulima.database.entity.CurrencyDict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Krzysztof Sulima on 15.06.2017.
 */
@Repository
public interface CurrencyDictRepository extends JpaRepository<CurrencyDict, Long> {

    CurrencyDict findByDictPkDictId(Long id);
//    CurrencyDict findByBaseCode(String code);


}
