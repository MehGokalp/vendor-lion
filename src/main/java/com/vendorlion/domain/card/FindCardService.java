package com.vendorlion.domain.card;

import com.vendorlion.entitiy.Card;
import com.vendorlion.repository.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class FindCardService extends AbstractCardService {
    public FindCardService(CardRepository cardRepository) {
        super(cardRepository);
    }

    public com.vendorlion.schema.Card find(String reference) {
        Card card = this.cardRepository.findByReference(reference);

        if (card == null) {
            return null;
        }

        com.vendorlion.schema.Card schema = new com.vendorlion.schema.Card();
        schema.setBalance(card.getBalance());
        schema.setReference(card.getReference());
        schema.setCvc(card.getCvc());
        schema.setCardNumber(card.getCardNumber());
        schema.setCurrency(card.getCurrency().getCode());
        schema.setActivationDate(card.getActivationDate());
        schema.setExpireDate(card.getExpireDate());

        return schema;
    }
}
