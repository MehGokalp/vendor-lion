package com.vendorlion.domain.card.delete;

import com.vendorlion.domain.card.AbstractCardService;
import com.vendorlion.domain.card.exception.CardNotFoundException;
import com.vendorlion.entity.Card;
import com.vendorlion.repository.CardRepository;
import org.springframework.stereotype.Component;

@Component("deleteCardService")
public class CardService extends AbstractCardService {

  public CardService(CardRepository cardRepository) {
    super(cardRepository);
  }

  public void remove(String cardReference) throws CardNotFoundException {
    Card card = this.cardRepository.findByReference(cardReference);

    if (card == null) {
      throw new CardNotFoundException(cardReference);
    }

    this.cardRepository.delete(card);
  }
}
