package com.ksulima.database.repository;

import com.ksulima.database.entity.MyCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Krzysztof Sulima on 03.04.2017.
 */

@Repository
public interface CurrencyRepository extends JpaRepository<MyCurrency, Long> {

    MyCurrency findById(Long id);

    MyCurrency findFirst1ByOrderByIdDesc();
}

