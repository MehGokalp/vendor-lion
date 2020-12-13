package com.vendorlion.domain.card;

import com.vendorlion.domain.card.read.CardService;
import com.vendorlion.entity.Currency;
import com.vendorlion.repository.CardRepository;
import com.vendorlion.web.create.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FindCardServiceTest {

    @Test
    public void testFind() {
        CardRepository cardRepositoryMock = Mockito.mock(CardRepository.class);
        com.vendorlion.entity.Card testCard = new com.vendorlion.entity.Card();

        int balance = 500;
        Date activationDate = new Date();
        Date expireDate = new Date();
        String currencyCode = "EUR";
        String cardNumber = "4065972557141631";
        String cvc = "500";
        String reference = "ddf49b87-5f70-4cb6-919f-24ef84361005";

        Currency currency = new Currency();
        currency.setId(1);
        currency.setCode(currencyCode);

        testCard
                .setCvc(cvc)
                .setCardNumber(cardNumber)
                .setCurrency(currency)
                .setReference(reference)
                .setBalance(balance)
                .setActivationDate(new java.sql.Date(activationDate.getTime()))
                .setExpireDate(new java.sql.Date(expireDate.getTime()))
                .setId(1)
        ;

        when(cardRepositoryMock.findByReference(anyString())).thenReturn(testCard);
        CardService findCardService = new CardService(cardRepositoryMock);

        Response response = findCardService.find(reference);

        assertEquals(balance, response.balance);
        assertEquals(activationDate.getTime(), response.activationDate.getTime());
        assertEquals(expireDate.getTime(), response.expireDate.getTime());
        assertEquals(currencyCode, response.currency);

        assertNotNull(response.cardNumber);
        assertEquals(16, response.cardNumber.length());
        assertNotNull(response.cvc);
        assertEquals(3, response.cvc.length());
        assertNotNull(response.reference);
    }

    @Test
    public void findNull() {
        CardRepository cardRepositoryMock = Mockito.mock(CardRepository.class);
        when(cardRepositoryMock.findByReference(anyString())).thenReturn(null);

        CardService findCardService = new CardService(cardRepositoryMock);

        Response nullCard = findCardService.find("nonexists");

        assertNull(nullCard);
    }
}
