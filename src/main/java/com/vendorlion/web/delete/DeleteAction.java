package com.vendorlion.web.delete;

import com.vendorlion.domain.card.delete.CardService;
import com.vendorlion.domain.card.exception.CardNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteAction {
  private final CardService removeCardService;

  public DeleteAction(CardService removeCardService) {
    this.removeCardService = removeCardService;
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{cardReference}")
  @Transactional
  public ResponseEntity<String> removeCard(
      @PathVariable(value = "cardReference") String cardReference) {
    try {
      removeCardService.remove(cardReference);

      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (CardNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
  }
}
