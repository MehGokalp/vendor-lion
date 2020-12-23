package com.vendorlion.web;

import com.vendorlion.domain.card.create.CardFactory;
import com.vendorlion.entity.Card;
import com.vendorlion.entity.Currency;
import com.vendorlion.library.DateHelper;
import com.vendorlion.repository.CardRepository;
import com.vendorlion.repository.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CRUDCardTest {
  private final String TEST_AUTH_USER = "admin";
  private final String TEST_AUTH_PASSWORD = "password";

  @Autowired private MockMvc mockMvc;

  @Autowired private CardFactory cardFactory;

  @Autowired private CardRepository cardRepository;

  @Autowired private CurrencyRepository currencyRepository;

  private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

  protected Card insertDummyCard() {
    Date activationDate = new Date();
    DateHelper.createLocalDateTimeFrom(activationDate).plusMonths(4);

    Date expireDate = (Date) activationDate.clone();
    DateHelper.createLocalDateTimeFrom(expireDate).plusMonths(3);

    Currency currency = this.currencyRepository.findByCode("EUR");

    Card card = cardFactory.create(5000, activationDate, expireDate, currency);
    this.cardRepository.save(card);

    return card;
  }

  @Test
  public void testSuccessCreate() throws Exception {
    Date activationDate = new Date();
    DateHelper.createLocalDateTimeFrom(activationDate).plusMonths(4);
    String formattedActivationDate = dateFormatter.format(activationDate);

    Date expireDate = (Date) activationDate.clone();
    DateHelper.createLocalDateTimeFrom(expireDate).plusMonths(3);
    String formattedExpireDate = dateFormatter.format(expireDate);

    this.mockMvc
        .perform(
            post("/")
                .with(httpBasic(this.TEST_AUTH_USER, this.TEST_AUTH_PASSWORD))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "application/json")
                .content(
                    "currency=EUR&activationDate="
                        + formattedActivationDate
                        + "&expireDate="
                        + formattedExpireDate
                        + "&balance=5000"))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.currency", is("EUR")))
        .andExpect(jsonPath("$.activationDate", is(formattedActivationDate)))
        .andExpect(jsonPath("$.expireDate", is(formattedExpireDate)))
        .andExpect(jsonPath("$.reference", isA(String.class)))
        .andExpect(jsonPath("$.balance", is(5000)))
        .andExpect(jsonPath("$.cardNumber", matchesRegex("\\d{16}")));
  }

  @Test
  public void testReadSuccessResponse() throws Exception {
    Card card = this.insertDummyCard();

    this.mockMvc
        .perform(
            get("/" + card.getReference())
                .with(httpBasic(this.TEST_AUTH_USER, this.TEST_AUTH_PASSWORD)))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.currency", is(card.getCurrency().getCode())))
        .andExpect(
            jsonPath("$.activationDate", is(this.dateFormatter.format(card.getActivationDate()))))
        .andExpect(jsonPath("$.expireDate", is(this.dateFormatter.format(card.getExpireDate()))))
        .andExpect(jsonPath("$.reference", is(card.getReference())))
        .andExpect(jsonPath("$.balance", is((int) card.getBalance())))
        .andExpect(jsonPath("$.cardNumber", is(card.getCardNumber())));
  }

  @Test
  public void testReadNotFoundResponse() throws Exception {
    this.mockMvc
        .perform(get("/not-found").with(httpBasic(this.TEST_AUTH_USER, this.TEST_AUTH_PASSWORD)))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  public void testDeleteSuccess() throws Exception {
    Card card = this.insertDummyCard();
    this.mockMvc
        .perform(
            delete("/" + card.getReference())
                .with(httpBasic(this.TEST_AUTH_USER, this.TEST_AUTH_PASSWORD)))
        .andDo(print())
        .andExpect(status().isNoContent());
  }

  @Test
  public void testCreateNoAuthenticationResponse() throws Exception {
    this.mockMvc.perform(post("/")).andDo(print()).andExpect(status().isUnauthorized());
  }

  @Test
  public void testReadNoAuthenticationResponse() throws Exception {
    this.mockMvc.perform(get("/not-found")).andDo(print()).andExpect(status().isUnauthorized());
  }

  @Test
  public void testDeleteNoAuthenticationResponse() throws Exception {
    this.mockMvc
        .perform(delete("/ddf49b87-5f70-4cb6-919f-24ef84361005"))
        .andDo(print())
        .andExpect(status().isUnauthorized());
  }
}
