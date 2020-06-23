package com.vendorlion.domain.card;

import com.vendorlion.entitiy.Currency;
import com.vendorlion.repository.CardRepository;
import com.vendorlion.schema.Card;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FindCardServiceTest {

    @Test
    public void testFind() {
        CardRepository cardRepositoryMock = Mockito.mock(CardRepository.class);
        com.vendorlion.entitiy.Card testCard = new com.vendorlion.entitiy.Card();

        int balance = 500;
        Date activationDate = new Date();
        Date expireDate = new Date();
        String currencyCode = "EUR";
        String cardNumber = "4065972557141631";
        String cvc = "500";
        String reference = "ddf49b87-5f70-4cb6-919f-24ef84361005";

        testCard.setCvc(cvc);
        testCard.setCardNumber(cardNumber);
        Currency currency = new Currency();
        currency.setId(1);
        currency.setCode(currencyCode);
        testCard.setCurrency(currency);
        testCard.setReference(reference);
        testCard.setBalance(balance);
        testCard.setActivationDate(new java.sql.Date(activationDate.getTime()));
        testCard.setExpireDate(new java.sql.Date(expireDate.getTime()));
        testCard.setId(1);

        when(cardRepositoryMock.findByReference(anyString())).thenReturn(testCard);
        FindCardService findCardService = new FindCardService(cardRepositoryMock);

        Card cardSchema = findCardService.find(reference);

        assertEquals(balance, cardSchema.getBalance());
        assertEquals(activationDate.getTime(), cardSchema.getActivationDate().getTime());
        assertEquals(expireDate.getTime(), cardSchema.getExpireDate().getTime());
        assertEquals(currencyCode, cardSchema.getCurrency());

        assertNotNull(cardSchema.getCardNumber());
        assertEquals(16, cardSchema.getCardNumber().length());
        assertNotNull(cardSchema.getCvc());
        assertEquals(3, cardSchema.getCvc().length());
        assertNotNull(cardSchema.getReference());
    }

    @Test
    public void findNull() {
        CardRepository cardRepositoryMock = Mockito.mock(CardRepository.class);
        when(cardRepositoryMock.findByReference(anyString())).thenReturn(null);

        FindCardService findCardService = new FindCardService(cardRepositoryMock);

        Card nullCard = findCardService.find("nonexists");

        assertNull(nullCard);
    }
}
