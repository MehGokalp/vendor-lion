package com.vendorlion.domain.card;

import com.vendorlion.domain.card.exception.CardNotFoundException;
import com.vendorlion.entitiy.Card;
import com.vendorlion.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class RemoveCardServiceTest {

    @Test
    public void testSuccessRemove() throws CardNotFoundException {
        CardRepository cardRepositoryMock = Mockito.mock(CardRepository.class);

        Card mockCard = new Card();
        when(cardRepositoryMock.findByReference(anyString())).thenReturn(mockCard);

        RemoveCardService removeCardService = new RemoveCardService(cardRepositoryMock);
        removeCardService.remove("test");

        Mockito.verify(cardRepositoryMock, Mockito.times(1)).delete(mockCard);
    }

    @Test
    public void testCardNotFound() {
        CardRepository cardRepositoryMock = Mockito.mock(CardRepository.class);

        when(cardRepositoryMock.findByReference(anyString())).thenAnswer(invocationOnMock -> {
            throw new CardNotFoundException("UNK");
        });
        RemoveCardService removeCardService = new RemoveCardService(cardRepositoryMock);

        assertThrows(CardNotFoundException.class, () -> {
            removeCardService.remove("test");
        });

    }
}
