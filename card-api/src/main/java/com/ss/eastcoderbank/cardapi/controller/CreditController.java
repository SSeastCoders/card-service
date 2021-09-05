package com.ss.eastcoderbank.cardapi.controller;

import com.ss.eastcoderbank.cardapi.dto.CreateCreditDto;
import com.ss.eastcoderbank.cardapi.service.CreditService;
import com.ss.eastcoderbank.core.model.card.CreditCard;
import com.ss.eastcoderbank.core.transferdto.CreditCardDto;
import com.ss.eastcoderbank.core.transferdto.UserDto;
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

@CrossOrigin
@RestController
@RequestMapping("/credit-cards")
public class CreditController {

    @Autowired
    private CreditService creditService;

    //getCreditCards()
    @GetMapping()
    public ResponseEntity<List<CreditCardDto>> getCreditCards() {
        //???
        return new ResponseEntity(creditService.getCreditCards(), HttpStatus.OK);
        //return new ResponseEntity<>(creditService.getCreditCards(), HttpStatus.OK);
    }

    @GetMapping("/dummy")
    public ResponseEntity<String> dummy() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Dummy");
    }

   // @GetMapping()
    //public Page<CreditCardDto> getCreditCards(@RequestParam(name="page") Integer pageNumber, @RequestParam(name="size") Integer pageSize) {
    //    return creditService.getCreditCards(pageNumber, pageSize);
   // }

    //getCreditCardByUser
    @PostMapping(value = "/user")
    public ResponseEntity<List<CreditCard>> getCreditCardsByUser(@Valid @RequestBody List<Integer> users, @RequestParam(name="page") Integer pageNumber, @RequestParam(name="size") Integer pageSize) {
        return new ResponseEntity(creditService.getCreditCardsByUser(users), HttpStatus.OK);
    }

    //getCreditCardsById()
    @GetMapping("/{id}")
    public ResponseEntity<CreditCard> getCreditCard(@PathVariable Integer id) {
        return new ResponseEntity(creditService.getCreditCardById(id), HttpStatus.OK);
    }

    //getCreditCardBy???

    //makenewcreditcard
    @PostMapping()
    public ResponseEntity<CreditCard> registration(@Valid @RequestBody CreateCreditDto card, HttpServletResponse response) {
        CreditCard creditCard = creditService.createCard(card);
        //response.addHeader("id", String.valueOf(creditCard.getId()));
        return new ResponseEntity(creditCard, HttpStatus.CREATED);
    }


    //deactiveate card


    //make a payement


    //update card


    //

}
