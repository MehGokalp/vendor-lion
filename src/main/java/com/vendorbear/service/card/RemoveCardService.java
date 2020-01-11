package com.vendorbear.service.card;

import com.vendorbear.entitiy.Card;
import com.vendorbear.repository.CardRepository;
import com.vendorbear.service.card.exception.CardNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RemoveCardService extends AbstractCardService {

    final FindCardService findCardService;

    public RemoveCardService(CardRepository cardRepository, FindCardService findCardService) {
        super(cardRepository);
        this.findCardService = findCardService;
    }

    public void remove(String cardReference) throws CardNotFoundException {
        Card card = this.cardRepository.findByReference(cardReference);

        if (card == null) {
            throw new CardNotFoundException(cardReference);
        }

        this.cardRepository.delete(card);
    }
}
