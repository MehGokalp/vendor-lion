package com.vendorbear.domain.card;

import com.vendorbear.entitiy.Currency;
import com.vendorbear.repository.CardRepository;
import com.vendorbear.repository.CurrencyRepository;
import com.vendorbear.schema.Card;
import com.vendorbear.domain.card.exception.CurrencyNotFoundException;
import com.vendorbear.service.card.Utils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateCardService extends AbstractCardService {
    private final CurrencyRepository currencyRepository;
    private final Utils utils;

    public CreateCardService(CardRepository cardRepository, CurrencyRepository currencyRepository, Utils utils) {
        super(cardRepository);
        this.currencyRepository = currencyRepository;
        this.utils = utils;
    }

    public Card create(long balance, Date activationDate, Date expireDate, String currencyCode) throws CurrencyNotFoundException {
        Currency currency = this.currencyRepository.findByCode(currencyCode);

        if (currency == null) {
            throw new CurrencyNotFoundException(currencyCode);
        }

        com.vendorbear.entitiy.Card cardEntity = this.createEntity(balance, new java.sql.Date(activationDate.getTime()), new java.sql.Date(expireDate.getTime()), currency);

        this.cardRepository.save(cardEntity);

        return this.buildCardFromEntity(cardEntity);
    }

    private com.vendorbear.entitiy.Card createEntity(long balance, java.sql.Date activationDate, java.sql.Date expireDate, Currency currency) {
        com.vendorbear.entitiy.Card cardEntity = new com.vendorbear.entitiy.Card();

        cardEntity.setActivationDate(activationDate);
        cardEntity.setExpireDate(new java.sql.Date(expireDate.getTime()));
        cardEntity.setBalance(balance);
        cardEntity.setCurrency(currency);
        cardEntity.setCardNumber(utils.generateCardNumber());
        cardEntity.setCvc(utils.generateCvc());
        cardEntity.setReference(utils.generateReference());

        return cardEntity;
    }

    private Card buildCardFromEntity(com.vendorbear.entitiy.Card entity) {
        Card card = new Card();

        card.setReference(entity.getReference());
        card.setActivationDate(entity.getActivationDate());
        card.setExpireDate(entity.getExpireDate());
        card.setBalance(entity.getBalance());
        card.setCurrency(entity.getCurrency().getCode());
        card.setCardNumber(entity.getCardNumber());
        card.setCvc(entity.getCvc());

        return card;
    }
}
