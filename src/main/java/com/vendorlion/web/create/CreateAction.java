package com.vendorlion.web.create;

import com.vendorlion.domain.card.create.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateAction {

  private final CardService createCardService;

  public CreateAction(CardService createCardService) {
    this.createCardService = createCardService;
  }

  @RequestMapping(method = RequestMethod.POST, value = "/")
  public ResponseEntity<Response> findCard(@ModelAttribute Request request) {
    try {
      Response card = createCardService.create(request);

      return new ResponseEntity<>(card, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
    }
  }
}
