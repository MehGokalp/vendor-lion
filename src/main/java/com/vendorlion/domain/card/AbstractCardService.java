package com.vendorlion.domain.card;

import com.vendorlion.repository.CardRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractCardService {
    protected final CardRepository cardRepository;

    public AbstractCardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }
}
