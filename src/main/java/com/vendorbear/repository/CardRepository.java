package com.vendorbear.repository;

import com.vendorbear.entitiy.Card;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

    @Query("SELECT c FROM Card AS c WHERE c.isDeleted = false AND c.reference = ?1")
    Card findByReference(String reference);
}
