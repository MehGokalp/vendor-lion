package com.vendorlion.web.api.action;

import com.vendorlion.schema.Card;
import com.vendorlion.domain.card.CreateCardService;
import com.vendorlion.web.api.request.CreateCardRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/card")
public class CreateController {

    private final CreateCardService createCardService;

    public CreateController(CreateCardService createCardService) {
        this.createCardService = createCardService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<Card> findCard(@ModelAttribute CreateCardRequest createCardRequest) {
        try {
            Card card = createCardService.create(createCardRequest.getBalance(), createCardRequest.getActivationDate(), createCardRequest.getExpireDate(), createCardRequest.getCurrency());

            return new ResponseEntity<>(card, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
