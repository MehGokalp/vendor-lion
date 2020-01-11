package com.vendorbear.web.api.card;

import com.vendorbear.schema.Card;
import com.vendorbear.service.card.FindCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/card")
public class FindController {

    private final FindCardService findCardService;

    public FindController(FindCardService findCardService) {
        this.findCardService = findCardService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find/{cardReference}")
    public ResponseEntity<Card> findCard(@PathVariable(value = "cardReference") String cardReference) {
        try {
            Card card = findCardService.find(cardReference);

            if (card == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(card, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
