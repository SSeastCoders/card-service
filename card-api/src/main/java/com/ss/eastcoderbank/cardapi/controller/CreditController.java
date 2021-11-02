package com.ss.eastcoderbank.cardapi.controller;

import com.ss.eastcoderbank.cardapi.dto.CreateCreditDto;
import com.ss.eastcoderbank.cardapi.service.CreditService;
import com.ss.eastcoderbank.core.model.card.CreditCard;
import com.ss.eastcoderbank.core.transferdto.CreditCardDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/credit-cards")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping("/dummy")
    public ResponseEntity<String> dummy() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Dummy");
    }

    //getCreditCards()
    @GetMapping()
    public Page<CreditCardDto> getCreditCards(@RequestParam(name = "page") Integer pageNumber, @RequestParam(name = "size") Integer pageSize, @RequestParam(value = "asc", required = false) boolean asc, Pageable page, String sort) {
        log.trace("GET card endpoint reached...");
        log.debug("Endpoint pageNumber: {}, pageSize: {}", pageNumber, pageSize);
        return creditService.getCreditCards(pageNumber, pageSize, asc, sort);
    }

    //getCreditCardByUser
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<List<CreditCard>> getCreditCardsByUser(@PathVariable Integer id, @RequestParam(name = "page") Integer pageNumber, @RequestParam(name = "size") Integer pageSize) {
        log.trace("GET card/user endpoint reached...");
        log.debug("Endpoint pageNumber: {}, pageSize: {}, user: {}", pageNumber, pageSize, id);
        return new ResponseEntity(creditService.getCreditCardsByUser(id), HttpStatus.OK);
    }

    //getCreditCardsById()
    @GetMapping("/{id}")
    public ResponseEntity<CreditCard> getCreditCard(@PathVariable Integer id) {
        log.trace("GET card/id endpoint reached...");
        log.debug("Endpoint id: {}", id);
        return new ResponseEntity(creditService.getCreditCardById(id), HttpStatus.OK);
    }

    //makenewcreditcard
    @PostMapping()
    public ResponseEntity<CreditCard> registration(@Valid @RequestBody CreateCreditDto card, HttpServletResponse response) {
        log.trace("POST card endpoint reached...");
        CreditCardDto creditCard = creditService.createCard(card).orElseThrow();
        return new ResponseEntity(creditCard, HttpStatus.CREATED);
    }

    //deactiveate card

    //make a payement

    //update card

}
