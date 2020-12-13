package com.vendorlion.domain.card;

import com.vendorlion.domain.card.create.CardFactory;
import com.vendorlion.domain.card.create.CardService;
import com.vendorlion.domain.card.exception.CurrencyNotFoundException;
import com.vendorlion.entity.Currency;
import com.vendorlion.repository.CardRepository;
import com.vendorlion.repository.CurrencyRepository;
import com.vendorlion.web.create.Request;
import com.vendorlion.web.create.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class CreateCardServiceTest {
  private static CardService createCardService;

  @BeforeAll
  static void init() {
    CardRepository cardRepositoryMock = Mockito.mock(CardRepository.class);
    CurrencyRepository currencyRepositoryMock = Mockito.mock(CurrencyRepository.class);

    // Mock card repository's save method
    when(cardRepositoryMock.save(any())).then(returnsFirstArg());

    when(currencyRepositoryMock.findByCode("UNK")).thenReturn(null);

    when(currencyRepositoryMock.findByCode("EUR"))
        .thenAnswer(
            (invocation) -> {
              Currency currency = new Currency();
              currency.setCode(invocation.getArgument(0));
              currency.setId(1);

              return currency;
            });

    CardFactory cardFactory = new CardFactory();

    createCardService = new CardService(cardRepositoryMock, currencyRepositoryMock, cardFactory);
  }

  @Test
  public void testCreate() throws CurrencyNotFoundException {
    Date activationDate = new Date();
    LocalDateTime.from(activationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
        .plusMonths(4);

    Date expireDate = (Date) activationDate.clone();
    LocalDateTime.from(expireDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
        .plusMonths(3);

    Request createCardRequest = new Request();

    createCardRequest
        .setBalance(500)
        .setActivationDate(activationDate)
        .setExpireDate(expireDate)
        .setCurrency("EUR");

    Response response = createCardService.create(createCardRequest);

    assertEquals(createCardRequest.getBalance(), response.balance);
    assertEquals(
        createCardRequest.getActivationDate().getTime(), response.activationDate.getTime());
    assertEquals(createCardRequest.getExpireDate().getTime(), response.expireDate.getTime());
    assertEquals(createCardRequest.getCurrency(), response.currency);

    assertNotNull(response.cardNumber);
    assertEquals(16, response.cardNumber.length());
    assertNotNull(response.cvc);
    assertEquals(3, response.cvc.length());
    assertNotNull(response.reference);
  }

  @Test
  public void testCurrencyNotFound() {
    Request createCardRequest = new Request();

    createCardRequest
        .setBalance(500)
        .setActivationDate(new Date())
        .setExpireDate(new Date())
        .setCurrency("UNK");

    assertThrows(
        CurrencyNotFoundException.class, () -> createCardService.create(createCardRequest));
  }
}
