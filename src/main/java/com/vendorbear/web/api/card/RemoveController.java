package com.vendorbear.web.api.card;

import com.vendorbear.service.card.RemoveCardService;
import com.vendorbear.service.card.exception.CardNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/card")
public class RemoveController {

    private final RemoveCardService removeCardService;

    public RemoveController(RemoveCardService removeCardService) {
        this.removeCardService = removeCardService;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/remove/{cardReference}")
    @Transactional
    public ResponseEntity<String> removeCard(@PathVariable(value = "cardReference") String cardReference) {
        try {
            removeCardService.remove(cardReference);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CardNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
