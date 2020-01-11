package com.vendorbear.web.api.card;

import com.vendorbear.service.card.RemoveCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/card")
public class RemoveController {

    private final RemoveCardService removeCardService;

    public RemoveController(RemoveCardService removeCardService) {
        this.removeCardService = removeCardService;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/remove/{cardReference}")
    public ResponseEntity<String> findCard(@PathVariable(value = "cardReference") String cardReference) {
        try {
            boolean isRemoved = removeCardService.remove(cardReference);

            if (isRemoved == true) {
                return new ResponseEntity<>("", HttpStatus.OK);
            }

            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("", HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
