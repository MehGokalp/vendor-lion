package com.vendorlion.repository;

import com.vendorlion.entitiy.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {
    Card findByReference(String reference);
}
