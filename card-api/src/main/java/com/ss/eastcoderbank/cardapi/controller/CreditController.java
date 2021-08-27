package com.ss.eastcoderbank.cardapi.controller;

import com.ss.eastcoderbank.cardapi.dto.CreditCardDto;
import com.ss.eastcoderbank.cardapi.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/credit-cards")
public class CreditController {

    @Autowired
    private CreditService creditService;

    @GetMapping()
    public ResponseEntity<List<CreditCardDto>> getCreditCards() {
        //???
        return new ResponseEntity(creditService.getCreditCards(), HttpStatus.OK);
        //return new ResponseEntity<>(creditService.getCreditCards(), HttpStatus.OK);
    }



    //getCreditCards()



    //getCreditCardByUser
    //getCreditCardById
    //getCreditCardByNumber
    //getCreditCardBy???
    //makenewcreditcard
    //deactiveate card
    //make a payement
    //update card
    //

}
