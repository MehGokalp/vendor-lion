package com.vendorlion.domain.card.create;

import com.vendorlion.entity.Card;
import com.vendorlion.entity.Currency;
import com.vendorlion.library.Helper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CardFactory {
  private final Helper helper = new Helper();

  public Card create(long balance, Date activationDate, Date expireDate, Currency currency) {
    Card card = new Card();

    card.setActivationDate(new java.sql.Date(activationDate.getTime()))
        .setExpireDate(new java.sql.Date(expireDate.getTime()))
        .setBalance(balance)
        .setCurrency(currency)
        .setCardNumber(helper.generateCardNumber())
        .setCvc(helper.generateCvc())
        .setReference(helper.generateReference());

    return card;
  }
}
