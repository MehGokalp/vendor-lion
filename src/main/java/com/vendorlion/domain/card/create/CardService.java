package com.vendorlion.domain.card.create;

import com.vendorlion.domain.card.AbstractCardService;
import com.vendorlion.domain.card.exception.CurrencyNotFoundException;
import com.vendorlion.entity.Card;
import com.vendorlion.entity.Currency;
import com.vendorlion.repository.CardRepository;
import com.vendorlion.repository.CurrencyRepository;
import com.vendorlion.web.create.Request;
import com.vendorlion.web.create.Response;
import org.springframework.stereotype.Component;

@Component("createCardService")
public class CardService extends AbstractCardService {
  private final CurrencyRepository currencyRepository;

  private final CardFactory cardFactory;

  public CardService(
      CardRepository cardRepository,
      CurrencyRepository currencyRepository,
      CardFactory cardFactory) {
    super(cardRepository);
    this.currencyRepository = currencyRepository;
    this.cardFactory = cardFactory;
  }

  public Response create(Request request) throws CurrencyNotFoundException {
    Currency currency = this.currencyRepository.findByCode(request.getCurrency());

    if (currency == null) {
      throw new CurrencyNotFoundException(request.getCurrency());
    }

    Card card =
        cardFactory.create(
            request.getBalance(),
            new java.sql.Date(request.getActivationDate().getTime()),
            new java.sql.Date(request.getExpireDate().getTime()),
            currency);

    this.cardRepository.save(card);

    return new Response(card);
  }
}
