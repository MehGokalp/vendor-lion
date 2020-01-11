package com.vendorbear.service.card.exception;

public class CardNotFoundException extends Exception {
    public CardNotFoundException(String cardReference) {
        super("Card not found with given reference:" + cardReference);
    }
}
