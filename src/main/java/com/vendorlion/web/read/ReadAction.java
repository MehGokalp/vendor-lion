package com.vendorlion.web.read;

import com.vendorlion.domain.card.read.CardService;
import com.vendorlion.web.create.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadAction {

  private final CardService findCardService;

  public ReadAction(CardService findCardService) {
    this.findCardService = findCardService;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{cardReference}")
  public ResponseEntity<Response> findCard(
      @PathVariable(value = "cardReference") String cardReference) {
    try {
      Response card = findCardService.find(cardReference);

      if (card == null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

      return new ResponseEntity<>(card, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
  }
}
