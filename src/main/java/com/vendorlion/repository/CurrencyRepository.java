package com.vendorlion.repository;

import com.vendorlion.entity.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
  Currency findByCode(String code);
}
