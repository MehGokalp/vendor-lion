package com.vendorlion.web.create;

import com.vendorlion.entity.Card;

import java.util.Date;

public class Response {
  public long balance;
  public String currency;
  public Date activationDate;
  public Date expireDate;
  public String reference;
  public String cardNumber;
  public String cvc;

  public Response() {}

  public Response(Card card) {
    this.reference = card.getReference();
    this.activationDate = (Date) card.getActivationDate().clone();
    this.expireDate = (Date) card.getExpireDate().clone();
    this.balance = card.getBalance();
    this.currency = card.getCurrency().getCode();
    this.cardNumber = card.getCardNumber();
    this.cvc = card.getCvc();
  }
}
