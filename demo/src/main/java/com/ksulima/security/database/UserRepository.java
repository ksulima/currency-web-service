package com.ksulima.security.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Krzysztof Sulima on 08.08.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);
    User findById(Long id);
}