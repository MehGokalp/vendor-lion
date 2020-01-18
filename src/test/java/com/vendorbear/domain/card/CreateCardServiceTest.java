package com.vendorbear.domain.card;

import com.vendorbear.domain.card.exception.CurrencyNotFoundException;
import com.vendorbear.entitiy.Currency;
import com.vendorbear.repository.CardRepository;
import com.vendorbear.repository.CurrencyRepository;
import com.vendorbear.schema.Card;
import com.vendorbear.service.card.Utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.*;
import static org.mockito.Mockito.*;

public class CreateCardServiceTest {
    private static CreateCardService createCardService;

    @BeforeAll
    static void init() {
        CardRepository cardRepositoryMock = Mockito.mock(CardRepository.class);
        CurrencyRepository currencyRepositoryMock = Mockito.mock(CurrencyRepository.class);
        Utils utils = new Utils();

        // Mock card repository's save method
        when(cardRepositoryMock.save(any())).then(returnsFirstArg());

        when(currencyRepositoryMock.findByCode("UNK")).thenReturn(null);

        when(currencyRepositoryMock.findByCode("EUR")).thenAnswer((invocation) -> {
            Currency currency = new Currency();
            currency.setCode(invocation.getArgument(0));
            currency.setId(1);

            return currency;
        });

        createCardService = new CreateCardService(cardRepositoryMock, currencyRepositoryMock, utils);
    }

    @Test
    public void testCreate() throws CurrencyNotFoundException {
        int balance = 500;
        Date activationDate = new Date();
        Date expireDate = new Date();
        String currency = "EUR";

        Card cardSchema = createCardService.create(balance, activationDate, expireDate, currency);

        assertEquals(balance, cardSchema.getBalance());
        assertEquals(activationDate.getTime(), cardSchema.getActivationDate().getTime());
        assertEquals(expireDate.getTime(), cardSchema.getExpireDate().getTime());
        assertEquals(currency, cardSchema.getCurrency());

        assertNotNull(cardSchema.getCardNumber());
        assertEquals(16, cardSchema.getCardNumber().length());
        assertNotNull(cardSchema.getCvc());
        assertEquals(3, cardSchema.getCvc().length());
        assertNotNull(cardSchema.getReference());
    }

    @Test
    public void testCurrencyNotFound() {
        assertThrows(CurrencyNotFoundException.class, () -> {
            createCardService.create(500, new Date(), new Date(), "UNK");
        });
    }
}
