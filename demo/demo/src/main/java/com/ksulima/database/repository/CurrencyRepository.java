package com.ksulima.database.repository;

import com.ksulima.database.entity.MyCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Krzysztof Sulima on 03.04.2017.
 */

@Repository
public interface CurrencyRepository extends JpaRepository<MyCurrency, Long> {

    MyCurrency findById(Long id);

    MyCurrency findFirst1ByOrderByIdDesc();

    List<MyCurrency> findByDateAndBaseAndWaluta(String date, String base, String waluta);

<<<<<<< HEAD
=======

<<<<<<< HEAD
=======

//    @Query("select count(u) from  MyCurrency u where u.name = ?1")
//    Long checkIfdataExist(String name);
>>>>>>> dc675a363da2a34cf5306099dd7008e0f0f30327

>>>>>>> c0b8253a0c4977ea974b7407441248be2e24105f
}

