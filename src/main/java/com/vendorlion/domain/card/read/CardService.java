package com.vendorlion.domain.card.read;

import com.vendorlion.domain.card.AbstractCardService;
import com.vendorlion.entity.Card;
import com.vendorlion.repository.CardRepository;
import com.vendorlion.web.create.Response;
import org.springframework.stereotype.Component;

@Component("readCardService")
public class CardService extends AbstractCardService {
  public CardService(CardRepository cardRepository) {
    super(cardRepository);
  }

  public Response find(String reference) {
    Card card = this.cardRepository.findByReference(reference);

    if (card == null) {
      return null;
    }

    Response response = new Response();
    response.balance = card.getBalance();
    response.reference = card.getReference();
    response.cvc = card.getCvc();
    response.cardNumber = card.getCardNumber();
    response.currency = card.getCurrency().getCode();
    response.activationDate = card.getActivationDate();
    response.expireDate = card.getExpireDate();

    return response;
  }
}
