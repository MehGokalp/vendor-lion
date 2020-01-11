package com.vendorbear.repository;

import com.vendorbear.entitiy.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    Currency findByCode(String code);
}
