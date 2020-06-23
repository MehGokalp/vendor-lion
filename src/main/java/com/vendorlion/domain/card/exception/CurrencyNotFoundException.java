package com.vendorlion.domain.card.exception;

public class CurrencyNotFoundException extends Exception {
    public CurrencyNotFoundException(String code) {
        super("Currency not found with given code:" + code);
    }
}
