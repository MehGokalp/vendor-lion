package com.vendorbear.domain.card;

import com.vendorbear.entitiy.Card;
import com.vendorbear.repository.CardRepository;
import com.vendorbear.domain.card.exception.CardNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RemoveCardService extends AbstractCardService {

    public RemoveCardService(CardRepository cardRepository) {
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
